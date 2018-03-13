package application.repositories;

import application.model.Timeline;

import java.util.ArrayList;
import java.util.function.Predicate;

public class TimelineRepository  implements RepositoryInterface{

    private ArrayList<Timeline> objects;

    public TimelineRepository(){
        this.objects = new ArrayList<>();
    }

    public ArrayList<Timeline> getTimelines(){
        return this.objects;
    }

    @Override
    public void add(Timeline object) {
        this.objects.add(object);

    }

    @Override
    public Timeline getTimeline(String id) {
        for (Timeline object : this.objects)
            if(object.getId().equals(id)){
                return object;
            }
            return null;
        }


    @Override
    public void remove(String id) {
        Predicate<Timeline> predicate = e->e.getId().equals(id);
        this.objects.removeIf(predicate);

    }
}
