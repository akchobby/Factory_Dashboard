package dashboard;

import java.awt.EventQueue;






import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;

public class Gui {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
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
	public Gui() {
		initialize();
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
		panel.setBounds(10, 0, 153, 375);
		frame.getContentPane().add(panel);
		
		JPanel Charts = new JPanel();
		Charts.setBounds(163, 0, 788, 375);
		frame.getContentPane().add(Charts);
		Charts.setLayout(null);
		
		JLabel lblRealtimeCharts = new JLabel("Real-Time Charts");
		lblRealtimeCharts.setBounds(214, 11, 116, 15);
		Charts.add(lblRealtimeCharts);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 374, 473, 217);
		frame.getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(483, 374, 468, 217);
		frame.getContentPane().add(panel_3);
	}
}
