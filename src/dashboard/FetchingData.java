package dashboard;

import java.util.*;
import java.io.*;
import java.net.*;



public class FetchingData {

    private ArrayList<Board> boards;
    private int[] ipList = new int[]{30, 32, 34, 36, 38, 40, 42, 44};
    private static final int PORT = 8080;
    private static final int TIMEOUT = 2000;
    private Gui gui;

    public FetchingData(Gui gui) {
		this.gui = gui;
		this.gui.addLog("Initialized FetchingData ...");
	}

	public void fetchAvailableBoards() {
        for (int ip : ipList) {
            String host_ip = "192.168.47.".concat(Integer.toString(ip));
            System.out.print("Attempting connection to " + host_ip + ": ");
            try {
                Socket clientSocket;
                PrintWriter out;
                BufferedReader in;
                clientSocket = new Socket();
                clientSocket.connect(new InetSocketAddress(host_ip, PORT), TIMEOUT);

                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                out.println("HELLO");
                String resp = in.readLine();

                System.out.print("SUCCESS");
                System.out.println("Server response: " + resp);
                this.gui.addLog("Connection to "+ host_ip +": SUCCESS");
                this.gui.addLog("Server response: " + resp);

                in.close();
                out.close();
                clientSocket.close();

            } catch (SocketTimeoutException e) {
                System.out.print("FAILED");
                this.gui.addLog("Connection to "+ host_ip +": TIMEOUT");
            } catch (Exception e) {
            	this.gui.addLog("Connection to "+ host_ip +": FAILED");
                System.out.print(e);
            }
            System.out.print("\n");

        }
        return;
    }


}
