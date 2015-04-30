package com.easyway.json.android;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.service.RegisterService;

@Path("/checkcode")
public class CheckCode {

		public CheckCode(){} 
	
	    @GET 
	    @Path("/{param}")  
	    public Response printMessage(@PathParam("param") String msg) {  
	   
	        //judge the msg is valid, return error if it is not valid cellphone.
	        RegisterService ser = new RegisterService();
	        JSONObject obj = new JSONObject();
	    	try {
				obj.put("GetCode", ser.GenerateCode(msg));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            String result = obj.toString();
	    	
	        return Response.status(200).entity(result).build();  
	    	//return ser;
	   
	    }
	    
	    
	    @GET
	    @Path("/register/{cellphone}/code/{code}")
	    public Response getCode(@PathParam("cellphone") String id,@PathParam("code") String code) {
	       // search my database and get a string representation and return it
	    	    RegisterService ser = new RegisterService();
		        JSONObject obj = new JSONObject();
		    	try {
					obj.put("Result", ser.Register(id, code));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            String result = obj.toString();	 	   
	        return Response.status(200).entity(result).build();  
	    }
}
