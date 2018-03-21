package application.model;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

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

        System.out.println("Test: Create an Event");
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

        System.out.println("Test: Edit an Event Description");
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

        System.out.println("Test: Edit an Event Title");
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

        System.out.println("Test: Edit an Event Date and time");
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

        System.out.println("Test: Edit an Event Location");
        System.out.println("------------------------");
        test.editEventLocation("5.0-6.0");

    }

    @Test
    public void t15testGetEvent() throws Exception {
        //eventDateTime in the form of YYYY-MM-dd-HH-mm-SS this is due to an inability to use : and , within the string
        Event test = new Event();

        System.out.println("Test: Get An Event");
        System.out.println("------------------------");
        test.getEvent("777");

    }

    @Test
    public void t16testLinkEvents() throws Exception {
        Event test = new Event(
                "testId",
                "testTitle",
                "testDescription",
                "2018-12-26-11-05-20",
                "3.5994309341289004-29.939200781250065" );

        System.out.println("Test: Link Events");
        System.out.println("------------------------");
        test.linkEvents("1111");;
    }

    /*@Test
    public void t18testUnlinkEvents() throws Exception {
        Event test = new Event(
                "testId",
                "testTitle",
                "testDescription",
                "2018-12-26-11-05-20",
                "3.5994309341289004-29.939200781250065" );

        System.out.println("Test: Link Events");
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

        System.out.println("Test: Get Linked Events");
        System.out.println("------------------------");
        test.getLinkEvents("testId");

    }


    /*@Test
    public void t19testDeleteEvent() throws Exception {
        //eventDateTime in the form of YYYY-MM-dd-HH-mm-SS this is due to an inability to use : and , within the string
        Event test = new Event(
                "testId",
                "testTitle",
                "testDescription",
                "2018-12-26-11-05-20",
                "3.5994309341289004-29.939200781250065" );

        System.out.println("Test: Delete Event");
        System.out.println("------------------------");
        test.deleteEvent();

    }*/

    @After
    public void tearDown() throws Exception {
    }
}