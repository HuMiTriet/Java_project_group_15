package com.fifteen.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A singleton class
 * Connects to the frankfurt UAS remote SQL server using Triet Huynh's account
 * Database Architecture:
 * 
 * @author Triet Huynh
 */
public class DBConnection {
  private final static String URL = "jdbc:oracle:thin:@db1.fb2.frankfurt-university.de:1521:info01";
  private final static String USERNAME = "S1_student2_52";
  private final static String PASSWORD = "Soltum/14032002";

  private static Connection connection = null;

  private DBConnection() {
  };

  /**
   * Establish the connection to the remote database server
   * 
   * @author Triet Huynh
   */

  static Connection getConnection() {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      // System.out.println("Connection established");
    } catch (SQLException e) {
      e.printStackTrace();
      // System.out.println("connection failed");
    }
    return connection;
  }

}
