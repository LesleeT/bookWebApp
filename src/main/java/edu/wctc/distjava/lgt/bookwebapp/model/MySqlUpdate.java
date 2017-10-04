/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.lgt.bookwebapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author Leslee
 */
public class MySqlUpdate {
    
  public static void main(String[] args)
  {
    try
    {
      // create the mysql database connection
      String myDriver = "com.mysql.jdbc.Driver";
      String myUrl = "jdbc:mysql://localhost:3306/book";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "admin");

      String query = "update author set author_id = ? where author_name = ?";
      PreparedStatement preparedStmt = conn.prepareStatement(query);
     // preparedStmt.setInt   (1, 25);
      //preparedStmt.setString(2, "Bob Smith");
           
      // execute the preparedstatement
     // preparedStmt.execute();
      //close the connection
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println(e.getMessage());
    }

  }
}