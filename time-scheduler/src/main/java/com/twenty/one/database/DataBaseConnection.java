package com.twenty.one.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 * A singleton class
 * Connects to the frankfurt UAS remote SQL server using PJ's account 
 * @author PJ
 */
public class DataBaseConnection {
	private static Connection connection = null;

	
	private DataBaseConnection(){};

	/**
	 * Establish the connection to the remote database server
	 * @author PJ
	 */
    static
    {
        String url = "jdbc:oracle:thin:@db1.fb2.frankfurt-university.de:1521:info01";
        String username = "S1_student2_52";
        String password = "Soltum/14032002";

        try {
            connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection established");
        } catch (SQLException e) {
            e.printStackTrace();
			System.out.println("connection failed");
        }

    }
	public static Connection getConnection() {
		return connection;
	}

}