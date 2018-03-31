package util;

import java.io.*;
import java.util.Arrays;

public class FileParser {

    public FileParser(){}

    public void fileParser(String inputString, String ext) throws IOException {

        String fileExtension = ".";
        fileExtension = (fileExtension + ext.substring(ext.lastIndexOf(".") + 1));
        System.out.println(fileExtension);

        StringBuffer sbf = new StringBuffer();
        sbf.append(inputString);
        String dest = "new_file" + fileExtension;

        BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(dest)));

        //write contents of StringBuffer to a file
        bwr.write(sbf.toString());

        //flush the stream
        bwr.flush();

        //close the stream
        bwr.close();

        System.out.println("Content of StringBuffer written to File.");


    }
}
