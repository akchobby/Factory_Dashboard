package dashboard;

import java.util.Random;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ChartFrame extends JFrame {
	private static final int MAX_ITERATION = 100000000;
	private static final int FPS = 25;
	private final int REFRESH_RATE = 2000/FPS;
	private XYSeries minFitSerie;
	private XYSeries avgFitSerie;
	private XYSeries maxFitSerie;
	
	private int N = 50;
	private double[] minFitVal = new double[N];
	private double[] maxFitVal = new double[N];
	private double[] avgFitVal = new double[N];
	
	private long lastTime;
	
	private Random rand = new Random();
	
	public ChartFrame() {
		super("Chart");
		setSize(1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(makeChart());
		setVisible(true);
		lastTime = System.nanoTime();
		
		run();
	}
	
	public void run() {
		for(int i = 0; i < MAX_ITERATION; i++) {
			//alg.runIteration(); here complex calculations
			generateSeries();
			long currentTime = System.nanoTime();
			long elapsed = currentTime - lastTime;
			elapsed /= 1000000;
			if(elapsed < REFRESH_RATE) continue;
			lastTime = currentTime;
			repaint();
		}
	}
	
	private void generateSeries() {
		minFitSerie.clear();
		maxFitSerie.clear();
		avgFitSerie.clear();
		
		for(int i = 0; i < N - 1; i++) {
			minFitVal[i] = minFitVal[i+1];
			maxFitVal[i] = maxFitVal[i+1];
			avgFitVal[i] = avgFitVal[i+1];
			minFitSerie.add(i, minFitVal[i]);
			maxFitSerie.add(i, maxFitVal[i]);
			avgFitSerie.add(i, avgFitVal[i]);
		}
		int i = N - 1;
		double v1 = rand.nextDouble()*10 + 180;
		double v2 = rand.nextDouble()*10 + 190;
		double v3 = (v1 + v2)/2;
		minFitVal[i] = v1;
		maxFitVal[i] = v2;
		avgFitVal[i] = v3;
		minFitSerie.add(i, minFitVal[i]);
		maxFitSerie.add(i, maxFitVal[i]);
		avgFitSerie.add(i, avgFitVal[i]);
	}
	
	ChartPanel makeChart() {
		minFitSerie = new XYSeries("min"); 
		maxFitSerie = new XYSeries("max"); 
		avgFitSerie = new XYSeries("avg");
		XYDataset xyDatasetMin = new XYSeriesCollection(minFitSerie);
		XYDataset xyDatasetMax = new XYSeriesCollection(maxFitSerie);
		XYDataset xyDatasetAvg = new XYSeriesCollection(avgFitSerie);
		
		JFreeChart chart = ChartFactory.createXYLineChart("Population overview", "Population number", "Fitness",
				null, PlotOrientation.VERTICAL, true, true, false);
		chart.setBackgroundImageAlpha(0.1f);
		XYPlot plot = chart.getXYPlot();

		
		
		plot.setDataset(0, xyDatasetMin);
		plot.setRenderer(0, new StandardXYItemRenderer());
		plot.setDataset(1, xyDatasetMax);
		plot.setRenderer(1, new StandardXYItemRenderer());
		plot.setDataset(2, xyDatasetAvg);
		plot.setRenderer(2, new StandardXYItemRenderer());
		return new ChartPanel(chart);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChartFrame frame = new ChartFrame();
	}

}
