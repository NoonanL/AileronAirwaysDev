package application.servlet;

import application.model.Timeline;
import application.repositories.TimelineRepository;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TimelineRegisterServlet extends HttpServlet{

    /*
    constructor for TimelineRegisterServlet
     */
    public TimelineRegisterServlet(){}


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

        TimelineRepository timelineRepository = new TimelineRepository();

        Timeline timeline1 = new Timeline("timelineId01", "Timeline Title 01");
        Timeline timeline2 = new Timeline("timelineId02", "Timeline Title 02");
        Timeline timeline3 = new Timeline("timelineId03", "Timeline Title 03");

        timelineRepository.add(timeline1);
        timelineRepository.add(timeline2);
        timelineRepository.add(timeline3);

        String json = new Gson().toJson(timelineRepository.getTimelines());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }
}
