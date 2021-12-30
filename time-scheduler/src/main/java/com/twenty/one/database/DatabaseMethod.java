package com.twenty.one.database;

import java.sql.SQLException;

import com.twenty.one.security.HashPassword;

import java.sql.Connection;

public class DatabaseMethod {
	static Connection connection = DataBaseConnection.getConnection();

	/**
	 * This method is run to close the connection to the SQL DB server. If this
	 * is not run the sever could be lag or not responsive due to the previous
	 * connection still running
	 * @author PJ
	 */
	private static void closeConnection() {
		try {
			connection.close();
			System.out.println("Connection close sucessfully");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection close failed");
		}
	}

	/**
	 * Method adds in a new row into the userdb table in the Oracle SQL remote server.
	 * @param user take in a user object of the User class
	 * @see User
	 * {@link package com.twenty.one.database}
	 * @author PJ
	 */
	public static void signUp(User user){
		String hashedPassword = HashPassword.sha2(user.getTextPassword());

		String sqlStatement = "INSERT INTO userdb VALUES('"+ user.getEmail() +"','"+ user.getUsername() + "','" +
		/** sys_guid() is a method of Oracle SQL to generate a Global unique Identifier.
		 *  @see
		 */
		hashedPassword + "', sys_guid(),"+ user.getIsAdmin() +")";
		System.out.println(sqlStatement);
		try {
			java.sql.Statement statement = connection.createStatement();
			int rowsUpdated = statement.executeUpdate(sqlStatement);
			System.out.println("Updated: " + rowsUpdated + " in the database");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("User added failed");
		} finally {
			closeConnection();
		}
	}
	// public static String getUserId(User user) {

	// }
}
