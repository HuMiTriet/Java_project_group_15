package com.fifteen;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import com.fifteen.database.DBMethod;

import org.junit.jupiter.api.*;

/**
 * Unit test for the User sign up function from User
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConnectionTest {

  @AfterAll
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
