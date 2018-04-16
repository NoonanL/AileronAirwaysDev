package application.servlet;

import application.Runner;
import application.model.Attachment;
import application.model.Event;
import application.model.Timeline;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class EditDetailsServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIRECTORY = "src\\main\\resources\\webapp\\images\\downloads";
    private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    /*
    Override function for html GET methods.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //System.out.println("Hello from Edit Details Servlet!");
        Timeline timeline = Runner.timelineRepository.get(Runner.timelineId);
        Runner.timelineRepository.remove(Runner.timelineId);
        // get current event
        Event event = timeline.getTimelineEvent(Runner.eventId);
        //remove current event from timeline
        timeline.removeEventFromArray(Runner.eventId);

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
            String selectedLinkedEvent = "";

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
                            //System.out.println("selected event is not null");
                            selectedLinkedEvent = item.getString();
                            break;
                        case "selectedTimeline":
                            selectedTimeline = item.getString();

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

                            Attachment attachment = new Attachment(event.getId(),fileName);
                            event.addAttachment(attachment);
                            attachment.createAttachment(filePath.replace("\\","\\\\"));


                        }
                    }
                }
            }

            //System.out.println(selectedLinkedEvent);
            //System.out.println("Got to servlet...");
            event.setTitle(title);
            event.editEventTitle(title);
            event.setEventDateTime(date + " " + time);
            event.editEventDateTime(date + " " + time);
            event.setDescription(description);
            event.editEventDescription(description);
            if((!selectedLinkedEvent.equals("")) && (!selectedLinkedEvent.equals("unlinked event")) ){
                //System.out.println("selectedLinkedEvent is "+ selectedLinkedEvent);
                event.setLinkedEvents(selectedLinkedEvent);
                event.linkEvents(selectedLinkedEvent);
            }
            event.setLocation(lat + " " + lng);
            event.editEventLocation(lat + " " + lng);


            timeline.addTimelineEvent(event);
            Runner.timelineRepository.add(timeline);


        } catch (Exception ex) {
            System.out.println("Error, " + ex.getMessage());
        }

        response.sendRedirect(response.encodeRedirectURL("Events.html"));
    }

}
