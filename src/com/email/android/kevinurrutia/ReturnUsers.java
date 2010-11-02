package com.email.android.kevinurrutia;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ReturnUsers extends Activity{
	protected static final String TAG = "ReturnUsers";
	Intent newIntent;
	private ListView list = null;
	private ArrayList<String> stringNames;
	private ArrayAdapter<String> adapter = null;
	String port = "";
	String host = "";
	String name = "";
	String pass = "";
	
	private PersistLogins pl;
	private ArrayList<Login> userList;//create new remember
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emaillist);
        
        newIntent = getIntent();
        userList = new ArrayList<Login>();
        stringNames = new ArrayList<String>();
        pl = new PersistLogins();
        userList = pl.getUsersSaved(this);
        System.out.println(userList);
        int i = 0;
        while (i < userList.size()){
        	stringNames.add(userList.get(i).getUserID());
        	i++;
        }
        System.out.println(stringNames);
        adapter = new ArrayAdapter<String>(this, R.layout.dataview,stringNames ); 
    	list = (ListView) this.findViewById(R.id.ListView);
    	list.setAdapter(adapter);   
    	list.setTextFilterEnabled(true);
    	
    	list.setOnItemClickListener(new OnItemClickListener() {
    	    public void onItemClick(AdapterView<?> parent, View view,
    	            int position, long id) {
    	    	  Log.d(TAG, "PARSING PORT NUMBER" + position);
    	    	  // We pass in the value and only fetch the non empty list item
    	    	  // We then pass the call to the fetch to get the bodies
    	    	  port = userList.get(position).getPort();
    	    	  name = userList.get(position).getUserID();
    	    	  host = userList.get(position).getHostName();
    	    	  pass = userList.get(position).getPassword();
    	    	  launchEmailList();
    	        }
    	      });
        
	}
	protected void launchEmailList() {
		Intent i = new Intent(this, EmailList.class);
		i.putExtra("port", port);
		i.putExtra("username", name);
		i.putExtra("pass", pass);
		i.putExtra("host", host);
		startActivity(i);
	}
	
}
