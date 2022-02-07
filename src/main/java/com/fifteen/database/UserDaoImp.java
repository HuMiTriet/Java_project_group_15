package com.fifteen.database;

import java.sql.SQLException;

import com.fifteen.auth.security.PasswordHasher;

/**
 * User access object implementation.
 * 
 * @author Triet Huynh
 */
public class UserDaoImp implements UserDao {
  /**
   * Implementation of the create user from login
   * 
   * @param email the entered email of the user.
   * 
   * @return User - object of the User class.
   * @author Triet Huynh
   */
  @Override
  public User createUserFromLogin(String email) {
    User loginUser = new User();

    loginUser.setEmail(email);
    return loginUser;
  }

  /**
   * Create a new user from the sign up page
   * 
   * @param email        the entered email of the user.
   * @param textPassword the entered password of the user.
   * @param username     the username that the user entered.
   * @param isAdmin      since the remote SQL database does not have a boolean
   *                     variable type, 1 is use for true and 0 is use for false.
   * 
   * @return User - object of the User class.
   * @author Triet Huynh
   */
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
