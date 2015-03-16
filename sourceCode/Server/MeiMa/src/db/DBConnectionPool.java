package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;

public class DBConnectionPool  {

	
	/**
	 * @author Selina Zhu
	 *
	 */

	 private Connection con=null;
	 private int inUsed=0;    //used connection
	 private ArrayList freeConnections = new ArrayList();//idel connection
	 private int minConn;     //min connection
	 private int maxConn;     //max connection
	 private String name;     //pool name
	 private String password; //password
	 private String url;      // url
	 private String driver;   // driver
	 private String user;     // username
	 public Timer timer;      //
	 /**
	  * 
	  */
	 public DBConnectionPool() {
	  // TODO Auto-generated constructor stub
	 }
	 /**
	  * create pool
	  * @param driver
	  * @param name
	  * @param URL
	  * @param user
	  * @param password
	  * @param maxConn
	  */
	 public DBConnectionPool(String name, String driver,String URL, String user, String password, int maxConn)
	 {
	  this.name=name;
	  this.driver=driver;
	  this.url=URL;
	  this.user=user;
	  this.password=password;
	  this.maxConn=maxConn;
	 }
	 /**
	  * release pool
	  * @param con
	  */
	 public synchronized void freeConnection(Connection con) 
	 {
	  this.freeConnections.add(con);//add to idle tail
	  this.inUsed--;
	 }
	 /**
	  * timeout get connection
	  * @param timeout
	  * @return
	  */
	 public synchronized Connection getConnection(long timeout)
	 {
	  Connection con=null;
	  if(this.freeConnections.size()>0)
	  {
	   con=(Connection)this.freeConnections.get(0);
	   if(con==null)con=getConnection(timeout); //continue to connection
	  }
	  else
	  {
	   con=newConnection(); //create connection
	  }
	  if(this.maxConn==0||this.maxConn<this.inUsed)
	  {
	   con=null;//reach max, can not get connection��
	  }
	  if(con!=null)
	  {
	   this.inUsed++;
	  }
	  return con;
	 }
	 /**
	  * 
	  * get connection from pool
	  * @return
	  */
	 public synchronized Connection getConnection()
	 {
	  Connection con=null;
	  if(this.freeConnections.size()>0)
	  {
	   con=(Connection)this.freeConnections.get(0);
	   this.freeConnections.remove(0);
	   if(con==null)con=getConnection(); 
	  }
	  else
	  {
	   con = newConnection(); 
	  }
	  if(this.maxConn==0||this.maxConn<this.inUsed)
	  {
	   con=null;
	  }
	  if(con!=null)
	  {
	   this.inUsed++;
	   System.out.println("get connection"+this.name+" connetion now "+inUsed+"connection in use!");
	  }
	  return con;
	 }
	 /**
	  *release connection
	  *
	  */
	 public synchronized void release()
	 {
	  Iterator allConns=this.freeConnections.iterator();
	  while(allConns.hasNext())
	  {
	   Connection con=(Connection)allConns.next();
	   try
	   {
	    con.close();
	   }
	   catch(SQLException e)
	   {
	    e.printStackTrace();
	   }
	   
	  }
	  this.freeConnections.clear();
	   
	 }
	 /**
	  * create new connection
	  * @return
	  */
	 private Connection newConnection()
	 {
	  try {
	   Class.forName(driver);
	   con=DriverManager.getConnection(url, user, password);
	  } catch (ClassNotFoundException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	   System.out.println("sorry can't find db driver!");
	  } catch (SQLException e1) {
	   // TODO Auto-generated catch block
	   e1.printStackTrace();
	   System.out.println("sorry can't create Connection!");
	  }
	  return con;
	  
	 }
	 /**
	  * timer function
	  */
	 public synchronized void TimerEvent() 
	 {
	     //not define
	 }
	
	 /**
	  * @return the driver
	  */
	 public String getDriver() {
	  return driver;
	 }
	 /**
	  * @param driver the driver to set
	  */
	 public void setDriver(String driver) {
	  this.driver = driver;
	 }
	 /**
	  * @return the maxConn
	  */
	 public int getMaxConn() {
	  return maxConn;
	 }
	 /**
	  * @param maxConn the maxConn to set
	  */
	 public void setMaxConn(int maxConn) {
	  this.maxConn = maxConn;
	 }
	 /**
	  * @return the minConn
	  */
	 public int getMinConn() {
	  return minConn;
	 }
	 /**
	  * @param minConn the minConn to set
	  */
	 public void setMinConn(int minConn) {
	  this.minConn = minConn;
	 }
	 /**
	  * @return the name
	  */
	 public String getName() {
	  return name;
	 }
	 /**
	  * @param name the name to set
	  */
	 public void setName(String name) {
	  this.name = name;
	 }
	 /**
	  * @return the password
	  */
	 public String getPassword() {
	  return password;
	 }
	 /**
	  * @param password the password to set
	  */
	 public void setPassword(String password) {
	  this.password = password;
	 }
	 /**
	  * @return the url
	  */
	 public String getUrl() {
	  return url;
	 }
	 /**
	  * @param url the url to set
	  */
	 public void setUrl(String url) {
	  this.url = url;
	 }
	 /**
	  * @return the user
	  */
	 public String getUser() {
	  return user;
	 }
	 /**
	  * @param user the user to set
	  */
	 public void setUser(String user) {
	  this.user = user;
	 }

}
