/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.dsg.jbpmengine.task;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.apache.derby.iapi.services.locks.VirtualLockTable;

/**
 *
 * @author Anindita
 */
public class Wearning {
    private static final Wearning INSTANCE = new Wearning();

    public static Wearning getInstance() {
        return INSTANCE;
    }

    public void start(String tableName, String keySpaceName) {
        //visualize the waerning
        JOptionPane optionPane = new JOptionPane("There need the further checking of tablename="+tableName+" and keySpaceName="+keySpaceName, JOptionPane.ERROR_MESSAGE);    
        JDialog dialog = optionPane.createDialog("Failure");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
       
    }
}
