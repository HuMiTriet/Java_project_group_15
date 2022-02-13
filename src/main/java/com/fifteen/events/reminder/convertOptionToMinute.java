package com.fifteen.events.reminder;

/**
 * Method to convert the option that the user choose in the drop down list
 * of add event to the minute that the user choose in int.
 * 
 * @author Triet Huynh
 */
public class convertOptionToMinute {
  public static int convert(String option) {
    int minute = 0;
    switch (option) {
      case "10 minutes":
        minute = 10;
        break;
      case "1 hour":
        minute = 60;
        break;
      case "3 days":
        minute = 4320;
        break;
      case "1 week":
        minute = 10080;
        break;
    }
    return minute;

  }
}
