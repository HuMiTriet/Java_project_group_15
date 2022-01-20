package com.fifteen.auth.security;

import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.fifteen.database.DBMethod;

public class UserAuthenticator {
  private UserAuthenticator() {
  };

  private static boolean authenticate(String email, String enteredPassword) throws SQLException {

    boolean result = false;

    String correctHashedPassword = DBMethod.getUserHashedPasswordFromEmail(email);
    if (correctHashedPassword.equals(PasswordHasher.sha2(enteredPassword)))
      result = true;

    return result;
  }

  private static void throwErrorpane(SQLException e) {
    JOptionPane.showMessageDialog(null, "Failed connecting to database,"
        + " Please check your internet connection");

    DBMethod.closeConnection();
    e.printStackTrace();
  }

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
