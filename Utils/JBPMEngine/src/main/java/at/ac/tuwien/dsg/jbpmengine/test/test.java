/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.dsg.jbpmengine.test;

import at.ac.tuwien.dsg.jbpmengine.engine.WorkflowEngine;



/**
 *
 * @author Jun
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //workflow description
        String daw = "daw5,Sensor11,sensor,collection_date = '2010/12/10',collection_time,collection_data";
        
        
        
        
        
        WorkflowEngine wf = new WorkflowEngine(daw);
        wf.startWFEngine();
    }
    
}
