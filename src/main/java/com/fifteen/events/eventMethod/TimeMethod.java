package com.fifteen.events.eventMethod;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.fifteen.events.local.CheckDate;
import com.toedter.calendar.JDateChooser;

/**
 * A suite of method to deal with problems related to EventLocal time.
 * 
 * @author Triet Huynh, Tim Görß
 */
public class TimeMethod {

  /**
   * @param startTime the time the event start
   * @param endTime   the time the event stop
   * @return a the minutes between two GregorianCalendar class (in this case it
   *         will be the start and end time)
   * @author Triet Huynh
   */
  public static long minutesBetween(GregorianCalendar startTime, GregorianCalendar endTime) {
    return ChronoUnit.MINUTES.between(startTime.toInstant(), endTime.toInstant());
  }

  /**
   * Function used reverse the changes to endTime
   * @param startTime the time the event start
   * @param duration   the duration of the event
   * @return the initial endTime of the event in the correct format
   * @author Tim Görß 1252200
   */
  public static String getEndTime(GregorianCalendar startTime, long duration) {
    GregorianCalendar endTime = (GregorianCalendar) startTime.clone();
    endTime.add(Calendar.MINUTE, Math.toIntExact(duration));

    String correctEndtime = getCorrectTimeFormat(endTime);
    return correctEndtime;
  }

  public static GregorianCalendar getReminderTime(GregorianCalendar startTime, int reminderMinutes) {
    GregorianCalendar reminderTime = (GregorianCalendar) startTime.clone();
    reminderTime.add(Calendar.MINUTE, -Math.toIntExact(reminderMinutes));
    return reminderTime;
  }

  /**
   * Function used to add leading zeros to dates missing one
   * @param time GregorianCalendar object that needs time to be changed
   * @return GregorianCalendar object with the correct format
   * @author Tim Görß 1252200
   */
  public static String getCorrectTimeFormat (GregorianCalendar time) {
    int hour = time.get(Calendar.HOUR_OF_DAY);
    int minute = time.get(Calendar.MINUTE);

    String correctTime = String.format("%01d:%02d", hour, minute);
    return correctTime;
  }

  /**
   * Because the JdateChooser retuns the String of the date being chosen but the
   * date of each event is stored as objects of the GregorianCalendar class, this
   * function is needed to convert the String of the date to the GregorianCalendar
   * object.
   * 
   * @param JdateChooser - the JDateChooser class that open a little GUI window to
   *                     choose the date
   * @author Triet Huynh
   */
  public static GregorianCalendar getGregorianCalendarFromJdateChooser(JDateChooser jDateChooser) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    String date = sdf.format(jDateChooser.getDate());

    return CheckDate.validateDate(date);
  }

}
