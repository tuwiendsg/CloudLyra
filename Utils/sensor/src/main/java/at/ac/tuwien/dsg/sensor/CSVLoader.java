/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.dsg.sensor;

import at.ac.tuwien.dsg.sensor.utils.RestfulWSClient;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
/**
 *
 * @author Jun
 */
public class CSVLoader {

    String ip;
    String port;
    int latency;
    String url;
    String mon_queue;

    public CSVLoader(String ip, String port, int latency, String mon_queue) {
        this.ip = ip;
        this.port = port;
        this.latency = latency;
        this.mon_queue = mon_queue;
        configure();
    }

   

    public String readSensorDataTemplate() {
        String rs = "";
        FileInputStream fstream = null;
        try {

            fstream = new FileInputStream("template/template");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine = "";
            while ((strLine = br.readLine()) != null) {
                rs += strLine;

            }

            in.close();

        } catch (Exception ex) {

        }

        return rs;
    }

//    public void testData() {
//
//        String str = readSensorDataTemplate();
//        UUID uuid = UUID.randomUUID();
//        String sensorName = "sensorX";
//        String sensorValue = "3.12";
//
//        str = str.replaceAll("#key#", uuid.toString());
//        str = str.replaceAll("#name#", sensorName);
//        str = str.replaceAll("#value#", sensorValue);
//
//        System.out.println(str);
//
//        RestfulWSClient ws = new RestfulWSClient(ip, port, resource);
//        ws.callPutMethod(str);
//
//    }
    
    
    public void produce(String filePath, String attrs) throws FileNotFoundException, IOException {

        try {
            // Create a ConnectionFactory

            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue(mon_queue);

            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

              
        FileInputStream fstream = new FileInputStream(filePath);

        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String[] attrList = attrs.split(",");
        int operationLatency = (int) latency / attrList.length;
        String strLine;

        while ((strLine = br.readLine()) != null) {

            String[] str = strLine.split("\t");

            for (int i = 0; i < attrList.length; i++) {

                int attIndex = Integer.parseInt(attrList[i]);
                System.out.println(str[attIndex]);

                String sensorDataStr = readSensorDataTemplate();

                UUID uuid = UUID.randomUUID();
                String sensorName = "fcu_ff1_space_temp";
                String sensorValue = str[attIndex];

                sensorDataStr = sensorDataStr.replaceAll("#key#", uuid.toString());
                sensorDataStr = sensorDataStr.replaceAll("#name#", sensorName);
                sensorDataStr = sensorDataStr.replaceAll("#value#", sensorValue);
              
//                RestfulWSClient ws = new RestfulWSClient(ip, port, resource);
//                ws.callPutMethod(sensorDataStr);
                
                MapMessage message = session.createMapMessage();
                        message.setString("DataItem", sensorDataStr);

                        producer.send(message);

                try {
                    Thread.sleep(operationLatency);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CSVLoader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            System.out.println("---");
        }

        in.close();

                        


            session.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e);
        }


    }

    private void configure() {

        url = "tcp://" + ip + ":" + port;

    }

}
