package com.fifteen.events.local;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import org.apache.commons.dbutils.DbUtils;

/**
 * List of local methods to connect with the local database which will be store
 * in a SQLite file called local.db
 * 
 * @author Triet Huynh
 */
public class localDb {
  static Connection connection = null;
  static java.sql.Statement statement = null;
  static ResultSet resultSet = null;

  private static final String LOCAL_DATABASE = "local.db";

  protected static boolean checkLocalDatabaseExist() {
    File localData = new File(LOCAL_DATABASE);
    if (localData.exists() && !localData.isDirectory()) {
      return true;
    } else {
      return false;
    }
  }

  public static void loadSqliteDriver() {
    try {
      Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
      JOptionPane.showMessageDialog(null, "Failed to load SQlite driver");
      e.printStackTrace();
    }
  }

  public static void closeLocalConnection() {
    if (connection != null) {
      DbUtils.closeQuietly(connection, statement, resultSet);
      connection = null;
    }
  }

  public static void createLocalConncetion() throws SQLException {
    if (connection == null)
      connection = DriverManager.getConnection("jdbc:sqlite:local.db");
    if (statement == null)
      statement = connection.createStatement();
    statement.setQueryTimeout(30);
  }

  private static void createEventTable() throws SQLException {
    // System.out.println("EVENT");
    statement.executeUpdate(
        "create table event ("
            + "	event_id 	varchar2(300) constraint event_eventId_pk primary key,"
            + "	event_name varchar2(200),"
            + "event_description varchar2(300),"
            + "location_name varchar2(200),"
            + "longtitude DOUBLE,"
            + "latitude DOUBLE,"
            + "	priority varchar2(10)"
            + ")");

  }

  private static void createLocationTable() throws SQLException {
    // System.out.println("participants");
    statement.executeUpdate(
        "create table participants("
            + "event_id varchar2(300),"
            + "participants_email varchar2(200),"
            + "foreign key (event_id) references event(event_id) on delete cascade,"
            + "constraint participants_eventID_participantsEmail_pk primary key (event_id, participants_email)"
            + ")");
  }

  private static void createTimeTable() throws SQLException {
    // System.out.println("TIME");
    statement.executeUpdate(
        "create table time ("
            + "event_id varchar2(300) constraint time_eventId_pk primary key,"
            + "start_hour INTEGER,"
            + "start_minute INTEGER,"
            + "event_duration_minute INTEGER,"
            + "minutes_until_reminder INTEGER,"
            + "day_of_week INTEGER,"
            + "date INTEGER,"
            + "month INTEGER,"
            + "year INTEGER,"
            // + "constraint time_eventId_fk"
            + "foreign key (event_id) references event(event_id) on delete cascade"
            + ")");
  }

  private static void createViewFullEvent() throws SQLException {
    statement.executeUpdate(
        "CREATE VIEW event_time AS "
            + " SELECT * "
            + " FROM event e "
            + "JOIN time t USING (event_id)");
  }

  private static void createContactsTable() throws SQLException {

  }

  public static void initializeLocalDatabase() {
    loadSqliteDriver();
    try {
      if (checkLocalDatabaseExist()) {
        createLocalConncetion();
        System.out.println("EXISTED");
      } else {
        createLocalConncetion();
        createEventTable();
        createLocationTable();
        createTimeTable();
        createViewFullEvent();
        System.out.println("created new database locally");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
