package application;

import application.servlet.IndexServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


public class Runner {

    private static final int PORT = 9000;

    private Runner() {}

    /*
    The runner is probably going to need some sort of method that fetches the required data on server start
    ie all timelines and events
     */

    private void start() throws Exception {
        Server server = new Server(PORT);

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
