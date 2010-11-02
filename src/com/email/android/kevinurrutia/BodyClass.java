package com.email.android.kevinurrutia;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class BodyClass extends Activity {
	   private static final String TAG = "BodyClass";
	   Intent bodyIntent;
	   private EditText bodyText;
	   public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.bodyclass);
	       bodyIntent = getIntent();
	       Log.d(TAG,"HERE IN BODY CLASS");
		   String body = bodyIntent.getStringExtra("body");
		   bodyText = (EditText)findViewById(R.id.body);
		   //Log.d(TAG,body);
		   bodyText.setText(body);
	   }
	
	
	
}
