package application.model;

import application.api.Get;
import application.api.CallBuilder;
import application.api.Put;
import util.ParameterStringBuilder;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

public class Timeline {


    /*
   VARS HERE
    */
    private String id;
    private String title;
    private String eventDateTime;
    private String description;
    private String isDeleted;
    private String location;
    private String creationTimeStamp;
    private String linkedTimelineEventIds;
    private ArrayList<Event> timelineEvents;


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
        this.eventDateTime = null;
        this.description = null;
        this.isDeleted = null;
        this.location = null;
        this.linkedTimelineEventIds = null;
    }

    /*
    Function to build the Map object specific to the Timeline class.
     */
    private Map <String, String> buildMap(){
        CallBuilder callBuilder = new CallBuilder("TimelineId", id);
        return callBuilder.buildHeader();
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

    public void getTimelines() throws UnsupportedEncodingException{
        Get getTimeline = new Get();
        getTimeline.get("/Timeline/GetTimelines", "", "");


    }

    /*
    This should probably be refactored elsewhere because it fetches both timelines and events
    Probably should happen on server start to populate the arrays. Potentially where offline mode will come in.
     */
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




    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public String getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(String eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLinkedTimelineEventIds() {
        return linkedTimelineEventIds;
    }

    public void setLinkedTimelineEventIds(String linkedTimelineEventIds) {
        this.linkedTimelineEventIds = linkedTimelineEventIds;
    }

    public String getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public void setCreationTimeStamp(String creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp;
    }

    public ArrayList getTimelineEvents() {
        return timelineEvents;
    }

    public void setTimelineEvents(ArrayList timelineEvents) {
        this.timelineEvents = timelineEvents;
    }

    @Override
    public String toString(){
        String str = this.id + ", " + this.title;
        return str;
    }
}
