package application.repositories;

import application.model.Timeline;

import java.util.ArrayList;
import java.util.function.Predicate;

public class TimelineRepository {

    private ArrayList<Timeline> objects;

    public TimelineRepository(){
        this.objects = new ArrayList<>();
    }

    public ArrayList<Timeline> getTimelines(){
        return this.objects;
    }


    public void add(Timeline object) {
        this.objects.add(object);

    }


    public Timeline get(String id) {
        for (Timeline object : this.objects)
            if(object.getId().equals(id)){
                return object;
            }
            return null;
        }



    public void remove(String id) {
        Predicate<Timeline> predicate = e->e.getId().equals(id);
        this.objects.removeIf(predicate);

    }


}
