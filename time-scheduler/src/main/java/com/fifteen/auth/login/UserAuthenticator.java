package com.fifteen.auth.login;

import java.sql.SQLException;

import com.fifteen.auth.security.PasswordHasher;
import com.fifteen.database.DBMethod;

public class UserAuthenticator {
  private UserAuthenticator() {
  };

  public static boolean authenticate(String email, String enteredPassword) {

    boolean result = false;

    try {
      String correctHashedPassword = DBMethod.getUserHashedPasswordFromEmail(email);
      if (correctHashedPassword.equals(PasswordHasher.sha2(enteredPassword)))
        result = true;
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }
}
