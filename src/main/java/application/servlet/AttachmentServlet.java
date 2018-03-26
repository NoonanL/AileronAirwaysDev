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

public class AttachmentServlet extends HttpServlet {

    /*
    constructor for servlet
     */
    public AttachmentServlet(){    }

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
        /*
        For the sake of testing I'm going to assume that the timeline we're looking at is ID 032934
         */
        String var = request.getParameter("eventId");
        System.out.println(var);

        Event event = Runner.eventRepository.get(var);
        //System.out.println(testTimeline.toString());
        ArrayList<Attachment> testData = event.getAttachments();
        System.out.println(testData.toString());
        String json = new Gson().toJson(testData);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

}
