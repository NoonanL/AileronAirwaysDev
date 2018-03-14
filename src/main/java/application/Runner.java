package application;


import application.model.Event;
import application.model.Timeline;
import application.repositories.EventRepository;
import application.repositories.TimelineRepository;
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



    private void start() throws Exception {
        Server server = new Server(PORT);

        /*temporarily populating timeline and event Repository for testing purposes
         */
//        Timeline timeline1 = new Timeline("timelineId01", "Timeline Title 01");
//        Timeline timeline2 = new Timeline("timelineId02", "Timeline Title 02");
//        Timeline timeline3 = new Timeline("timelineId03", "Timeline Title 03");
//        timelineRepository.add(timeline1);
//        timelineRepository.add(timeline2);
//        timelineRepository.add(timeline3);
//
//        Event event1 = new Event("eventId01", "Event 01 Title", "I am test event 01", "22:36","Glasgow");
//        Event event2 = new Event("eventId02", "Event 02 Title", "I am test event 02", "22:37","Edinburgh");
//        Event event3 = new Event("eventId03", "Event 03 Title", "I am test event 03", "22:38","London");
//        eventRepository.add(event1);
//        eventRepository.add(event2);
//        eventRepository.add(event3);

        Timeline test = new Timeline("150","testTitle2");
        System.out.println("Testing: Get all Linked Events with Timelines");
        System.out.println("------------------------");
        test.getTimelinesAndEvents();


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
