package application.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

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

                String jsonString = content.toString();
                JsonElement jelement = new JsonParser().parse(content.toString());

                //For timelines and events
                if (jelement instanceof JsonObject) {

                    //turn timeline element into a JsonObject
                    JsonObject timelineFromGson = jelement.getAsJsonObject();
                    JsonArray temp = (JsonArray) timelineFromGson.get("Timelines");
                    for (int i = 0; i < temp.size(); i++) {
                        JsonObject jsonObject1 = (JsonObject) temp.get(i);
                        System.out.println(jsonObject1.get("Id"));
                        System.out.println(jsonObject1.get("Title"));
                        JsonArray temp1 = (JsonArray) jsonObject1.get("TimelineEvents");
                        for (int x = 0; x < temp1.size(); x++) {
                            JsonObject jsonObject2 = (JsonObject) temp1.get(x);
                            System.out.println(jsonObject2.get("Id"));
                            System.out.println(jsonObject2.get("Title"));
                            JsonArray temp2 = (JsonArray) jsonObject2.get("LinkedTimelineEventIds");
                            JsonArray temp3 = (JsonArray) jsonObject2.get("Attachments");
                        }
                    }
                    //System.out.println(timelineFromGson.get("Title").getAsString());
                    //System.out.println(timelineFromGson.get("Id").getAsString());
                    //JsonArray temp = timelineFromGson.get("TimelineEvents").getAsJsonArray();
                    //System.out.println(temp.get(0).getAsString());

                    //For Linked timelines
                } else if (jelement instanceof JsonArray) {
                    JsonArray timelineFromGson = jelement.getAsJsonArray();
                    for(int i=0;i< timelineFromGson.size();i++) {
                        JsonObject temp = timelineFromGson.get(i).getAsJsonObject();
                        System.out.println("This is the array");
                        System.out.println(temp.get("Title"));
                    }

                    //System.out.println(timelineFromGson.get(Integer.parseInt("Title")).getAsString());
                    //System.out.println(timelineFromGson.get("Id").getAsString());

                }

                //JsonObject timelineFromGson = new JsonParser().parse(content.toString()).getAsJsonObject();


                //close input buffer
                in.close();
                //disconnect from server
                con.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
