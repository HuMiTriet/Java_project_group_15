package com.fifteen.events.eventMethod;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.GregorianCalendar;

import com.fifteen.events.local.CheckDate;
import com.toedter.calendar.JDateChooser;

/**
 * A suite of method to deal with problems related to EventLocal time.
 * 
 * @author Triet Huynh
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
  */
  public static GregorianCalendar getGregorianCalendarFromJdateChooser(JDateChooser jDateChooser) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    String date = sdf.format(jDateChooser.getDate());

    return CheckDate.validateDate(date);
  }

  public static int reminderMinutes(GregorianCalendar reminder) {
    int hour = reminder.get(GregorianCalendar.HOUR_OF_DAY);
    int minutes = reminder.get(GregorianCalendar.MINUTE);

    return hour * 60 + minutes;
  }
}
