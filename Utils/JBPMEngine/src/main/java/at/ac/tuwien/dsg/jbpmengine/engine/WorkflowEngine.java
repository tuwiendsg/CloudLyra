/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.dsg.jbpmengine.engine;

/**
 *
 * @author Anindita
 */

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.api.io.ResourceType;
import org.kie.internal.logger.KnowledgeRuntimeLogger;
import org.kie.internal.logger.KnowledgeRuntimeLoggerFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

public class WorkflowEngine {

    private String daw;
    private String tableName;
    private String keySpaceName;
    private String condition;
    private String columnX;
    private String columnY;
    
    public WorkflowEngine(String dawdes) {
        StringTokenizer st=new StringTokenizer(dawdes,",");
        while (st.hasMoreTokens()) {
            this.daw=st.nextToken();
            this.tableName=st.nextToken();
            this.keySpaceName=st.nextToken();
            this.condition=st.nextToken();
            this.columnX=st.nextToken();
            this.columnY=st.nextToken();
            
            
        }
        
    }

    public String getDaw() {
        
        return daw;
    }

    public void setDaw(String daw) {
        this.daw = daw;
    }
    
    

    public void startWFEngine() {

        try {
            // load up the knowledge base
            KnowledgeBase kbase = readKnowledgeBase();
           StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
           KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newThreadedFileLogger(ksession, "test", 1000);
            
            
            System.out.println("daw="+daw);
            System.out.println("tableName="+tableName);
            System.out.println("keySpaceName="+keySpaceName);
            System.out.println("condition="+condition);
            System.out.println("columnX="+columnX);
            System.out.println("columnY="+columnY);
            // specify the process variable
             Map<String, Object> params = new HashMap<String, Object>();
             params.put("exist", false);
             params.put("tableName",tableName);
             params.put("keySpaceName", keySpaceName);
             params.put("condition", condition);
             params.put("columnX", columnX);
             params.put("columnY", columnY);
             
             
             
             
             ksession.startProcess("ac.at.tuwien.dsg.daw1", params);
            
             logger.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }
         //pass the bpmn workflow diagram
    private KnowledgeBase readKnowledgeBase() throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource(daw+".bpmn"), ResourceType.BPMN2);
        return kbuilder.newKnowledgeBase();
        
       
    }
    
   

}
