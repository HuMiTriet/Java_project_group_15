package com.fifteen.database;

/**
 * A user Data access object, to increase decoupling between the remote database
 * and the implmentation
 * 
 * @see com.fifteen.database.UserDaoImp
 *      {@link com.fifteen.database.UserDaoImp}
 * @author Triet Huynh
 */
public interface UserDao {
  /**
   * Create a new User class object when signing in from login page
   * 
   * @author Triet Huynh
   */
  public User createUserFromLogin(String email);

  /**
   * Create a new User class object when signing up from signup page
   * 
   * @author Triet Huynh
   */
  public User createUserFromSignUp(String email, String username, String textPassword, int isAdmin);
}
