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

import com.sun.service.RegisterService;

@Path("/checkcode")
public class CheckCode {

		public CheckCode(){} 
	
	    @GET 
	    @Path("/{param}")  
	    public Response printMessage(@PathParam("param") String msg) {  
	   
	        //judge the msg is valid, return error if it is not valid cellphone.
	        RegisterService ser = new RegisterService();
	    	
	    	String result = "Restful example : "+ ser.GenerateCode(msg);
	        
	        return Response.status(200).entity(result).build();  
	    	//return ser;
	   
	    }
	    
	    @GET
	    @Path("/code")
	    public Response getCode() {
	    	RegisterService ser = new RegisterService();
	    	
	    	 String result = "Restful example : "+ ser.GenerateCode("");  
	    	   
	         return Response.status(200).entity(result).build();  
	    }
	    
	    
	    @GET
	    @Path("/code/{cellphone}")
	    public Response getCode(@PathParam("cellphone") String id) {
	       // search my database and get a string representation and return it
	    	String result = "Restful example : " + id;  
	 	   
	        return Response.status(200).entity(result).build();  
	    }
}
