package db;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;
import java.util.Iterator;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ParseDSConfig {

	 public ParseDSConfig() {
		  // TODO Auto-generated constructor stub
		 }
		 /**
		  * read xml config file
		  * @param path
		  * @return
		  */
		 public Vector readConfigInfo(String path)
		 {
		  String rpath=this.getClass().getResource("").getPath().substring(0)+path;
		  Vector dsConfig=null;
		  FileInputStream fi = null;
		  try
		  {
		   fi=new FileInputStream(rpath);
		   dsConfig=new Vector();
		   SAXBuilder sb=new SAXBuilder();
		   Document doc=sb.build(fi);
		   Element root=doc.getRootElement();
		   List pools=root.getChildren();
		   Element pool=null;
		   Iterator allPool=pools.iterator();
		   while(allPool.hasNext())
		   {
		    pool=(Element)allPool.next();
		    DSConfigBean dscBean=new DSConfigBean();
		    dscBean.setType(pool.getChild("type").getText());
		    dscBean.setName(pool.getChild("name").getText());
		    System.out.println(dscBean.getName());
		    dscBean.setDriver(pool.getChild("driver").getText());
		    dscBean.setUrl(pool.getChild("url").getText());
		    dscBean.setUsername(pool.getChild("username").getText());
		    dscBean.setPassword(pool.getChild("password").getText());
		    dscBean.setMaxconn(Integer.parseInt(pool.getChild("maxconn").getText()));
		    dsConfig.add(dscBean);
		   }
		   
		  } catch (FileNotFoundException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  } catch (JDOMException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  
		  finally
		  {
		   try {
		    fi.close();
		   } catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
		  }
		  
		  return dsConfig;
		 }
		/**
		 *
		 */
		 public void modifyConfigInfo(String path,DSConfigBean dsb) throws Exception
		 {
		  String rpath=this.getClass().getResource("").getPath().substring(1)+path;
		  FileInputStream fi=null; //
		  FileOutputStream fo=null; //
		  
		 }
		/**
		 *add to config file
		 *
		 */
		 public void addConfigInfo(String path,DSConfigBean dsb) 
		 {
		  String rpath=this.getClass().getResource("").getPath().substring(1)+path;
		  FileInputStream fi=null;
		  FileOutputStream fo=null;
		  try
		  {
		   fi=new FileInputStream(rpath);
		   
		   SAXBuilder sb=new SAXBuilder();
		   
		   Document doc=sb.build(fi); 
		   Element root=doc.getRootElement();
		   List pools=root.getChildren();
		   
		   Element newpool=new Element("pool");
		   
		   Element pooltype=new Element("type"); 
		   pooltype.setText(dsb.getType());
		   newpool.addContent(pooltype);
		   
		   Element poolname=new Element("name");
		   poolname.setText(dsb.getName());
		   newpool.addContent(poolname);
		   
		   Element pooldriver=new Element("driver"); 
		   pooldriver.addContent(dsb.getDriver());
		   newpool.addContent(pooldriver);
		   
		   Element poolurl=new Element("url");
		   poolurl.setText(dsb.getUrl());
		   newpool.addContent(poolurl);
		   
		   Element poolusername=new Element("username");
		   poolusername.setText(dsb.getUsername());
		   newpool.addContent(poolusername);
		   
		   Element poolpassword=new Element("password");
		   poolpassword.setText(dsb.getPassword());
		   newpool.addContent(poolpassword);
		   
		   Element poolmaxconn=new Element("maxconn");
		   poolmaxconn.setText(String.valueOf(dsb.getMaxconn()));
		   newpool.addContent(poolmaxconn);
		   pools.add(newpool);
		   Format format = Format.getPrettyFormat();
		      format.setIndent("");
		      format.setEncoding("utf-8");
		      XMLOutputter outp = new XMLOutputter(format);
		      fo = new FileOutputStream(rpath);
		      outp.output(doc, fo);
		  } catch (FileNotFoundException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  } catch (JDOMException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  finally
		  {
		   
		  }
		 }
		 /**
		  *delete config file
		  */
		 public void delConfigInfo(String path,String name)
		 {
		  String rpath=this.getClass().getResource("").getPath().substring(1)+path;
		  FileInputStream fi = null;
		  FileOutputStream fo=null;
		  try
		  {
		   fi=new FileInputStream(rpath);
		   SAXBuilder sb=new SAXBuilder();
		   Document doc=sb.build(fi);
		   Element root=doc.getRootElement();
		   List pools=root.getChildren();
		   Element pool=null;
		   Iterator allPool=pools.iterator();
		   while(allPool.hasNext())
		   {
		    pool=(Element)allPool.next();
		    if(pool.getChild("name").getText().equals(name))
		    {
		     pools.remove(pool);
		     break;
		    }
		   }
		   Format format = Format.getPrettyFormat();
		      format.setIndent("");
		      format.setEncoding("utf-8");
		      XMLOutputter outp = new XMLOutputter(format);
		      fo = new FileOutputStream(rpath);
		      outp.output(doc, fo);
		   
		  } catch (FileNotFoundException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  } catch (JDOMException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  
		  finally
		  {
		   try {
		    fi.close();
		   } catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
		  }
		 }
		
}
