package com.email.android.kevinurrutia;

import java.util.ArrayList;
import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class EmailList extends Activity {
    private static final String TAG = "EmailList";
	protected static final int CHOICE_MODE_SINGLE = 0;
	static Vector<String> dateHeader;
	static Vector<String> fromHeader;
	static Vector<String> subjectHeader;
	static ArrayList<String> header;
	static int emailNumber;
	static unsecureSocket us;
	String body = "";
	private PersistLogins pl;
	private Login login;

    Intent newIntent;

	private ListView list = null;
	private ArrayAdapter<String> adapter = null;

    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emaillist);
        
        newIntent = getIntent();
        String password = newIntent.getStringExtra("pass");
        String username = newIntent.getStringExtra("username");
        String port = newIntent.getStringExtra("port");
        String host = newIntent.getStringExtra("host");
        socketCreator(username,password,port,host);
        login = new Login(username,password,port,host);
        pl = new PersistLogins(login);
        pl.serializeOut(this);
        adapter = new ArrayAdapter<String>(this, R.layout.dataview, header); 
    	list = (ListView) this.findViewById(R.id.ListView);
    	list.setAdapter(adapter);   
    	list.setTextFilterEnabled(true);
    	
    	list.setOnItemClickListener(new OnItemClickListener() {
    	    public void onItemClick(AdapterView<?> parent, View view,
    	            int position, long id) {
    	    	  Log.d(TAG, "PARSING PORT NUMBER" + position);
    	    	  // We pass in the value and only fetch the non empty list item
    	    	  // We then pass the call to the fetch to get the bodies
		    	   if(position % 2 ==0){ 	  
		    	    	  body = us.fetchBody(position);
		    	    	  launchbodyFetch();
		    	   }
    	        }
    	      });


    	

	}
	@Override
	protected void onActivityResult(int a, int b, Intent intent){
		us.logout();
		this.finish();
	}
	
	public static void socketCreator(String userNameIn, String passwordIn, String portIn, String hostIn){
		String port = portIn;
		String username = userNameIn;
		String host = hostIn;
		String pass = passwordIn;
		int portNumber = 0;
		 try{
			 portNumber = Integer.parseInt(port);

				Log.d(TAG, "PARSING PORT NUMBER");

		 }
		 //Will catch an error if the user puts in a string for a port number.
		 catch(NumberFormatException e){
			 System.out.println("The port wasnt able to convert");
			 e.printStackTrace();
			 }
			us = new unsecureSocket(username,pass,port, host);
			if(us.loginMethod()){
				Log.d(TAG, "Loged in succesfully.");
				dateHeader = us.fromHeader("date");
				subjectHeader = us.fromHeader("subject");
				fromHeader = us.fromHeader("from");
				String tempDate = "";
				String tempSubject = "";
				String tempFrom = "";
				String tempHeader = "";
				header = new ArrayList<String>();
				int i = 0;
				System.out.println(dateHeader.size());
				while(i < dateHeader.size()){
					tempDate = "";
					tempSubject = "";
					tempHeader = "";
					tempDate = dateHeader.get(i);
					tempSubject = subjectHeader.get(i);
					tempFrom = fromHeader.get(i);
					tempHeader = tempDate + "\n" + tempSubject + "\n" + tempFrom;
					header.add(tempHeader);
					i++;
				}

			}
			else{

				Log.d(TAG, "Did not log in.");
			}

	}
	
	protected void launchbodyFetch() {
		Intent a = new Intent(this, BodyClass.class);
		String bodyText = body;
		Log.d(TAG,bodyText);
		a.putExtra("body", bodyText);
		//a.putExtra("body", "HEY");
		startActivity(a);
	}
	
}
