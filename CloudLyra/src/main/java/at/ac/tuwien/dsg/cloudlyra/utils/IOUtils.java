/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.dsg.cloudlyra.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Jun
 */
public class IOUtils {

    public static void writeData(String data, String fileName) {

        String tomcatTempFolder = System.getProperty("java.io.tmpdir");
        //String tomcatTempFolder="/Volumes/DATA/BigData";
        fileName =  tomcatTempFolder +"/" + fileName;
        FileWriter fstream;
        try {
            fstream = new FileWriter(fileName, false);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(data);

            out.close();
        } catch (IOException ex) {
        }
    }

    public static String readData(String fileName) {

        String tomcatTempFolder = System.getProperty("java.io.tmpdir");
        //String tomcatTempFolder="/Volumes/DATA/BigData";
        fileName =  tomcatTempFolder +"/" + fileName;
        String data = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            data = sb.toString();
            br.close();
        } catch (Exception e) {

        }
        return data;
    }
    
    public static void cleanTempData(){
        String tomcatTempFolder = System.getProperty("java.io.tmpdir");
        File dir = new File(tomcatTempFolder);
        try { 
            FileUtils.cleanDirectory(dir);
        } catch (IOException ex) {
            Logger.getLogger(IOUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
