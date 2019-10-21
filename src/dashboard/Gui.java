package dashboard;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTextPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;

public class Gui extends ApplicationFrame {

	private JFrame frame;

	private Thread t;
	

	/** The time series data. */
   // private TimeSeries series;
    /** The most recent value added. */
    private double lastValue = 100.0;
    private XYSeries series = new XYSeries("Temperature ");
    private XYSeries series1 = new XYSeries("Pressure ");
    private XYSeries series2 = new XYSeries("Humidity ");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui("Factory of the Future");
					window.pack();
					RefineryUtilities.centerFrameOnScreen(window);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	//@SuppressWarnings("deprecation")
	public Gui(final String title) {
		super(title);
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws InterruptedException 
	 */
	//@SuppressWarnings("deprecation")
	private void initialize()  {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 998, 668);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 153, 375);
		frame.getContentPane().add(panel);
		
		JPanel Charts = new JPanel();
		Charts.setBounds(175, 0, 811, 375);
		frame.getContentPane().add(Charts);
		Charts.setLayout(null);
		
		JLabel lblRealtimeCharts = new JLabel("Real-Time Charts");
		lblRealtimeCharts.setBounds(214, 11, 116, 15);
		Charts.add(lblRealtimeCharts);
		
		JPanel Chart_1 = new JPanel();
		Chart_1.setBounds(12, 38, 251, 263);
		Charts.add(Chart_1);
		/*Temperature_Chart temp=new Temperature_Chart();
		temp.Temperature(1, 43.6);
        Chart_1.add(temp.PanelChart);*/
		ReceiveDataFactory m1=new ReceiveDataFactory();  
		Thread t =new Thread(m1);  
		t.start();  
		Chart_1.add(ReceiveDataFactory.PanelTemp);
        
        JPanel Chart_2 = new JPanel();
        Chart_2.setBounds(275, 38, 256, 263);
        Charts.add(Chart_2);
        Chart_2.add(ReceiveDataFactory.PanelPress);
        
        JPanel Chart_3 = new JPanel();
        Chart_3.setLayout(null);
        Chart_3.setBounds(543, 38, 256, 263);
        Charts.add(Chart_3);
        Chart_3.add(ReceiveDataFactory.PanelHum);
       
        
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 374, 473, 217);
		frame.getContentPane().add(panel_2);
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(483, 374, 468, 217);
		frame.getContentPane().add(panel_3);
	}


	}

