package application.servlet;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class IndexServlet extends HttpServlet{

    public IndexServlet(){}

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
            System.out.println("Hello I am a get method");
            List<String> list = new ArrayList<String>();
            list.add("item1");
            list.add("item2");
            list.add("item3");
            String json = new Gson().toJson(list);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

