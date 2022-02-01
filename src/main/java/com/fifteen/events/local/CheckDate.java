package com.fifteen.events.local;

import java.util.GregorianCalendar;

import org.apache.commons.validator.routines.CalendarValidator;

/**
 * Function to check whether the time String is written in the correct format.
 * The format should be day month then year seperated by /
 *
 * @author Triet Huynh
 */
public class CheckDate {
  private static final String DATE = "dd/MM/yyyy";
  private static final String TIME_AND_DATE = "HH:mm dd/MM/yyyy";
  private static final String TIME = "HH:mm";
  private static CalendarValidator GregorianCalendarValidator = new CalendarValidator();

  /**
   * Return a GregorianCalendar class object if the String is in the correct
   * format if the
   * String is not correct return null
   *
   * @see GregorianCalendar
   * @author Triet Huynh
   */
  public static GregorianCalendar validateDate(String timeString) {
    return (GregorianCalendar) GregorianCalendarValidator.validate(timeString, DATE);
  }

  public static GregorianCalendar validateTime(String timeString) {
    return (GregorianCalendar) GregorianCalendarValidator.validate(timeString, TIME);
  }

  public static GregorianCalendar validateTimeDate(String timeString) {
    return (GregorianCalendar) GregorianCalendarValidator.validate(timeString, TIME_AND_DATE);
  }

}
