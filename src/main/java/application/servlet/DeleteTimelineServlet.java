package application.servlet;

import application.Runner;
import application.model.Timeline;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTimelineServlet extends HttpServlet {

    /*
    Override function for html POST methods.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hello I am a post method");

        String timelineId = request.getParameter("timelineId");
        System.out.println(timelineId);

        //Timeline timeline = Runner.timelineRepository.get(timelineId);
        //timeline.deleteTimeline();
        //Runner.timelineRepository.remove(timelineId);
        response.sendRedirect(response.encodeRedirectURL("Timelines.html"));
    }

    /*
    Override function for html GET methods.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("I am a get method");
    }

}
