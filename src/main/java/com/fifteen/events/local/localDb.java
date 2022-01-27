package com.fifteen.events.local;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.commons.dbutils.DbUtils;

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
    System.out.println("EVENT");
    statement.executeUpdate(
        "create table event ("
            + "	event_id 	varchar2(300) constraint event_eventId_pk primary key,"
            + "	name varchar2(200),"
            + "	priority varchar2(10))");

  }

  private static void createLocationTable() throws SQLException {
    System.out.println("LOCATIOn");
    statement.executeUpdate(
        "create table location("
            + "event_id varchar2(300) constraint location_eventId_pk primary key,"
            + "name varchar2(200),"
            + "longtitude REAL,"
            + "latitude REAL,"
            + "foreign key (event_id) references event(event_id) on delete cascade"
            + ")");
  }

  private static void createTimeTable() throws SQLException {
    System.out.println("TIME");
    statement.executeUpdate(
        "create table time ("
            + "event_id varchar2(300) constraint time_eventId_pk primary key,"
            + "minute INTEGER,"
            + "hour INTEGER,"
            + "day_of_week INTEGER,"
            + "day INTEGER,"
            + "month INTEGER,"
            + "year INTEGER,"
            // + "constraint time_eventId_fk"
            + "foreign key (event_id) references event(event_id) on delete cascade"
            + ")");

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
        System.out.println("created new database locally");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    // try {
    // createLocalConncetion();
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }

    // if (checkLocalDatabaseExist()) {
    // System.out.println("EXISTED");
    // } else {
    // try {
    // createEventTable();
    // createLocationTable();
    // createTimeTable();
    // System.out.println("created new database locally");
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }
  }

}
