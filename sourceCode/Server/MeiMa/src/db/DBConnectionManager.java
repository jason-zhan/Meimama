package db;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
//import com.chunkyo.db.ParseDSConfig;
//import com.chunkyo.db.DSConfigBean;

public class DBConnectionManager {

	/**
	 * @author Selina Zhu
	 *
	 */
	 static private DBConnectionManager instance;//sington design pattern
	 static private int clients;                 //client number
	 private Vector drivers  = new Vector();//driver information 
	 private Hashtable pools=new Hashtable();//connection pool
	 
	 /**
	  * init
	  */
	 public DBConnectionManager() {
	  // TODO Auto-generated constructor stub
	  this.init();
	 }
	 /**
	  * get single instance
	  * @return
	  */
	 static synchronized public DBConnectionManager getInstance()
	 {
	  if(instance==null)
	  {
	   instance = new DBConnectionManager();
	  }
	  return instance;
	  
	 }
	 /**
	  * release
	  * @param name
	  * @param con
	  */
	 public void freeConnection(String name, Connection con)
	 {
	  DBConnectionPool pool=(DBConnectionPool)pools.get(name);//
	  if(pool!=null)
	  pool.freeConnection(con);//
	 }
	 /**
	  * get connection by name
	  * @param name
	  * @return
	  */
	 public Connection getConnection(String name)
	 {
	  DBConnectionPool pool=null;
	  Connection con=null;
	  pool=(DBConnectionPool)pools.get(name);
	  con=pool.getConnection();
	  if(con!=null)
	  System.out.println("get connection success");
	  return con;
	 }
	 /**
	  * get connection by time 
	  * @param name
	  * @param time
	  * @return
	  */
	 public Connection getConnection(String name, long timeout)
	 {
	  DBConnectionPool pool=null;
	  Connection con=null;
	  pool=(DBConnectionPool)pools.get(name);
	  con=pool.getConnection(timeout);
	  System.out.println("get connection������");
	  return con;
	 }
	 /**
	  * release connection
	  */
	 public synchronized void release()
	 {
	  Enumeration allpools=pools.elements();
	  while(allpools.hasMoreElements())
	  {
	   DBConnectionPool pool=(DBConnectionPool)allpools.nextElement();
	   if(pool!=null)pool.release();
	  }
	  pools.clear();
	 }
	 /**
	  * create connection
	  * @param props
	  */
	 private void createPools(DSConfigBean dsb)
	 {
	  DBConnectionPool dbpool=new DBConnectionPool();
	  dbpool.setName(dsb.getName());
	  dbpool.setDriver(dsb.getDriver());
	  dbpool.setUrl(dsb.getUrl());
	  dbpool.setUser(dsb.getUsername());
	  dbpool.setPassword(dsb.getPassword());
	  dbpool.setMaxConn(dsb.getMaxconn());
	  //dbpool.getConnection();
	  System.out.println("ioio:"+dsb.getMaxconn());
	  pools.put(dsb.getName(), dbpool);
	 }
	 /**
	  * init database connection
	  */
	 private void init()
	 {
	  
	  this.loadDrivers();
	  Iterator alldriver=drivers.iterator();
	  while(alldriver.hasNext())
	  {
	   this.createPools((DSConfigBean)alldriver.next());
	   System.out.println("Create connection success.");
	   
	  }
	  System.out.println("create connection finish");
	 }
	 /**
	  * Load driver
	  * @param props
	  */
	 private void loadDrivers()
	 {
	  ParseDSConfig pd=new ParseDSConfig();
	 //read db config file
	  drivers=pd.readConfigInfo("ds.config.xml");
	  System.out.println("loading drive......");
	 }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
