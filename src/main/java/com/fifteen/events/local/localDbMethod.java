package com.fifteen.events.local;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Vector;

/**
 * Provide a suite of methods to interact with the local database.
 * 
 * @author Triet Huynh
 */
public class localDbMethod extends localDb {

  /**
   * Helper method to insert the values inside each EventLocal object into the
   * Event Table.
   * 
   * @author Triet Huynh
   */
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

  /**
   * Helper method to insert the values inside each EventLocal object into the
   * Participant Table.
   * 
   * @author Triet Huynh
   */
  private static void addToParticipantsTable(EventLocal eventLocal) throws SQLException {

    for (String participant : eventLocal.getParticipants_email()) {
      statement.executeUpdate("insert into participants values("
          + "'" + eventLocal.getEventID() + "',"
          + "'" + participant
          + "')");
    }
  }

  /**
   * Helper method to insert the values inside each EventLocal object into the
   * Time table.
   * 
   * @author Triet Huynh
   */
  private static void addToTimeTable(EventLocal eventLocal) throws SQLException {
    statement.executeUpdate("insert into time values("
        + "'" + eventLocal.getEventID() + "',"
        + eventLocal.getDayOfEvent().get(GregorianCalendar.HOUR_OF_DAY) + ","
        + eventLocal.getDayOfEvent().get(GregorianCalendar.MINUTE) + ","
        + eventLocal.getEvent_duration_minute() + ","
        + eventLocal.getMinutesUntilReminder() + ","
        + eventLocal.getDayOfEvent().get(GregorianCalendar.DAY_OF_WEEK) + ","
        + eventLocal.getDayOfEvent().get(GregorianCalendar.DATE) + ","
        + eventLocal.getDayOfEvent().get(GregorianCalendar.MONTH) + ","
        + eventLocal.getDayOfEvent().get(GregorianCalendar.YEAR)
        + ")");
  }

