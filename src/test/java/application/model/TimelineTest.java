package application.model;

import application.Runner;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TimelineTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void t1testCreateTimeline() throws Exception {

        Timeline test = new Timeline("150","testTitle2");
        System.out.println("Testing: Create Timeline");
        System.out.println("------------------------");
        test.createTimeline();

    }

    @Test
    public void t2testLinkTimelineAndEvent() throws Exception {

        Timeline test = new Timeline("150","testTitle2");

        System.out.println("Testing: Create Timeline");
        System.out.println("------------------------");
        test.linkEvent("123654");

    }

    @Test
    public void t3testUnLinkTimelineAndEvent() throws Exception {

        Timeline test = new Timeline("150","testTitle2");

        System.out.println("Testing: Create Timeline");
        System.out.println("------------------------");


        test.unLinkEvent("123654");

    }

    @Test
    public void t4testEditTitle() throws Exception{
        Timeline test = new Timeline("150","testTitle2");
        System.out.println("Testing: Edit Timeline Title");
        System.out.println("------------------------");
        test.editTitle("TestChange");

    }

//    @Test
//    public void t5testGetOneTimeline() throws Exception{
//        Timeline test = new Timeline("150","testTitle2");
//        System.out.println("Testing: Get Specific Timeline");
//        System.out.println("------------------------");
//        test.getTimeline("1234567");
//        System.out.println(Runner.timelineRepository.toString());
//
//    }

    @Test
    public void t6testGetLinkedEvents() throws Exception{
        Timeline test = new Timeline("150","testTitle2");
        System.out.println("Testing: Get Linked Events with Timeline");
        System.out.println("------------------------");
        test.getLinkedEvents("1234567");

    }


    @Test
    public void t7testDeleteTimeline() throws Exception{
        Timeline test = new Timeline("150","testTitle2");
        System.out.println("Testing: Delete Timeline");
        System.out.println("------------------------");
        test.deleteTimeline();

    }

    /*
    This is hella broke.
     */
//    @Test
//    public void t8testGetAllTimelinesAndEvents() throws Exception{
//        Timeline test = new Timeline("150","testTitle2");
//        System.out.println("Testing: Get all Linked Events with Timelines");
//        System.out.println("------------------------");
//        test.getAllTimelinesAndEvents();
//        System.out.println(Runner.timelineRepository);
//        System.out.println("------------------------");
//        System.out.println("------------------------");
//        System.out.println("------------------------");
//        System.out.println(Runner.eventRepository);
//        System.out.println("------------------------");
//
//    }


    @After
    public void tearDown() throws Exception {

//        System.out.println("LISTING ALL TIMELINES AND EVENTS FOR DEBUGGING");
//        Timeline test = new Timeline("150","testTitle2");
//        System.out.println("Testing: Get all Linked Events with Timelines");
//        System.out.println("------------------------");
//        test.getAllTimelinesAndEvents();
//        System.out.println(Runner.timelineRepository);
//        System.out.println("------------------------");
//        System.out.println("------------------------");
//        System.out.println("------------------------");
//        System.out.println(Runner.eventRepository);
//        System.out.println("------------------------");
//
    }
}