package com.email.android.kevinurrutia;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class EmailInfo extends Activity {

	public static final String TAG = "Email";
	private EditText usernameText;
	private EditText hostText;
	private EditText passwordText;
	private EditText portText;
	private Button emailButton;
	private Button logButton;
	private ListView userLogList = null;
	private ArrayAdapter<String> userLogAdapter = null;
	
	
	/** Called when the activity is first created. */	
	@Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        usernameText = (EditText)findViewById(R.id.username);
        hostText = (EditText)findViewById(R.id.host);
        passwordText = (EditText)findViewById(R.id.password);
        portText = (EditText)findViewById(R.id.port);
        emailButton = (Button) findViewById(R.id.emailButton);
        logButton = (Button) findViewById(R.id.logButton);

        //Email Button listener, It will create a socket and get all the headers in a list.
        emailButton.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		System.out.println("Inside the setOnClickListener");
        		launchEmailList();

        	}

        });
        //Log Button, this will get the past users data.
        logButton.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View e){
        		System.out.println("Inside the setOnClickListener");
        		launchLogList();

        	}

        });
    }

	
	
	protected void launchLogList(){
		Intent x = new Intent(this,ReturnUsers.class);
		startActivity(x);
		
	}

	protected void launchEmailList() {
		Intent i = new Intent(this, EmailList.class);
    	String name = usernameText.getText().toString();
    	String host = hostText.getText().toString();
    	String pass = passwordText.getText().toString();
    	String port = portText.getText().toString();
		i.putExtra("port", port);
		i.putExtra("username", name);
		i.putExtra("pass", pass);
		i.putExtra("host", host);
		startActivity(i);
	}
	
}
