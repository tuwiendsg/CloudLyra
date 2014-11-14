/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.dsg.cloudlyra.service.core.dafstore;

import static at.ac.tuwien.dsg.cloudlyra.configuration.Configuration.getConfig;
import at.ac.tuwien.dsg.cloudlyra.entity.daf.complexeventprocessing.DafAnalyticCep;
import at.ac.tuwien.dsg.cloudlyra.entity.daf.complexeventprocessing.DataAnalyticFunctionCep;
import at.ac.tuwien.dsg.cloudlyra.utils.JAXBUtils;
import at.ac.tuwien.dsg.cloudlyra.utils.MySqlConnectionManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Jun
 */
public class DafStore {
    
    String ip;
    String port;
    String database;
    String username;
    String password;

    public DafStore() {
        this.ip = getConfig("DB.CLOUDLYRA.IP");
        this.port = getConfig("DB.CLOUDLYRA.PORT");
        this.database = getConfig("DB.CLOUDLYRA.DATABASE");
        this.username = getConfig("DB.CLOUDLYRA.USERNAME");
        this.password = getConfig("DB.CLOUDLYRA.PASSWORD");
        
    }
    
    
    

    public ResultSet getDAF() {
        
        

        
        MySqlConnectionManager connectionManager = new MySqlConnectionManager(ip, port, database, username, password);

        String sql = "Select * from Daf";

        ResultSet rs = connectionManager.ExecuteQuery(sql);

        return rs;
    }
    
    public ResultSet getEvent() {
        

        
        MySqlConnectionManager connectionManager = new MySqlConnectionManager(ip, port, database, username, password);

        String sql = "Select * from Event WHERE 1 ORDER BY id DESC LIMIT 30";

        ResultSet rs = connectionManager.ExecuteQuery(sql);

        return rs;
    }

    public String getDafXML(String dafName) {

        String dafStr = "";
        try {
            InputStream inputStream = null;

            
            MySqlConnectionManager connectionManager = new MySqlConnectionManager(ip, port, database, username, password);

            String sql = "Select * from Daf where name='" + dafName + "'";

            ResultSet rs = connectionManager.ExecuteQuery(sql);

            try {
                while (rs.next()) {
                    inputStream = rs.getBinaryStream("file");
                }
            } catch (SQLException ex) {
                Logger.getLogger(DafStore.class.getName()).log(Level.SEVERE, null, ex);
            }

            StringWriter writer = new StringWriter();
            String encoding = StandardCharsets.UTF_8.name();

            IOUtils.copy(inputStream, writer, encoding);
            dafStr = writer.toString();
            Logger.getLogger(DafStore.class.getName()).log(Level.INFO, null, "DAF XML: " + dafStr);
                

        } catch (IOException ex) {
            Logger.getLogger(DafStore.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dafStr;
    }

    public void insertDAF(String dafName, String type, InputStream daf) {

        String ip = getConfig("DB.CLOUDLYRA.IP");
        String port = getConfig("DB.CLOUDLYRA.PORT");
        String database = getConfig("DB.CLOUDLYRA.DATABASE");
        String username = getConfig("DB.CLOUDLYRA.USERNAME");
        String password = getConfig("DB.CLOUDLYRA.PASSWORD");

        MySqlConnectionManager connectionManager = new MySqlConnectionManager(ip, port, database, username, password);

        String sql = "INSERT INTO Daf (name,type,status,file) VALUES ('" + dafName + "','" + type + "','stop',?)";
        connectionManager.ExecuteUpdateBlob(sql, daf);

    }

    public void deleteDAF(String dafName) {


        MySqlConnectionManager connectionManager = new MySqlConnectionManager(ip, port, database, username, password);

        String sql = "DELETE FROM Daf WHERE name='" + dafName + "'";

        connectionManager.ExecuteUpdate(sql);

    }

    public void updateDAF(String dafName, String status) {


        MySqlConnectionManager connectionManager = new MySqlConnectionManager(ip, port, database, username, password);

        String sql = "UPDATE Daf SET status='" + status + "' WHERE name='" + dafName + "'";

        connectionManager.ExecuteUpdate(sql);

    }

}
