package com.email.android.kevinurrutia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * The Class PersistLogins.
 */
public class PersistLogins {
	
	/** The logins. */
	private ArrayList<Login> logins = new ArrayList<Login>();
	
	/** The file name. */
	private String fileName;
	
	/** The current login. */
	private Login currentLogin;
	
	/** The file out. */
	private FileOutputStream fileOut;
	
	/** The object out. */
	private ObjectOutputStream objectOut;
	
	/** The fis. */
	private FileInputStream fis;
	
	/** The in. */
	private ObjectInputStream in;
	
	/** The log info. */
	File logInfo;
	
	/**
	 * Instantiates a new persist logins.
	 */
	public PersistLogins(){
		this.fileName = "emptyfile1.dat";
	}
	
	/**
	 * Instantiates a new persist logins.
	 *
	 * @param person the person
	 */
	public PersistLogins(Login person){
			this.fileName = "emptyfile1.dat";
			this.currentLogin = person;
			this.logins = new ArrayList<Login>();
	}
	
	/**
	 * Serialize out.
	 */
	@SuppressWarnings("unchecked")
	public void serializeOut(Activity main){
			try{
				/**
				 * 
				 * WE first try to open the file and if it doesnt exist
				 * then make a new one..also we check to see if there
				 * are any doubles in the file 
				 */
				int counter = 0;
				try {
					System.out.println("Trying to open the file");
					fis = main.getApplicationContext().openFileInput(fileName);
					in = new ObjectInputStream(fis);
					logins = (ArrayList<Login>)in.readObject();
				} catch (FileNotFoundException e){
					System.out.println("File not found new one");
					fileOut = main.getApplicationContext().openFileOutput(fileName,Context.MODE_PRIVATE);
					objectOut = new ObjectOutputStream(fileOut);
					objectOut.writeObject(logins);
					objectOut.close();
				}
				boolean doublepeople = false;
				while( counter <= logins.size()-1){
					System.out.println("Going to the while loop to check for doubles");
					if((logins.get(counter).getPassword().toString()).equalsIgnoreCase((currentLogin.getPassword().toString()))){
						doublepeople = true;
						//System.out.println("Inside the else statement.");
					
					} 
					counter++;
					
				}
				if (!doublepeople){
					logins.add(currentLogin);
					System.out.println("Writing to the file");
					System.out.println(logins);
					fileOut = main.getApplicationContext().openFileOutput(fileName,Context.MODE_PRIVATE);
					objectOut = new ObjectOutputStream(fileOut);
					objectOut.writeObject(logins);
					objectOut.close();
				}

			}catch(IOException e){
				//JOptionPane.showMessageDialog( null, "ERROR");
				e.printStackTrace();
				System.out.println("ERROR1");
				System.exit(0);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				//JOptionPane.showMessageDialog( null, "ERROR");
				System.out.println("ERROR2");
				System.exit(0);
			}
}
	
	/**
	 * Gets the users saved.
	 *
	 * @return the users saved
	 */
	public ArrayList<Login> getUsersSaved(Activity main) {
		//if(!logInfo.exists()){	

			try {
				fis = main.getApplicationContext().openFileInput(fileName);
				in = new ObjectInputStream(fis);
				logins = (ArrayList<Login>)in.readObject();
			} catch (FileNotFoundException e) {
				//create an empty file to display in the activity
				logInfo = new File(fileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//JOptionPane.showMessageDialog( null, "ERROR");
				System.exit(0);

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				//JOptionPane.showMessageDialog( null, "ERROR");
				System.exit(0);

			}
		return logins;
	}	
	
	
}
