package application.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LinkedEventsServlet extends HttpServlet{

    /*
    constructor for servlet
     */
    public LinkedEventsServlet(){    }




    private String timelineId;
    /*
    Override function for html POST methods.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hello I am a post method");
        ArrayList<Event> testData = (ArrayList<Event>)request.getAttribute("testData");
        timelineId = request.getAttribute("testData").toString();
        System.out.println(testData.toString());
    }

    /*
    Override function for html GET methods.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("I am a get method");
        //get the timeline that the id points to
        Timeline testTimeline = Runner.timelineRepository.get(Runner.timelineId);
        //get the events on that timeline
        ArrayList<Event> testData = testTimeline.getTimelineEvents();
        System.out.println(testData.toString());
        String json = new Gson().toJson(testData);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        //response.sendRedirect(response.encodeRedirectURL("/Events.html"));


    }

}
