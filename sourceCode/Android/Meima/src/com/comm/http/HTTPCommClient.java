package com.comm.http;

import java.io.IOException;  

import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.util.EntityUtils;  
import org.json.JSONException;  
import org.json.JSONObject;  
  
import android.app.Activity;  
import android.app.AlertDialog;  
import android.app.AlertDialog.Builder;  
import android.content.DialogInterface;  
import android.os.Bundle;  
import android.os.StrictMode;  
import android.util.Log;  
import android.view.View;  
import android.widget.Button;  
import android.widget.EditText;  
public class HTTPCommClient {

	
	 private static  String processURL="http://192.168.1.116:8080/MeiMa/";  
            
       /** 
        *  Called when the activity is first created. 
        */  
       public void onCreate() {  
           ///在Android2.2以后必须添加以下代码  
           //本应用采用的Android4.0  
           //设置线程的策略  
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()     
            .detectDiskReads()     
            .detectDiskWrites()     
            .detectNetwork()   // or .detectAll() for all detectable problems     
            .penaltyLog()     
            .build());     
           //设置虚拟机的策略  
             StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()     
                    .detectLeakedSqlLiteObjects()     
                    //.detectLeakedClosableObjects()     
                    .penaltyLog()     
                    .penaltyDeath()     
                    .build());  
          
       
       }  
     
     
   /** 
    * 获取Struts2 Http 登录的请求信息 
    * @param  userName 
    * @param  password 
    */  
   public String GetCode(String cellPhone){  
       String result=null;  
       try {  
                  
           //创建一个HttpClient对象  
           HttpClient httpclient = new DefaultHttpClient();  
           String strURL = "";
           //远程登录URL  
           strURL=processURL+"checkcode/"+cellPhone; 
           Log.d("远程URL", strURL);  
           //创建HttpGet对象  
           HttpGet request=new HttpGet(strURL);  
           //请求信息类型MIME每种响应类型的输出（普通文本、html 和 XML，json）。允许的响应类型应当匹配资源类中生成的 MIME 类型  
           //资源类生成的 MIME 类型应当匹配一种可接受的 MIME 类型。如果生成的 MIME 类型和可接受的 MIME 类型不 匹配，那么将  
           //生成 com.sun.jersey.api.client.UniformInterfaceException。例如，将可接受的 MIME 类型设置为 text/xml，而将  
           //生成的 MIME 类型设置为 application/xml。将生成 UniformInterfaceException。  
           //request.addHeader("Accept","text/json");  
           //获取响应的结果  
           HttpResponse response =httpclient.execute(request);  
           //获取HttpEntity  
           HttpEntity entity=response.getEntity();  
           //获取响应的结果信息  
           String json =EntityUtils.toString(entity,"UTF-8"); 
           System.out.println(json);
           //JSON的解析过程  
           if(json!=null){  
               JSONObject jsonObject=new JSONObject(json);  
               result=jsonObject.get("GetCode").toString();  
           }  
          if(result==null){  
              json="登录失败请重新登录";  
          }  
        } catch (ClientProtocolException e) { 
        	result = "ERROR";
           // TODO Auto-generated catch block  
           e.printStackTrace();  
       } catch (IOException e) {  
    	   result = "ERROR";
           // TODO Auto-generated catch block  
           e.printStackTrace();  
       } catch (JSONException e) {  
    	   result = "ERROR";
           // TODO Auto-generated catch block  
           e.printStackTrace();  
       }  
       return result;
   }  
   public String Register(String cellPhone, String strCode){  
       String result=null;  
       try {  
                  
           //创建一个HttpClient对象  
           HttpClient httpclient = new DefaultHttpClient();  
           String strURL = "";
           //远程登录URL  
           strURL=processURL+"checkcode/register/"+cellPhone + "/code/" + strCode; 
           Log.d("远程URL", strURL);  
           //创建HttpGet对象  
           HttpGet request=new HttpGet(strURL);  
           //请求信息类型MIME每种响应类型的输出（普通文本、html 和 XML，json）。允许的响应类型应当匹配资源类中生成的 MIME 类型  
           //资源类生成的 MIME 类型应当匹配一种可接受的 MIME 类型。如果生成的 MIME 类型和可接受的 MIME 类型不 匹配，那么将  
           //生成 com.sun.jersey.api.client.UniformInterfaceException。例如，将可接受的 MIME 类型设置为 text/xml，而将  
           //生成的 MIME 类型设置为 application/xml。将生成 UniformInterfaceException。  
           //request.addHeader("Accept","text/json");  
           //获取响应的结果  
           HttpResponse response =httpclient.execute(request);  
           //获取HttpEntity  
           HttpEntity entity=response.getEntity();  
           //获取响应的结果信息  
           String json =EntityUtils.toString(entity,"UTF-8"); 
           System.out.println(json);
           //JSON的解析过程  
           if(json!=null){  
               JSONObject jsonObject=new JSONObject(json);  
               result=jsonObject.get("Result").toString();  
           }  
          if(result==null){  
              json="登录失败请重新登录";  
          }  
        } catch (ClientProtocolException e) {  
           // TODO Auto-generated catch block  
        	result = "ERROR";
           e.printStackTrace();  
       } catch (IOException e) {  
    	   result = "ERROR";
           // TODO Auto-generated catch block  
           e.printStackTrace();  
       } catch (JSONException e) {  
    	   result = "ERROR";
           // TODO Auto-generated catch block  
           e.printStackTrace();  
       }  
       return result;
   }  
}
