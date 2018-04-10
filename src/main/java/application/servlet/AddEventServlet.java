package application.servlet;


import application.Runner;
import application.model.Attachment;
import application.model.Event;
import application.model.Timeline;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddEventServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIRECTORY = "src\\main\\resources\\webapp\\images\\downloads";
    private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB


    /**
     * handles file upload via HTTP POST method
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        Event newEvent = new Event();

        // checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(request)) {
            System.out.println("Request does not contain upload data");
            return;
        }

        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(THRESHOLD_SIZE);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // constructs the directory path to store upload file
        String uploadPath = UPLOAD_DIRECTORY;
        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // parses the request's content to extract file data
            List formItems = upload.parseRequest(request);
            //System.out.println(formItems.size());

            String title = "";
            String date = "";
            String time = "";
            String lat = "";
            String lng = "";
            String description = "";
            String selectedTimeline = "";
            String selectedLinkedEvent ="";

            // iterates over form's fields
            for (Object formItem : formItems) {
                FileItem item = (FileItem) formItem;
                if (item.isFormField()) {
                    String testString = item.getFieldName();
                    switch (testString) {
                        case "title":
                            title = item.getString();
                            break;
                        case "date":
                            date = item.getString();
                            break;
                        case "time":
                            time = item.getString();
                            break;
                        case "lat":
                            lat = item.getString();
                            break;
                        case "lng":
                            lng = item.getString();
                            break;
                        case "description":
                            description = item.getString();
                            break;
                        case "selectedLinkedEvent":
                            selectedLinkedEvent = item.getString();
                            break;
                        case "selectedTimeline":
                            selectedTimeline = item.getString();
                            //Runner.timelineId = selectedTimeline;
                            break;
                    }
                }
                // processes only fields that are not form fields
                else {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        if(!fileName.equals("")) {
                            File storeFile = new File(filePath);
                            // saves the file on disk
                            item.write(storeFile);


                            /*
                                CREATE ATTACHMENT AND LINK IT TO EVENT HERE
                            */
                            //System.out.println(filePath.replace("\\","\\\\"));
                            Attachment attachment = new Attachment(newEvent.getId(),fileName);
                            newEvent.addAttachment(attachment);
                            attachment.createAttachment(filePath.replace("\\","\\\\"));
                            //Runner.attachmentRepository.add(attachment);

                        }
                    }
                }
            }



            //System.out.println("Selected Timeline is: " + selectedTimeline);
            newEvent.setTitle(title);
            //System.out.println(title);
            newEvent.setEventDateTime(date + " " + time);
            //System.out.println(date + " " + time);
            newEvent.setDescription(description);
            newEvent.setLocation(lat + " " + lng);
            //System.out.println(newEvent.toString());
            //Runner.eventRepository.add(newEvent);
            newEvent.createEvent();
            if(!selectedLinkedEvent.equals("unlinked event")){
                newEvent.setLinkedEvents(selectedLinkedEvent);
                newEvent.linkEvents(selectedLinkedEvent);
            }


            if(!selectedTimeline.equals("")) {
                Runner.timelineId = selectedTimeline;
                Timeline timeline = Runner.timelineRepository.get(selectedTimeline);
                timeline.addTimelineEvent(newEvent);
                timeline.linkEvent(newEvent.getId());
                //Runner.timelineId = selectedTimeline;
                //System.out.println(Runner.timelineId);
            }
            else{
                //Runner.timelineId = selectedTimeline;
                Timeline timeline = Runner.timelineRepository.get(Runner.timelineId);
                timeline.addTimelineEvent(newEvent);
                timeline.linkEvent(newEvent.getId());
            }

        } catch (Exception ex) {
            System.out.println("Error, " + ex.getMessage());
        }

        response.sendRedirect(response.encodeRedirectURL("/selectTimelineServlet"));
    }

    /*
Override function for html GET methods.
 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
