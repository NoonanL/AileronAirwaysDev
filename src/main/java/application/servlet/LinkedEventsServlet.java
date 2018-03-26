package application.servlet;

import application.Runner;
import application.model.Event;
import application.model.Timeline;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class LinkedEventsServlet extends HttpServlet{

    /*
    constructor for servlet
     */
    public LinkedEventsServlet(){    }

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
        System.out.println("I am a get method");

        //get the timeline id the user wants the events from
        String var = request.getParameter("timelineID");
        System.out.println(var);

        //get the timeline that the id points to
        Timeline testTimeline = Runner.timelineRepository.get(var);
        //get the events on that timeline
        ArrayList<Event> testData = testTimeline.getTimelineEvents();
        System.out.println(testData.toString());
        
        //prepare json array of events and return it to html
        String json = new Gson().toJson(testData);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

}
