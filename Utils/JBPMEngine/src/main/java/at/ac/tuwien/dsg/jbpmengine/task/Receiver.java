package at.ac.tuwien.dsg.jbpmengine.task;

import at.ac.tuwien.dsg.utility.CassandraConnection;

/**
 *
 * @author Anindita
 */
public class Receiver {
    private static final Receiver INSTANCE = new Receiver();
    CassandraConnection client=new CassandraConnection();
    
    Boolean exist=false;
    
    String ipAddress="localhost";
    int port=9042;

    public static Receiver getInstance() {
        return INSTANCE;
    }
    
   
    public Boolean start(String tableName, String keySpaceName) {
       
       client.connect(ipAddress, port);
       String tableQuery="SELECT columnfamily_name FROM system.schema_columnfamilies WHERE keyspace_name='"+keySpaceName+"';";
       exist=client.getTableNotification(tableQuery, tableName); 
       client.close();
       return exist;
        
    }
    
}
