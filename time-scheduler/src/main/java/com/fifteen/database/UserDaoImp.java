package com.fifteen.database;

import java.sql.SQLException;

import com.fifteen.beforeLogin.security.PasswordHasher;

public class UserDaoImp implements UserDao {
  @Override
  public User createUserFromLogin(String email) {
    User loginUser = new User();

    loginUser.setEmail(email);
    return loginUser;
  }

  @Override
  public User createUserFromSignUp(String email, String username,
      String textPassword, int isAdmin) {
    User newUser = new User();
    newUser.setEmail(email);
    newUser.setUsername(username);
    newUser.setHashedPassword(PasswordHasher.sha2(textPassword));
    newUser.setIsAdmin(isAdmin);
    try {
      DBMethod.signUp(newUser);
      newUser.setUserID(DBMethod.getUserId(newUser));
    } catch (SQLException e) {
      System.err.println("Failed to create user from sign up");
      e.printStackTrace();
    }
    return newUser;
  }
}

// public static User createUserFromLogin(String email) {
// User loginUser = new User();

// loginUser.setEmail(email);
// return loginUser;
// }

// public static User createUserFromSignUp(String email, String username, String
// textPassword, int isAdmin) {
// User newUser = new User();
// newUser.setEmail(email);
// newUser.setUsername(username);
// newUser.setHashedPassword(PasswordHasher.sha2(textPassword));
// newUser.setIsAdmin(isAdmin);
// try {
// DBMethod.signUp(newUser);
// newUser.setUserID(DBMethod.getUserId(newUser));
// } catch (SQLException e) {
// System.err.println("Failed to create user from sign up");
// e.printStackTrace();
// }
// return newUser;
// }
