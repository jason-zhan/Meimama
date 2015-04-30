package com.meima.ui;



import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.WindowManager;
import com.meima.R;

public class Appstart extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.appstart);
		

		
	new Handler().postDelayed(new Runnable(){
		@Override
		public void run(){	   
			//读取SharedPreFerences中需要的数据,使用SharedPreFerences来记录程序启动的使用次数
	        SharedPreferences preferences = getSharedPreferences("share", MODE_PRIVATE);
	        //取得相应的值,如果没有该值,说明还未写入,用true作为默认值
	        boolean isFirstIn = preferences.getBoolean("isFirstIn", true);
	        //判断程序第几次启动
	        if (isFirstIn) 
			{  
			    Intent intent = new Intent (Appstart.this,Welcome.class);			
				startActivity(intent);	
			} else{ 
				Intent intent = new Intent (Appstart.this,MainWeixin.class);			
				startActivity(intent);	
			} 
			Appstart.this.finish();
		}
	}, 1000);
   }
}