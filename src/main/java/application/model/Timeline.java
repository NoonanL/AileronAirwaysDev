package application.model;

import application.api.Get;
import application.api.CallBuilder;
import application.api.Put;
import util.DateHelper;
import util.ParameterStringBuilder;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class Timeline {


    /*
    ----------------------------------------------------------------------------------------
    Variables:
    ----------------------------------------------------------------------------------------
     */

    private String id;
    private String title;
    private String dateTime;
    private String description;
    private String location;
    private String creationTimeStamp;
    private ArrayList<String> linkedTimelineEventIds;
    private ArrayList<Event> timelineEvents;


    /*
    ----------------------------------------------------------------------------------------
    Constructors:
    ----------------------------------------------------------------------------------------
     */

    public Timeline(String id, String title){
        this.id = id;
        this.title = title;
        this.timelineEvents = new ArrayList<Event>();
        this.linkedTimelineEventIds = new ArrayList<>();
    }

    public Timeline(String title){
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.timelineEvents = new ArrayList<Event>();
    }

    public Timeline(){
        this.id = UUID.randomUUID().toString();
        this.title = null;
        this.dateTime = null;
        this.description = null;
        this.location = null;
        this.creationTimeStamp = null;
        this.linkedTimelineEventIds = new ArrayList<>();
        this.timelineEvents = new ArrayList<Event>();
    }


    /*
    Function to build the Map object specific to the Timeline class.
     */
    private Map <String, String> buildMap(){
        CallBuilder callBuilder = new CallBuilder("TimelineId", id);
        return callBuilder.buildHeader();
    }


    /*
    ----------------------------------------------------------------------------------------
    API Methods:
    ----------------------------------------------------------------------------------------
     */

    public void createTimeline() throws UnsupportedEncodingException {
        //CallBuilder callBuilder = new CallBuilder("TimelineId", this.id);
        //create hashmap of key-value pairs
        Map<String, String> map = buildMap();
        map.put("title", this.title);
        System.out.println(map.toString());
        //send the parameters to the ParameterStringBuilder utility class for formatting
        String postData = ParameterStringBuilder.getParamsString(map);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/Timeline/Create",postData);
    }

    public void editTitle(String editTitle) throws UnsupportedEncodingException {
        //Will this method need input validation or will this be handled on the front end?

        this.title = editTitle;
        Map<String, String> editMap = buildMap();
        editMap.put("title", this.title);

        String postData = ParameterStringBuilder.getParamsString(editMap);

        Put.put("/Timeline/EditTitle",postData);
    }

    public void linkEvent(String eventId) throws UnsupportedEncodingException {
        //Will this method need input validation or will this be handled on the front end?


        Map<String, String> linkMap = buildMap();
        linkMap.put("TimelineId", this.id);
        linkMap.put("EventId", eventId);

        String postData = ParameterStringBuilder.getParamsString(linkMap);

        Put.put("/Timeline/LinkEvent",postData);
    }

    public void unLinkEvent(String eventId) throws UnsupportedEncodingException {
        //Will this method need input validation or will this be handled on the front end?


        Map<String, String> linkMap = buildMap();
        linkMap.put("EventId", eventId);

        String postData = ParameterStringBuilder.getParamsString(linkMap);

        Put.put("/Timeline/UnlinkEvent",postData);
    }

    public void deleteTimeline() throws UnsupportedEncodingException {

        //Currently gives an error if passed an id that doesnt exist

        Map<String, String> deleteMap = buildMap();


        String postData = ParameterStringBuilder.getParamsString(deleteMap);

        Put.put("/Timeline/Delete",postData);
    }

    public void getLinkedEvents(String id) throws UnsupportedEncodingException{
        Get getTimeline = new Get();
        getTimeline.getWithObject("/Timeline/GetEvents", "TimelineId", id, this);
    }


    /*
    ----------------------------------------------------------------------------------------
    Getters and Setters:
    ----------------------------------------------------------------------------------------
     */

    public void setTitle(String title) {
        this.title = title.replaceAll("\"","").replaceAll("\\+", " ");
    }

    public String getTitle(){
        return this.title;
    }

    public void setId(String id){
        this.id = id.replaceAll("\"","").replaceAll("\\+", " ");
    }

    public String getId(){
        return this.id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime.replaceAll("\"","").replaceAll("\\+", " ");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.replaceAll("\"","").replaceAll("\\+", " ");
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location.replaceAll("\"","").replaceAll("\\+", " ");
    }

    public ArrayList<String> getLinkedTimelineEventIds() {
        return linkedTimelineEventIds;
    }

    public void setLinkedTimelineEventIds(String linkedTimelineEvent) {
        this.linkedTimelineEventIds.add(linkedTimelineEvent);
    }

    public String getCreationTimeStamp() {
        return this.creationTimeStamp;
    }

    public void setCreationTimeStamp(String creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp.replaceAll("\"","").replaceAll("\\+", " ");
        DateHelper dateHelper = new DateHelper();
        this.dateTime = dateHelper.parseDate(this.creationTimeStamp);
    }

    public ArrayList<Event> getTimelineEvents() {
        return timelineEvents;
    }

    public void setTimelineEvents(ArrayList<Event> timelineEvents) {
        this.timelineEvents = timelineEvents;
    }

    public void addTimelineEvent(Event event) throws UnsupportedEncodingException {
        //System.out.println(event.toString());
        //linkEvent(event.getId());
        timelineEvents.add(event);
        //System.out.println(event.toString());
    }

    public void removeEventFromArray(String eventId){
        //System.out.println(this.timelineEvents);
        if(!this.timelineEvents.isEmpty()) {
            for (Event event : this.timelineEvents) {
                if (event.getId().equals(eventId)) {
                    this.timelineEvents.remove(event);
                    break;
                }
            }
            //System.out.println(this.timelineEvents);
        }
    }
     /*
    ----------------------------------------------------------------------------------------
    Overrides
    ----------------------------------------------------------------------------------------
     */

    @Override
    public String toString(){
        String str = "\n{" + this.id + ", " + this.title + ", " + this.timelineEvents + ", " + this.dateTime + "}";
        return str;
    }
}
