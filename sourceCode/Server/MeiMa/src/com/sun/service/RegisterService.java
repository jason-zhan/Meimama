package com.sun.service;

import com.db.model.CheckCodeDB;

public class RegisterService {
	private String mCell ="408";
	//private String mCode ="1234";
	
	 public RegisterService(){
		 mCell ="408";
		// mCode ="1234";
	 }
	 public String getCell(){
		 return mCell;
	 }
	 public void setCell(String cell){
		 mCell = cell;
	 }
	 private String GenCode(){
		 String code = "";
    	 for(int i = 0; i < 4; i++){
    		 code += (int)(Math.random()*10);
    	 }
    	 
    	 return code;
	 }
     public String GenerateCode(String strCell){
    	 // judge the cellphone is exist, the code has already use.
    	 //if it is new user, generate the new code
    	 CheckCodeDB checkC = new CheckCodeDB();
    	 String code = checkC.FindCode(strCell);
    	 if(code == ""){
    		 code = GenCode();
    		 checkC.UpdateCode(strCell, code);
    	 }
    	 return code;
    	 
     }
     
     public String Register(String strCell, String strCode){
    	 // judge the cellphone is exist, the code has already use.
    	 //if it is new user, generate the new code
    	 CheckCodeDB checkC = new CheckCodeDB();
    	 String code = checkC.JudgeCode(strCell, strCode);
    	 if(code == ""){
    		 return "ERROR";
    	 }
    	 return "SUCC";
    	 
     }

}