package com.meima;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import db.ParseDSConfig;

public final class applicationStart implements ServletContextListener{
   public void contextInitialized(ServletContextEvent event){
	     ParseDSConfig pd=new ParseDSConfig();
		  String path="ds.config.xml";
		  pd.readConfigInfo(path);
   }
   public void contextDestroyed(ServletContextEvent event){
	   
   }
}
