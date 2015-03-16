package db;

import java.sql.*; 
public class DBTest {
	public void init(){
		  ParseDSConfig pd=new ParseDSConfig();
		  String path="ds.config.xml";
		  pd.readConfigInfo(path);
		  //pd.delConfigInfo(path, "tj012006");
		 /* DSConfigBean dsb=new DSConfigBean();
		  dsb.setType("oracle");
		  dsb.setName("yyy004");
		  dsb.setDriver("org.oracle.jdbc");
		  dsb.setUrl("jdbc:oracle://localhost");
		  dsb.setUsername("sa");
		  dsb.setPassword("");
		  dsb.setMaxconn(1000);
		  pd.addConfigInfo(path, dsb);
		  pd.delConfigInfo(path, "yyy001");*/
	}
	public void test(){
		 DBConnectionManager   connectionMan = DBConnectionManager.getInstance();
		 String name="meima";
		 Connection  conn = connectionMan.getConnection(name);
			try {

			Statement statement = conn.createStatement();

			// Ҫִ�е�SQL���
			String sql = "select * from book;";
			ResultSet rs = statement.executeQuery(sql);  
			System.out.println("-----------------");  
			System.out.println("-----------------");  
			System.out.println(" id" + "\t" + " name");  
			System.out.println("-----------------");  
			String name1 = null;  
			while(rs.next()) {  
			name1 = rs.getString("name");
			System.out.println(rs.getInt("id") + "\t" + name1);  
			}  
			rs.close();  
			}  catch(SQLException e) {   
			e.printStackTrace();   
			} catch(Exception e) {   
			e.printStackTrace();   
			} 

		 connectionMan.freeConnection(name,conn);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DBTest te = new DBTest();
		te.init();
		te.test();
		
		/*String driver = "com.mysql.jdbc.Driver";
		
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "123456";
		try {
		// ������������
		Class.forName(driver);
		// �������ݿ�
		Connection conn = DriverManager.getConnection(url, user, password);
		if(!conn.isClosed()){
			System.out.println("Succeeded connecting to the Database!");
		}

		// statement����ִ��SQL���

		Statement statement = conn.createStatement();

		// Ҫִ�е�SQL���
		String sql = "select * from book;";
		ResultSet rs = statement.executeQuery(sql);  
		System.out.println("-----------------");  
		System.out.println("ִ�н��������ʾ:");  
		System.out.println("-----------------");  
		System.out.println(" id" + "\t" + " name");  
		System.out.println("-----------------");  
		String name = null;  
		while(rs.next()) {  
		name = rs.getString("bookname");
		System.out.println(rs.getString("id") + "\t" + name);  
		}  
		rs.close();  
		conn.close();   
		} catch(ClassNotFoundException e) {   
		  
		   System.out.println("Sorry,can`t find the Driver!");   
		e.printStackTrace();   
		} catch(SQLException e) {   
		e.printStackTrace();   
		} catch(Exception e) {   
		e.printStackTrace();   
		}    */

	}
}
