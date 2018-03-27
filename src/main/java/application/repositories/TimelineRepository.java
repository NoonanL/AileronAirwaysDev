package application.repositories;

import application.api.Get;
import application.model.Timeline;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class TimelineRepository {

    private ArrayList<Timeline> objects;

    public TimelineRepository(){
        this.objects = new ArrayList<Timeline>();
    }

    public ArrayList<Timeline> getTimelines(){
        return this.objects;
    }


    public void add(Timeline object) throws UnsupportedEncodingException {
        this.objects.add(object);
        //object.createTimeline();

    }


    public Timeline get(String id) {
        for (Timeline object : this.objects)
            if(object.getId().equals(id)){
                System.out.println("Found a match");
                return object;
            }
            return null;
        }



    public void remove(String id) {
        Predicate<Timeline> predicate = e->e.getId().equals(id);
        this.objects.removeIf(predicate);

    }

    public void getAPITimelines() throws UnsupportedEncodingException{
        Get getTimeline = new Get();
        getTimeline.get("/Timeline/GetTimelines", "", "");

    }

    /*
    DONT USE ME, I FILL EVENTS TOO
     */
    public void getAllTimelinesAndEvents() throws UnsupportedEncodingException {
        Get getTimeline = new Get();
        getTimeline.get("/Timeline/GetAllTimelinesAndEvent", "", "");

    }

    public void getTimeline(String id) throws UnsupportedEncodingException{
        Get getTimeline = new Get();
        getTimeline.get("/Timeline/GetTimeline", "TimelineId", id);

    }

    @Override
    public String toString(){
        String str = this.objects.toString();
        return str;
    }


}
