package util;

import application.Runner;
import application.model.Event;
import application.model.Timeline;
import com.google.gson.Gson;

import java.util.ArrayList;

public class FetchLinkedEvents {

    public String fetchLinkedEvents(String timelineId){

        Timeline timeline = Runner.timelineRepository.get(timelineId);
        ArrayList<Event> events = timeline.getTimelineEvents();

        for(Event e : events){
            ArrayList linkedIds = e.getLinkedEvents();
            for(Object s : linkedIds){
                for(Event compareEvent : events){
                    if(compareEvent.getLinkedEvents().contains(s)){

                    }
                }

            }
        }

        String json = new Gson().toJson("test");
        return json;
    }

}
