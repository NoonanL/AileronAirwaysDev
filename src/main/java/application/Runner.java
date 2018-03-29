package application;

import application.model.Event;
import application.model.Timeline;
import application.repositories.AttachmentRepository;
import application.repositories.EventRepository;
import application.repositories.TimelineRepository;
import application.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.ArrayList;


public class Runner {

    private static final int PORT = 9000;

    private Runner() {}

    /*
    Declare repository for Timeline objects for persistence
     */
    public static TimelineRepository timelineRepository = new TimelineRepository();
    /*
    Declare repository for Event objects for persistence
     */
    public static EventRepository eventRepository = new EventRepository();
    /*
    Declare repository for Event objects for persistence
     */
    public static AttachmentRepository attachmentRepository = new AttachmentRepository();

    public static String timelineId;
    public static String eventId;
    public static String searchTimelines;

    private void start() throws Exception {
        Server server = new Server(PORT);


        //Run get Timelines and Events to populate both timeline and event repositories
        timelineRepository.getAllTimelinesAndEvents();
        timelineId = "";
        eventId = "";
        searchTimelines=null;
        //Run get Timelines and Events to populate repository. Pure hack atm.
        //timelineRepository.getAPITimelines();
       // eventRepository.getAPIEvents();


        //Print all Timelines fetched from API
        //System.out.println(timelineRepository.getTimelines());
        //System.out.println(eventRepository.getEvents());
        //System.out.println(attachmentRepository.getAttachments());


        /*
        servlet handler controls the context, ie where web resources are located.
         */
        ServletContextHandler handler = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        handler.setInitParameter("org.eclipse.jetty.servlet.Default." + "resourceBase", "src/main/resources/webapp");

        /*
        Servlet to handle index page.
         */
        IndexServlet indexServlet = new IndexServlet();
        handler.addServlet(new ServletHolder(indexServlet), "/indexServlet");

        /*
        Servlet to handle timelineRegister page.
         */
        TimelineRegisterServlet timelineRegisterServletServlet = new TimelineRegisterServlet();
        handler.addServlet(new ServletHolder(timelineRegisterServletServlet), "/timelineRegisterServlet");

        /*
        Servlet to handle EventDetails page
         */
        EventDetailsServlet eventDetailsServlet = new EventDetailsServlet();
        handler.addServlet(new ServletHolder(eventDetailsServlet), "/eventDetailsServlet");

         /*
        Servlet to handle Timelines Search
         */
        SearchTimelinesServlet searchTimelinesServlet = new SearchTimelinesServlet();
        handler.addServlet(new ServletHolder(searchTimelinesServlet), "/searchTimelinesServlet");

        /*
        Servlet to handle Timelines Search
         */
        AddEventServlet addEventServlet = new AddEventServlet();
        handler.addServlet(new ServletHolder(addEventServlet), "/addEventServlet");

         /*
        Servlet to handle Timelines Search
         */
        LinkedEventsServlet linkedEventsServlet = new LinkedEventsServlet();
        handler.addServlet(new ServletHolder(linkedEventsServlet), "/linkedEventsServlet");

        /*
        sets default servlet path.
         */
        DefaultServlet ds = new DefaultServlet();
        handler.addServlet(new ServletHolder(ds), "/");


        /*
        starts server
         */
        server.start();
        System.out.println("Server started, will run until terminated");
        server.join();
    }

    /*
    main program start here
     */
    public static void main(String[] args) {
        try {
            System.out.println("starting");
            new Runner().start();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

}
