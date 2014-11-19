/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.dsg.jbpmengine.task;

import at.ac.tuwien.dsg.utility.DesignChart;
import at.ac.tuwien.dsg.utility.CassandraConnection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Anindita
 */
public class Visualization {
    private static final Visualization INSTANCE = new Visualization();
    CassandraConnection client=new CassandraConnection();
    
    String ipAddress="localhost";
    int port=9042;

    public static Visualization getInstance() {
        return INSTANCE;
    }

    public void start(String keySpaceName, String tableName, String x, String y, String condition) {
        
        // retriving the column data using cassandra 
        client.connect(ipAddress, port);
        String xQuery= "SELECT "+x+" FROM "+keySpaceName+"."+tableName+" WHERE "+condition+";";
        LinkedList<String> xValue=client.readAll(xQuery);
        String yQuery= "SELECT "+y+" FROM "+keySpaceName+"."+tableName+" WHERE "+condition+";";
        LinkedList<String> yValue=client.readAll(yQuery);
        
        // visualize data in chart
        DesignChart chart12=new DesignChart();
        try {
            chart12.chart(xValue, yValue);
            
        } catch (Exception ex) {
            Logger.getLogger(Visualization.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         client.close();
    }
}
