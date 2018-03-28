package application.servlet;

import application.Runner;
import application.model.Timeline;
import com.google.gson.Gson;
import util.SearchFunction;

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
        //System.out.println("Hello I am a post method");
        Timeline timeline = new Timeline();
        timeline.setTitle(request.getParameter("timeline_title"));
        timeline.createTimeline();
        Runner.timelineRepository.add(timeline);
        //System.out.println(timeline.toString());
        response.sendRedirect(response.encodeRedirectURL("Timelines.html"));
    }

    /*
    Override function for html GET methods.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("Hello I am a get method");
        String json;


        if(Runner.searchTimelines != null){
            SearchFunction s = new SearchFunction("Timeline",Runner.searchTimelines);
            json = s.SearchFunction();
            System.out.println(json);
        }else {
            json = new Gson().toJson(application.Runner.timelineRepository.getTimelines());
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        Runner.searchTimelines = null;

    }
}
