/*package util;

import application.Runner;
import application.model.Timeline;
import application.repositories.TimelineRepository;
import org.junit.Test;

import javax.rmi.CORBA.Util;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class FetchLinkedEventsTest {

    @Test
    public void getAllLinks() throws UnsupportedEncodingException {
        FetchLinkedEvents linkedEvents = new FetchLinkedEvents();
        Runner.timelineRepository.getAllTimelinesAndEvents();
//        ArrayList<Timeline> timelines = Runner.timelineRepository.getTimelines();
//        Iterator<Timeline> iterator = timelines.iterator();
//        while(iterator.hasNext()){
            //String id = iterator.next().getId();
            //System.out.println(id);
            List list =  linkedEvents.getAllLinks("8a3fbe6d-04ab-49c9-a142-67b678f9cbaf");
            System.out.println(list.toString());
        }
    }*/