  /**
   * Public method that adds an event to the local database.
   *
   * @author Triet Huynh
   */
  public static void addEventLocal(EventLocal eventLocal) {
    try {
      addToEventTable(eventLocal);
      addToParticipantsTable(eventLocal);
      addToTimeTable(eventLocal);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Function to delete events from database
   * 
   * @param id - primary key of events to be deleted
   * @author Tim Görß 1252200
   */
  public static void deleteEvent(String id) throws SQLException {

    statement.executeUpdate("DELETE FROM event WHERE event_id = '" + id + "'");
    statement.executeUpdate("DELETE FROM participants WHERE event_id = '" + id + "'");
    statement.executeUpdate("DELETE FROM time WHERE event_id = '" + id + "'");

  }

  /**
   * Function to initialize the edit of an event
   * 
   * @param eventLocal - the event that is to be edited
   * @author Tim Görß 1252200
   */
  public static void editEvent(EventLocal eventLocal) throws SQLException {
    try {
      editEventTable(eventLocal);
      editTimeTable(eventLocal);
      editParticipantTable(eventLocal);
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  /**
   * Function to edit events from the EventTable
   * 
   * @param eventLocal - the event that is to be edited
   * @author Tim Görß 1252200
   */
  private static void editEventTable(EventLocal eventLocal) throws SQLException {
    statement.executeUpdate("UPDATE event SET event_name = '" + eventLocal.getEventName() +
        "' WHERE event_id = '" + eventLocal.getEventID() + "'");
    statement.executeUpdate("UPDATE event SET event_description = '" + eventLocal.getEventDescription() +
        "' WHERE event_id = '" + eventLocal.getEventID() + "'");
    statement.executeUpdate("UPDATE event SET location_name = '" + eventLocal.getLocation().getName() +
        "' WHERE event_id = '" + eventLocal.getEventID() + "'");
    statement.executeUpdate("UPDATE event SET longtitude = '" + eventLocal.getLocation().getLongitude() +
        "' WHERE event_id = '" + eventLocal.getEventID() + "'");
    statement.executeUpdate("UPDATE event SET latitude = '" + eventLocal.getLocation().getLatitude() +
        "' WHERE event_id = '" + eventLocal.getEventID() + "'");
    statement.executeUpdate("UPDATE event SET priority = '" + eventLocal.getPriority() +
        "' WHERE event_id = '" + eventLocal.getEventID() + "'");
  }

  /**
   * Function to edit events from the TimeTable
   * 
   * @param eventLocal - the event that is to be edited
   * @author Tim Görß 1252200
   */
  private static void editTimeTable(EventLocal eventLocal) throws SQLException {
    statement.executeUpdate(
        "UPDATE time SET start_hour = '" + eventLocal.getDayOfEvent().get(GregorianCalendar.HOUR_OF_DAY) +
            "' WHERE event_id = '" + eventLocal.getEventID() + "'");
    statement
        .executeUpdate("UPDATE time SET start_minute = '" + eventLocal.getDayOfEvent().get(GregorianCalendar.MINUTE) +
            "' WHERE event_id = '" + eventLocal.getEventID() + "'");
    statement.executeUpdate("UPDATE time SET event_duration_minute = '" + eventLocal.getEvent_duration_minute() +
        "' WHERE event_id = '" + eventLocal.getEventID() + "'");
    statement.executeUpdate("UPDATE time SET minutes_until_reminder = '" + eventLocal.getMinutesUntilReminder() +
        "' WHERE event_id = '" + eventLocal.getEventID() + "'");
    statement.executeUpdate(
        "UPDATE time SET day_of_week = '" + eventLocal.getDayOfEvent().get(GregorianCalendar.DAY_OF_WEEK) +
            "' WHERE event_id = '" + eventLocal.getEventID() + "'");
    statement.executeUpdate("UPDATE time SET date = '" + eventLocal.getDayOfEvent().get(GregorianCalendar.DATE) +
        "' WHERE event_id = '" + eventLocal.getEventID() + "'");
    statement.executeUpdate("UPDATE time SET month = '" + eventLocal.getDayOfEvent().get(GregorianCalendar.MONTH) +
        "' WHERE event_id = '" + eventLocal.getEventID() + "'");
    statement.executeUpdate("UPDATE time SET year = '" + eventLocal.getDayOfEvent().get(GregorianCalendar.YEAR) +
        "' WHERE event_id = '" + eventLocal.getEventID() + "'");
  }

  /**
   * Function to edit events from the ParticipantTable
   * 
   * @param eventLocal - the event that is to be edited
   * @author Tim Görß 1252200
   */
  private static void editParticipantTable(EventLocal eventLocal) throws SQLException {
    statement.executeUpdate("DELETE FROM participants WHERE event_id = '" + eventLocal.getEventID() + "'");
    addToParticipantsTable(eventLocal);
  }

  /**
   * Helper method to get all of the partcipants tuples from the local database
   * 
   * @author Triet Huynh
   */
  private static void getParticipantsTable(EventLocal eventLocal) throws SQLException {

    Statement participantStatement = connection.createStatement();

    ResultSet participants_rs = participantStatement.executeQuery("Select * from participants"

        + " where event_id = " + "'" + eventLocal.getEventID() + "'");
    while (participants_rs.next()) {
      eventLocal.getParticipants_email().add(participants_rs.getString("participants_email"));
    }
    participants_rs.close();

  }

  /**
   * Method to convert event data from the local database to an EventLocal object
   * 
   * @author Triet Huynh
   */
  public static ArrayList<EventLocal> buildEventLocal(int month, int year) throws SQLException {
    ArrayList<EventLocal> monthEvents = new ArrayList<EventLocal>();
    resultSet = statement.executeQuery("select * from event_time where month = " + month +
        " and  year = " + year);
    while (resultSet.next()) {
      EventLocal eventLocal = new EventLocal();
      // index of first column starts at 1
      eventLocal.setEventID(resultSet.getString("event_id"));
      eventLocal.setEventName(resultSet.getString("event_name"));
      eventLocal.setEventDescription(resultSet.getString("event_description"));

      eventLocal.getDayOfEvent().set(GregorianCalendar.HOUR_OF_DAY, resultSet.getInt("start_hour"));
      eventLocal.getDayOfEvent().set(GregorianCalendar.MINUTE, resultSet.getInt("start_minute"));
      eventLocal.getDayOfEvent().set(GregorianCalendar.DAY_OF_WEEK, resultSet.getInt("day_of_week"));// Testing stuff
      eventLocal.getDayOfEvent().set(GregorianCalendar.DATE, resultSet.getInt("date"));
      eventLocal.getDayOfEvent().set(GregorianCalendar.MONTH, resultSet.getInt("month"));
      eventLocal.getDayOfEvent().set(GregorianCalendar.YEAR, resultSet.getInt("year"));

      eventLocal.setEvent_duration_minute(resultSet.getLong("event_duration_minute"));
      eventLocal.setMinutesUntilReminder(resultSet.getInt("minutes_until_reminder"));

      eventLocal.getLocation().setName(resultSet.getString("location_name"));
      eventLocal.getLocation().setLongitude(resultSet.getDouble("longtitude"));
      eventLocal.getLocation().setLatitude(resultSet.getDouble("latitude"));
      eventLocal.setPriority(resultSet.getString("priority"));

      getParticipantsTable(eventLocal);

      monthEvents.add(eventLocal);
    }
    return monthEvents;
  }

  /**
   * Add participants emails to the contact tables used for the contact list.
   * 
   * @author Triet Huynh
   */
  public static void addToContactsTable(String email) throws SQLException {
    statement.executeUpdate("INSERT INTO contacts values('" + email + "')");
  }

  /**
   * Function to delete all contacts chosen by the user in the edit contacts page
   * from the local database
   * 
   * @param ArrayList<String> emails - emails of contacts that are to be deleted
   * @throws SQLException
   * @author Triet Huynh
   */
  public static void deleteContacts(ArrayList<String> emails) throws SQLException {
    for (String email : emails) {
      statement.executeUpdate("DELETE FROM contacts WHERE email = '" + email + "'");
    }
  }

  /**
   * Function to get all of the contacts tuples from the local database, this
   * is used to show all of the contacts in the edit contacts page and the
   * add event page
   * 
   * @author Triet Huynh
   */
  public static Vector<String> getAllContacts() throws SQLException {
    resultSet = statement.executeQuery("select * from contacts");

    Vector<String> contacts = new Vector<String>();
    while (resultSet.next()) {
      contacts.add(resultSet.getString("email"));
    }
    return contacts;
  }

  /**
   * Helper function to get all informations from the event_time table. Use
   * for exporting data to txt.
   * 
   * @author Triet Huynh
   */
  private static ArrayList<String> getAllEventTimeView() throws SQLException {
    ArrayList<String> result = new ArrayList<>();
    resultSet = statement.executeQuery("SELECT * FROM event_time");
    while (resultSet.next()) {
      String row = resultSet.getString("event_id") + " "
          + resultSet.getString("event_description") + " "
          + resultSet.getString("location_name") + " "
          + resultSet.getString("longtitude") + " "
          + resultSet.getString("latitude") + " "
          + resultSet.getString("priority") + " "
          + resultSet.getString("start_hour") + " "
          + resultSet.getString("start_minute") + " "
          + resultSet.getString("event_duration_minute") + " "
          + resultSet.getString("minutes_until_reminder") + " "
          + resultSet.getString("day_of_week") + " "
          + resultSet.getString("date") + " "
          + resultSet.getString("month") + " "
          + resultSet.getString("year") + " ";
      result.add(row);
    }
    return result;
  }

  /**
   * Helper function to get all partcipants of each events in the local database.
   * for exporting data to txt.
   * 
   * @author Triet Huynh
   */
  private static ArrayList<String> getAllParticipants() throws SQLException {
    ArrayList<String> result = new ArrayList<>();
    resultSet = statement.executeQuery("SELECT * FROM participants");

    result.add("event ID | participant email");
    while (resultSet.next()) {
      String row = resultSet.getString("event_id") + " "
          + resultSet.getString("participants_email");
      result.add(row);
    }
    return result;
  }

  /**
   * Public method to export all data about events from the local database to a
   * txt file.
   * 
   * @author Triet Huynh
   */
  public static String getExportContent() throws SQLException {
    ArrayList<String> eventTime = getAllEventTimeView();
    String stringEventTime = String.join("\n", eventTime);
    stringEventTime = stringEventTime + " \n ----------------------------------------- \n";

    ArrayList<String> participants = getAllParticipants();
    String Stringparticipants = String.join("\n", participants);

    return stringEventTime + Stringparticipants;
  }
}
