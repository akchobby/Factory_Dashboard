package dashboard;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
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

public class Gui {

	protected JFrame frame;
	private JTextPane textPane;
	private FetchingData fetchingData;
	private StyledDocument doc;
	private JCheckBox[] board_connectivity = new JCheckBox[7];

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
		this.fetchingData = new FetchingData(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
				fetchingData.fetchAvailableBoards();
			}
		});
		btnScan.setBounds(6, 248, 141, 29);
		panel.add(btnScan);
		
		JLabel lblAvailableBoards = new JLabel("Available Boards");
		lblAvailableBoards.setBounds(20, 18, 103, 16);
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
		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.setEnabled(false);
		checkBox.setBounds(6, 50, 26, 20);
		panel.add(checkBox);
		
		JCheckBox checkBox_1 = new JCheckBox("");
		checkBox_1.setEnabled(false);
		checkBox_1.setBounds(6, 76, 26, 20);
		panel.add(checkBox_1);
		
		JCheckBox checkBox_2 = new JCheckBox("");
		checkBox_2.setEnabled(false);
		checkBox_2.setBounds(6, 104, 26, 20);
		panel.add(checkBox_2);
		
		JCheckBox checkBox_3 = new JCheckBox("");
		checkBox_3.setEnabled(false);
		checkBox_3.setBounds(6, 132, 26, 20);
		panel.add(checkBox_3);
		
		JCheckBox checkBox_4 = new JCheckBox("");
		checkBox_4.setEnabled(false);
		checkBox_4.setBounds(6, 160, 26, 20);
		panel.add(checkBox_4);
		
		JCheckBox checkBox_5 = new JCheckBox("");
		checkBox_5.setEnabled(false);
		checkBox_5.setBounds(6, 188, 26, 20);
		panel.add(checkBox_5);
		
		JCheckBox checkBox_6 = new JCheckBox("");
		checkBox_6.setEnabled(false);
		checkBox_6.setBounds(6, 216, 26, 20);
		panel.add(checkBox_6);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(162, 0, 836, 375);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBounds(0, 374, 484, 272);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
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
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(6, 6, 126, 16);
		panel_3.add(lblNewLabel);
	}
	
	public void addLog(String log) {
		textPane.setText(log + "\n" + textPane.getText() );
	}
}
