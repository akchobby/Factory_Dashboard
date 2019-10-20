package dashboard;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class Gui extends ApplicationFrame implements Runnable{

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
		Charts.setBounds(163, 0, 788, 375);
		frame.getContentPane().add(Charts);
		Charts.setLayout(null);
		
		JLabel lblRealtimeCharts = new JLabel("Real-Time Charts");
		lblRealtimeCharts.setBounds(214, 11, 116, 15);
		Charts.add(lblRealtimeCharts);
		
		JPanel Chart_1 = new JPanel();
		Chart_1.setBounds(12, 38, 239, 263);
		Charts.add(Chart_1);
		
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
        Chart_1.setLayout(null);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(0, 0, 239, 264);
        chartPanel.setPreferredSize(new java.awt.Dimension(239, 200));
        Chart_1.add(chartPanel);
        
        JPanel Chart_2 = new JPanel();
        Chart_2.setBounds(263, 38, 256, 263);
        Charts.add(Chart_2);
        
        //ChartPanel chartPanel_1 = new ChartPanel((JFreeChart) null);
        //Chart_2.add(chartPanel_1);
        XYDataset data1 = new XYSeriesCollection(this.series1);
		JFreeChart chart1 = ChartFactory.createXYLineChart("Pressure graph", "Time",
		    "Temperature (deg C)", data1, PlotOrientation.VERTICAL, true, true, true);
		chart1.getXYPlot().getRenderer().setSeriesPaint(0, Color.blue);
		final XYPlot plot1 = chart1.getXYPlot();
        ValueAxis axis1 = plot1.getDomainAxis();
        axis1.setAutoRange(true);
        axis1.setFixedAutoRange(60000.0);  // 60 seconds
        axis1 = plot.getRangeAxis();
        axis1.setRange(0.0, 200.0);
        Chart_2.setLayout(null);
        ChartPanel chartPanel_1 = new ChartPanel(chart1);
        chartPanel_1.setBounds(0, 0, 256, 265);
        chartPanel_1.setPreferredSize(new java.awt.Dimension(256, 200));
        Chart_2.add(chartPanel_1);
        
        JPanel Chart_3 = new JPanel();
        Chart_3.setLayout(null);
        Chart_3.setBounds(532, 38, 256, 263);
        Charts.add(Chart_3);
        XYDataset data2 = new XYSeriesCollection(this.series2);
		JFreeChart chart2 = ChartFactory.createXYLineChart("Humidity graph", "Time",
		    "Temperature (deg C)", data2, PlotOrientation.VERTICAL, true, true, true);
		chart2.getXYPlot().getRenderer().setSeriesPaint(0, Color.blue);
		final XYPlot plot2 = chart1.getXYPlot();
        ValueAxis axis2 = plot2.getDomainAxis();
        axis2.setAutoRange(true);
        axis2.setFixedAutoRange(60000.0);  // 60 seconds
        axis2 = plot2.getRangeAxis();
        axis2.setRange(0.0, 200.0);
        Chart_3.setLayout(null);
        ChartPanel chartPanel_2 = new ChartPanel(chart2);
        chartPanel_2.setBounds(0, 0, 256, 265);
        chartPanel_2.setPreferredSize(new java.awt.Dimension(256, 200));
        Chart_3.add(chartPanel_2);
       
        
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 374, 473, 217);
		frame.getContentPane().add(panel_2);
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(483, 374, 468, 217);
		frame.getContentPane().add(panel_3);
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for (;;)
        {
			int i=0;
        	try {
				Thread.sleep(100);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	/*final double factor = 0.90 + 0.2 * Math.random();
            
            this.lastValue = this.lastValue * factor;
            final Millisecond now = new Millisecond();
            System.out.println("Now = " + now.toString());
            this.series.add(new Millisecond(), this.lastValue);*/
        	this.series.add( i++, Math.random() * 1000);
            }
        }
	}

