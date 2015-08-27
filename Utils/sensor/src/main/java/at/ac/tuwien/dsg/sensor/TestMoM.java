/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.dsg.sensor;

import at.ac.tuwien.dsg.sensor.utils.Configuration;

/**
 *
 * @author Jun
 */
public class TestMoM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String ip = "128.130.172.230";
        String port = "9124";
        String momQueue = "QUEUE1";
       QueueClient qc = new QueueClient(ip, port, momQueue);
       
       
   //    QueueClient qc = new QueueClient("128.130.172.230", "9124", "QUEUE1");
       qc.start();
       
        
        
    }
    
}
