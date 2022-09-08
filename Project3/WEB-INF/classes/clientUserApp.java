import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import com.mysql.cj.jdbc.MysqlDataSource;



import java.sql.*;
import java.lang.*;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.MysqlDataSource;
// the imports below commented out are for Tomcat version 9.x.x and earlier.  Tomcat 10.0.x and later all use jakarta
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.UnavailableException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

/*
 * Carlos Garcia
 * CNT 4714 Summer 
 * Project 3
 * */
public class clientUserApp  extends HttpServlet {
	
	private Connection connection;
	   private Statement statement;
	   ResultSet resultSet;
	   boolean conSQL;
	   String cSQL ;
	   gtW2 getTable = new gtW2();
	   String table ;
	  String clientQ;
	  private  int numOfRowsUpdated;
	
	   // set up database connection and create SQL statement
	   
		   protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,IOException  {
			  
			   
			   Properties properties = new Properties();
			    FileInputStream filein = null;
			    MysqlDataSource dataSource = null;
			    //read a properties file
			    try {
			    	filein = new FileInputStream("/usr/local/apache-tomcat-10.0.22/webapps/Project3/WEB-INF/lib/client-db.properties");
			    	properties.load(filein);
			    	dataSource = new MysqlDataSource();
			    	dataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
			    	dataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
			    	dataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));	 
			    	connection = dataSource.getConnection();
				    statement = connection.createStatement();
				   
				   clientQ = request.getParameter("sqlCommand");
				   if(clientQ.contains("select") || clientQ.contains("SELECT") || clientQ.contains("select") || clientQ.contains("SELECT")|| clientQ.contains("Select")) {
					 resultSet=statement.executeQuery(clientQ);
				    table = getTable.getTable(resultSet);
				   }else {
					   numOfRowsUpdated = statement.executeUpdate(clientQ);
					   table =  "<span id=\"data\" class=\"update\" ><u><h3> The Statement Executed Successfully:"+numOfRowsUpdated+"row(s) Affected</h3></u></span>";
				   }
				    
				   
				    
				    statement.close();
				    connection.close();
				   conSQL = true;
			    }
			    catch (SQLException e){
					e.printStackTrace();
					table = e.getMessage();
					 table = "<span id=\"data\" class=\"error\" ><u><h3> Error Executing The SQL Statement: "+table+"</h3></u></span>";
					
					  
				}
			    catch (IOException e) {
			    	e.printStackTrace();
			    	
			    }
			    
			   
			    
			     
			 
			     
			     HttpSession session = request.getSession();
			     session.setAttribute("sqlCommand", clientQ);
			     session.setAttribute("table", table);
			     RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/client-Project3.jsp");
			     dispatcher.forward(request, response);
			     
			      
			     
			      
			      
			    
			   } //end doPost() method

	   
}//END OF CLASS
