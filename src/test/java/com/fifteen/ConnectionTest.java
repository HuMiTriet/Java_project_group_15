package com.fifteen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import com.fifteen.database.DBMethod;
import com.fifteen.database.UserDao;
import com.fifteen.database.UserDaoImp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * Unit test for the User sign up function from User
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConnectionTest {

  @BeforeAll
  void createDummyUser() {
    DBMethod.createConnection();
    try {
      if (DBMethod.checkfieldExisted("B", 'u') == false) {
        UserDao userHandler = new UserDaoImp();
        userHandler.createUserFromSignUp("test@noUse.com", "B", "B", 0);
      }
    } catch (SQLException e) {
      System.out.println("FAILED CREATING USER");
      e.printStackTrace();
    }
    DBMethod.closeConnection();
  }

  @BeforeEach
  void createConnection() {
    DBMethod.createConnection();
  }

  @AfterEach
  void closeConnection() {
    DBMethod.closeConnection();
  }

  @Test
  void checkAndClose() {
    try {
      assertEquals(true, DBMethod.checkfieldExisted("B", 'u'));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  void checkAfterClose() {
    try {
      assertEquals(true, DBMethod.checkfieldExisted("B", 'u'));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
