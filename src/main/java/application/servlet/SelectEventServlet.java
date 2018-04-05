package application.servlet;

import application.Runner;
import application.model.Attachment;
import application.model.Event;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class SelectEventServlet extends HttpServlet {

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
        //System.out.println("Hello I am a get method");
        Runner.eventId = request.getParameter("eventId");
        //System.out.println(Runner.eventId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.sendRedirect(response.encodeRedirectURL("/EventDetails.html"));

    }
}
