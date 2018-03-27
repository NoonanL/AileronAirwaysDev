package application.servlet;

import application.Runner;
import application.model.Attachment;
import application.model.Event;
import application.model.Timeline;

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
        System.out.println("Hello I am a get method");
        String timelineId = request.getParameter("timelineId");
        System.out.println(timelineId);

        //get the timeline that the id points to
        Timeline testTimeline = Runner.timelineRepository.get(timelineId);
        //get the events on that timeline
        ArrayList<Event> testData = testTimeline.getTimelineEvents();
        System.out.println(testData.toString());
        Runner.timelineId = timelineId;
        request.setAttribute("timelineId", testData);
        try{
            System.out.println("forwarding...");
            request.getRequestDispatcher("/Events.html").forward(request,response);
        }
        catch (ServletException e)
        {
            e.printStackTrace();
        }

    }

}
