package application.model;

import application.Runner;
import application.repositories.EventRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Iterator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void t10testCreateEvent() throws Exception {
        //eventDateTime in the form of YYYY-MM-dd-HH-mm-SS this is due to an inability to use : and , within the string
        Event test = new Event(
                "testId",
                "testTitle",
                "testDescription",
                "2018-12-26-11-05-20",
                "3.5994309341289004-29.939200781250065" );

        System.out.println("AddEventServlet: Create an Event");
        System.out.println("------------------------");
        test.createEvent();

    }

    @Test
    public void t11testEditEventDescription() throws Exception {
        //eventDateTime in the form of YYYY-MM-dd-HH-mm-SS this is due to an inability to use : and , within the string
        Event test = new Event(
                "testId",
                "testTitle",
                "testDescription",
                "2018-12-26-11-05-20",
                "3.5994309341289004-29.939200781250065" );

        System.out.println("AddEventServlet: Edit an Event Description");
        System.out.println("------------------------");
        test.editEventDescription("NewDescription");

    }

    @Test
    public void t12testEditEventTitle() throws Exception {
        //eventDateTime in the form of YYYY-MM-dd-HH-mm-SS this is due to an inability to use : and , within the string
        Event test = new Event(
                "testId",
                "testTitle",
                "testDescription",
                "2018-12-26-11-05-20",
                "3.5994309341289004-29.939200781250065" );

        System.out.println("AddEventServlet: Edit an Event Title");
        System.out.println("------------------------");
        test.editEventTitle("NewTitle");

    }

    @Test
    public void t13testEditEventDateTime() throws Exception {
        //eventDateTime in the form of YYYY-MM-dd-HH-mm-SS this is due to an inability to use : and , within the string
        Event test = new Event(
                "testId",
                "testTitle",
                "testDescription",
                "2018-12-26-11-05-20",
                "3.5994309341289004-29.939200781250065" );

        System.out.println("AddEventServlet: Edit an Event Date and time");
        System.out.println("------------------------");
        test.editEventDateTime("2017-11-25-10-04-19");

    }

    @Test
    public void t14testEditEventLocation() throws Exception {
        //eventDateTime in the form of YYYY-MM-dd-HH-mm-SS this is due to an inability to use : and , within the string
        Event test = new Event(
                "testId",
                "testTitle",
                "testDescription",
                "2018-12-26-11-05-20",
                "3.5994309341289004-29.939200781250065" );

        System.out.println("AddEventServlet: Edit an Event Location");
        System.out.println("------------------------");
        test.editEventLocation("5.0-6.0");

    }

    @Test
    public void t15testGetEvent() throws Exception {
        //eventDateTime in the form of YYYY-MM-dd-HH-mm-SS this is due to an inability to use : and , within the string
        Event test = new Event();

        System.out.println("AddEventServlet: Get An Event");
        System.out.println("------------------------");
        test.getEvent("777");
        System.out.println(test.toString());

    }

    @Test
    public void t16testLinkEvents() throws Exception {
        Event test = new Event(
                "testId",
                "testTitle",
                "testDescription",
                "2018-12-26-11-05-20",
                "3.5994309341289004-29.939200781250065" );

        System.out.println("AddEventServlet: Link Events");
        System.out.println("------------------------");
        test.linkEvents("1111");;
    }

    /*@AddEventServlet
    public void t18testUnlinkEvents() throws Exception {
        Event test = new Event(
                "testId",
                "testTitle",
                "testDescription",
                "2018-12-26-11-05-20",
                "3.5994309341289004-29.939200781250065" );

        System.out.println("AddEventServlet: Link Events");
        System.out.println("------------------------");
        test.unlinkEvents("1111");;
    }*/


    @Test
    public void t17testGetLinkedEvents() throws Exception {
        //eventDateTime in the form of YYYY-MM-dd-HH-mm-SS this is due to an inability to use : and , within the string
        Event test = new Event(
                "testId",
                "testTitle",
                "testDescription",
                "2018-12-26-11-05-20",
                "3.5994309341289004-29.939200781250065" );

        System.out.println("AddEventServlet: Get Linked Events");
        System.out.println("------------------------");
        test.getLinkEvents("testId");
        Iterator<String> iterator = test.getLinkedEvents().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        //System.out.println(test.getLinkedEvents());
    }


    /*@AddEventServlet
    public void t19testDeleteEvent() throws Exception {
        //eventDateTime in the form of YYYY-MM-dd-HH-mm-SS this is due to an inability to use : and , within the string
        Event test = new Event(
                "testId",
                "testTitle",
                "testDescription",
                "2018-12-26-11-05-20",
                "3.5994309341289004-29.939200781250065" );

        System.out.println("AddEventServlet: Delete Event");
        System.out.println("------------------------");
        test.deleteEvent();

    }*/

    @After
    public void tearDown() throws Exception {
    }
}