
package db;

public class DSConfigBean {

	private String type     =""; //type of db
	 private String name     =""; //pool name
	 private String driver   =""; //db driver
	 private String url      =""; //db url
	 private String username =""; //user name 
	 private String password =""; //password
	 private int maxconn  =0; //max connection
	 /**
	  * 
	  */
	 public DSConfigBean() {
	  // TODO Auto-generated constructor stub
	 }
	 /**
	  * @param args
	  */
	 public static void main(String[] args) {
	  // TODO Auto-generated method stub
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
	  * @return the maxconn
	  */
	 public int getMaxconn() {
	  return maxconn;
	 }
	 /**
	  * @param maxconn the maxconn to set
	  */
	 public void setMaxconn(int maxconn) {
	  this.maxconn = maxconn;
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
	  * @return the type
	  */
	 public String getType() {
	  return type;
	 }
	 /**
	  * @param type the type to set
	  */
	 public void setType(String type) {
	  this.type = type;
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
	  * @return the username
	  */
	 public String getUsername() {
	  return username;
	 }
	 /**
	  * @param username the username to set
	  */
	 public void setUsername(String username) {
	  this.username = username;
	 }

}
