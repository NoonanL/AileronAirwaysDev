package application.servlet;


import application.Runner;
import application.model.Event;
import application.model.Timeline;
import com.google.gson.Gson;
import util.FetchLinkedEvents;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class EventsServlet extends HttpServlet{

    /*
    constructor for servlet
     */
    public EventsServlet(){    }




    private String timelineId;
    /*
    Override function for html POST methods.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("Hello I am a post method");
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

        Timeline timeline = Runner.timelineRepository.get(Runner.timelineId);
        timeline.addTimelineEvent(newEvent);
        timeline.linkEvent(newEvent.getId());

        response.sendRedirect(response.encodeRedirectURL("/Events.html"));

    }

    /*
    Override function for html GET methods.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("I am a get method");
        //get the timeline that the id points to
        if(!Runner.timelineId.equals("")){

            FetchLinkedEvents fetchLinkedEvents = new FetchLinkedEvents();
            ArrayList<ArrayList<Event>> data = fetchLinkedEvents.getAllLinks(Runner.timelineId);
//            //System.out.println(Runner.timelineId);
//            Timeline testTimeline = Runner.timelineRepository.get(Runner.timelineId);
//            ArrayList<Event> events = testTimeline.getTimelineEvents();
//
//            for(int i = 0; i < events.size(); i++){
//                if(events.get(i).getEventsLinked().isEmpty()){
//                    //System.out.println("FOUND NULL");
//                    events.remove(i);
//                }
//            }
            String json = new Gson().toJson(data);
            //System.out.println(json);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }else {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.sendRedirect(response.encodeRedirectURL("/Timelines.html"));
        }

    }

}
