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
import java.util.ArrayList;
import java.util.Iterator;

public class SearchTimelinesServlet extends HttpServlet {

    /*
        Override function for html POST methods. These will likely be used more so than GET as they do not limit the size of
        transfers and are more secure albeit less efficient.
         */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hello I am a post method");
    }

    /*
    Override function for html GET methods. Not likely to be used a great deal but appear as a default and may be better in some cases.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchString = request.getParameter("searchBox");
        Runner.searchTimelines = searchString;
        //System.out.println(Runner.searchTimelines);
        SearchFunction s = new SearchFunction("Timeline",searchString);
        //System.out.println(s);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(s.SearchFunction());
        response.sendRedirect(response.encodeRedirectURL("Timelines.html"));
    }

}
