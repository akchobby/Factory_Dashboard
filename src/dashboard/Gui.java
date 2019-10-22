package dashboard;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.border.MatteBorder;
import java.awt.Color;

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
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Checkbox;
import java.awt.Label;

public class Gui extends ApplicationFrame {
	protected JFrame frame;
	private JTextPane textPane;
	private FetchingData fetchingData;
	private FactoryTelecommand telecommand;
	private StyledDocument doc;
	private JCheckBox[] board_connectivity = new JCheckBox[7];

	private Thread t;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

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
		System.out.println("initializing GUI");
		initialize();
		//RefineryUtilities.centerFrameOnScreen(this);
		this.fetchingData = new FetchingData(this);
		this.telecommand = new FactoryTelecommand(this);
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
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(0, 82, 163, 293);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnScan = new JButton("Scan");
		btnScan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean[] res = fetchingData.fetchAvailableBoards();
				for (int i=0; i<7; i++) {
					board_connectivity[i].setSelected(res[i]);
				}
			}
		});
		btnScan.setBounds(6, 248, 141, 29);
		panel.add(btnScan);

		JLabel lblAvailableBoards = new JLabel("Available Boards");
		lblAvailableBoards.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblAvailableBoards.setBounds(17, 18, 130, 16);
		panel.add(lblAvailableBoards);

		JLabel lblFact = new JLabel("Fact_02");
		lblFact.setBounds(34, 78, 103, 16);
		panel.add(lblFact);

		JLabel label = new JLabel("Fact_01");
		label.setBounds(34, 50, 103, 16);
		panel.add(label);

		JLabel lblFact_1 = new JLabel("Fact_03");
		lblFact_1.setBounds(34, 106, 103, 16);
		panel.add(lblFact_1);

		JLabel label_2 = new JLabel("Fact_04");
		label_2.setBounds(34, 134, 103, 16);
		panel.add(label_2);

		JLabel label_3 = new JLabel("Fact_05");
		label_3.setBounds(34, 162, 103, 16);
		panel.add(label_3);

		JLabel label_4 = new JLabel("Fact_06");
		label_4.setBounds(34, 190, 103, 16);
		panel.add(label_4);

		JLabel label_5 = new JLabel("Fact_07");
		label_5.setBounds(34, 218, 103, 16);
		panel.add(label_5);

		board_connectivity[0] = new JCheckBox("");
		board_connectivity[0].setEnabled(false);
		board_connectivity[0].setBounds(6, 50, 26, 20);
		panel.add(board_connectivity[0]);

		board_connectivity[1] = new JCheckBox("");
		board_connectivity[1].setEnabled(false);
		board_connectivity[1].setBounds(6, 76, 26, 20);
		panel.add(board_connectivity[1]);

		board_connectivity[2] = new JCheckBox("");
		board_connectivity[2].setEnabled(false);
		board_connectivity[2].setBounds(6, 104, 26, 20);
		panel.add(board_connectivity[2]);

		board_connectivity[3] = new JCheckBox("");
		board_connectivity[3].setEnabled(false);
		board_connectivity[3].setBounds(6, 132, 26, 20);
		panel.add(board_connectivity[3]);

		board_connectivity[4] = new JCheckBox("");
		board_connectivity[4].setEnabled(false);
		board_connectivity[4].setBounds(6, 160, 26, 20);
		panel.add(board_connectivity[4]);

		board_connectivity[5] = new JCheckBox("");
		board_connectivity[5].setEnabled(false);
		board_connectivity[5].setBounds(6, 188, 26, 20);
		panel.add(board_connectivity[5]);

		board_connectivity[6] = new JCheckBox("");
		board_connectivity[6].setEnabled(false);
		board_connectivity[6].setBounds(6, 216, 26, 20);
		panel.add(board_connectivity[6]);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 0, 163, 83);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		panel_4.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));

		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.setHorizontalAlignment(SwingConstants.CENTER);
		lblDashboard.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblDashboard.setBounds(16, 20, 130, 16);
		panel_4.add(lblDashboard);

		JPanel Charts = new JPanel();
		Charts.setBounds(175, 6, 811, 363);
		frame.getContentPane().add(Charts);
		Charts.setLayout(null);

		JLabel lblRealtimeCharts = new JLabel("Real-Time Charts");
		lblRealtimeCharts.setHorizontalAlignment(SwingConstants.CENTER);
		lblRealtimeCharts.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRealtimeCharts.setBounds(275, 11, 256, 15);
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
		
		JPanel Prediction = new JPanel();
		Prediction.setBounds(12, 308, 787, 55);
		Charts.add(Prediction);
		Prediction.setLayout(null);
		
		JLabel lblPrediction = new JLabel("Prediction");
		lblPrediction.setBounds(12, 0, 82, 21);
		Prediction.add(lblPrediction);
		
		JCheckBox chckbxFact = new JCheckBox("Fact_01");
		chckbxFact.setBounds(118, -1, 126, 23);
		Prediction.add(chckbxFact);
		
		JCheckBox chckbxFact_1 = new JCheckBox("Fact_02");
		chckbxFact_1.setBounds(118, 24, 126, 23);
		Prediction.add(chckbxFact_1);
		
		JCheckBox chckbxFact_2 = new JCheckBox("Fact_03");
		chckbxFact_2.setBounds(248, -1, 126, 23);
		Prediction.add(chckbxFact_2);
		
		JCheckBox chckbxFact_3 = new JCheckBox("Fact_04");
		chckbxFact_3.setBounds(248, 24, 126, 23);
		Prediction.add(chckbxFact_3);
		
		JCheckBox chckbxFact_4 = new JCheckBox("Fact_05");
		chckbxFact_4.setBounds(391, -1, 126, 23);
		Prediction.add(chckbxFact_4);
		
		JCheckBox chckbxFact_5 = new JCheckBox("Fact_06");
		chckbxFact_5.setBounds(391, 24, 126, 23);
		Prediction.add(chckbxFact_5);
		
		JCheckBox chckbxFact_6 = new JCheckBox("Fact_07");
		chckbxFact_6.setBounds(527, -1, 126, 23);
		Prediction.add(chckbxFact_6);
		
		JButton btnResults = new JButton("Results");
		btnResults.setBounds(673, 23, 114, 25);
		Prediction.add(btnResults);


		JPanel btGetButton = new JPanel();
		btGetButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btGetButton.setBounds(0, 374, 484, 272);
		frame.getContentPane().add(btGetButton);
		btGetButton.setLayout(null);

		JLabel lblTelecommands = new JLabel("Telecommands");
		lblTelecommands.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblTelecommands.setBounds(28, 6, 138, 16);
		btGetButton.add(lblTelecommands);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"FACT_01", "FACT_02", "FACT_03", "FACT_04", "FACT_05", "FACT_06", "FACT_07"}));
		comboBox.setBounds(328, 6, 150, 27);
		btGetButton.add(comboBox);

		JLabel lblConnectTo = new JLabel("connect to: ");
		lblConnectTo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConnectTo.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblConnectTo.setBounds(189, 10, 138, 16);
		btGetButton.add(lblConnectTo);

		JButton btnAlarmOn = new JButton("Alarm ON");
		btnAlarmOn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				telecommand.setAlarmState(comboBox.getSelectedIndex(), 1);
			}
		});
		btnAlarmOn.setBounds(16, 143, 103, 29);
		btGetButton.add(btnAlarmOn);

		JButton btnAlarmOff = new JButton("Alarm OFF");
		btnAlarmOff.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAlarmOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				telecommand.setAlarmState(comboBox.getSelectedIndex(), 0);
			}
		});
		btnAlarmOff.setBounds(16, 184, 117, 29);
		btGetButton.add(btnAlarmOff);

		JButton btnLedOn = new JButton("LED ON");
		btnLedOn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				telecommand.setLEDState(comboBox.getSelectedIndex(), 1);
			}
		});
		btnLedOn.setBounds(143, 143, 108, 29);
		btGetButton.add(btnLedOn);

		JButton btnLedOff = new JButton("LED OFF");
		btnLedOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				telecommand.setLEDState(comboBox.getSelectedIndex(), 0);
			}
		});
		btnLedOff.setBounds(145, 184, 108, 29);
		btGetButton.add(btnLedOff);

		JButton btnRelayOn = new JButton("Relay ON");
		btnRelayOn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				telecommand.setRelayState(comboBox.getSelectedIndex(), 1);
			}
		});
		btnRelayOn.setBounds(272, 143, 103, 29);
		btGetButton.add(btnRelayOn);

		JButton btnRelayOff = new JButton("Relay OFF");
		btnRelayOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				telecommand.setLEDState(comboBox.getSelectedIndex(), 0);
			}
		});
		btnRelayOff.setBounds(265, 184, 110, 29);
		btGetButton.add(btnRelayOff);

		textField = new JTextField();
		textField.setBounds(16, 225, 124, 26);
		btGetButton.add(textField);
		textField.setColumns(10);

		JButton btnSendToDsp = new JButton("Send to DSP");
		btnSendToDsp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				telecommand.setDisplayMessage(comboBox.getSelectedIndex(), textField.getText());
			}
		});
		btnSendToDsp.setBounds(155, 223, 130, 29);
		btGetButton.add(btnSendToDsp);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(16, 81, 103, 26);
		btGetButton.add(textField_1);

		JButton btnTmpThrs = new JButton("TMP THRS");
		btnTmpThrs.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				telecommand.setTempThreshold(comboBox.getSelectedIndex(), textField_1.getText());
			}
		});
		btnTmpThrs.setBounds(16, 108, 103, 27);
		btGetButton.add(btnTmpThrs);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(143, 81, 103, 26);
		btGetButton.add(textField_2);

		JButton btnHumThrs = new JButton("HUM THRS");
		btnHumThrs.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				telecommand.setHumThreshold(comboBox.getSelectedIndex(), textField_2.getText());
			}
		});
		btnHumThrs.setBounds(143, 107, 117, 29);
		btGetButton.add(btnHumThrs);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(272, 81, 103, 26);
		btGetButton.add(textField_3);

		JButton btnPreThrs = new JButton("PRE THRS");
		btnPreThrs.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				telecommand.setPresThreshold(comboBox.getSelectedIndex(), textField_3.getText());
			}
		});
		btnPreThrs.setBounds(272, 107, 103, 29);
		btGetButton.add(btnPreThrs);

		JButton btnNewButton = new JButton("Get board sensors");
		btnNewButton.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				telecommand.getBoardSensors(comboBox.getSelectedIndex());
			}
		});
		btnNewButton.setBounds(249, 38, 229, 29);
		btGetButton.add(btnNewButton);
		
		Label get_status = new Label("Get Status:");
		get_status.setBounds(393, 91, 81, 21);
		btGetButton.add(get_status);
		
		JComboBox status_gpios = new JComboBox();
		status_gpios.setModel(new DefaultComboBoxModel(new String[] {"G_IOL", "G_IOS", "S_IOS", "G_IOD", "S_IOD", "FACT_06", "S_IOR"}));
		status_gpios.setBounds(387, 118, 85, 24);
		btGetButton.add(status_gpios);
		
		JButton btnGetStatus = new JButton("Status");
		btnGetStatus.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				telecommand.getBoardSensors(status_gpios.getSelectedIndex());//TODO: tomorow change to required fuction calls
			}
		});
		btnGetStatus.setBounds(382, 145, 96, 25);
		btGetButton.add(btnGetStatus);


		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_3.setBounds(483, 374, 515, 272);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		this.textPane = new JTextPane();
		this.textPane.setEditable(false);
		this.textPane.setBounds(6, 30, 503, 236);
		panel_3.add(this.textPane);
		this.doc = this.textPane.getStyledDocument();

		JLabel lblNewLabel = new JLabel("Console Logs");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(6, 6, 126, 16);
		panel_3.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(162, 0, 836, 375);
		frame.getContentPane().add(panel_1);
	}

	public void addLog(String log) {
		textPane.setText(log + "\n" + textPane.getText() );
	}
}

