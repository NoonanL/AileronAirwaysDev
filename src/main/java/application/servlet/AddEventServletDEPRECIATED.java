package application.servlet;

import application.Runner;
import application.model.Event;
import application.model.Timeline;
import util.FileParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddEventServletDEPRECIATED extends HttpServlet {

    /*
        Override function for html POST methods.
         */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("Hello I am a post method");

        Event newEvent = new Event();

        String title = request.getParameter("title");
        //System.out.println(title);
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String lat = request.getParameter("lat");
        String lng = request.getParameter("lng");
        String description = request.getParameter("description");
        String selectedTimeline = request.getParameter("selectedTimeline");
        String attachmentString = request.getParameter("attachments");
        String filename = request.getParameter("filename");
        //System.out.println(attachmentString);
        FileParser fileParser = new FileParser();
        fileParser.fileParser(attachmentString,filename);
        //System.out.println(selectedTimeline);
        newEvent.setTitle(title);
        newEvent.setEventDateTime(date + " " + time);
        //System.out.println(date + " " + time);
        newEvent.setDescription(description);
        newEvent.setLocation(lat + " " + lng);
        //System.out.println(newEvent.toString());
        Runner.eventRepository.add(newEvent);
        newEvent.createEvent();

        if(selectedTimeline!=null) {
            Timeline timeline = Runner.timelineRepository.get(selectedTimeline);
            timeline.addTimelineEvent(newEvent);
            timeline.linkEvent(newEvent.getId());
            //System.out.println(timeline.toString());
        }
        else{
            System.out.println("No timeline selected hur durr durr");
        }

        //Runner.timelineId = request.getParameter("selectedTimeline");
        response.sendRedirect(response.encodeRedirectURL("/Events.html"));
    }

    /*
    Override function for html GET methods.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hello I am a get method");
        //String json = null;
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
       // response.getWriter().write(json);

    }

}