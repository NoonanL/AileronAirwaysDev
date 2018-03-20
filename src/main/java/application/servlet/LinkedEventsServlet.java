package application.servlet;

import application.Runner;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LinkedEventsServlet extends HttpServlet{

    /*
    constructor for servlet
     */
    public LinkedEventsServlet(){    }

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
        For the sake of testing I'm going to assume
         */
        String json = new Gson().toJson("Hello, I am a Get Method");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

}
