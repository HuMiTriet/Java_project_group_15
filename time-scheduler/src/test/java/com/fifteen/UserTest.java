package com.fifteen;

import static org.junit.jupiter.api.Assertions.*;

import com.fifteen.database.DBMethod;
import com.fifteen.database.User;
import com.fifteen.database.UserDao;
import com.fifteen.database.UserDaoImp;

import org.junit.jupiter.api.*;

/**
 * Unit test for the User sign up function from User
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserTest {
  User newUser;
  static private final String EMAIL = "A";
  static private final String USERNAME = "A";
  static private final String PASSWORD = "A";
  static private final int IS_ADMIN = 1;

  @BeforeAll
  public void setUpNewuser() {
    UserDao dao = new UserDaoImp();

    newUser = dao.createUserFromSignUp(EMAIL, USERNAME, PASSWORD, IS_ADMIN);
  }

  @Test
  public void shouldReturncorrectEmail() {
    assertEquals(EMAIL, newUser.getEmail());
  }

  @Test
  public void shouldReturncorrectUsername() {
    assertEquals(USERNAME, newUser.getUsername());
  }

  @Test
  public void shouldReturncorrectHashedPassword() {
    assertNotEquals(newUser.getHashedPassword(), "S");
  }

  @Test
  public void shouldReturncorrectisAdmin() {
    assertEquals(IS_ADMIN, newUser.getIsAdmin());
  }

  // @Test
  // public void shouldreturnNonNaId() {
  // assertNotEquals("NA", newUser.getUserID());
  // }

  @AfterAll
  public void closeConnection() {
    DBMethod.closeConnection();
  }
}
