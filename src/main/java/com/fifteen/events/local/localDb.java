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
      connection = DriverManager.getConnection("jdbc:sqlite:" + LOCAL_DATABASE);
    if (statement == null)
      statement = connection.createStatement();
    statement.setQueryTimeout(30);
  }

  private static void createEventTable() throws SQLException {
    statement.executeUpdate(
        "create table event ("
            + "	id 	varchar2(300) constraint event_eventId_pk primary key,"
            + "	name varchar2(200),"
            + "	priority varchar2(10))");
  }

  private static void createLocationTable() throws SQLException {
    statement.executeUpdate(
        "create table location ("
            + "	id 	varchar2(300) constraint location_eventId_pk primary key,"
            + "	name varchar2(200),"
            + "	longitude REAL,"
            + "	latitude REAL),"
            + "constraint location_eventId_fk"
            + "foreign key (id) references event(id) on delete cascade"
            + ")");
  }

  private static void createTimeTable() throws SQLException {
    statement.executeUpdate(
        "create table time ("
            + "	id 	varchar2(300) constraint time_eventId_pk primary key,"
            + "minute INTEGER,"
            + "hour INTEGER,"
            + "day_of_week INTEGER,"
            + "day INTEGER,"
            + "month INTEGER,"
            + "year INTEGER,"
            + "constraint time_eventId_fk"
            + "foreign key (id) references event(id) on delete cascade"
            + ")");
  }

}
