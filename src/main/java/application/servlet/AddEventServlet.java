package application.servlet;

import application.Runner;
import application.model.Event;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddEventServlet extends HttpServlet {

    /*
        Override function for html POST methods.
         */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("Hello I am a post method");

        Event newEvent = new Event();

        String title = request.getParameter("title");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String latlng = request.getParameter("latlng");
        String description = request.getParameter("description");

        System.out.println(title);
        System.out.println(date);
        System.out.println(time);
        System.out.println(latlng);
        System.out.println(description);

        newEvent.setTitle(title);
        newEvent.setEventDateTime(date + " " + time);
        newEvent.setDescription(description);
        newEvent.setLocation(latlng);

        System.out.println(newEvent.toString());
        Runner.eventRepository.add(newEvent);
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
    }

    /*
    Override function for html GET methods.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hello I am a get method");
        //String json = null;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
       // response.getWriter().write(json);

    }

}
