package application.servlet;

import application.Runner;
import application.model.Event;
import application.model.Timeline;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LinkedEventsServletTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void doPost() {
    }

    @Test
    public void doGet() throws UnsupportedEncodingException {
        Runner.timelineRepository.getAllTimelinesAndEvents();
        //System.out.println(Runner.timelineRepository.toString());
        Timeline testTimeline = Runner.timelineRepository.get("\"032943\"");
        //System.out.println(testTimeline.toString());
        ArrayList<Event> testData = testTimeline.getTimelineEvents();
        System.out.println(testData.toString());

    }
}