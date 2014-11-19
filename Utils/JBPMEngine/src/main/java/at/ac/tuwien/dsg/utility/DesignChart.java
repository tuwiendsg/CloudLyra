/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.dsg.utility;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import org.jfree.chart.*;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Anindita
 */
public class DesignChart {
   
    
   public void chart(LinkedList<String> xValue, LinkedList<String> yValue) throws Exception
    {
        
     XYSeries series = new XYSeries("Sensory Data");
     final JFreeChart chart;
   
    //data assignment in the chart
     {
     for(int i=0;i<xValue.size();i++) 
     {
       series.add(Double.parseDouble(xValue.get(i)), Double.parseDouble(yValue.get(i)));
      }
    
       final XYSeriesCollection data = new XYSeriesCollection(series);
       chart = ChartFactory.createXYLineChart(
          "Graph Visualization",
          "collection_time", 
          "collection_data", 
          data,
          PlotOrientation.VERTICAL,
          true,
          true,
          false
        );
      }
     
       //design the plot of the chart
       {
        XYPlot xyPlot = chart.getXYPlot();
        NumberAxis xAxis = (NumberAxis)xyPlot.getDomainAxis();
        NumberAxis yAxis = (NumberAxis)xyPlot.getRangeAxis();

        xAxis.setRange(20, 120);
        xAxis.setTickUnit(new NumberTickUnit(15));  
        yAxis.setRange(947, 950);
        yAxis.setTickUnit(new NumberTickUnit(0.5));
       }
   
      //generation of the image  
       {
        BufferedImage img=chart.createBufferedImage( 300, 200);
        File outputfile = new File("./example/Sample.png");
        ImageIO.write(img, "png", outputfile);
       }
    
    }

    }

