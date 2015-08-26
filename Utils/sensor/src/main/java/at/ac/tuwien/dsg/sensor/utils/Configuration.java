/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.dsg.sensor.utils;

/**
 *
 * @author Jun
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Configuration {

    public Configuration() {
    }

    public static String getConfig(String configureName) {

        
        Properties prop = new Properties();
        String configString = "";
        InputStream input = null;

        try {

            input = new FileInputStream("config/config.properties");

            // load a properties file
            prop.load(input);

            switch (configureName) {
                case "IP":
                    configString = prop.getProperty("IP");
                    break;
                case "PORT":
                    configString = prop.getProperty("PORT");
                    break;
                case "RESOURCE":
                    configString = prop.getProperty("RESOURCE");
                    break;
              

            }

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
