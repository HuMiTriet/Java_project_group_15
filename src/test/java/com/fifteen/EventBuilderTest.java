package com.fifteen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fifteen.events.local.EventLocal;
import com.fifteen.events.local.localDb;
import com.fifteen.events.local.localDbMethod;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventBuilderTest {
  @BeforeAll
  void connectToLocalDB() {
    localDb.loadSqliteDriver();
    try {
      localDb.createLocalConncetion();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /*@Test
  void builEventsJanuaryTest() {
    try {
      ArrayList<EventLocal> january = localDbMethod.buildEventLocal(0);
      System.out.println(january.size());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  void builEventsFebruaryTest() {
    try {
      ArrayList<EventLocal> february = localDbMethod.buildEventLocal(1);
      assertEquals(4, february.size(), "The event builder should all events of that month");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  */
  @Test
  void printingEventFebruary() {

  }

  @AfterAll
  void closeLocalConnection() {
    localDb.closeLocalConnection();
  }
}
