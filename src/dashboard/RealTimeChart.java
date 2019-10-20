package dashboard;


import java.awt.Color;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class RealTimeChart {

 public static void main(String[] args) throws Exception {
  XYSeries series = new XYSeries("Real time graph");
  XYDataset data = new XYSeriesCollection(series);
  JFreeChart chart = ChartFactory.createXYLineChart("Real time graph", "x-series",
    "y-series", data, PlotOrientation.VERTICAL, true, true, true);
  chart.getXYPlot().getRenderer().setSeriesPaint(0, Color.blue);
  ChartFrame frame = new ChartFrame("RealTimeChart", chart);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.pack();
  frame.setVisible(true);
  for (int i = 0; i < 1000;) {
   Thread.sleep(5000);
   series.add(i++, Math.random() * 1000);
   series.add(i++, Math.random() * 1000);
   series.add(i++, Math.random() * 1000);
   series.add(i++, Math.random() * 1000);
   series.add(i++, Math.random() * 1000);
   chart = ChartFactory.createXYLineChart("Real time graph", "x-series", "y-series",
     data, PlotOrientation.VERTICAL, true, true, true);
   frame = new ChartFrame("Real time graph", chart);
  }
 }

}