/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.dsg.cloudlyra.service.engine.ext;

import static at.ac.tuwien.dsg.cloudlyra.configuration.Configuration.getConfig;
import at.ac.tuwien.dsg.cloudlyra.service.core.registry.UtilServiceRegistry;
import at.ac.tuwien.dsg.cloudlyra.utils.MySqlConnectionManager;
import java.sql.ResultSet;

/**
 *
 * @author Jun
 */
public class AnalyticEngineManager {
    public static AnalyticEngineConfiguration getAnalyticEngineConfiguration(String analyticEngineID){
        
        System.out.println("Analytic Engine ID: " + analyticEngineID);
        
        AnalyticEngineConfiguration analyticEngineConfiguration=null;
        String ip = getConfig("DB.CLOUDLYRA.IP");
        String port = getConfig("DB.CLOUDLYRA.PORT");
        String database = getConfig("DB.CLOUDLYRA.DATABASE");
        String username = getConfig("DB.CLOUDLYRA.USERNAME");
        String password = getConfig("DB.CLOUDLYRA.PASSWORD");
        
        UtilServiceRegistry utilServiceRegistry = new UtilServiceRegistry();    
        String sql = "Select * from AnalyticEngine where analyticEngineID='"+analyticEngineID+"'";
        
        ResultSet rs = utilServiceRegistry.executeQueryMySQL(ip, port, database, username, password, sql);

        try {
            while (rs.next()) {
                String analyticEngineName = rs.getString("analyticEngineName");
                String analyticEngineIp = rs.getString("ip");
                String analyticEnginePort = rs.getString("port");
                String analyticEngineApi = rs.getString("api");
                analyticEngineConfiguration = new AnalyticEngineConfiguration(analyticEngineID, analyticEngineName, analyticEngineIp, analyticEnginePort, analyticEngineApi);
            
            }

        } catch (Exception ex) {

        }
        
        
        return  analyticEngineConfiguration;
    }
}
