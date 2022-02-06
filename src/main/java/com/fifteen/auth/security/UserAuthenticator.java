package com.fifteen.auth.security;

import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.fifteen.database.DBMethod;

/**
 * Provide a suite of method to authenticate user. This is used in the login
 * page and the sign up page and deal mostly with the remote database.
 * 
 * @author Triet Huynh
 */
public class UserAuthenticator {
  private UserAuthenticator() {
  };

  /**
   * Authenticate user, check the remote data base and see if the user with that
   * email has the same hashed password created locally as the hashed password
   * in the remote database.
   * 
   * @param username
   *                 username of user
   * @param password
   *                 password of user
   * @return true if user is authenticated, false otherwise
   * @author Triet Huynh
   */
  private static boolean authenticate(String email, String enteredPassword) throws SQLException {

    boolean result = false;

    String correctHashedPassword = DBMethod.getUserHashedPasswordFromEmail(email);
    if (correctHashedPassword.equals(PasswordHasher.sha2(enteredPassword)))
      result = true;

    return result;
  }

  /**
   * Helpter function to throw an error panel if there is error related to
   * connection with the remote database.
   * 
   * @param e takes in the SQL exception error
   * @author Triet Huynh
   */
  private static void throwErrorpane(SQLException e) {
    JOptionPane.showMessageDialog(null, "Failed connecting to database,"
        + " Please check your internet connection");

    DBMethod.closeConnection();
    e.printStackTrace();
  }

  /**
   * @return false if the email is not filled or filled with spaces, true if
   *         otherwise and the email is in the correct format
   * @author Triet Huynh
   */
  public static boolean checkEmailFormat(JLabel emailLabel, String enteredEmail) {

    if (enteredEmail.isBlank()) {
      emailLabel.setText("Please enter your email");
      return false;
    } else {
      if (RegexChecker.checkEmail(enteredEmail)) {
        emailLabel.setText("");
        return true;
      } else {
        emailLabel.setText("Invalid email format");
        return false;
      }
    }

  }

  /**
   * This function is used to check whether the entered email exist on the remote
   * database. If the email does not exist, the email label will be set to error
   * messeage specify in the String message
   * 
   * @param emailLabel   the label that will show the error
   * @param enteredEmail the email that the user entered
   * @param message      the error message that will be shown if the email does
   *                     not exist
   * 
   * @author Triet Huynh
   */
  public static boolean authenticateEmailField(JLabel emailLabel,
      String enteredEmail, String message) {

    try {
      if (DBMethod.checkfieldExisted(enteredEmail, 'e')) {
        emailLabel.setText(message);
        return true;
      } else {
        return false;
      }

    } catch (SQLException e) {
      throwErrorpane(e);
      return false;
    }

  }

  public static boolean checkFieldEmpty(JLabel label, String enteredText, String message) {
    if (enteredText.isBlank()) {
      label.setText(message);
      return false;
    } else {
      label.setText("");
      return true;
    }
  }

  public static boolean authenticatePasswordField(JLabel passwordLabel,
      String enteredEmail, String stringPassword) {
    try {
      if (UserAuthenticator.authenticate(enteredEmail, stringPassword)) {
        passwordLabel.setText("");
        return true;
      } else {
        passwordLabel.setText("Invalid email or password");
        return false;
      }
    } catch (SQLException e) {
      throwErrorpane(e);
      return false;
    }

  }

}
