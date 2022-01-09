package com.twenty.one.beforeLogin.login;

import com.twenty.one.database.DBMethod;

public class Authentication {
  public static boolean authenticate(String email, String enteredPassword) {
    try
    String correctPassword = DBMethod.getUserPasswordFromEmail(email);

    if (correctPassword == enteredPassword) {
      return true;
    } else {
      return false;
    }
  }
}
