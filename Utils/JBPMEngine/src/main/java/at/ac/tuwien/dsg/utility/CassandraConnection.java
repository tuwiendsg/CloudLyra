/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.dsg.utility;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author Anindita
 */
public class CassandraConnection {
    
   private Cluster cluster;
   private Session session;

   //connected with cassandra
   public void connect(final String node, final int port)
   {
      this.cluster = Cluster.builder().addContactPoint(node).withPort(port).build();
      final Metadata metadata = cluster.getMetadata();
      session = cluster.connect();
   }
   
   //read data from cassandra
    public LinkedList<String> readAll(String xQuery)
      {
          LinkedList<String> queryValue=new LinkedList<String>();
         
          try
          {
            ResultSet results=session.execute(xQuery);
            int i=0;
            for(Row row : results.all())
                {
                    StringTokenizer st=new StringTokenizer(row.toString(),"[]");
                    while(st.hasMoreTokens())
                    {
                        st.nextToken();
                        
                        queryValue.add(st.nextToken());
                    }
                    
                    System.out.println("value: "+row.toString());
                    i++;
                }
          }
          catch(Exception e)
          {
              System.out.println("does not exist="+e);
          }
          return queryValue;
      }
    
    /// check the existance of the table in a keyspace
    public Boolean getTableNotification(String tablequery, String tableName)
    {
        
        Boolean value=false;
           ResultSet result=session.execute(tablequery);
           int i=0;
           for(Row row : result.all())
                {
                    System.out.println("value: "+row.getString(i)+"   i="+i);
                    if(row.getString(i).equalsIgnoreCase(tableName))
                    {
                        value=true;
                        break;
                    }
                    else{
                        value=false;
                    }
                    
                    
                    i++;
                }
          return value;
           
    }
     // Close cluster. 
   public void close()
   {
       cluster.close();
      
   }
    
}
