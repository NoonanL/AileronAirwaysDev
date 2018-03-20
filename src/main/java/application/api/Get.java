package application.api;

import application.Runner;
import application.model.Event;
import application.model.Timeline;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


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
                if (path.contains("/TimelineEventAttachment/GenerateUploadPresignedUrl")){
                    String jsonString = content.toString();

                }else {
                    JsonElement jelement = new JsonParser().parse(content.toString());

                    //For timelines and events
                    if (jelement instanceof JsonObject) {
                        JsonObject timelineFromGson = jelement.getAsJsonObject();

                        if (path.contains("/TimelineEvent")) {
                            Event event = new Event();
                            event.setTitle(timelineFromGson.get("Title").toString());
                            event.setEventDateTime(timelineFromGson.get("EventDateTime").toString());
                            event.setDescription(timelineFromGson.get("Description").toString());
                            event.setLocation(timelineFromGson.get("Location").toString());
                            event.setId(timelineFromGson.get("Id").toString());

                        } else if (path.contains("/Timeline")) {
                            if (path.contains("/GetAllTimelinesAndEvent")) {
                                //turn timeline element into a JsonObject
                                JsonArray temp = (JsonArray) timelineFromGson.get("Timelines");
                                for (int i = 0; i < temp.size(); i++) {
                                    JsonObject timelinesFromJson = (JsonObject) temp.get(i);
                                    Timeline timeline = new Timeline();
                                    timeline.setTitle(timelinesFromJson.get("Title").toString());
                                    timeline.setId(timelinesFromJson.get("Id").toString());
                                    //Gson googleJson = new Gson();
                                    //System.out.println(googleJson.fromJson(timelinesFromJson.get("LinkedTimelineEventIds"),ArrayList.class));
                                    //timeline.setLinkedTimelineEventIds(googleJson.fromJson(timelinesFromJson.get("LinkedTimelineEventIds"),ArrayList.class));
                                    JsonArray temp1 = (JsonArray) timelinesFromJson.get("TimelineEvents");
                                    for (int x = 0; x < temp1.size(); x++) {
                                        Event event = new Event();
                                        JsonObject jsonObject2 = (JsonObject) temp1.get(x);
                                        event.setTitle(jsonObject2.get("Title").toString());
                                        event.setEventDateTime(jsonObject2.get("EventDateTime").toString());
                                        event.setDescription(jsonObject2.get("Description").toString());
                                        event.setLocation(jsonObject2.get("Location").toString());
                                        event.setId(jsonObject2.get("Id").toString());
                                        //System.out.println(event.toString());

                                        //add event to timeline object
                                        timeline.addTimelineEvent(event);
                                        Runner.eventRepository.add(event);
                                        /*
                                        Are these no longer used?
                                         */
                                        JsonArray temp2 = (JsonArray) jsonObject2.get("LinkedTimelineEventIds");
                                        JsonArray temp3 = (JsonArray) jsonObject2.get("Attachments");
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
                        if (path.contains("/TimelineEvent")) {
                            if (path.contains("/GetLinkedTimelineEvents")) {
                                Event event = new Event();
                                event.setId(value);
                                for (int i = 0; i < timelineFromGson.size(); i++) {
                                    JsonObject temp = timelineFromGson.get(i).getAsJsonObject();
                                    event.setLinkedEvents(temp.get("LinkedToTimelineEventId").toString());
                                    //System.out.println(temp.get("TimelineEventId"));
                                    //System.out.println(temp.get("LinkedToTimelineEventId"));
                                    //System.out.println(temp.get("Id"));
                                    //System.out.println("");
                                }
                                Runner.eventRepository.add(event);
                            }
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
                            if (path.contains("/GetTimelines")) {
                                for (int i = 0; i < timelineFromGson.size(); i++) {
                                    Timeline timeline = new Timeline();
                                    JsonObject temp = timelineFromGson.get(i).getAsJsonObject();
                                    timeline.setTitle(temp.get("Title").toString());
                                    timeline.setId(temp.get("Id").toString());
                                    Runner.timelineRepository.add(timeline);
                                }
                            }
                            if (path.contains("/GetEvents")) {
                                Timeline timeline = new Timeline();
                                timeline.setId(value);
                                Event event = new Event();
                                for (int i = 0; i < timelineFromGson.size(); i++) {
                                    JsonObject temp = timelineFromGson.get(i).getAsJsonObject();
                                    event.setId(temp.get("TimelineEventId").toString());
                                    //timeline.setEvents(event);
                                    Runner.eventRepository.add(event);

                                }
                            }
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
}
