import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*
 * Carlos Garcia
 * CNT 4714 Summer 
 * Project 3
 * */
public class dataEntryApp extends HttpServlet{
	private Connection connection;
	   private PreparedStatement prestatement;
	   private Statement statement;
	
	private String snumE;
	private int snum;
	private String pnumE;
	private int pnum;
	private String jnumE;
	private int jnum;
	private String quantityE;
	private int quantity;
	
	String logic;
	
	 String table ;
//	private Connection connection;
//	private Statement statement;
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,IOException  {
		  
		   
		   Properties properties = new Properties();
		    FileInputStream filein = null;
		    MysqlDataSource dataSource = null;
		   // read a properties file
		    try {
		    	filein = new FileInputStream("/usr/local/apache-tomcat-10.0.22/webapps/Project3/WEB-INF/lib/root-db.properties");
		    	properties.load(filein);
		    	dataSource = new MysqlDataSource();
		    	dataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
		    	dataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
		    	dataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));	 
		    	connection = dataSource.getConnection();
			    
			   
			    snumE = request.getParameter("snum");
			    
			    pnumE = request.getParameter("pnum");
			    
			    
			    jnumE = request.getParameter("jnum");
			
			    
			    quantityE = request.getParameter("quantity");
			    quantity = Integer.parseInt(quantityE);
			    
			    prestatement = connection.prepareStatement("insert into shipments(snum,pnum,jnum,quantity)values (?,?,?,?)");
			    prestatement.setString(1, snumE);
			    prestatement.setString(2, pnumE);
			    prestatement.setString(3, jnumE);
			    prestatement.setInt(4, quantity);
			    
			    table = snum +" " + pnum +" " + jnum +" " + quantity;
			    int rowsA=0;
			    rowsA =prestatement.executeUpdate();
			    
			    table = "number of rows affected"+rowsA;
			    
			    if(quantity >= 100) {
			    	statement = connection.createStatement();
			    	statement.executeUpdate("update suppliers set status = status + 5 where snum in (select snum from shipments where quantity >= 100)");
			    	logic = "<h3> Business Logic Triggered  </h3>";
			    }else {
			    	logic = "<h3> Business NOT Logic Triggered  </h3>";
			    }
			   
			    table = "<span id=\"data\" class=\"update\" ><u><h3> Numbers Input : "+table+"</h3>" +logic+"</u></span>";
			    
			    prestatement.close();
			    connection.close();
			   
		    }
		    catch (SQLException e){
				e.printStackTrace();
				table = e.getMessage();
				 table = "<span id=\"data\" class=\"error\" ><u><h3> Error Executing The SQL Statement: "+table+"</h3></u></span>";
				
				  
			}
		   
	    	
		    
		   
		    
		     
		 
		     
		     HttpSession session = request.getSession();
		     session.setAttribute("snum", snumE);
		     session.setAttribute("pnum", pnumE);
		     session.setAttribute("jnum", jnumE);
		     session.setAttribute("quantity", quantityE);
		     session.setAttribute("table", table);
		     RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/entry-Project3.jsp");
		     dispatcher.forward(request, response);
		     
		      
		     
		      
		      
		    
		   } //e

}
