package application.servlet;

import application.Runner;
import application.model.Event;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EventDetailsServlet extends HttpServlet{

    /*
    I need to be passed something to specify which event details i have to provide
     */

    /*
    constructor for servlet
     */
    public EventDetailsServlet(){    }

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

        /*
        Fetch something from front end to specify which event to send
        send event details as json
         */

        String json = new Gson().toJson(Runner.eventRepository.getEvents());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

}
