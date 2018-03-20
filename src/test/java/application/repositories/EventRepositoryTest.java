package application.repositories;

import application.Runner;
import application.model.Event;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class EventRepositoryTest {

    EventRepository eventRepository = new EventRepository();

    @Before
    public void setUp() throws Exception {
        Event event01 = new Event("testId01","Title","Description","DateTime","Location");
        Event event02 = new Event("testId02","Title","Description","DateTime","Location");
        eventRepository.add(event01);
        eventRepository.add(event02);

    }

    @Test
    public void getEvents() {
        System.out.println(eventRepository.getEvents());
    }

    @Test
    public void add() {
        Event event03 = new Event("testId03","Title","Description","DateTime","Location");
        eventRepository.add(event03);
        System.out.println(eventRepository.getEvents());
    }

    @Test
    public void get() {
        System.out.println(eventRepository.get("testId01"));
    }

    @Test
    public void remove() {
        Event event03 = new Event("testId03","Title","Description","DateTime","Location");
        eventRepository.add(event03);
        System.out.println(eventRepository.getEvents());
        eventRepository.remove("testId03");
        System.out.println(eventRepository.getEvents());
    }

    @Test
    public void getAPIEvents() throws UnsupportedEncodingException {
        Runner.eventRepository.getAPIEvents();
        System.out.println(Runner.eventRepository.toString());
    }
}