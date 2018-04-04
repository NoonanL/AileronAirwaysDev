package util;



import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;


public class FileParser {

    public FileParser(){}

    public void fileParser(String inputString, String ext) throws IOException {

        String fileExtension = ".";
        //System.out.println(inputString);
        //System.out.println(ext);
        fileExtension = (fileExtension + ext.substring(ext.lastIndexOf(".") + 1));
        //System.out.println(fileExtension);
        StringBuffer sbf = new StringBuffer();
        sbf.append(inputString);
        String dest = "new_file" + fileExtension;

        //Checks for filetype of the new path some error checking might be good here for conflicting types
        if (fileExtension.equals(".doc") || fileExtension.equals(".txt")) {

            BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(dest)));

            //write contents of StringBuffer to a file
            bwr.write(sbf.toString());

            //flush the stream
            bwr.flush();

            //close the stream
            bwr.close();
        }
        else if (fileExtension.equals(".png")){
            InputStream stream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));
            System.out.println(stream.toString());
            BufferedImage image = ImageIO.read(stream);
            ImageIO.write(image, "jpg", new File(dest));
        }
        else if(fileExtension.equals(".jpg")|| fileExtension.equals(".jpeg")){
            InputStream stream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));
            System.out.println(stream.toString());
            BufferedImage image = ImageIO.read(stream);
            ImageIO.write(image, "jpg", new File(dest));
            }



        System.out.println("Content of StringBuffer written to File.");


    }
}
