package dashboard;

import java.awt.Color;
import java.io.*;
import java.net.*;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class ReceiveDataFactory implements Runnable {
	
	public static ChartPanel PanelTemp = null;
	public static ChartPanel PanelHum = null;
	public static ChartPanel PanelPress = null;
	//Temperature_Chart temp=new Temperature_Chart();
	public double temperature;
	 public int factory_id;
	 public double pressure;
	 public double humidity;
	 public static XYSeries[] series_temp = new XYSeries[7];
	 public static XYSeries[] series_hum = new XYSeries[7];
	 public static XYSeries[] series_press = new XYSeries[7];
	 public static XYDataset[] data1=new XYDataset[7];
	 public static XYDataset[] data2=new XYDataset[7];
	 public static XYDataset[] data3=new XYDataset[7];
	 
	 XYLineAndShapeRenderer [] r = new XYLineAndShapeRenderer[7];
	 
	public ReceiveDataFactory()  
    { 
		for (int i=1;i<=7;i++) {
    		series_temp[i-1] = new XYSeries("Factory"+i);
    		data1[i-1] = new XYSeriesCollection(series_temp[i-1]);
    		series_hum[i-1] = new XYSeries("Factory"+i);
    		data3[i-1] = new XYSeriesCollection(series_hum[i-1]);
    		series_press[i-1] = new XYSeries("Factory"+i);
    		data2[i-1] = new XYSeriesCollection(series_press[i-1]);
    		
    	}
    	JFreeChart chart1 = ChartFactory.createXYLineChart("Temperature graph", "Time",
			    "Temperature (deg C)", null, PlotOrientation.VERTICAL, true, true, true);
		JFreeChart chart2 = ChartFactory.createXYLineChart("Pressure graph", "Time",
			    "Pressure (Pa)", null, PlotOrientation.VERTICAL, true, true, true);
		JFreeChart chart3 = ChartFactory.createXYLineChart("Humidity graph", "Time",
			    "Humidity (%)",null, PlotOrientation.VERTICAL, true, true, true);
		
		chart1.getXYPlot().getRenderer().setSeriesPaint(0, Color.blue);
		final XYPlot plot1 = chart1.getXYPlot();
		chart2.getXYPlot().getRenderer().setSeriesPaint(0, Color.blue);
		final XYPlot plot2 = chart2.getXYPlot();
		chart3.getXYPlot().getRenderer().setSeriesPaint(0, Color.blue);
		final XYPlot plot3 = chart3.getXYPlot();
		
		/*create a for loop 
		 * */
		for (int i =0;i<7;i++)
			r[i] = new XYLineAndShapeRenderer();
		
		
		r[0].setSeriesPaint(0, new Color(0xFF, 0xFF, 0x00)); //yellow
		r[1].setSeriesPaint(0, new Color(0x0C, 0xAF, 0x37)); //0CAF37-GREEN
		r[2].setSeriesPaint(0, new Color(0xff, 0x11, 0x00)); //FF1100 - RED
		r[3].setSeriesPaint(0, new Color(0xCC, 0x00, 0x66)); //CC0066 -PINK
		r[4].setSeriesPaint(0, new Color(0x00, 0x4C, 0x99)); //004C99 -blue
		r[5].setSeriesPaint(0, new Color(0x33, 0xFF, 0xFF)); //33FFFF -light blue
		r[6].setSeriesPaint(0, new Color(0xCC, 0x66, 0x00)); //CC6600 - brown
		
		for (int i=0;i<7;i++) {
			plot1.setDataset(i, data1[i]);
			plot1.setRenderer(i, r[i]);
			plot2.setDataset(i, data2[i]);
			plot2.setRenderer(i, r[i]);
			plot3.setDataset(i, data3[i]);
			plot3.setRenderer(i, r[i]);
		}
		PanelTemp=new ChartPanel(chart1);
		PanelTemp.setBounds(0, 0, 256, 263);
		PanelTemp.setPreferredSize(new java.awt.Dimension(254, 256));
		PanelHum=new ChartPanel(chart2);
		PanelHum.setBounds(0, 0, 256, 263);
		PanelHum.setPreferredSize(new java.awt.Dimension(254, 256));
		PanelPress=new ChartPanel(chart3);
		PanelPress.setBounds(0, 0, 256, 263);
		PanelPress.setPreferredSize(new java.awt.Dimension(254, 256));
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Server Started: "); 

        // server is listening on port 5056 
        ServerSocket ss = null;
		try {
			ss = new ServerSocket(5056);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
          
        // running infinite loop for getting 
        // client request 
        while (true)  
        { 
            Socket s = null; 
              
            try 
            { 
                // socket object to receive incoming client requests 
                s = ss.accept(); 
                  
                System.out.println("A new client is connected : " + s); 
                  
                // obtaining input and out streams 
                DataInputStream dis = new DataInputStream(s.getInputStream()); 
                DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
                  
                System.out.println("Assigning new thread for this client"); 
  
                // create a new thread object 
                Thread t = new ClientHandler(s, dis, dos); 
                //Thread t1 = new Thread(new MyClass ());
                //t1.start();
                // Invoking the start() method 
                t.start(); 
                  
            } 
            catch (Exception e){ 
                try {
					s.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
                e.printStackTrace(); 
            } 
        }
	}
	
	
}

