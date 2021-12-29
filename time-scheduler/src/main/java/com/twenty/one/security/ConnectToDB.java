package com.twenty.one.security;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author PJ
 */
public class ConnectToDB {
	private ConnectToDB(){};

	private static Connection connection;
    public static void connect()
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
	public static void addUser(String email, String username, String hashedPassword) {
		String sqlStatement = "INSERT INTO userdb VALUES('"+ email +"','"+ username+ "','" +
		hashedPassword + "', sys_guid(),0)";
		System.out.println(sqlStatement);
		try {
			java.sql.Statement statement = connection.createStatement();
			int rows = statement.executeUpdate(sqlStatement);
			if (rows > 0) System.out.println("User added");
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("User added failed");
		}
	}

}
