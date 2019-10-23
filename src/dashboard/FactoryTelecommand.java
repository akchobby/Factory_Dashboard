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
		return this.sendTelecommand(host, "cmd:S_ALA,state:" +state); 
	}																			
	
	public String setLEDState(int host, int state) {
		return this.sendTelecommand(host, "cmd:S_LED,state:"+ state);  
	}															
	
	public String setRelayState(int host, int state) {
		return this.sendTelecommand(host, "cmd:S_REL,state:" + state);
	}
	
	public String setDisplayMessage(int host, String message) {
		return this.sendTelecommand(host, "cmd:S_LCD,message:" + message);
	}
	
	public String getBoardSensors(int host) {
		return this.sendTelecommand(host, "cmd:G_SEL");
	}
	
	public String setTempThreshold(int host, String message) {
		return this.sendTelecommand(host, "cmd:S_TET,temp_threshold:" + message);
	}
	
	public String setHumThreshold(int host, String message) {
		return this.sendTelecommand(host, "cmd:S_HUT,hum_threshold:" + message);
	}
	
	public String setPresThreshold(int host, String message) {
		return this.sendTelecommand(host, "cmd:S_PRT,pres_threshold:" + message);
	}
	
	public String generalGPIO(int host, int instruction_id, String pin, String state) {
		switch (instruction_id){
			case 0:
				return this.sendTelecommand(host, "cmd:G_IOL");
			case 1:
				return this.sendTelecommand(host, "cmd:G_IOS,pin:" + pin);
			case 2:
				return this.sendTelecommand(host, "cmd:S_IOS,pin:" + pin + ",state:" + state);
			case 3:
				return this.sendTelecommand(host, "cmd:G_IOD,pin:" + pin);
			case 4:
				return this.sendTelecommand(host, "cmd:S_IOD,pin:" + pin + ",direction:" + state);
			case 5:
				return this.sendTelecommand(host, "cmd:S_IOR,pin:" + pin + ",mode:" + state);
		}
		return "";
		
	}
	
}
