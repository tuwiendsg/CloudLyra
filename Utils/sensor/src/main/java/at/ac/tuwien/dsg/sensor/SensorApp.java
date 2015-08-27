/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.dsg.sensor;

import at.ac.tuwien.dsg.sensor.utils.Configuration;
import java.io.IOException;

/**
 *
 * @author Jun
 */
public class SensorApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        
        String filePath = "data/methane";
        String attrs ="12,13,14,15,16";
        String latency ="3000";
      
        String ip = "128.130.172.230";
        String port = "9124";
        String momQueue = "QUEUE1";
        
        
        CSVLoader loader = new  CSVLoader(ip, port, Integer.parseInt(latency),momQueue);
        
        loader.produce(filePath,attrs);
        
        
    }
    
}
