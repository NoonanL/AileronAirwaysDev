package util;

import application.Runner;
import application.model.Event;
import application.model.Timeline;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchFunction{

    private String objectType;
    private String searchString;

    public SearchFunction(String objectType,String searchString) {
        this.objectType = objectType;
        this.searchString = searchString;
    }

    public String SearchFunction(){

        ArrayList searchResults = new ArrayList();
        Iterator it;

        if(objectType.equals("Timeline")){
            it = Runner.timelineRepository.getTimelines().listIterator();
            searchResults = new ArrayList<Timeline>();
            while (it.hasNext()){
                Timeline n = (Timeline) it.next();
                if (n.getId().contains(searchString) || n.getTitle().contains(searchString)){
                    searchResults.add(n);
                }
            }

        }else if (objectType.equals("Event")){
            it = Runner.eventRepository.getEvents().listIterator();
            searchResults = new ArrayList<Event>();
            while (it.hasNext()){
                Event n = (Event) it.next();
                if (n.getId().contains(searchString) || n.getTitle().contains(searchString)){
                    searchResults.add(n);
                }
            }
        }

        System.out.println(searchResults.toString());
        String json = new Gson().toJson(searchResults);
        return json;
    }

}
