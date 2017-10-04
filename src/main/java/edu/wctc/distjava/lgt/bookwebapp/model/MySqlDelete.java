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
public class MySqlDelete
{

  public static void main(String[] args)
  {
    try
    {
      // create the mysql database connection
      String myDriver = "com.mysql.jdbc.Driver";
      String myUrl = "jdbc:mysql://localhost:3306/book";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "admin");
      
      // create the mysql delete statement.
      String query = "delete from author where author_id = ?";
      //PreparedStatement preparedStmt = conn.prepareStatement(query);
     // preparedStmt.setInt(1, 2);

      // execute the preparedstatement
      //preparedStmt.execute();
      //close the connection
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println(e.getMessage());
    }

  }
}
