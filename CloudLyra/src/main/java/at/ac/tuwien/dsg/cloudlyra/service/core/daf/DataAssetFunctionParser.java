/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.dsg.cloudlyra.service.core.daf;


import at.ac.tuwien.dsg.cloudlyra.entity.daf.complexeventprocessing.DataAnalyticFunctionCep;
import at.ac.tuwien.dsg.cloudlyra.utils.JAXBUtils;


/**
 *
 * @author Jun
 */

public class DataAssetFunctionParser {
    
    public static DataAnalyticFunctionCep loadDataAnalyticFunctionCep(String daf) {
        
        DataAnalyticFunctionCep dataAnalyticFunction =null;
                   
        try {
            dataAnalyticFunction = JAXBUtils.unmarshal(daf, DataAnalyticFunctionCep.class);
        } catch (Exception e) {

        }
 
        return dataAnalyticFunction;
    }
    
}
