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
	    //public ChartPanel Panel; 
	  
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
     String received; 
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
		 System.out.println(factory_id); 
		 double temperature=Double.parseDouble(str_array[1].split(":")[1]);
		 double humidity=Double.parseDouble(str_array[2].split(":")[1]);
		 double pressure=Double.parseDouble(str_array[3].split(":")[1]);
		 int time=Integer.parseInt(str_array[4].split(":")[1]);
		 switch (factory_id) {
		 case 1: 
			  ReceiveDataFactory.series_temp[0].add(time,temperature);
			  ReceiveDataFactory.series_press[0].add(time,pressure);
			  ReceiveDataFactory.series_hum[0].add(time,humidity);
		 			break;
		 case 2: 
			  ReceiveDataFactory.series_temp[1].add(time,temperature);
			  ReceiveDataFactory.series_press[1].add(time,pressure);
			  ReceiveDataFactory.series_hum[1].add(time,humidity);
		 			break;
		 case 3: 
			  ReceiveDataFactory.series_temp[2].add(time,temperature);
			  ReceiveDataFactory.series_press[2].add(time,pressure);
			  ReceiveDataFactory.series_hum[2].add(time,humidity);
		 			break;
		 case 4: 
			  ReceiveDataFactory.series_temp[3].add(time,temperature);
			  ReceiveDataFactory.series_press[3].add(time,pressure);
			  ReceiveDataFactory.series_hum[3].add(time,humidity);
		 			break;
		 case 5: 
			  ReceiveDataFactory.series_temp[4].add(time,temperature);
			  ReceiveDataFactory.series_press[4].add(time,pressure);
			  ReceiveDataFactory.series_hum[4].add(time,humidity);
		 			break;
		 case 6: 
			  ReceiveDataFactory.series_temp[5].add(time,temperature);
			  ReceiveDataFactory.series_press[5].add(time,pressure);
			  ReceiveDataFactory.series_hum[5].add(time,humidity);
		 			break;
		 case 7: 
			  ReceiveDataFactory.series_temp[6].add(time,temperature);
			  ReceiveDataFactory.series_press[6].add(time,pressure);
			  ReceiveDataFactory.series_hum[6].add(time,humidity);
		 			break;
		 }
      
      
      //temp.Temperature(factory_id, temperature);
      //Panel=temp.PanelChart;
	}
}
