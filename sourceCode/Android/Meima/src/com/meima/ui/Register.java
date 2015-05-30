package com.meima.ui;


import com.comm.http.HTTPCommClient;
import com.meima.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {
	private EditText mCell; 
	private EditText mCode; 
	private Button btnLogin;  
	private String mstrCell;

	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.register);
	        
	        mCell = (EditText)findViewById(R.id.register_user_edit);
	        mCode = (EditText)findViewById(R.id.register_code_edit);
	        
	    }

	    public void ButtonGetCode(View v) {
	    	mstrCell = mCell.getText().toString();
	    	if("".equals(mCell.getText().toString()))  
	        {
	        	new AlertDialog.Builder(Register.this)
				.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
				.setTitle("register")
				.setMessage("cell phone can  not empty. please try again.")
				.create().show();
	         } else if(mCell.getText().toString().length() < 10) {
	        		new AlertDialog.Builder(Register.this)
					.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
					.setTitle("register")
					.setMessage("Invalid Cell Phone Number.")
					.create().show();
	         } else{
	        	 HTTPCommClient client = new HTTPCommClient();
	    		 client.onCreate();
	    		 String result = null;
	    		 String code = "";
	    		 result = client.GetCode(mCell.getText().toString());
	    		 
	    		 result = "Your Code is:" + result;
	    		 SmsManager smsManager = SmsManager.getDefault();
	    		 smsManager.sendTextMessage(mCell.getText().toString(), null, result, null, null);
		            
	             AlertDialog.Builder builder=new Builder(Register.this);  
	             builder.setTitle("Error") 
	              .setMessage(result)  
	             .setPositiveButton("OK", new DialogInterface.OnClickListener() {  
	                  
	                @Override  
	                public void onClick(DialogInterface dialog, int which) {  
	                    dialog.dismiss();  
	                }  
	            }).create().show(); 
	        
	        }
	    	
	    	
	    	/*
	      	Intent intent = new Intent();
			intent.setClass(Login.this,Whatsnew.class);
			startActivity(intent);
			Toast.makeText(getApplicationContext(), "ÂµÃ‡Ã‚Â¼Â³Ã‰Â¹Â¦", Toast.LENGTH_SHORT).show();
			this.finish();*/
	      }  
	    public void login_back(View v) {     //Â±ÃªÃŒÃ¢Ã€Â¸ Â·ÂµÂ»Ã˜Â°Â´Ã…Â¥
	      	this.finish();
	      }  
	    public void Register(View v) {     
	    	mstrCell = mCell.getText().toString();

	    	 HTTPCommClient client = new HTTPCommClient();
    		 client.onCreate();
    		 String result = "";
    		 
    		 result = client.Register(mstrCell, mCode.getText().toString());
    		 if(!result.equals("SUCC")){
    			 AlertDialog.Builder builder=new Builder(Register.this);  
	             builder.setTitle("æ��ç¤º") 
	              .setMessage("Code is error, please try again")  
	             .setPositiveButton("ç¡®å®š", new DialogInterface.OnClickListener() {  
	                  
	                @Override  
	                public void onClick(DialogInterface dialog, int which) {  
	                    dialog.dismiss();  
	                }  
	            }).create().show(); 
    		 } else {
    		 Intent intent = new Intent();
             intent.setClass(Register.this,LoadingActivity.class);
             startActivity(intent);
	        
 			  Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
    		 }

	      }  
}
