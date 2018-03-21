package application.api;


import java.util.LinkedHashMap;
import java.util.Map;

public class CallBuilder {

    private String idType;
    private String id;

    public CallBuilder(String idType, String id){
        this.idType = idType;
        this.id = id;
    }

    public Map buildHeader(){
        //create hashmap of key-value pairs
        Map<String, String> parameters = new LinkedHashMap<>();
        //hardcoded body parameters allowing access to API
        parameters.put("TenantId", "Team8");
        parameters.put("AuthToken", "28dfb21a-8dbd-47cb-a6a2-96fb225cb138");
        //below are the variables you may need to add to/change depending on the method you're implementing
        parameters.put(this.idType, this.id);
        return parameters;
        //When this is returned to the calling method it is then free to add to the bottom of the map
    }

}
