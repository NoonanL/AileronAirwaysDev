package util;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;



public class FileParser {

    public FileParser(){}

    public void fileParser(String inputString, String ext) throws IOException {

        String fileExtension = ".";
        fileExtension = (fileExtension + ext.substring(ext.lastIndexOf(".") + 1));
        System.out.println(fileExtension);
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
            InputStream stream = IOUtils.toInputStream(inputString, "UTF-8");
            BufferedImage image = ImageIO.read(stream);
            ImageIO.write(image, "png", new File(dest));
        }
        else if(fileExtension.equals(".jpg")|| fileExtension.equals(".jpeg")){
            OutputStream outputStream = null;
            byte [] imageInByteArray = Base64.decodeBase64(
                    inputString);
            try {
                outputStream = new FileOutputStream("new_file.jpeg");
                outputStream.write(imageInByteArray);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }



        System.out.println("Content of StringBuffer written to File.");


    }
}
