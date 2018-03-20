package application.repositories;

import application.Runner;
import application.model.Timeline;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class TimelineRepositoryTest {

    TimelineRepository timelineRepository = new TimelineRepository();

    @Before
    public void setUp() throws Exception {
        Timeline timeline01 = new Timeline("testId 01", "Test Title 01");
        Timeline timeline02 = new Timeline("testId 02", "Test Title 02");
        timelineRepository.add(timeline01);
        timelineRepository.add(timeline02);
    }

    @Test
    public void getTimelines() {
        System.out.println(timelineRepository.getTimelines());
    }

    @Test
    public void add() {
        Timeline timeline03 = new Timeline("testId 03", "Test Title 03");
        timelineRepository.add(timeline03);
        System.out.println(timelineRepository.getTimelines());
    }

    @Test
    public void get() {
        System.out.println(timelineRepository.get("testId 01"));
    }

    @Test
    public void remove() {
        Timeline timeline03 = new Timeline("testId 03", "Test Title 03");
        timelineRepository.add(timeline03);
        System.out.println(timelineRepository.getTimelines());
        timelineRepository.remove("testId 03");
        System.out.println(timelineRepository.getTimelines());

    }

    @Test
    public void getAPITimelines() throws UnsupportedEncodingException {
        Runner.timelineRepository.getAPITimelines();
        System.out.println(Runner.timelineRepository.toString());
    }

    @Test
    public void getAllTimelinesAndEvents() throws UnsupportedEncodingException {
        Runner.timelineRepository.getAllTimelinesAndEvents();
        System.out.println(Runner.timelineRepository.toString());
        System.out.println(Runner.eventRepository.toString());
    }

    @Test
    public void getTimeline() {
    }
}