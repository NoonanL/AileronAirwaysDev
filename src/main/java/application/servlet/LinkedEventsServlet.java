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




    private String timelineId;
    /*
    Override function for html POST methods.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hello I am a post method");
        /*

         */
        Event newEvent = new Event();

        String title = request.getParameter("title");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String lat = request.getParameter("lat");
        String lng = request.getParameter("lng");
        String description = request.getParameter("description");
        //String selectedTimeline = request.getParameter("selectedTimeline");
        System.out.println(request.getParameter("attachments"));

        //System.out.println(selectedTimeline);
        newEvent.setTitle(title);
        newEvent.setEventDateTime(date + " " + time);
        System.out.println(date + " " + time);
        newEvent.setDescription(description);
        newEvent.setLocation(lat + " " + lng);
        //System.out.println(newEvent.toString());
        Runner.eventRepository.add(newEvent);
        newEvent.createEvent();

//        if(selectedTimeline!=null) {
            Timeline timeline = Runner.timelineRepository.get(Runner.timelineId);
            timeline.addTimelineEvent(newEvent);
            timeline.linkEvent(newEvent.getId());
//            //System.out.println(timeline.toString());
//        }
//        else{
//            System.out.println("No timeline selected");
//        }

        //Runner.timelineId = request.getParameter("selectedTimeline");
        response.sendRedirect(response.encodeRedirectURL("/Events.html"));
   // }
    }

    /*
    Override function for html GET methods.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("I am a get method");
        //get the timeline that the id points to
        Timeline testTimeline = Runner.timelineRepository.get(Runner.timelineId);
        //get the events on that timeline
        ArrayList<Event> testData = testTimeline.getTimelineEvents();
        //System.out.println(testData.toString());
        String json = new Gson().toJson(testData);
        //System.out.println(json);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);



    }

}
