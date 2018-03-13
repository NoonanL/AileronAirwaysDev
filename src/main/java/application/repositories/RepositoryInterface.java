package application.repositories;

import application.model.Timeline;

public interface RepositoryInterface {

    /*
    add object to repository
     */
    void add (Timeline object);

    /*
    get object by its id
     */
    Timeline getTimeline(String id);

    /*
    remove object from repository
     */
    void remove (String id);


}
