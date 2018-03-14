package application.model;


import application.api.Get;
import application.api.Put;
import util.ParameterStringBuilder;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

    public void createAndUploadAttatchment(String filename) throws IOException {
        //Generate the URL to upload to from the filename
        String urlString = generateUploadPresignedURL(filename);
        URL url = new URL(urlString);
        try {
            //Passes the url and file to be uploaded
            uploadObject(url, filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downloadAttatchment(String filename, String newFilePath) throws UnsupportedEncodingException, MalformedURLException {
        //Generate the URL to download from using the filename
        String urlString = generateDownloadPresignedURL(filename);
        URL url = new URL(urlString);
        try {
            //Passes the URL and the new path for the file to download to
            downloadObject(url,newFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public String generateUploadPresignedURL(String filename) throws UnsupportedEncodingException {
        Get getEvent = new Get();

        String website = getEvent.getReturn("/TimelineEventAttachment/GenerateUploadPresignedUrl", "AttachmentId", filename);
        return website;
    }

    public String generateDownloadPresignedURL(String filename) throws UnsupportedEncodingException {
        Get getEvent = new Get();

        String website = getEvent.getReturn("/TimelineEventAttachment/GenerateGetPresignedUrl", "AttachmentId", filename);
        return website;
    }

    public static void uploadObject(URL url,String filename) throws IOException {
        HttpURLConnection connection=(HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("PUT");
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());


        //Checks for the filetype and then uploads based on that, only accepts the three filetypes Ideagen gave
        try {
            if (filename.contains(".doc")) {
                FileInputStream fin = new FileInputStream(filename);
                int i = 0;
                while ((i = fin.read()) != -1) {
                    out.write(i);
                }
                fin.close();
        }else if (filename.contains(".png") ){
                //Creates a bufferedimage from the filename and then writes to the URL(fuck this bit was annoying)
            BufferedImage image = ImageIO.read(new File(filename));
                ImageIO.write(image, "png", out);
        }
        else if(filename.contains(".jpeg")){
                BufferedImage image = ImageIO.read(new File(filename));
                ImageIO.write(image, "jpeg", out);
            }
            //int[] temp = new int[fSize];
        }catch (Exception e ){System.out.println(e);}
        out.close();
        int responseCode = connection.getResponseCode();
        System.out.println("Service returned response code " + responseCode);
    }

    private static void downloadObject(URL url, String filePath)
            throws IOException {
        HttpURLConnection connection=(HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("GET");

        //Checks for filetype of the new path some error checking might be good here for conflicting types
        if (filePath.contains(".doc")) {
            BufferedReader in = Put.getBufferedReader(connection.getResponseCode(), connection.getInputStream(), connection.getErrorStream());
            System.out.println(connection.getInputStream().read());
            PrintWriter writer = new PrintWriter(filePath);
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                writer.println(inputLine);
            in.close();
            writer.close();
            }
        else if (filePath.contains(".png")){
            BufferedImage image = ImageIO.read(connection.getInputStream());
            ImageIO.write(image, "png", new File(filePath));
        }
        else if(filePath.contains(".jpeg")){
            BufferedImage image = ImageIO.read(connection.getInputStream());
            ImageIO.write(image, "png", new File(filePath));
        }

    }

}
