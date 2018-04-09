package application.servlet;

import application.Runner;
import application.model.Event;
import application.model.Timeline;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditDetailsServlet extends HttpServlet {




    /*
    Override function for html GET methods.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  //      Timeline timeline = Runner.timelineRepository.get(Runner.timelineId);
//        Event event = timeline.getTimelineEvent(Runner.eventId);
//        String json = new Gson().toJson(event);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(json);
    }

}
