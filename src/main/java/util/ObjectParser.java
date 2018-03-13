package util;

import application.model.Event;
import application.model.Timeline;

import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectParser {

    /*
    Function used by the parseTimeline and parseEvent functions to split the content stringbuffer into
    key value pairs and returns the resulting map
     */
    private Map<String,String> parse(StringBuffer content) {

        final char comma = ',';
        final char colon = ':';

        //create hashmap of key-value pairs
        Map<String, String> parameters = new LinkedHashMap<>();
        //create array of strings and then split the values
        String tempLine[];
        tempLine = content.toString().split(Character.toString(comma));
        //System.out.println(Arrays.toString(tempLine));

        //for each string in the split templine split again on the colon
        for (String s : tempLine) {
            String[] keyVals = s.split(Character.toString(colon));
            //add the split values into the parameters list
            parameters.put(keyVals[0], keyVals[1]);
        }

    return parameters;

    }

    /*
    Returns a timeline object parsed from the content stringbuffer

    Pretty sure John is about to depreciate the hell outta this. We <3 John
     */
    public Timeline parseTimeline(StringBuffer content){

        //create an empty timeline object
         Timeline timeline = new Timeline();
         //create empty map of key value pairs
         Map<String,String> parameters = parse(content);
        //for each mapped key-value pair in the list
        for (Map.Entry<String, String> x : parameters.entrySet()) {
            //System.out.println(x.toString());
            //if the key is Title then set that to the timeline title
            if (x.getKey().contains(" \"Title\"")) {
                timeline.setTitle(x.getValue());
            }
            //if the key is Id then set that to the timeline title
            if (x.getKey().contains(" \"Id\"")) {
                timeline.setId(x.getValue());
            }
        }

        return timeline;
    }

    /*
    Returns and event object parsed from the content Stringbuffer

    Pretty sure John is about to depreciate the hell outta this. We <3 John
     */
    public Event parseEvent(StringBuffer content){

        //create an empty event object
        Event event = new Event();
        //create empty map of key value pairs
        Map<String,String> parameters = parse(content);
        //for each mapped key-value pair in the list
        for (Map.Entry<String, String> x : parameters.entrySet()) {
            if (x.getKey().contains(" \"Id\"")) {
                event.setId(x.getValue());
            }
            if (x.getKey().contains(" \"Title\"")) {
                event.setTitle(x.getValue());
            }
            if (x.getKey().contains("Description")) {
                event.setDescription(x.getValue());
            }
            if (x.getKey().contains("DateTime")) {
                event.setEventDateTime(x.getValue());
            }
            if (x.getKey().contains("Location")) {
                event.setLocation(x.getValue());
            }

        }

        return event;
    }

}
