package dashboard;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.Color;
import java.io.*;
import java.net.*;

public class ClientHandler extends Thread  {
	 
	
	//DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
	  //  DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
	    final DataInputStream dis; 
	    final DataOutputStream dos; 
	    final Socket s;
	    static int buffer[]=new int[7];
	    //public ChartPanel Panel; 
	    public int client_id;
	    private machineLearning ML;
	    
	    
	    // Constructor 
	    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)  
	    { 
	     
	    	//this.Panel = null;
			this.s = s; 
	    	this.dis = dis; 
	    	this.dos = dos; 
	    	
    		
	    } 

 @Override
 public void run()  
 { 
     String received =null; 
     String toreturn; 
     while (true)  
     { 
         try { 

             // Ask user what he wants 
            /* dos.writeUTF("What do you want?[Date | Time]..\n"+ 
                         "Type Exit to terminate connection."); 
               */
             // receive the answer from client 
             received = dis.readUTF();
        	 //byte[] test = new byte[296];
        	 //dis.read(test, 0, 100);
        	 System.out.println(received);
        	 
        	 
        	 
             if(received.equals("Exit")) 
             {  
                 System.out.println("Client " + this.s + " sends exit..."); 
                 System.out.println("Closing this connection."); 
                 this.s.close(); 
                 System.out.println("Connection closed"); 
                 break; 
             } 
             System.out.println(received); 
             // creating Date object 
             //Date date = new Date(); 
             FormatMessage(received); 
            
             // write on output stream based on the 
             // answer from the client  
                	 //System.out.println(received);
                	 dos.writeUTF("Send Next message:\n"); 
                    // break; 
             
         } catch (IOException e) { 
             e.printStackTrace(); 
         } 
     } 
       
     try
     { 
         // closing resources 
         this.dis.close(); 
         this.dos.close(); 
           
     }catch(IOException e){ 
         e.printStackTrace(); 
     } 
 } 

 
  public void FormatMessage(String received) {
	  
	  //ReceiveDataFactory receive= new ReceiveDataFactory();
		 String[] str_array= received.split(",");
		 int factory_id= Integer.parseInt(str_array[0].split(":")[1]);
		 System.out.println(client_id); 
		 double temperature=Double.parseDouble(str_array[1].split(":")[1]);
		 double humidity=Double.parseDouble(str_array[3].split(":")[1]);
		 double pressure=Double.parseDouble(str_array[2].split(":")[1]);
		 double time=Double.parseDouble(str_array[4].split(":")[1]);
		 for (int i=1;;i++) {
			 if(i==factory_id) {
				// buffer[i-1]++;
			  ReceiveDataFactory.series_temp[i-1].add(time,temperature);
			  ReceiveDataFactory.series_press[i-1].add(time,pressure);
			  ReceiveDataFactory.series_hum[i-1].add(time,humidity);
			 // ML.sendMLData(factory_id, time, temperature, pressure, humidity);
			  if(buffer[i-1]==20) {
			  	ReceiveDataFactory.series_hum[i-1].clear();
			  	ReceiveDataFactory.series_press[i-1].clear();
			  	ReceiveDataFactory.series_temp[i-1].clear();
			  }
			  break;
			 }
		 }
	}
}
