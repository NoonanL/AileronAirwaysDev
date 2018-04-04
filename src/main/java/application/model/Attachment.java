package application.model;


import application.api.CallBuilder;
import application.api.Get;
import application.api.Put;
import util.ParameterStringBuilder;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.UUID;



public class Attachment {

    /*
    ----------------------------------------------------------------------------------------
    Variables:
    ----------------------------------------------------------------------------------------
     */

    private String eventId;
    private String attachmentId;
    private String title;
    private String type;
    private String href;

    /*
    ----------------------------------------------------------------------------------------
    Constructors:
    ----------------------------------------------------------------------------------------
     */

    public Attachment(String eventId, String attachmentId, String title){
        this.eventId = eventId;
        this.attachmentId = attachmentId;
        this.title = title;
    }

    public Attachment(String eventId, String title){
        this.eventId = eventId;
        this.attachmentId = UUID.randomUUID().toString();
        this.title = title;
        this.type = "";
        if(title.contains(".jpeg") || title.contains(".png")){
            this.type = "img";
        }else{
            this.type = "doc";
        }
    }

    public Attachment(){
        this.eventId = "";
        this.attachmentId = "";
        this.title = "";
    }


    /*
    Function to build the map object specific to the Attachment class.
     */
    private Map <String, String> buildMap(){
        CallBuilder callBuilder = new CallBuilder("AttachmentId", attachmentId);
        return callBuilder.buildHeader();
    }


    /*
    ----------------------------------------------------------------------------------------
    API Methods:
    ----------------------------------------------------------------------------------------
     */


    public void createAndUploadAttachment(String filepath) throws IOException {
        //Generate the URL to upload to from the filename
        String filename = (new File(filepath)).getName();
        String urlString = generateUploadPresignedURL(filename);
        URL url = new URL(urlString);
        try {
            //Passes the url and file to be uploaded
            uploadObject(url, filename, filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downloadAttachment(String filename, String newFilePath) throws UnsupportedEncodingException, MalformedURLException {
        //Generate the URL to download from using the filename
        //Figure out what will be passed in here assuming it will come from a list of attachments therefore the id will
        //be given so no filepath stripping to do
        String urlString = generateDownloadPresignedURL(filename);
        URL url = new URL(urlString);
        try {
            //Passes the URL and the new path for the file to download to
            downloadObject(url,newFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createAttachment(String filepath) throws IOException {
        Map<String, String> createAttachmentMap = buildMap();

        createAttachmentMap.put("TimelineEventId", this.eventId);
        createAttachmentMap.put("AttachmentId", this.attachmentId);
        createAttachmentMap.put("Title", this.title);

        String postData = ParameterStringBuilder.getParamsString(createAttachmentMap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/TimelineEventAttachment/Create",postData);

        createAndUploadAttachment(filepath);
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

    public static void uploadObject(URL url,String filename, String filepath) throws IOException {
        HttpURLConnection connection=(HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("PUT");
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());


        //Checks for the filetype and then uploads based on that, only accepts the three filetypes Ideagen gave
        try {
            if (filename.contains(".doc")) {
                FileInputStream fin = new FileInputStream(filepath);
                int i = 0;
                while ((i = fin.read()) != -1) {
                    out.write(i);
                }
                fin.close();
        }else if (filename.contains(".png") ){
                //Creates a bufferedimage from the filename and then writes to the URL(fuck this bit was annoying)
            BufferedImage image = ImageIO.read(new File(filepath));
                ImageIO.write(image, "png", out);
        }
        else if(filename.contains(".jpg") || filename.contains(".jpeg")){
                BufferedImage image = ImageIO.read(new File(filepath));
                ImageIO.write(image, "jpg", out);
            }
            //int[] temp = new int[fSize];
        }catch (Exception e ){System.out.println(e);}
        out.close();
        int responseCode = connection.getResponseCode();
        System.out.println("Service returned response code " + responseCode);
    }


    public void editAttachmentTitle(String Title) throws UnsupportedEncodingException{
        Map<String, String> createAttachMap = buildMap();
        createAttachMap.put("Title" , Title);
        //send the parameters to the ParameterStringBuilder utility class for formatting
        String postData = ParameterStringBuilder.getParamsString(createAttachMap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/TimelineEventAttachment/EditTitle", postData);

    }


    public void deleteAttachment(String Id) throws UnsupportedEncodingException {
        this.attachmentId = Id;
        Map<String, String> attachmap = buildMap();
        //send the parameters to the ParameterStringBuilder utility class for formatting
        String postData = ParameterStringBuilder.getParamsString(attachmap);
        //call the Put class method which requires the path (which api call you're executing and the postData itself
        Put.put("/TimelineEventAttachment/Delete",postData);
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
        else if(filePath.contains(".jpg") || filePath.contains(".jpeg")){
            BufferedImage image = ImageIO.read(connection.getInputStream());
            ImageIO.write(image, "jpg", new File(filePath));
        }

    }


    /*
    ----------------------------------------------------------------------------------------
    Getters and Setters:
    ----------------------------------------------------------------------------------------
     */

    //get eventId
    public String getId(){
        return this.eventId;
    }

    //set id
    public void seteventId(String eventId){
        this.eventId = eventId.replaceAll("\"","").replaceAll("\\+", " ");
    }

    //get attachmentId
    public String getAttachmentId(){
        return this.attachmentId;
    }

    //set attachmentId
    public void setAttachmentId(String attachmentId){
        this.attachmentId = attachmentId.replaceAll("\"","").replaceAll("\\+", " ");
    }

    //get title
    public String getTitle(){
        return this.title;
    }

    //set title
    public void setTitle(String title){
        this.title = title.replaceAll("\"","").replaceAll("\\+", " ");
    }

    public void setHref(String href){
        this.href = href;
    }

    public String getHref(){
        return this.href;
    }


    /*
    ----------------------------------------------------------------------------------------
    Overrides
    ----------------------------------------------------------------------------------------
     */

    @Override
    public String toString(){
        String str = "{" + this.eventId + " , " + this.attachmentId + ", " + this.title + "}";
        return str;
    }

}
