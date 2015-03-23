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
	    		 result = client.loginRemoteService(mCell.getText().toString(), code);
	    		 
	    		 result = "Your Code is:" + result;
		            
	    		//创建提示框提醒是否登录成功  
	             AlertDialog.Builder builder=new Builder(Register.this);  
	             builder.setTitle("提示") 
	              .setMessage(result)  
	             .setPositiveButton("确定", new DialogInterface.OnClickListener() {  
	                  
	                @Override  
	                public void onClick(DialogInterface dialog, int which) {  
	                    dialog.dismiss();  
	                }  
	            }).create().show();  
	            // Intent intent = new Intent();
	             //intent.setClass(Login.this,LoadingActivity.class);
	             //startActivity(intent);
	             //Toast.makeText(getApplicationContext(), "µÇÂ¼³É¹¦", Toast.LENGTH_SHORT).show();
	     
	        
	        }
	    	
	    	
	    	/*
	      	Intent intent = new Intent();
			intent.setClass(Login.this,Whatsnew.class);
			startActivity(intent);
			Toast.makeText(getApplicationContext(), "µÇÂ¼³É¹¦", Toast.LENGTH_SHORT).show();
			this.finish();*/
	      }  
	    public void login_back(View v) {     //±êÌâÀ¸ ·µ»Ø°´Å¥
	      	this.finish();
	      }  
	    public void Register(View v) {     
	    	 HTTPCommClient client = new HTTPCommClient();
    		 client.onCreate();
    		 String result = "";
    		 
    		 result = client.loginRemoteService(mstrCell, mCode.getText().toString());
    		 Intent intent = new Intent();
             intent.setClass(Register.this,LoadingActivity.class);
             startActivity(intent);
	            
	    	//Uri uri = Uri.parse("http://3g.qq.com"); 
	    	//Intent intent = new Intent(Intent.ACTION_VIEW, uri); 
	    	//startActivity(intent);
	    	//Intent intent = new Intent();
	    	//intent.setClass(Login.this,Whatsnew.class);
	        //startActivity(intent);
 			Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();

	      }  
}
