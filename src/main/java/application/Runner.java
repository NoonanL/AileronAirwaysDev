package application;

import application.repositories.AttachmentRepository;
import application.repositories.EventRepository;
import application.repositories.TimelineRepository;
import application.servlet.EventDetailsServlet;
import application.servlet.IndexServlet;
import application.servlet.TimelineRegisterServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


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



    private void start() throws Exception {
        Server server = new Server(PORT);


        //Run get Timelines and Events to populate repository. Pure hack atm.
        timelineRepository.getAPITimelines();
        eventRepository.getAPIEvents();


        //Print all Timelines fetched from API
        System.out.println(timelineRepository.getTimelines());
        System.out.println(eventRepository.getEvents());
        System.out.println(attachmentRepository.getAttachments());


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
            System.out.println("Unexpected error running shop: " + e.getMessage());
        }
    }

}
