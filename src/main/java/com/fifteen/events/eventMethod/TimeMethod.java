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
   * Becase the JdateChooser retuns the String of the date being choosen but the
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
