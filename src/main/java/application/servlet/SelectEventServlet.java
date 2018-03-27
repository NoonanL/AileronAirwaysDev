package application.servlet;

import application.Runner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        //Runner.eventId = request.getParameter("eventId");
        Runner.eventId = "1932";
        response.sendRedirect(response.encodeRedirectURL("/EventDetails.html"));

    }
}
