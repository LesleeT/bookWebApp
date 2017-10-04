/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.lgt.bookwebapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;

/**
 *
 * @author Leslee
 */
public class MySqlCreate {
 public static void main(String[] args)
  {
    try
    {
      // create the mysql database connection
      String myDriver = "com.mysql.jdbc.Driver";
      String myUrl = "jdbc:mysql://localhost:3306/book";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "admin");

      Calendar calendar = Calendar.getInstance();
      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
      
      String query = " insert into author (author_id, author_name, date_added)"
        + " values (?, ?, ?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setInt (1, 4);
      preparedStmt.setString (2, "Mister Rodgers");
      preparedStmt.setDate   (3, startDate);
           
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