package com.fifteen.auth.security;

import java.awt.Frame;
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

  public static boolean authenticateEmailField(JLabel emailLabel,
      String enteredEmail) {
    if (enteredEmail.isEmpty()) {
      emailLabel.setText("Please enter your email");
      return false;
    } else {
      if (RegexChecker.checkEmail(enteredEmail)) {
        emailLabel.setText("");
      } else {
        emailLabel.setText("Invalid email format");
        return false;
      }

      try {
        if (DBMethod.checkfieldExisted(enteredEmail, 'e')) {
          emailLabel.setText("");
          return true;
        } else {
          emailLabel.setText("An account with this email doesn't exist'");
          return false;
        }

      } catch (SQLException e) {
        throwErrorpane(e);
        return false;
      }
    }
  }

  public static boolean authenticatePasswordField(JLabel passwordLabel,
      String enteredEmail, String stringPassword) {
    if (stringPassword.isEmpty()) {
      passwordLabel.setText("Please enter your password");
      return false;
    } else {
      try {
        if (UserAuthenticator.authenticate(enteredEmail, stringPassword)) {
          passwordLabel.setText("");
          return true;
        } else {
          passwordLabel.setText("Incorrect password");
          return false;
        }
      } catch (SQLException e) {
        throwErrorpane(e);
        return false;
      }

    }
  }

}
