package application.model;

import application.api.Get;
import application.api.Put;
import util.ParameterStringBuilder;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Timeline {


    /*
   VARS HERE
    */
    private String id;
    private String title;
    private ArrayList<Event> events;



    /*
    CONSTRUCTORS HERE
     */
    public Timeline(String id, String title){
        this.id = id;
        this.title = title;
    }

    public Timeline(){
        this.id = null;
        this.title = null;
    }




    /*
    GETTERS/SETTERS HERE
    Note - these getters/setters are for the java objects only and will not persist to the API database.
     */

    public Map timelinePut() throws UnsupportedEncodingException {


        //To stop the repeating the same bits of code over and over for each put method
        //Created a method that can be called to create a map for any put method that requires it
        //The TenantID and AuthToken will always be the same so they are hard coded
        //Each of the API calls for the timeline involve TimelineID so this is stored here
        //Any method that calls this map can then add any further required fields to the map


        //create hashmap of key-value pairs
        Map<String, String> parameters = new LinkedHashMap<>();
        //hardcoded body parameters allowing access to API
        parameters.put("TenantId", "Team8");
        parameters.put("AuthToken", "28dfb21a-8dbd-47cb-a6a2-96fb225cb138");
        //below are the variables you may need to add to/change depending on the method you're implementing
        parameters.put("TimelineId", this.id);


        return parameters;
        //When this is returned to the calling method it is then free to add to the bottom of the map

    }

    //sets title
    public void setTitle(String title){
        this.title = title;
    }

    //gets title
    public String getTitle(){
        return this.title;
    }

    //set id
    public void setId(String id){
        this.id = id;
    }

    //get id
    public String getId(){
        return this.id;

    }

    public String getEvents(){
    return this.events.toString();
    }

    public void setEvents(Event newEvent){
        this.events.add(newEvent);
    }


    /*
    API METHODS HERE
     */

    /*
    Example of a method calling the API. Things you'll need to change are pretty much just the last few
    parameters.put() values depending on what values you need to send. The first two are hardcoded so should stay
    as they are.
     */
    //send this timeline object to the API for persistence
    public void createTimeline() throws UnsupportedEncodingException {
        //create hashmap of key-value pairs
        Map<String, String> createMap = timelinePut();

        createMap.put("title", this.title);
        //send the parameters to the ParameterStringBuilder utility class for formatting
        String postData = ParameterStringBuilder.getParamsString(createMap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/Timeline/Create",postData);
    }

    public void editTitle(String editTitle) throws UnsupportedEncodingException {
        //Will this method need input validation or will this be handled on the front end?

        this.title = editTitle;
        Map<String, String> editMap = timelinePut();
        editMap.put("title", this.title);

        String postData = ParameterStringBuilder.getParamsString(editMap);

        Put.put("/Timeline/EditTitle",postData);
    }


    public void linkEvent(String eventId) throws UnsupportedEncodingException {
        //Will this method need input validation or will this be handled on the front end?


        Map<String, String> linkMap = timelinePut();
        linkMap.put("EventId", eventId);

        String postData = ParameterStringBuilder.getParamsString(linkMap);

        Put.put("/Timeline/LinkEvent",postData);
    }

    public void unLinkEvent(String eventId) throws UnsupportedEncodingException {
        //Will this method need input validation or will this be handled on the front end?


        Map<String, String> linkMap = timelinePut();
        linkMap.put("EventId", eventId);

        String postData = ParameterStringBuilder.getParamsString(linkMap);

        Put.put("/Timeline/UnlinkEvent",postData);
    }

    public void deleteTimeline() throws UnsupportedEncodingException {

        //Currently gives an error if passed an id that doesnt exist

        Map<String, String> deleteMap = timelinePut();


        String postData = ParameterStringBuilder.getParamsString(deleteMap);

        Put.put("/Timeline/Delete",postData);
    }



    public void getTimelines() throws UnsupportedEncodingException{
        Get getTimeline = new Get();
        getTimeline.get("/Timeline/GetTimelines", "", "");


    }

    public void getTimelinesAndEvents() throws UnsupportedEncodingException{
        Get getTimeline = new Get();
        getTimeline.get("/Timeline/GetAllTimelinesAndEvent", "", "");


    }

    public void getTimeline(String id) throws UnsupportedEncodingException{
        Get getTimeline = new Get();
        getTimeline.get("/Timeline/GetTimeline", "TimelineId", id);


    }

    public void getLinkedEvents(String id) throws UnsupportedEncodingException{
        Get getTimeline = new Get();

        getTimeline.get("/Timeline/GetEvents", "TimelineId", id);


    }



}
