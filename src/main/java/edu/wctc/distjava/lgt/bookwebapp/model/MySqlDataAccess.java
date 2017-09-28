package edu.wctc.distjava.lgt.bookwebapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Leslee
 */
public class MySqlDataAccess {
    private Connection conn;
    
   public void openConnection(String driverClass, String url, String userName, String password)
           throws ClassNotFoundException, SQLException{
       
       Class.forName(driverClass);
       conn = DriverManager.getConnection(url,userName,password);
   }
   
   public void closeConnection()throws SQLException{
       if (conn!= null) conn.close();
   }
   
   
   
}
