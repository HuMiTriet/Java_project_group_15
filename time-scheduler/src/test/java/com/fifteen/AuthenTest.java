package com.fifteen;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import com.fifteen.beforeLogin.login.UserAuthenticator;
import com.fifteen.database.DBMethod;

import org.junit.jupiter.api.*;

/**
 * Unit test for the User sign up function from User
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthenTest {

  static private final String EMAIL = "A";
  static private final String USERNAME = "A";
  static private final int IS_ADMIN = 1;
  static private final String PASSWORD = "A";

  // @BeforeAll
  // public void setUpNewuser() {
  //   // User Id
  //   int isAdmin = IS_ADMIN;
  //   User.createUserFromSignUp(EMAIL, USERNAME, PASSWORD, IS_ADMIN);
  // }

  @Test
  void correctUser() {
    assertEquals(true, UserAuthenticator.authenticate(EMAIL, PASSWORD));
  }

  @Test
  void incorrectEmail() {
    assertEquals(false, UserAuthenticator.authenticate("SUDO", PASSWORD));
  }

  @Test
  void incorrectPassword() {
    assertEquals(false, UserAuthenticator.authenticate(EMAIL, "bruh"));
  }

  @AfterAll
  public void closeConnection() {
      DBMethod.closeConnection();
  }
}
