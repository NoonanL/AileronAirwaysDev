package util;

import application.Runner;
import application.model.Event;
import application.model.Timeline;
import application.repositories.TimelineRepository;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FetchLinkedEvents {

    ArrayList<String> linkedArrayList;

    public void getAllLinks(String timelineId) throws UnsupportedEncodingException {
        //TimelineRepository timelineRepository = new TimelineRepository();
        //Runner.timelineRepository.getAllTimelinesAndEvents();
//
//        //get the timeline that the id points to
        Timeline testTimeline = Runner.timelineRepository.get(timelineId);
//        //get the events on that timeline
        ArrayList<Event> testData = testTimeline.getTimelineEvents();   
//


        for (Event event:testData
                ) {
            ArrayList<Event> events = new ArrayList<>();
            //Add current event to the start of the arraylist unsure if needed but looked that way
            //event.setEventsLinked(event);
            if(event.getLinkedEvents() != null){

                ArrayList<String> arrayList = event.getLinkedEvents();
                //System.out.println(arrayList.toString());

                for (String linkedeventId : arrayList
                        ) {
                    Event event1 = testTimeline.getTimelineEvent(linkedeventId);
                    event.setEventsLinked(event1);

                }
            }
        }
    }
}

