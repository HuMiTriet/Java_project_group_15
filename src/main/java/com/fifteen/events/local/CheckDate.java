package com.fifteen.events.local;

import java.util.Calendar;

import org.apache.commons.validator.routines.CalendarValidator;

/**
 * Function to check whether the time String is written in the correct format.
 * The format should be day month then year seperated by /
 * 
 * @author Triet Huynh
 */
public class CheckDate {
  private static final String PATTERN = "dd/MM/yyyy HH:mm";
  private static CalendarValidator calendarValidator = new CalendarValidator();

  /**
   * Return a Calendar class object if the String is in the correct format if the
   * String is not correct return null
   * 
   * @see Calendar
   * @author Triet Huynh
   */
  public static Calendar validate(String timeString) {
    return calendarValidator.validate(timeString, PATTERN);
  }
}
