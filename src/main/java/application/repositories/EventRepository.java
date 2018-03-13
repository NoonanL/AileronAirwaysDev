package application.repositories;


import application.model.Event;

import java.util.ArrayList;
import java.util.function.Predicate;

public class EventRepository {

    private ArrayList<Event> objects;

    public EventRepository(){
        this.objects = new ArrayList<>();
    }

    public ArrayList<Event> getEvents(){
        return this.objects;
    }


    public void add(Event object) {
        this.objects.add(object);

    }


    public Event get(String id) {
        for (Event object : this.objects)
            if(object.getId().equals(id)){
                return object;
            }
        return null;
    }



    public void remove(String id) {
        Predicate<Event> predicate = e->e.getId().equals(id);
        this.objects.removeIf(predicate);

    }


}
