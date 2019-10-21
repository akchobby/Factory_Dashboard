package dashboard;
import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
public final class Temperature_Chart implements Runnable{
	 
	 public ChartPanel PanelChart = null;
	 public XYSeries series;
	 public double temperature = 0;
	 public int factory_id;
	 public void Temperature(int factory_ID, double temperature) {
		// TODO Auto-generated constructor stub
		 this.factory_id=factory_ID;
		this.temperature=temperature;
		this.series = new XYSeries("Factory"+factory_ID);
		XYDataset data = new XYSeriesCollection(this.series);
		JFreeChart chart = ChartFactory.createXYLineChart("Temperature graph", "Time",
		    "Temperature (deg C)", data, PlotOrientation.VERTICAL, true, true, true);
		chart.getXYPlot().getRenderer().setSeriesPaint(0, Color.blue);
		final XYPlot plot = chart.getXYPlot();
        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        axis.setFixedAutoRange(60000.0);  // 60 seconds
        axis = plot.getRangeAxis();
        axis.setRange(0.0, 200.0); 
         
        //Chart_1.setLayout(null);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(0, 0, 256, 263);
        chartPanel.setPreferredSize(new java.awt.Dimension(254, 256));
        PanelChart=chartPanel;
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
}
