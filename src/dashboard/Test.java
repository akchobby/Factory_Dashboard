package dashboard;

import java.awt.EventQueue;

public class Test {

    public static void main(String[] args) {
        System.out.println("Program started\n");
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
        System.out.println("Program finished\n");
    }
}
