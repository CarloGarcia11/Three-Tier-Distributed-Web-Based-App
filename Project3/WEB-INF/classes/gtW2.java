// Expansion of the Simple JDBC Example which uses Metadata 
// Java application program connects to the bikedb database and has the query SELECT (attribute set) FROM bikes
// executed with results displayed in the command window.

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import com.mysql.cj.jdbc.MysqlDataSource;
/*
 * Carlos Garcia
 * CNT 4714 Summer 
 * Project 3
 * */
public class gtW2 
{
	public String tableR;
	public String ckR;
	ResultSet resultSet;
   // launch the application
   public String getTable(ResultSet resultSet)
   {
	 
	    try {
	    	
	    	
	    	this.resultSet = resultSet;
	         
	         // query the database
	         
	         // process query results 
	         ResultSetMetaData metaData = resultSet.getMetaData();
	         int numberOfColumns = metaData.getColumnCount();
	         System.out.println( "Bikes Table of bikedb Database:" );
	         
	         String table ="<table id=\"data\">";
	         System.out.println("<table>");
	         
	         String tableHead="<tr>";
	         System.out.println("<tr>");
	         for ( int i = 1; i <= numberOfColumns; i++ ) {
	        	 tableHead+= "<th>"+metaData.getColumnName( i )+"</th>";
	            System.out.printf( "%-20s\t", "<th>"+metaData.getColumnName( i )+"</th>" );
	         }
	         tableHead+="</tr>";
	         System.out.println("</tr>");
	         
	         for ( int i = 1; i <= numberOfColumns; i++ )
	             System.out.printf( "%-20s\t", "-------------------" );
	          System.out.println();
	        
	          String tablerows="";
	         while ( resultSet.next() ) 
	         {	
	        	 
	        	 tablerows +="<tr>";
	        	 System.out.println("<tr>");
	            for ( int i = 1; i <= numberOfColumns; i++ ) {
	            	
	               System.out.printf( "%-20s\t", "<td>"+resultSet.getObject( i )+"</td>" );
	            String	rsString = resultSet.getObject(i).toString();
	            tablerows += "<td>"+rsString+"</td>" ;
	           
	               }
	            tablerows+="</tr>";
	            System.out.println("</tr>");
	         } // end while
	        
	         System.out.println("</table>");
	         System.out.println(); System.out.println();
	         table+= tableHead+tablerows+"</table>";
	         System.out.println("/n/n"+table);
	         //close connection and statement
	        
	         tableR = table;
	    
	      }  // end try
	      catch ( SQLException sqlException ) 
	      {
	        sqlException.printStackTrace();
	        tableR = sqlException.getMessage();
	        return tableR;
	        
	      } // end catch  
	    System.out.println(tableR);
	   
		return tableR;
     } // end of getTable
}  // end class DisplayBikes
