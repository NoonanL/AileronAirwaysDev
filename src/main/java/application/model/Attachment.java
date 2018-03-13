package application.model;


import application.api.Put;
import util.ParameterStringBuilder;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class Attachment {

    /*
   VARS HERE
    */
    private String eventId;
    private String attatchmentId;
    private String title;

    /*
    CONSTRUCTORS HERE
     */

    public Attachment(String eventId, String attatchmentId, String title){
        this.eventId = eventId;
        this.attatchmentId = attatchmentId;
        this.title = title;
    }

    public Attachment(String eventId, String title){
        this.eventId = eventId;
        this.attatchmentId = UUID.randomUUID().toString();
        this.title = title;
    }

    public Attachment(){
        this.eventId = "";
        this.attatchmentId = "";
        this.title = "";
    }

    /*
    GETTERS/SETTERS HERE
     */

    //get eventId
    public String getId(){
        return this.eventId;
    }

    //set id
    public void seteventId(String eventId){
        this.eventId = eventId;
    }

    //get attatchmentId
    public String getAttatchmentId(){
        return this.attatchmentId;
    }

    //set attatchmentId
    public void setAttatchmentId(String attatchmentId){
        this.attatchmentId = attatchmentId;
    }

    //get title
    public String getTitle(){
        return this.title;
    }

    //set title
    public void setTitle(String title){
        this.title = title;
    }

    public Map attatchmentPut() throws UnsupportedEncodingException {

        //create hashmap of key-value pairs
        Map<String, String> parameters = new LinkedHashMap<>();
        //hardcoded body parameters allowing access to API
        parameters.put("TenantId", "Team8");
        parameters.put("AuthToken", "28dfb21a-8dbd-47cb-a6a2-96fb225cb138");
        //below are the variables you may need to add to/change depending on the method you're implementing;
        parameters.put("AttachmentId" , this.attatchmentId);

        return parameters;
        //When this is returned to the calling method it is then free to add to the bottom of the map

    }

    /*
    API METHODS HERE
     */

    public void createAttatchment() throws UnsupportedEncodingException {
        Map<String, String> createAttatchmentMap = attatchmentPut();

        createAttatchmentMap.put("TimelineEventId", this.eventId);
        createAttatchmentMap.put("AttachmentId", this.attatchmentId);
        createAttatchmentMap.put("Title", this.title);

        String postData = ParameterStringBuilder.getParamsString(createAttatchmentMap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/TimelineEventAttachment/Create",postData);
    }

    public void generateUploadPresignedURL() throws UnsupportedEncodingException {

    }

}
