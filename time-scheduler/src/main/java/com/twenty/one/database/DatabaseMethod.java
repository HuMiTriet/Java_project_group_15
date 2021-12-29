package com.twenty.one.database;

import java.sql.SQLException;


import java.sql.Connection;

public class DatabaseMethod {
	static Connection connection = DataBaseConnection.getConnection();

	public static boolean signUp(User user){
		String sqlStatement = "INSERT INTO userdb VALUES('"+ user.getEmail() +"','"+ user.getUsername() + "','" +
		user.getHashedPassword() + "', sys_guid(),"+ user.getIsAdmin() +")";
		System.out.println(sqlStatement);
		try {
			java.sql.Statement statement = connection.createStatement();
			int rowsUpdated = statement.executeUpdate(sqlStatement);
			connection.close();
			if (rowsUpdated > 0) return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("User added failed");
			return false;
		}

	}
}
