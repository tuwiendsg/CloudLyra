/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.dsg.esperstreamprocessing.utils;

import at.ac.tuwien.dsg.esperstreamprocessing.handler.EventHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Jun
 */
public class RestfulWSClient {
    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private String ip;
    private String port;
    private String resource;
    private String url;


    public RestfulWSClient(String ip, String port, String resource) {
        this.ip = ip;
        this.port = port;
        this.resource = resource;
        url = "http://" + ip + ":" + port + resource;
 
    }

    public RestfulWSClient(String url) {
        this.url = url;
    }
    
    
    

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
    public String callGetDirectURL(String xmlString){
        
        url += xmlString;
        String rs="";
        
        try {
            URL url_ac = new URL(url);
            
            // read text returned by server
            BufferedReader in=null;
            try {
                in = new BufferedReader(new InputStreamReader(url_ac.openStream()));
            } catch (IOException ex) {
                Logger.getLogger(RestfulWSClient.class.getName()).log(Level.SEVERE, null, ex);
            }

            String line;

            try {
                while ((line = in.readLine()) != null) {
                    
                    rs +=line;
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(RestfulWSClient.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(RestfulWSClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(RestfulWSClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return rs;
    }
    
    
    public String callGetMethod(String xmlString) {

      /*  try {
            xmlString = URLEncoder.encode(xmlString, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RestfulWSClient.class.getName()).log(Level.SEVERE, null, ex);
        }
              */
              
        url += xmlString;
        String getResult = "";
        try {

            Logger.getLogger(RestfulWSClient.class.getName()).log(Level.INFO,  "Connection .. " + url);
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "text/plain; charset=utf-8");
            request.addHeader("Accept", "text/plain, multipart/related");
            
           
         
            HttpResponse methodResponse = this.getHttpClient().execute(request);

            int statusCode = methodResponse.getStatusLine().getStatusCode();

            Logger.getLogger(RestfulWSClient.class.getName()).log(Level.INFO, "Status Code: " + statusCode);
      
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(methodResponse.getEntity().getContent()));

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            getResult = result.toString();
            // System.out.println("Response String: " + result.toString());
        } catch (Exception ex) {

        }
        return getResult;
    }

    public void callPutMethod(String xmlString) {

        try {
            

            //HttpGet method = new HttpGet(url);
            StringEntity inputKeyspace = new StringEntity(xmlString);
  
      
            Logger.getLogger(RestfulWSClient.class.getName()).log(Level.INFO,  "Connection .. " + url);
            HttpPut request = new HttpPut(url);
            request.addHeader("content-type", "application/xml; charset=utf-8");
            request.addHeader("Accept", "application/xml, multipart/related");
            request.setEntity(inputKeyspace);

            HttpResponse methodResponse = this.getHttpClient().execute(request);

            int statusCode = methodResponse.getStatusLine().getStatusCode();

            Logger.getLogger(RestfulWSClient.class.getName()).log(Level.INFO, "Status Code: " + statusCode);
      
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(methodResponse.getEntity().getContent()));

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            // System.out.println("Response String: " + result.toString());
        } catch (Exception ex) {

        }

    }
    
    
    public void callPostMethod(String xmlString) {

        try {
            

            //HttpGet method = new HttpGet(url);
            StringEntity inputKeyspace = new StringEntity(xmlString);
            System.out.println("Connection .. " + url);
            //HttpPut request = new HttpPut(url);
            
            HttpPost request=new HttpPost(url);
     
            request.addHeader("content-type", "application/json; charset=utf-8");
           // request.addHeader("Accept", "application/json, multipart/related");
            request.setEntity(inputKeyspace);

            HttpResponse methodResponse = this.getHttpClient().execute(request);

            int statusCode = methodResponse.getStatusLine().getStatusCode();

            System.out.println("Status Code: " + statusCode);
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(methodResponse.getEntity().getContent()));

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            // System.out.println("Response String: " + result.toString());
        } catch (Exception ex) {

        }

    }
    
    
}
