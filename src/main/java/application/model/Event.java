package application.model;

import application.api.Get;
import application.api.Put;
import util.ParameterStringBuilder;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class Event {


    /*
   VARS HERE
    */
    private String id;
    private String title;
    private String description;
    private String eventDateTime;
    private String location;
    private ArrayList<String> linkedEvents;


    /*
    CONSTRUCTORS HERE
     */
    public Event(String id, String title, String description, String eventDateTime, String location){
        this.id = id;
        this.title = title;
        this.description = description;
        this.eventDateTime = eventDateTime;
        this.location = location;
    }

    public Event(String title, String description, String eventDateTime, String location){
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.eventDateTime = eventDateTime;
        this.location = location;
    }

    public Event(){
        this.id = "";
        this.title = "";
        this.description = "";
        this.eventDateTime = "";
        this.location = "";
    }


    /*
    GETTERS/SETTERS HERE
     */

    //get id
    public String getId(){
        return this.id;
    }

    //set id
    public void setId(String id){
        this.id = id;
    }

    //get title
    public String getTitle(){
        return this.title;
    }

    //set title
    public void setTitle(String title){
        this.title = title;
    }

    //get description
    public String getDescription(){
        return this.description;
    }

    //set description
    public void setDescription(String description){
        this.description = description;
    }

    //get eventdatetime
    public String getEventDateTime(){
        return this.eventDateTime;
    }

    //set eventdatetime
    public void setEventDateTime(String eventDateTime){
        this.eventDateTime = eventDateTime;
    }

    //get location
    public String getLocation(){
        return this.location;
    }

    //setlocation
    public void setLocation(String location){
        this.location = location;
    }

    public void setLinkedEvents(String linkedEvent){
        this.linkedEvents.add(linkedEvent);
    }

    public String getLinkedEvents(){
    return this.linkedEvents.toString();
    }


    public Map eventPut() throws UnsupportedEncodingException{

        /*
        Included a function to reduce the coding requirement for each hashmap created, currently including timelineEventId
        although this may later need to be removed as some of the functionality do not use it but use a different id
        this can be worked with later down the line, taken from Jordans code.
         */

        //create hashmap of key-value pairs
        Map<String, String> parameters = new LinkedHashMap<>();
        //hardcoded body parameters allowing access to API
        parameters.put("TenantId", "Team8");
        parameters.put("AuthToken", "28dfb21a-8dbd-47cb-a6a2-96fb225cb138");
        //below are the variables you may need to add to/change depending on the method you're implementing;
        parameters.put("TimelineEventId" , this.id);

        return parameters;
        //When this is returned to the calling method it is then free to add to the bottom of the map

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
    public void createEvent() throws UnsupportedEncodingException {
        //create hashmap of key-value pairs
        Map<String, String> createEventMap = eventPut();
        //below are the variables you may need to add to/change depending on the method you're implementing
        createEventMap.put("Title", this.title);
        createEventMap.put("Description" , this.description);
        createEventMap.put("EventDateTime" , this.eventDateTime);
        createEventMap.put("Location" , this.location);
        //send the parameters to the ParameterStringBuilder utility class for formatting
        String postData = ParameterStringBuilder.getParamsString(createEventMap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/TimelineEvent/Create",postData);
    }

    public void deleteEvent() throws UnsupportedEncodingException {
        Map<String, String> createEventMap = eventPut();
        //send the parameters to the ParameterStringBuilder utility class for formatting
        String postData = ParameterStringBuilder.getParamsString(createEventMap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/TimelineEvent/Delete",postData);
    }

    public void editEventDescription(String newDescription) throws UnsupportedEncodingException {

        Map<String, String> createEventMap = eventPut();
        createEventMap.put("Description" , newDescription);
        //send the parameters to the ParameterStringBuilder utility class for formatting
        String postData = ParameterStringBuilder.getParamsString(createEventMap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/TimelineEvent/EditDescription",postData);
    }

    public void editEventTitle(String newTitle) throws UnsupportedEncodingException {

        Map<String, String> createEventMap = eventPut();
        createEventMap.put("Title" , newTitle);
        //send the parameters to the ParameterStringBuilder utility class for formatting
        String postData = ParameterStringBuilder.getParamsString(createEventMap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/TimelineEvent/EditTitle",postData);
    }

    public void editEventDateTime(String newDateTime) throws UnsupportedEncodingException {
        Map<String, String> createEventMap = eventPut();
        createEventMap.put("EventDateTime" , newDateTime);
        //send the parameters to the ParameterStringBuilder utility class for formatting
        String postData = ParameterStringBuilder.getParamsString(createEventMap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/TimelineEvent/EditEventDateTime",postData);
    }

    public void editEventLocation(String newLocation) throws UnsupportedEncodingException {
        Map<String, String> createEventMap = eventPut();
        createEventMap.put("Location" , newLocation);
        //send the parameters to the ParameterStringBuilder utility class for formatting
        String postData = ParameterStringBuilder.getParamsString(createEventMap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/TimelineEvent/EditLocation",postData);
    }

    //Currently working but not doing any more linked events atm, need to know what type of validation is being done as
    //currently can make some really odd things happen here
    public void linkEvents(String linkedEventId) throws UnsupportedEncodingException {
        Map<String, String> createEventMap = eventPut();
        createEventMap.put("LinkedToTimelineEventId" , linkedEventId);
        //send the parameters to the ParameterStringBuilder utility class for formatting
        String postData = ParameterStringBuilder.getParamsString(createEventMap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/TimelineEvent/LinkEvents",postData);
    }

    public void unlinkEvents(String linkedEventId) throws UnsupportedEncodingException {
        Map<String, String> createEventMap = eventPut();
        createEventMap.put("UnlinkedFromTimelineEventId" , linkedEventId);
        //send the parameters to the ParameterStringBuilder utility class for formatting
        String postData = ParameterStringBuilder.getParamsString(createEventMap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/TimelineEvent/UnlinkEvents",postData);
    }

    public void getLinkEvents(String id) throws UnsupportedEncodingException {
        Get getEvent = new Get();

        getEvent.get("/TimelineEvent/GetLinkedTimelineEvents", "TimelineEventId", id);
    }

    public void getEvent(String id) throws UnsupportedEncodingException {
        Get getEvent = new Get();

        getEvent.get("/TimelineEvent/GetTimelineEvent", "TimelineEventId", id);
    }

    public void getAllEvents() throws UnsupportedEncodingException {
        Get getEvent = new Get();

        getEvent.get("/TimelineEvent/GetAllEvents", "", "");
    }

    @Override
    public String toString(){
        String str = this.id + ", " + this.title;
        return str;
    }
}
