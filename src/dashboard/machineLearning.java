package dashboard;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class machineLearning   {

	 private ServerSocket ss = null;
	 private Socket s = null; 
	 private InetAddress host =null;
     Socket socket = null;
     private ObjectOutputStream oos = null;
     private ObjectInputStream ois = null;
     private Gui gui;
     private String host_ip = "127.0.0.1";
     private static final int PORT = 8081;
 	private static final int TIMEOUT = 2000;
	
	public machineLearning(Gui gui) throws IOException {
		this.gui=gui;
		s = new Socket();
		
		s.connect(new InetSocketAddress(host_ip, PORT), TIMEOUT);
		
	}
	private String sendCommandML(int host, String message) {
		try {
			
			PrintWriter out;
			BufferedReader in;
			
			this.gui.addLog("[" + host + "] - " + host_ip + " - "+ message);
			

		out = new PrintWriter(s.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));

		out.println(message);
		String resp = in.readLine();

		System.out.print("SUCCESS");
		System.out.println("Server response: " + resp);

		in.close();
		out.close();
		s.close();
		return resp;

	} catch (SocketTimeoutException e) {
		System.out.print("FAILED");
	} catch (Exception e) {
		System.out.print(e);
	}
	System.out.print("\n");
	return "err";
	}
	public String informML(int host) {
		//String time_s=Double.toString(time);
		return this.sendCommandML(host, "join,"+host);
	}

	public String sendMLData(int host, double time,double temp, double pressure, double humidity) {
		String time_s=Double.toString(time);
		String temp_s=Double.toString(temp);
		String press_s=Double.toString(pressure);
		String hum_s=Double.toString(humidity);
		System.out.println("sending data to ML!!!");
		return this.sendCommandML(host, "recv,"+host+","+time_s+","+temp_s+","+press_s+","+hum_s);
	}
	/*public String receiveMLestimation(String message) {
		String time_s=Double.toString(time);
		String temp_s=Double.toString(temp);
		String press_s=Double.toString(pressure);
		String hum_s=Double.toString(humidity);
		return this.sendCommandML(host, "recv,"+host+temp_s+press_s+hum_s);
	}*/
	
	

}
