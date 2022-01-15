package com.fifteen;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import com.fifteen.auth.login.UserAuthenticator;
import com.fifteen.database.DBMethod;
import com.fifteen.database.UserDao;
import com.fifteen.database.UserDaoImp;

import org.junit.jupiter.api.*;

/**
 * Unit test for the User sign up function from User
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthenTest {

  static private final String EMAIL = "t@g.com";
  static private final String USERNAME = "B";
  static private final int IS_ADMIN = 0;
  static private final String PASSWORD = "B";

  @Test
  void correctUser() {
    try {
      assertEquals(true, UserAuthenticator.authenticate(EMAIL, PASSWORD));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  void incorrectEmail() {
    try {
      assertEquals(false, UserAuthenticator.authenticate("SUDO", PASSWORD));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  void incorrectPassword() {
    try {
      assertEquals(false, UserAuthenticator.authenticate(EMAIL, "bruh"));
    } catch (SQLException e) {

      e.printStackTrace();
    }
  }

  @AfterAll
  public void closeConnection() {
    DBMethod.closeConnection();
  }
}
