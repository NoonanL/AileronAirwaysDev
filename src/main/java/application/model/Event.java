package application.model;

import application.api.CallBuilder;
import application.api.Get;
import application.api.Put;
import util.ParameterStringBuilder;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.*;

public class Event {


    /*
    ----------------------------------------------------------------------------------------
    Variables:
    ----------------------------------------------------------------------------------------
     */

    private String id;
    private String title;
    private String description;
    private String eventDateTime;
    private String location;
    private ArrayList<String> linkedEvents;
    private ArrayList<Attachment> attachments;
    private String yyyy;
    private String MM;
    private String dd;
    private String hh;
    private String mm;



    /*
    ----------------------------------------------------------------------------------------
    Constructors:
    ----------------------------------------------------------------------------------------
     */

    public Event(String id, String title, String description, String eventDateTime, String location){
        this.id = id;
        this.title = title;
        this.description = description;
        this.eventDateTime = eventDateTime;
        this.location = location;
        this.linkedEvents = new ArrayList<>();
    }

    public Event(String title, String description, String eventDateTime, String location){
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.eventDateTime = eventDateTime;
        this.location = location;
        this.linkedEvents = new ArrayList<>();
    }

    public Event(){
        this.id = UUID.randomUUID().toString();
        this.title = "";
        this.description = "";
        this.eventDateTime = "";

        this.linkedEvents = new ArrayList<>();
        this.location = "";
        this.linkedEvents = new ArrayList<>();
    }

    /*
    Function to build the Map object specific to the Event class.
     */
    private Map <String, String> buildMap(){
        CallBuilder callBuilder = new CallBuilder("TimelineEventId", id);
        return callBuilder.buildHeader();
    }


    /*
    ----------------------------------------------------------------------------------------
    API Methods:
    ----------------------------------------------------------------------------------------
     */



    public void createEvent() throws UnsupportedEncodingException {
        //create hashmap of key-value pairs
        Map<String, String> createEventMap = buildMap();
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
        Map<String, String> createEventMap = buildMap();
        //send the parameters to the ParameterStringBuilder utility class for formatting
        String postData = ParameterStringBuilder.getParamsString(createEventMap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/TimelineEvent/Delete",postData);
    }

    public void editEventDescription(String newDescription) throws UnsupportedEncodingException {

        Map<String, String> createEventMap = buildMap();
        createEventMap.put("Description" , newDescription);
        //send the parameters to the ParameterStringBuilder utility class for formatting
        String postData = ParameterStringBuilder.getParamsString(createEventMap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/TimelineEvent/EditDescription",postData);
    }

    public void editEventTitle(String newTitle) throws UnsupportedEncodingException {

        Map<String, String> createEventMap = buildMap();
        createEventMap.put("Title" , newTitle);
        //send the parameters to the ParameterStringBuilder utility class for formatting
        String postData = ParameterStringBuilder.getParamsString(createEventMap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/TimelineEvent/EditTitle",postData);
    }

    public void editEventDateTime(String newDateTime) throws UnsupportedEncodingException {
        Map<String, String> createEventMap = buildMap();
        createEventMap.put("EventDateTime" , newDateTime);
        //send the parameters to the ParameterStringBuilder utility class for formatting
        String postData = ParameterStringBuilder.getParamsString(createEventMap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/TimelineEvent/EditEventDateTime",postData);
    }

    public void editEventLocation(String newLocation) throws UnsupportedEncodingException {
        Map<String, String> createEventMap = buildMap();
        createEventMap.put("Location" , newLocation);
        //send the parameters to the ParameterStringBuilder utility class for formatting
        String postData = ParameterStringBuilder.getParamsString(createEventMap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/TimelineEvent/EditLocation",postData);
    }

    //Currently working but not doing any more linked events atm, need to know what type of validation is being done as
    //currently can make some really odd things happen here
    public void linkEvents(String linkedEventId) throws UnsupportedEncodingException {
        Map<String, String> createEventMap = buildMap();
        createEventMap.put("LinkedToTimelineEventId" , linkedEventId);
        //send the parameters to the ParameterStringBuilder utility class for formatting
        String postData = ParameterStringBuilder.getParamsString(createEventMap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/TimelineEvent/LinkEvents",postData);
    }

    public void unlinkEvents(String linkedEventId) throws UnsupportedEncodingException {
        Map<String, String> createEventMap = buildMap();
        createEventMap.put("UnlinkedFromTimelineEventId" , linkedEventId);
        //send the parameters to the ParameterStringBuilder utility class for formatting
        String postData = ParameterStringBuilder.getParamsString(createEventMap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/TimelineEvent/UnlinkEvents",postData);
    }

    public void getLinkEvents(String id) throws UnsupportedEncodingException {
        Get getEvent = new Get();

        getEvent.getWithObject("/TimelineEvent/GetLinkedTimelineEvents", "TimelineEventId", id,this);
    }

    public void getEvent(String id) throws UnsupportedEncodingException {
        Get getEvent = new Get();

        getEvent.get("/TimelineEvent/GetTimelineEvent", "TimelineEventId", id);
    }


    /*
    ----------------------------------------------------------------------------------------
    Getters and Setters:
    ----------------------------------------------------------------------------------------
     */

    //get id
    public String getId(){
        return this.id;
    }

    //set id
    public void setId(String id){
            this.id = id.replaceAll("\"", "").replaceAll("\\+", " ");
    }

    //get title
    public String getTitle(){
        return this.title;
    }

    //set title
    public void setTitle(String title){
        this.title = title.replaceAll("\"", "").replaceAll("\\+", " ");
    }

    //get description
    public String getDescription(){
        return this.description;
    }

    //set description
    public void setDescription(String description){
        this.description = description.replaceAll("\"", "").replaceAll("\\+", " ");
    }

    //get eventdatetime
    public String getEventDateTime(){
        return this.eventDateTime;
    }

    //set eventdatetime
    public void setEventDateTime(String eventDateTime){
        //System.out.println(eventDateTime);
        this.eventDateTime = eventDateTime
                .replaceAll("%3A","/")
                .replaceAll("\"","")
                .replaceAll("-","/")
                .replaceAll(":","/")
                .replaceAll(", ","/")
                .replaceAll(" ","/")
                .replaceAll("\\+", "/");
        System.out.println(this.eventDateTime);
        String[] temp = this.eventDateTime.split("/");
        System.out.println(Arrays.toString(temp));
        this.dd = temp[0];
        //System.out.println(this.dd);
        this.MM = temp[1];
        //System.out.println(this.MM);
        this.yyyy = temp[2];
        //System.out.println(this.yyyy);
        this.hh = temp[3];
        //System.out.println(this.hh);
        this.mm = temp[4];
        //System.out.println(this.mm);

    }

    //get location
    public String getLocation(){
        return this.location;
    }

    //setlocation
    public void setLocation(String location){
        this.location = location.replaceAll("\"","").replaceAll("\\+", " ");
    }

    public void setLinkedEvents(String linkedEvent){
        this.linkedEvents.add(linkedEvent.replaceAll("\"","").replaceAll("\\+", " "));
    }

    public ArrayList getLinkedEvents(){
        return this.linkedEvents;
    }

    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<Attachment> attachments) {
        this.attachments = attachments;
    }

     /*
    ----------------------------------------------------------------------------------------
    Overrides
    ----------------------------------------------------------------------------------------
     */

    @Override
    public String toString(){
        String str = " \n" +  this.id + ", "
                + this.title + ", "
                + this.description + ", "
                + this.eventDateTime + ", "
                + this.location;
        return str;
    }


}
