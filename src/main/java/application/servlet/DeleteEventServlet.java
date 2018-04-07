package application.servlet;

import application.Runner;
import application.model.Event;
import application.model.Timeline;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEventServlet extends HttpServlet{
    /*
        Override function for html POST methods.
         */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("Hello I am a post method");

        String eventId = request.getParameter("eventId");
        //System.out.println(eventId);

        if(!eventId.equals("")) {
            Timeline timeline = Runner.timelineRepository.get(Runner.timelineId);
            Runner.timelineRepository.remove(Runner.timelineId);
            timeline.unLinkEvent(eventId);
            timeline.removeEventFromArray(eventId);
            Runner.timelineRepository.add(timeline);
            //System.out.println("Deleting event id " + eventId);
            //Event event = Runner.eventRepository.get(eventId);
            //Timeline timeline = Runner.timelineRepository.get(Runner.timelineId);
            //timeline.unLinkEvent(eventId);
            //timeline.removeEventFromArray(eventId);
            //event.deleteEvent();
            //Runner.eventRepository.remove(eventId);
            response.sendRedirect(response.encodeRedirectURL("Events.html"));
        }
        else{
            System.out.println("Failed to delete event with eventId = " + eventId + " (probably null)...");
        }
    }

    /*
    Override function for html GET methods.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("I am a get method");
    }

}
