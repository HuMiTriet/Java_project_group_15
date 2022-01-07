package com.twenty.one.database;

import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Class contains all of the method to work with the database.
 * Automatically create a connection when called but user have to close the connection when they are done with it
 * to close the database
 * @see DBConnection
 *  {@link package com.twenty.one.database#closeConnection}
 * To see the database schema 
 */
public class DBMethod {
	static Connection connection = DBConnection.getConnection();
	static java.sql.Statement statement = null;
	static ResultSet resultSet = null;


	/**
	 * This method is run to close the connection to the SQL DB server. If this
	 * is not run the sever could be lag or not responsive due to the previous
	 * connection still running
	 * @author PJ
	 */
	public static void closeConnection() throws SQLException {
		DbUtils.closeQuietly(connection, statement, resultSet);
	}
	/**
	 * Method adds in a new row into the userdb table in the Oracle SQL remote server.
	 * @param user take in a user object of the User class
	 * @see User
	 * {@link package com.twenty.one.database}
	 * @author PJ
	 */
	public static void signUp(User user) throws SQLException{


		String sqlStatement = "INSERT INTO userdb VALUES('"+ user.getEmail() +"','"+ user.getUsername() + "','" +
		/** sys_guid() is a method of Oracle SQL to generate a Global unique Identifier.
		 *  @see
		 */
		user.getHashedPassword() + "', sys_guid(),"+ user.getIsAdmin() +")";
		statement = connection.createStatement();
		int rowsUpdated = statement.executeUpdate(sqlStatement);
		System.out.println("Updated: " + rowsUpdated + " in the database");
	}

	/**
	 * Since the user ID is assigned by the Oracle DB through the SYS_GUID() function
	 * we need to get it from the DB to assign it
	 * @param user 
	 * {@link package com.twenty.one.database} 
	 */
	public static String getUserId(User user) throws SQLException {

		String userId = "NA";
		String sqlStatement = "select user_ID from userdb where username = '"+user.getUsername() +"'";
		// System.out.print(sqlStatement);
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sqlStatement);
			while(resultSet.next()) {
				userId = resultSet.getString("user_ID");
			}
		return userId;
	}

	/**
	 * A multirole function to check whether user entered email, username, or password has already been used, e.g.,
	 * chech if email: FooFar@gmail.com has been used checkFieldExisted("FooBar@gmail.com",'e')
	 * @param propose the user input text in that field
	 * @param flag specify whether the propose is an email e flag, username u flag, or password p flag 
	 * @return boolean true if field is already been used, false if not
	 */
	public static boolean checkfieldExisted(String propose, char flag) throws SQLException{

		String fieldToBeChecked = "";

		switch (flag) {
			case 'e':
				fieldToBeChecked = "email";
				break;
			case 'u':
				fieldToBeChecked = "username";
				break;
			case 'p':
				fieldToBeChecked = "password";
				break;
			default:
				System.err.println("Flag invalid, (e)mail, (p)assword, (u)sername");
				break;
		}

		String sqlStatment = "select email from userdb where exists ( select * from userdb where"+ fieldToBeChecked
		 						+" = '"+ propose +"')";

		boolean fieldExisted = false;
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sqlStatment);
		if (resultSet.next()) fieldExisted = true;

		return fieldExisted;
	}
}