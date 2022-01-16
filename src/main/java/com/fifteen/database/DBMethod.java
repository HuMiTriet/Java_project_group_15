package com.fifteen.database;

import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Class contains all of the method to work with the database.
 * Automatically create a connection when called but user have to close the
 * connection when they are done with it
 * to close the database
 * 
 * @see DBConnection
 *      {@link package com.twenty.one.database#closeConnection}
 *      To see the database schema
 */
public class DBMethod {
  static Connection connection = null;
  static java.sql.Statement statement = null;
  static ResultSet resultSet = null;

  private static final String TABLE_NAME = "userdb";

  private DBMethod() {
  };

  public static void createConnection() {
    connection = DBConnection.getConnection();
  }

  /**
   * This method is run to close the connection to the SQL DB server. If this
   * is not run the sever could be lag or not responsive due to the previous
   * connection still running
   * 
   * @author PJ
   */
  public static void closeConnection() {
    DbUtils.closeQuietly(connection, statement, resultSet);
  }

  private static ResultSet executeQuery(String sqlStatement) throws SQLException {
    statement = connection.createStatement();
    return statement.executeQuery(sqlStatement);
  }

  /**
   * Method adds in a new row into the userdb table in the Oracle SQL remote
   * server.
   * 
   * @param user take in a user object of the User class
   * @see User
   *      {@link package com.twenty.one.database}
   * @author PJ
   */
  public static void signUp(User user) throws SQLException {

    String sqlStatement = "INSERT INTO " + TABLE_NAME + " VALUES('" + user.getEmail() + "','"
        + user.getUsername() + "','" +
        /**
         * sys_guid() is a method of Oracle SQL to generate a Global unique Identifier.
         * 
         * @see
         */
        user.getHashedPassword() + "', sys_guid()," + user.getIsAdmin() + ")";
    executeQuery(sqlStatement);
  }

  /**
   * Since the user ID is assigned by the Oracle DB through the SYS_GUID()
   * function
   * we need to get it from the DB to assign it
   * 
   * @param user
   *             {@link package com.twenty.one.database}
   */
  public static String getUserId(User user) throws SQLException {

    String userId = "NA";
    String sqlStatement = "select user_ID from " + TABLE_NAME + " where username = '"
        + user.getUsername() + "'";
    // System.out.print(sqlStatement);
    resultSet = executeQuery(sqlStatement);
    while (resultSet.next()) {
      userId = resultSet.getString("user_ID");
    }
    return userId;
  }

  /**
   * A multirole function to check whether user entered email, username, or
   * password has already been used, e.g.,
   * chech if email: FooFar@gmail.com has been used
   * checkFieldExisted("FooBar@gmail.com",'e')
   * 
   * @param propose the user input text in that field
   * @param flag    specify whether the propose is an email e flag, username u
   *                flag, or password p flag
   * @return boolean true if field is already been used, false if not
   */
  public static boolean checkfieldExisted(String propose, char flag) throws SQLException {

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

    String sqlStatement = "select email from " + TABLE_NAME + " where exists ( select * from "
        + TABLE_NAME + " where " + fieldToBeChecked
        + " = '" + propose + "')";

    boolean fieldExisted = false;

    resultSet = executeQuery(sqlStatement);

    if (resultSet.next())
      fieldExisted = true;

    return fieldExisted;
  }

  public static String getUserHashedPasswordFromEmail(String email) throws SQLException {
    String hashedPassword = "NA";

    String sqlStatement = "select hashed_password from " + TABLE_NAME + " where email = '"
        + email + "'";

    resultSet = executeQuery(sqlStatement);

    if (resultSet.next()) {
      hashedPassword = resultSet.getString("hashed_password");
    }
    return hashedPassword;
  }

  public static void fillInUserInfoFromUserEmail(User loginUser, String hashedPassword) throws SQLException {
    String sqlStatement = "select * from " + TABLE_NAME + " where email = '"
        + loginUser.getEmail() + "'";

    resultSet = executeQuery(sqlStatement);

    while (resultSet.next()) {
      loginUser.setUsername(resultSet.getString("username"));
      // loginUser.setHashedPassword(resultSet.getString("hashed_password"));
      loginUser.setUserID(resultSet.getString("user_id"));
      loginUser.setIsAdmin(resultSet.getInt("is_admin"));
      // userId = resultSet.getString("user_ID");
    }
  }
}
