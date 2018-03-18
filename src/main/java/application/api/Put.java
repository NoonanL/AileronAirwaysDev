package application.api;


//import com.sun.deploy.net.HttpRequest;

import java.io.*;
import java.net.*;
import java.nio.Buffer;

/*
Class that carries out PUT api calls
 */

public class Put {

    public static void put(String path, String postData){

    /*
    Java url library isn't great so the following code block converts the hardcoded url to URI and then back
    to url which deals with any of the issues with java parsing.
    */
    URI uri = null;
    {
        try {
            uri = new URI("https", "gcu.ideagen-development.com", path, null); //ALTERNATIVELY PASS ME HERE AND USE URL STRING AS STATIC
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
    -opens the url connection
    -sends appropriate body to the api
    -gets the server response
     */
    HttpURLConnection con;
    {
        try {
            //open url connection
            con = (HttpURLConnection) url.openConnection();
            //set connection type
            con.setRequestMethod("PUT");
            con.setDoOutput(true);
            //write body to api - body is received from the object calling the put request
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(postData);
            //flush the writer - housekeeping
            writer.flush();

            /*create buffered reader
                if response is good, get the output from the server
             */
            BufferedReader in = getBufferedReader(con.getResponseCode(), con.getInputStream(), con.getErrorStream());

            //get reply from server
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                //content.append(inputLine);
                //print output for now
                System.out.println(inputLine);
            }
            //close input
            in.close();

            /*PARSE SERVER RESPONSE - THIS MAY INVOLVE A CONSTRUCTOR METHOD TAKING A STRING AND PARSING IT
             * ALSO - HOW DO I TELL IT WHAT IM CREATING?
             *
             * IF POSSIBLE (SYNTAX DEPENDANT) HAVE THIS BLOCK OF CODE ELSEWHERE OR AS A METHOD
             *
             * Yeah, probably just RETURN this to the object calling the put method.
             */

            //get server response code and message
            int status = con.getResponseCode();
            String response = con.getResponseMessage();
            System.out.println(status);
            System.out.println(response);

            //disconnect from server
            con.disconnect();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Handy buffered reader override:
-good server responses receive normal server output
-bad server responses receive and print the error logs
 */
    public static BufferedReader getBufferedReader(int responseCode, InputStream inputStream, InputStream errorStream) throws IOException {
        BufferedReader in;
        if (200 <= responseCode && responseCode <= 299) {
            in = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println("CODE 200");

        } else { //if response is bad then get the server messages
            in = new BufferedReader(new InputStreamReader(errorStream));
            System.out.println("CODE 500");
        }
        return in;
    }


}
