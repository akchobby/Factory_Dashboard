package dashboard;

import java.awt.EventQueue;

public class Test {

    public static void main(String[] args) {
        System.out.println("Program started\n");
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui("Factory of the Future");
					window.pack();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        
//        public static void main(String[] args) {
//    		EventQueue.invokeLater(new Runnable() {
//    			public void run() {
//    				try {
//    					Gui window = new Gui("Factory of the Future");
//    					window.pack();
//    					RefineryUtilities.centerFrameOnScreen(window);
//    					window.frame.setVisible(true);
//    				} catch (Exception e) {
//    					e.printStackTrace();
//    				}
//    			}
//    		});
//    	}
        System.out.println("Program finished\n");
    }
}
