package com.fifteen.database;

public interface UserDao {
  public User createUserFromLogin(String email);

  public User createUserFromSignUp(String email, String username, String textPassword, int isAdmin);
}
