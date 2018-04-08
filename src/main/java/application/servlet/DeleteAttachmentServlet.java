package application.servlet;

import application.Runner;
import application.api.Put;
import application.model.Attachment;
import application.model.Event;
import application.model.Timeline;
import com.google.gson.Gson;
import util.ParameterStringBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class DeleteAttachmentServlet extends HttpServlet {

    /*
    Override function for html POST methods.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //System.out.println("Hello I am a post method");
        /*
        Get eventId parameter from html request.
         */
        String attachmentId = request.getParameter("attachmentId");

        if (!attachmentId.equals("")) {
            Timeline timeline = Runner.timelineRepository.get(Runner.timelineId);
            Runner.timelineRepository.remove(timeline.getId());

            timeline.removeEventAttachment(Runner.eventId, attachmentId);

            Runner.timelineRepository.add(timeline);
        }else{System.out.println("null attachment id.");}
        /*
        return to desired page
         */
        response.sendRedirect(response.encodeRedirectURL("/EventDetails.html"));
    }

    /*
    Override function for html GET methods.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /*
        Get eventId parameter from html request.
         */
//        String attachmentId = request.getParameter("attachmentId");
//        Attachment attachment = Runner.attachmentRepository.get(attachmentId);
//        attachment.deleteAttachment(attachmentId);
//        Runner.attachmentRepository.remove(attachmentId);
//
//        /*
//        return to desired page
//         */
//        response.sendRedirect(response.encodeRedirectURL("/Timelines.html"));

    }

}
