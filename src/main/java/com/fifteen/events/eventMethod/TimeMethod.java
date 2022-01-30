package com.fifteen.events.eventMethod;

import java.time.temporal.ChronoUnit;
import java.util.GregorianCalendar;

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
}
