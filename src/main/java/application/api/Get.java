package application.api;

import application.Runner;
import application.model.Attachment;
import application.model.Event;
import application.model.Timeline;
import application.repositories.EventRepository;
import com.google.gson.*;

import javax.sound.midi.SysexMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;
import java.util.Iterator;

public class Get {

    public void get(String path, String key, String value) {


        /*
        Java url library isn't great so the following code block converts the hardcoded url to URI and then back
        to url which deals with any of the issues with java parsing.
         */
        URI uri = null;
        {
            try {
                uri = new URI("https", "gcu.ideagen-development.com", path, null); //MAKE ME GENERIC - PASS ME A VAR
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        URL url = null;
        {
            try {
                url = uri.toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }


        /*
        The following code block does most of the hard work:
        -opens a url connection
        -sends appropriate headers as per the passed variables
        -prints out the result from the server
         */
        {
            try {
                //open url connection
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                //set header*s
                con.setRequestProperty("TenantId", "Team8");
                con.setRequestProperty("AuthToken", "28dfb21a-8dbd-47cb-a6a2-96fb225cb138");
                con.setRequestProperty(key, value);
                //set connection type
                con.setRequestMethod("GET");
                con.setDoOutput(true);

                //create buffered reader for appropriate server response
                BufferedReader in = Put.getBufferedReader(con.getResponseCode(), con.getInputStream(), con.getErrorStream());

                //get reply from server
                String inputLine;
                StringBuffer content = new StringBuffer();
                while (((inputLine = in.readLine()) != null)) {

                    content.append(inputLine);

                }

                /*
                WARNING: John wizardry below. We <3 John.
                 */
                    JsonElement jelement = new JsonParser().parse(content.toString());

                    //For timelines and events
                    if (jelement instanceof JsonObject) {
                        JsonObject timelineFromGson = jelement.getAsJsonObject();

                        if (path.contains("/TimelineEventAttachment")){
                            Attachment attachment = new Attachment();
                            attachment.setTitle(timelineFromGson.get("Title").toString());
                            attachment.setAttachmentId(timelineFromGson.get("Id").toString());
                            attachment.seteventId(timelineFromGson.get("TimelineEventId").toString());
                            //Repository part here
                    }else if (path.contains("/TimelineEvent")) {
                            Event event = new Event();
                            event.setTitle(timelineFromGson.get("Title").toString());
                            event.setEventDateTime(timelineFromGson.get("EventDateTime").toString());
                            event.setDescription(timelineFromGson.get("Description").toString());
                            event.setLocation(timelineFromGson.get("Location").toString());
                            event.setId(timelineFromGson.get("Id").toString());
                            //Repository part here
                        } else if (path.contains("/Timeline")) {
                            if (path.contains("/GetAllTimelinesAndEvent")) {
                                //turn timeline element into a JsonObject
                                JsonArray temp = (JsonArray) timelineFromGson.get("Timelines");
                                for (int i = 0; i < temp.size(); i++) {
                                    JsonObject timelinesFromJson = (JsonObject) temp.get(i);
                                    Timeline timeline = new Timeline();

                                    timeline.setTitle(timelinesFromJson.get("Title").toString());
                                    timeline.setId(timelinesFromJson.get("Id").toString());
                                    JsonArray temp1 = (JsonArray) timelinesFromJson.get("TimelineEvents");
                                    for (int x = 0; x < temp1.size(); x++) {
                                        Event event = new Event();
                                        JsonObject jsonObject2 = (JsonObject) temp1.get(x);
                                        event.setTitle(jsonObject2.get("Title").toString());
                                        event.setEventDateTime(jsonObject2.get("EventDateTime").toString());
                                        event.setDescription(jsonObject2.get("Description").toString());
                                        event.setLocation(jsonObject2.get("Location").toString());
                                        event.setId(jsonObject2.get("Id").toString());
                                        JsonArray temp2 = (JsonArray) jsonObject2.get("LinkedTimelineEventIds");
                                        String eventId;
                                        for (int events = 0; events < temp2.size(); events++) {
                                            eventId = temp2.get(events).toString();
                                            event.setLinkedEvents(eventId);
                                        }
                                        JsonArray temp3 = (JsonArray) jsonObject2.get("Attachments");
                                        for (int attach = 0; attach < temp3.size(); attach++) {
                                            Attachment attachment = new Attachment();
                                            JsonObject attachmentObject = (JsonObject) temp3.get(attach);
                                            attachment.setAttachmentId(attachmentObject.get("Id").toString());
                                            attachment.seteventId(attachmentObject.get("TimelineEventId").toString());
                                            attachment.setTitle(attachmentObject.get("Title").toString());
                                            event.addAttachment(attachment);
                                            Runner.attachmentRepository.add(attachment);
                                        }
                                        timeline.addTimelineEvent(event);
                                        Runner.eventRepository.add(event);
                                    }
                                    Runner.timelineRepository.add(timeline);
                                }
                            }

                            /*
                            Fetches an individual timeline object.
                             */
                            if (path.contains("/GetTimeline")) {
                                Timeline timeline = new Timeline();
                                timeline.setTitle(timelineFromGson.get("Title").toString());
                                timeline.setId(timelineFromGson.get("Id").toString());
                                Runner.timelineRepository.add(timeline);
                            }
                        }


                    /*
                    Fetches Events
                     */
                    } else if (jelement instanceof JsonArray) {
                        JsonArray timelineFromGson = jelement.getAsJsonArray();
                        if (path.contains("/TimelineEventAttachment")) {
                            for (int i = 0; i < timelineFromGson.size(); i++) {
                                JsonObject temp = timelineFromGson.get(i).getAsJsonObject();
                                Attachment attachment = new Attachment();
                                attachment.setTitle(temp.get("Title").toString());
                                attachment.setAttachmentId(temp.get("Id").toString());
                                attachment.seteventId(temp.get("TimelineEventId").toString());
                            }
                        }else if (path.contains("/TimelineEvent")){
                            if (path.contains("/GetAllEvents")) {
                                for (int i = 0; i < timelineFromGson.size(); i++) {
                                    JsonObject temp = timelineFromGson.get(i).getAsJsonObject();
                                    Event event = new Event();
                                    event.setTitle(temp.get("Title").toString());
                                    event.setEventDateTime(temp.get("EventDateTime").toString());
                                    event.setDescription(temp.get("Description").toString());
                                    event.setLocation(temp.get("Location").toString());
                                    event.setId(temp.get("Id").toString());
                                    Runner.eventRepository.add(event);

                                }
                            }
                        } else if (path.contains("/Timeline")) {
                                for (int i = 0; i < timelineFromGson.size(); i++) {
                                    Timeline timeline = new Timeline();
                                    JsonObject temp = timelineFromGson.get(i).getAsJsonObject();
                                    timeline.setTitle(temp.get("Title").toString());
                                    timeline.setId(temp.get("Id").toString());
                                    Runner.timelineRepository.add(timeline);
                            }
                        }
                    }


                //close input buffer
                in.close();
                //disconnect from server
                con.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /*
    Not sure what this does - need John to explain his witchcraft. Attachments or summit.
     */
    public String getReturn(String path, String key, String value) {

        /*
        Java url library isn't great so the following code block converts the hardcoded url to URI and then back
        to url which deals with any of the issues with java parsing.
         */
        URI uri = null;
        {
            try {
                uri = new URI("https", "gcu.ideagen-development.com", path, null); //MAKE ME GENERIC - PASS ME A VAR
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        URL url = null;
        {
            try {
                url = uri.toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }


        /*
        The following code block does most of the hard work:
        -opens a url connection
        -sends appropriate headers as per the passed variables
        -prints out the result from the server
         */
        {
            try {
                //open url connection
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                //set header*s
                con.setRequestProperty("TenantId", "Team8");
                con.setRequestProperty("AuthToken", "28dfb21a-8dbd-47cb-a6a2-96fb225cb138");
                con.setRequestProperty(key, value);
                //set connection type
                con.setRequestMethod("GET");
                con.setDoOutput(true);

                //create buffered reader for appropriate server response
                BufferedReader in = Put.getBufferedReader(con.getResponseCode(), con.getInputStream(), con.getErrorStream());

                //get reply from server
                String inputLine;
                StringBuffer content = new StringBuffer();
                while (((inputLine = in.readLine()) != null)) {

                    content.append(inputLine);

                }

                /*
                WARNING: John wizardry below. We <3 John.
                 */
                if (path.contains("/TimelineEventAttachment/Generate")) {
                    String jsonString = content.toString();
                    return jsonString;
                }


                //close input buffer
                in.close();
                //disconnect from server
                con.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "failed";
    }

    public void getWithObject(String path, String key, String value, Object object) {


        /*
        Java url library isn't great so the following code block converts the hardcoded url to URI and then back
        to url which deals with any of the issues with java parsing.
         */
        URI uri = null;
        {
            try {
                uri = new URI("https", "gcu.ideagen-development.com", path, null); //MAKE ME GENERIC - PASS ME A VAR
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        URL url = null;
        {
            try {
                url = uri.toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }


        /*
        The following code block does most of the hard work:
        -opens a url connection
        -sends appropriate headers as per the passed variables
        -prints out the result from the server
         */
        {
            try {
                //open url connection
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                //set header*s
                con.setRequestProperty("TenantId", "Team8");
                con.setRequestProperty("AuthToken", "28dfb21a-8dbd-47cb-a6a2-96fb225cb138");
                con.setRequestProperty(key, value);
                //set connection type
                con.setRequestMethod("GET");
                con.setDoOutput(true);

                //create buffered reader for appropriate server response
                BufferedReader in = Put.getBufferedReader(con.getResponseCode(), con.getInputStream(), con.getErrorStream());

                //get reply from server
                String inputLine;
                StringBuffer content = new StringBuffer();
                while (((inputLine = in.readLine()) != null)) {

                    content.append(inputLine);

                }

                /*
                WARNING: John wizardry below. We <3 John.
                 */
                JsonElement jelement = new JsonParser().parse(content.toString());
                JsonArray timelineFromGson = jelement.getAsJsonArray();
                if (path.contains("/TimelineEventAttachment")) {

                }else if(path.contains("/TimelineEvent")){
                    Event event = (Event) object;
                    for (int i = 0; i < timelineFromGson.size(); i++) {
                        JsonObject temp = timelineFromGson.get(i).getAsJsonObject();
                        event.setLinkedEvents(temp.get("LinkedToTimelineEventId").toString());
                    }
                    //Insert Repository part here
                }else if(path.contains("/Timeline")){
                        Timeline timeline = (Timeline) object;
                        String eventId;
                        for (int i = 0; i < timelineFromGson.size(); i++) {
                            JsonObject temp = timelineFromGson.get(i).getAsJsonObject();
                            timeline.setLinkedTimelineEventIds(temp.get("TimelineEventId").toString());
                            eventId = temp.get("TimelineEventId").toString();
                            Event event = Runner.eventRepository.get(eventId);
                            timeline.addTimelineEvent(event);
                        }
                        Runner.timelineRepository.add(timeline);
                        for (int i = 0; i < timelineFromGson.size(); i++) {
                            JsonObject temp = timelineFromGson.get(i).getAsJsonObject();
                            timeline.setLinkedTimelineEventIds(temp.get("TimelineEventId").toString());
                        }

                    }
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
