package com.email.android.kevinurrutia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

import javax.net.SocketFactory;
//import javax.swing.JOptionPane;

/**
 * The Class unsecureSocket.
 */
public class unsecureSocket {

	 /** The socket factory. */
 	SocketFactory socketFactory;
	 
 	/** The socket. */
 	Socket socket;
	 
 	/** The user id. */
 	String userID;
	 
 	/** The password. */
 	String password;
	 
 	/** The port. */
 	String port;
	 
 	/** The server. */
 	String server;
	 
 	/** The response. */
 	String response;
	// create PrintWriter for sending login to server
    /** The output. */
	PrintWriter output;
    //Create BufferedReader.
    /** The input. */
    BufferedReader input;
	 
	 /**
 	 * Instantiates a new unsecure socket.
 	 *
 	 * @param userIDIn the user id in
 	 * @param passwordIn the password in
 	 * @param portIn the port in
 	 * @param serverIn the server in
 	 */
 	public unsecureSocket(String userIDIn, String passwordIn, String portIn, String serverIn){
		 this.userID = userIDIn;
		 this.password = passwordIn;
		 this.port = portIn;
		 this.server = serverIn;
		 int portNumber = 0;
		 try{
			 portNumber = Integer.parseInt(port);
		 }
		 catch(NumberFormatException e){
			 //JOptionPane.showMessageDialog( null, "ERROR: Please input your port as a number." );
			 
			 System.out.println("ERROR: Please input your port as a number");
			 e.printStackTrace();
			 System.exit(0);
		 }
		 try {
				 socketFactory = 
			            ( SocketFactory ) SocketFactory.getDefault();
				 socket = 
			            ( Socket ) socketFactory.createSocket( 
			               server, portNumber );
				 output = new PrintWriter(new OutputStreamWriter( socket.getOutputStream() ) );
				 
				 input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				 
		 }catch ( IOException ioException ) { 
			 //JOptionPane.showMessageDialog( null, "ERROR: Please make sure your host and port are correct." );
			 ioException.printStackTrace();
			 System.out.println("ERROR: Please make sure your host and port are correct.");
		 }
	}
	 
	 /**
 	 * Login method.
 	 *
 	 * @return true, if successful
 	 */
 	public boolean loginMethod(){
		boolean loginSuccess = false;
		 try{
			 String loginCommand= ". login " + this.userID + " " + this.password;
	         //Sends login command to the server
	         output.println(loginCommand);
	         output.flush();
	
	         // Read response from server
	         response = input.readLine();
	         response = input.readLine();
	         //Check to see if  Username and Password are correct
	         if(!(response.contains(". NO "))){
	        	 loginSuccess = true;
		         // Display response to user
		        // JOptionPane.showMessageDialog( null, response );
		         response = input.readLine();
		        // JOptionPane.showMessageDialog(null, response);
		         output.println(". select INBOX");
		         output.flush();
		         response = input.readLine();
		         response = input.readLine();
		         response = input.readLine();

		         while(!(response.equals(". OK [READ-WRITE] SELECT completed"))){
		        	 response = input.readLine();
		         }
	         }
	         else{
	        	 //JOptionPane.showMessageDialog( null, "ERROR: Please make sure your Username and Password are correct." );
	        	 System.out.println("ERROR: Please make sure your Username and PAssword are correct.");
	        	 loginSuccess = false;
	         }
		 
		 }
			 catch(IOException e){
				 return false;
			 }
			return loginSuccess;
		
	 }
	 
	 /**
 	 * From header.
 	 *
 	 * @param fieldType the field type
 	 * @return the vector
 	 */
 	public Vector<String> fromHeader(String fieldType){

	     Vector<String> messagesHeader = new Vector<String>();
		 try{
	         //Send fetch command to the server and get the 'from' for the headers
	         output.println(". fetch 1:* (body[header.fields ("+fieldType+")])");
	         output.flush();
	         
	         //Read all the lines and stop when it is at the end, signified by ". OK Success"		         
	         String header;
	         String test;
	         response = input.readLine();

	         while( !(response.equals(". OK FETCH completed"))){
		         response = input.readLine();
		         //Checks for the header, from, and date lines and stores them into our vector
		         if(!response.contains("*") && !(response.equals("(")) 
		        		 && !(response.equals(")")) && !(response.equals(". OK FETCH completed"))){
		        	 test = response;
		        	 header = test;
		        	 messagesHeader.add(header);
		         }
		         
	         }
		}
		catch(IOException e){
			//JOptionPane.showMessageDialog( null, "ERROR: in getting Headers " );
       	 System.out.println("ERROR: in getting Headers");
		}
		return messagesHeader;
	 }
	 
//Method to call get the body of the email.
	 /**
 * Fetch body.
 *
 * @param mailNumber the mail number
 * @return the string
 */
public String fetchBody(int mailNumber){
			String messageString = null;
			String test = "";
			if(mailNumber % 2 == 0){
				mailNumber = ((mailNumber/2)+1);
				output.println(". fetch " + mailNumber + " body[text]");
				output.flush();
				try {
					messageString = input.readLine();
					while(!(messageString.equals(". OK FETCH completed"))){
						messageString = input.readLine();
						test+= messageString + "\n";
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		 return test;
	    
	 }
	 //Logs out the program.
	 /**
 	 * Logout.
 	 */
 	public void logout(){
			try{
				socket.close();
				input.close();
				output.close();
				 
		 
			}catch(IOException e){
				//JOptionPane.showMessageDialog(null, "ERROR: Error closing connection.");
				System.out.println("ERROR: Error in closing the connection.");
				e.printStackTrace();
				System.exit(0);
			}
		 }


}
