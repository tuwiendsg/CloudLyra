/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.dsg.esperstreamprocessing.utils;

/**
 *
 * @author Jun
 */


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Configuration {

    public Configuration() {
    }

    public static String getConfiguration(String configureName) {

        String path = Configuration.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        int index = path.indexOf("/classes/at/ac");
        path = path.substring(0, index);
        try {
            path = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Logger.getLogger(Configuration.class.getName()).log(Level.INFO,"Configure Path: " + path);
        
        Properties prop = new Properties();
        String configString = "";
        InputStream input = null;

        try {

            input = new FileInputStream(path + "/config.properties");
            prop.load(input);
            configString = prop.getProperty(configureName);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return configString;
    }
 
    


}
