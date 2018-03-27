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

        /*
        Get eventId parameter from html request.
         */
        String var = request.getParameter("eventId");
        //Get event associated with that Id from repository
        Event event = Runner.eventRepository.get(var);
        //get attachments associated with that event
        ArrayList<Attachment> testData = event.getAttachments();

        /*
        prepare json array of attachments and return to html.
         */
        String json = new Gson().toJson(testData);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

}
