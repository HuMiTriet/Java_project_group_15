package com.twenty.one.database;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Class contains all of the method to work with the database.
 * Automatically create a connection when called but user have to close the connection when they are done with it
 * to close the database
 * @see DBConnection
 *  {@link package com.twenty.one.database#closeConnection}
 */
public class DBMethod {
	static Connection connection = DBConnection.getConnection();


	/**
	 * Method adds in a new row into the userdb table in the Oracle SQL remote server.
	 * @param user take in a user object of the User class
	 * @see User
	 * {@link package com.twenty.one.database}
	 * @author PJ
	 */
	public static void signUp(User user){


		String sqlStatement = "INSERT INTO userdb VALUES('"+ user.getEmail() +"','"+ user.getUsername() + "','" +
		/** sys_guid() is a method of Oracle SQL to generate a Global unique Identifier.
		 *  @see
		 */
		user.getHashedPassword() + "', sys_guid(),"+ user.getIsAdmin() +")";
		// System.out.println(sqlStatement);
		try(java.sql.Statement statement = connection.createStatement()) {
			int rowsUpdated = statement.executeUpdate(sqlStatement);
			System.out.println("Updated: " + rowsUpdated + " in the database");
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("User added failed");
		}
	}

	/**
	 * Since the user ID is assigned by the Oracle DB through the SYS_GUID() function
	 * we need to get it from the DB to assign it
	 * @param user 
	 * {@link package com.twenty.one.database} 
	 */
	public static void getUserId(User user) {
		String sqlStatement = "select user_ID from userdb where username = '"+user.getUsername() +"'";
		// System.out.print(sqlStatement);
		try (java.sql.Statement statement = connection.createStatement() ) {
			ResultSet userIdSet = statement.executeQuery(sqlStatement);
			while(userIdSet.next()) {
				String userId = userIdSet.getString("user_ID");
				user.setUserID(userId);
				userIdSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A multirole function to check whether user entered email, username, or password has already been used, e.g.,
	 * chech if email: FooFar@gmail.com has been used checkFieldExisted("FooBar@gmail.com",'e')
	 * @param propose the user input text in that field
	 * @param flag specify whether the propose is an email e flag, username u flag, or password p flag 
	 * @return boolean true if field is already been used, false if not
	 */
	public static boolean checkfieldExisted(String propose, char flag) {

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
		try(java.sql.Statement statement = connection.createStatement()) {
			ResultSet existingFieldSet = statement.executeQuery(sqlStatment);
			if (existingFieldSet.next()) fieldExisted = true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return fieldExisted;
	}
}