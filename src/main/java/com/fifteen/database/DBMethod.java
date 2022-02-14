package com.fifteen.database;

import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Class contains all of the method to work with the database.
 * Automatically create a connection when called but user have to close the
 * connection when they are done with it to close the database
 * 
 * @see DBConnection
 *      {@link package com.twenty.one.database#closeConnection}
 *      To see the database schema
 * @author Triet Huynh
 */
public class DBMethod {
  static Connection connection = null;
  static java.sql.Statement statement = null;
  static ResultSet resultSet = null;

  private static final String TABLE_NAME = "userdb";

  private DBMethod() {
  };

  public static void createConnection() {
    if (connection == null)
      connection = DBConnection.getConnection();
  }

  /**
   * This method is run to close the connection to the SQL DB server. If this
   * is not run the sever could be lag or not responsive due to the previous
   * connection still running
   * 
   * @author Triet Huynh
   */
  public static void closeConnection() {
    if (connection != null) {
      DbUtils.closeQuietly(connection, statement, resultSet);
      connection = null;
    }
  }

  /**
   * Wrapper function to execute a query on the remote database.
   * param: sqlStatement - the sql statement to be executed
   * 
   * @throws SQLException if can't execute the query
   * @return ResultSet the result of the query
   * 
   * @author Triet Huynh
   */
  private static ResultSet executeQuery(String sqlStatement) throws SQLException {
    statement = connection.createStatement();
    return statement.executeQuery(sqlStatement);
  }

  /**
   * Method run to create a new user in the remote database and assign them with
   * a unique user id.
   * 
   * @param user take in a user object of the User class
   *             Method adds in a new row into the userdb table in the Oracle SQL
   *             remote
   *             server.
   * 
   * @throws SQLException if can't execute the query
   * 
   * @see User
   *      {@link package com.twenty.one.database}
   * @author Triet Huynh
   */
  public static void signUp(User user) throws SQLException {

    String sqlStatement = "INSERT INTO " + TABLE_NAME
        + " (email, username, hashed_password, user_id, is_admin) VALUES('" + user.getEmail() + "','"
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
   * function we need to get it from the DB to assign it to the user as login.
   * 
   * @param user
   *             {@link package com.twenty.one.database}
   * @throws SQLException - if can't retrieve user id
   * @return userID - the user id of the user as a String class
   * @author Triet Huynh
   */
  public static String getUserId(User user) throws SQLException {

    String userId = "NA";
    String sqlStatement = "select user_ID from " + TABLE_NAME + " where email = '"
        + user.getEmail() + "'";
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
   * @author Triet Huynh
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

  public static void changeFieldExisted(User user, String propose, char flag) throws SQLException {

    String fieldToBeChecked = "";
    String originalValue = "";

    switch (flag) {
      case 'e':
        fieldToBeChecked = "email";
        originalValue = user.getEmail();
        break;
      case 'u':
        fieldToBeChecked = "username";
        originalValue = user.getUsername();
        break;
      case 'p':
        fieldToBeChecked = "password";
        originalValue = user.getHashedPassword();
        break;
      default:
        System.err.println("Flag invalid, (e)mail, (p)assword, (u)sername");
        break;
    }

    String sqlStatement = "update " + TABLE_NAME + " set " + fieldToBeChecked + " = '" + propose
        + "' where " + fieldToBeChecked + " = '" + originalValue + "'";

    executeQuery(sqlStatement);
  }

  /***
   * Query the remote database to get the user's hased password store on it
   * 
   * @param email - the email of the user
   * 
   * @throws SQLException if can't retrieve the password
   * 
   * @return String the hashed password of the user
   * 
   * @author Triet Huynh
   */
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

  /**
   * After the user has successfuly login from the login page, at that point only
   * the user's hashed password and email is known the rest of the information of
   * the user, which is on the remote database, must be queried and filled in.
   * 
   * @param loginUser      - User object that needed to be filled in.
   * @param hashedPassword - the hashed password that they entered
   * @throws SQLException - if can't retrieve the user information.
   * 
   * @author Triet Huynh
   */
  public static void fillInUserInfoFromUserEmail(User loginUser, String hashedPassword) throws SQLException {
    String sqlStatement = "select username, user_id, is_admin from " + TABLE_NAME
        + " where email = '"
        + loginUser.getEmail() + "'";

    resultSet = executeQuery(sqlStatement);

    while (resultSet.next()) {
      loginUser.setUsername(resultSet.getString("username"));
      loginUser.setUserID(resultSet.getString("user_id"));
      loginUser.setIsAdmin(resultSet.getInt("is_admin"));
    }
    loginUser.setHashedPassword(hashedPassword);
  }

  /**
   * Get all existing users from the remote database
   * 
   * @return ResultSet of all users
   * @author Triet Huynh
   */
  public static ResultSet getAllUsers() throws SQLException {
    String sqlStatement = "select * from " + TABLE_NAME;
    return executeQuery(sqlStatement);
  }

}
