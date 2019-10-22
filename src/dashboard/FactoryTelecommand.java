package dashboard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class FactoryTelecommand {
	private int[] ipList = new int[]{32, 34, 36, 38, 40, 42, 44};
	private static final int PORT = 8080;
	private static final int TIMEOUT = 2000;
	private Gui gui;

	public FactoryTelecommand(Gui gui) {
		this.gui = gui;
		this.gui.addLog("Initialized FactoryTelecommand ...");
	}

	private String sendTelecommand(int host, String message) {
		this.gui.addLog("==========| Sending Telecommand " + message);
		try {
			Socket clientSocket;
			PrintWriter out;
			BufferedReader in;
			String host_ip = "192.168.47.".concat(Integer.toString(this.ipList[host]));
			this.gui.addLog("[" + host + "] - " + host_ip + " - "+ message);
			clientSocket = new Socket();
			clientSocket.connect(new InetSocketAddress(host_ip, PORT), TIMEOUT);

			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			out.println(message);
			String resp = in.readLine();

			System.out.print("SUCCESS");
			System.out.println("Server response: " + resp);

			in.close();
			out.close();
			clientSocket.close();
			return resp;

		} catch (SocketTimeoutException e) {
			System.out.print("FAILED");
		} catch (Exception e) {
			System.out.print(e);
		}
		System.out.print("\n");
		return "err";
	}
	
	public String setAlarmState(int host, int state) {
		return this.sendTelecommand(host, "S_ALA:" + state);
	}
	
	public String setLEDState(int host, int state) {
		return this.sendTelecommand(host, "S_LED:" + state);
	}
	
	public String setRelayState(int host, int state) {
		return this.sendTelecommand(host, "S_REL:" + state);
	}
	
	public String setDisplayMessage(int host, String message) {
		return this.sendTelecommand(host, "S_LCD:" + message);
	}
	
	public String getBoardSensors(int host) {
		return this.sendTelecommand(host, "G_SEL");
	}
}
