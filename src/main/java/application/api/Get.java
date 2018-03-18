package application.api;

import application.Runner;
import application.model.Event;
import application.model.Timeline;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;


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
                            //System.out.println(timelineFromGson.get("Title"));
                            event.setTitle(timelineFromGson.get("Title").toString());
                            //System.out.println(timelineFromGson.get("EventDateTime"));
                            event.setEventDateTime(timelineFromGson.get("EventDateTime").toString());
                            //System.out.println(timelineFromGson.get("Description"));
                            event.setDescription(timelineFromGson.get("Description").toString());
                            //System.out.println(timelineFromGson.get("Location"));
                            event.setLocation(timelineFromGson.get("Location").toString());
                            //System.out.println(timelineFromGson.get("Id"));
                            event.setId(timelineFromGson.get("Id").toString());
                        } else if (path.contains("/Timeline")) {
                            if (path.contains("/GetAllTimelinesAndEvent")) {
                                //turn timeline element into a JsonObject
                                JsonArray temp = (JsonArray) timelineFromGson.get("Timelines");
                                for (int i = 0; i < temp.size(); i++) {



                                    JsonObject timelinesFromJson = (JsonObject) temp.get(i);
                                    Timeline timeline = new Timeline();
                                    timeline.setTitle(timelinesFromJson.get("Title").toString());
                                    //System.out.println(jsonObject1.get("Title"));
                                    timeline.setId(timelinesFromJson.get("Id").toString());
                                    //System.out.println(jsonObject1.get("Id"));
                                    JsonArray temp1 = (JsonArray) timelinesFromJson.get("TimelineEvents");
                                    Runner.timelineRepository.add(timeline);
                                    for (int x = 0; x < temp1.size(); x++) {
                                        Event event = new Event();
                                        JsonObject jsonObject2 = (JsonObject) temp1.get(x);
                                        event.setTitle(jsonObject2.get("Title").toString());
                                        //System.out.println(jsonObject2.get("Title"));
                                        event.setEventDateTime(jsonObject2.get("EventDateTime").toString());
                                        //System.out.println(jsonObject2.get("EventDateTime"));
                                        event.setDescription(jsonObject2.get("Description").toString());
                                        //System.out.println(jsonObject2.get("Description"));
                                        event.setLocation(jsonObject2.get("Location").toString());
                                        //System.out.println(jsonObject2.get("Location"));
                                        event.setId(jsonObject2.get("Id").toString());
                                        //System.out.println(jsonObject2.get("Id"));
                                        Runner.eventRepository.add(event);

                                        /*
                                        This needs looked at - Timeline objects store an array of events (is this necessary if we can
                                        link them by Ids anyway? Anyways - this code tries to just pass one event at a time.
                                        It would need to either use an add() method or create the full array and then pass that.
                                         */
                                        //timeline.setEvents(event);

                                        /*
                                        Are these no longer used?
                                         */
                                        JsonArray temp2 = (JsonArray) jsonObject2.get("LinkedTimelineEventIds");
                                        JsonArray temp3 = (JsonArray) jsonObject2.get("Attachments");
                                    }
                                    //System.out.println("");
                                }
                            }
                            if (path.contains("/GetTimeline")) {
                                Timeline timeline = new Timeline();
                                timeline.setTitle(timelineFromGson.get("Title").toString());
                                //System.out.println(timelineFromGson.get("Title"));
                                timeline.setId(timelineFromGson.get("Id").toString());
                                //System.out.println(timelineFromGson.get("Id"));
                                Runner.timelineRepository.add(timeline);
                            }
                        }


                        //For Linked timelines
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
                                    //System.out.println(temp.get("Title"));
                                    event.setEventDateTime(temp.get("EventDateTime").toString());
                                    //System.out.println(temp.get("EventDateTime"));
                                    event.setDescription(temp.get("Description").toString());
                                    //System.out.println(temp.get("Description"));
                                    event.setLocation(temp.get("Location").toString());
                                    //System.out.println(temp.get("Location"));
                                    event.setId(temp.get("Id").toString());
                                    //System.out.println(temp.get("Id"));
                                    //System.out.println("");
                                    Runner.eventRepository.add(event);

                                }
                            }
                        } else if (path.contains("/Timeline")) {
                            if (path.contains("/GetTimelines")) {
                                for (int i = 0; i < timelineFromGson.size(); i++) {
                                    Timeline timeline = new Timeline();
                                    JsonObject temp = timelineFromGson.get(i).getAsJsonObject();
                                    //System.out.println(temp.get("Title"));
                                    timeline.setTitle(temp.get("Title").toString());
                                    timeline.setId(temp.get("Id").toString());
                                    //System.out.println(temp.get("Id"));
                                    //System.out.println("");
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
                                    //System.out.println(temp.get("TimelineEventId"));
                                    //System.out.println(temp.get("TimelineId"));
                                    //System.out.println("");
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
