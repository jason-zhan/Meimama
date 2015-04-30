package com.db.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBConnectionManager;
import db.ParseDSConfig;

public class CheckCodeDB {
	public CheckCodeDB(){
		
	}
	public String FindCode(String strCell){
		 DBConnectionManager   connectionMan = DBConnectionManager.getInstance();
		 String name="meima";
		 Connection  conn = connectionMan.getConnection(name);
		 String code = "";  
		 try {

			Statement statement = conn.createStatement();
			String sql = "select * from checkcode where cellphone = '" + strCell + "';";
			ResultSet rs = statement.executeQuery(sql); 
            if(rs.wasNull()){
            	return code;
            }
			while(rs.next()) {  
			    code = rs.getString("checkcode");
			}  
			rs.close();  
			}  catch(SQLException e) {   
			e.printStackTrace();   
			} catch(Exception e) {   
			e.printStackTrace();   
			} 

		 connectionMan.freeConnection(name,conn);
		 return code;
		
	}
	public void UpdateCode(String strCell, String code){
		DBConnectionManager   connectionMan = DBConnectionManager.getInstance();
		 String name="meima";
		 Connection  conn = connectionMan.getConnection(name);
		 try {

			Statement statement = conn.createStatement();
			String sql = "insert into checkcode (id, cellphone, checkcode) values ( 1, '" + strCell + "','";
			sql += code + "');";
			statement.execute(sql);
		}  catch(SQLException e) {   
			e.printStackTrace();   
		} 
		connectionMan.freeConnection(name,conn);
	}
	public String JudgeCode(String strCell, String code){
		DBConnectionManager   connectionMan = DBConnectionManager.getInstance();
		 String name="meima";
		 Connection  conn = connectionMan.getConnection(name);
		 try {

			Statement statement = conn.createStatement();
			String sql = "select * from checkcode where cellphone = '" + strCell + "' and checkcode ='" + code + "';";
			statement.execute(sql);
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql); 
            if(rs.wasNull()){
            	return "";
            }
            if(rs.next()) {  
			    code = "SUCC";
			} else {
				code ="";
			}
			  
			rs.close();  
			}  catch(SQLException e) {   
			e.printStackTrace();   
			} catch(Exception e) {   
			e.printStackTrace();   
			}  
		 
		 connectionMan.freeConnection(name,conn);
		 return code;

	}
	
}
