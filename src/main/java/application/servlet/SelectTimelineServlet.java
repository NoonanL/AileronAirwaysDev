package application.servlet;

import application.Runner;
import application.model.Attachment;
import application.model.Event;
import application.model.Timeline;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class SelectTimelineServlet extends HttpServlet {

    /*
Override function for html POST methods.
 */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hello I am a post method");
    }

    /*
    Override function for html GET methods.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("Hello I am a get method");
        Runner.timelineId = request.getParameter("timelineId");
        Runner.attachmentRepository.cleanUpFiles();
        //get the timeline that the id points to
        if(!Runner.timelineId.equals("")){
            //System.out.println(Runner.timelineId);
            Timeline testTimeline = Runner.timelineRepository.get(Runner.timelineId);
            //get the events on that timeline
            ArrayList<Event> events = testTimeline.getTimelineEvents();

            for(Event e : events) {
                ArrayList<Attachment> testData = e.getAttachments();
                //System.out.println(testData.toString());
                for (Attachment a : testData) {
                    //System.out.println(a.getTitle());
                    String title = a.getTitle();
                    //Attachment test = new Attachment();
                    String filepath = "C:\\Users\\LiamN\\Dropbox\\AileronAirwaysDev\\downloads\\" + title;
                    a.setHref(filepath);
                    a.downloadAttachment(title, filepath);
                    //System.out.println(a.getHref());
                }
            }
        }else {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.sendRedirect(response.encodeRedirectURL("/Timelines.html"));
        }
        response.sendRedirect(response.encodeRedirectURL("/Events.html"));

    }

}
