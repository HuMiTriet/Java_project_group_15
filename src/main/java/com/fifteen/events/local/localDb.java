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

  private static boolean checkLocalDatabaseExist() {
    File localData = new File(LOCAL_DATABASE);
    if (localData.exists() && !localData.isDirectory()) {
      return true;
    } else {
      return false;
    }
  }

  private static void loadSqliteDriver() {
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

  private static void addToEventTable(EventLocal eventLocal) throws SQLException {
    String sql = "insert into event values("
        + "'" + eventLocal.getEventID() + "',"
        + "'" + eventLocal.getEventName() + "',"
        + "'" + eventLocal.getEventDescription() + "',"
        + "'" + eventLocal.getLocation().getName() + "',"
        + eventLocal.getLocation().getLongitude() + ","
        + eventLocal.getLocation().getLatitude() + ","
        + "'" + eventLocal.getPriority() + "'"
        + ")";
    // System.out.println(sql);
    statement.executeUpdate(sql);
  }

  private static void addToParticipantsTable(EventLocal eventLocal) throws SQLException {

    for (String participant : eventLocal.getParticipants_email()) {
      statement.executeUpdate("insert into participants values("
          + "'" + eventLocal.getEventID() + "',"
          + "'" + participant
          + "')");
    }
  }

  private static void addToTimeTable(EventLocal eventLocal) throws SQLException {
    statement.executeUpdate("insert into time values("
        + "'" + eventLocal.getEventID() + "',"
        + eventLocal.getStartTime().get(GregorianCalendar.HOUR_OF_DAY) + ","
        + eventLocal.getStartTime().get(GregorianCalendar.MINUTE) + ","
        + eventLocal.getEvent_duration_minute() + ","
        + eventLocal.getMinutesUntilReminder() + ","
        + eventLocal.getDayOfEvent().get(GregorianCalendar.DAY_OF_WEEK) + ","
        + eventLocal.getDayOfEvent().get(GregorianCalendar.DATE) + ","
        + eventLocal.getDayOfEvent().get(GregorianCalendar.MONTH) + ","
        + eventLocal.getDayOfEvent().get(GregorianCalendar.YEAR)
        + ")");
  }

  public static void addEventLocal(EventLocal eventLocal) {
    loadSqliteDriver();
    try {
      createLocalConncetion();
      addToEventTable(eventLocal);
      addToParticipantsTable(eventLocal);
      addToTimeTable(eventLocal);
      closeLocalConnection();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void getEventTable(EventLocal eventLocal, int month) throws SQLException {
    resultSet = statement.executeQuery("select * from event");
    while (resultSet.next()) {
      // index of first column starts at 1
      eventLocal.setEventID(resultSet.getString("event_id"));
      eventLocal.setEventName(resultSet.getString("event_name"));
      eventLocal.setEventDescription(resultSet.getString("event_description"));
      eventLocal.getLocation().setName(resultSet.getString("location_name"));
      eventLocal.getLocation().setLongitude(resultSet.getDouble("longtitude"));
      eventLocal.getLocation().setLatitude(resultSet.getDouble("latitude"));
      eventLocal.setPriority(resultSet.getString("priority"));
    }

  }
  // select *
  // from event e
  // NATURAL JOIN time t
  // NATURAL JOIN participants p
  // where month = 0;
  // public static ArrayList<EventLocal> getEventsByMonth(int month) {
  // ArrayList<EventLocal> eventList = new ArrayList<EventLocal>();
  // }

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
        System.out.println("created new database locally");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
