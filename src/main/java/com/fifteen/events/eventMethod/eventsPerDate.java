package com.fifteen.events.eventMethod;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import com.fifteen.events.local.EventLocal;

/**
 * Since there is a panel called Show events which show all of the events that
 * is happening on that particular date, this class provide a method that only
 * returns the events that is happening on that particular date.
 * 
 * @author Triet Huynh
 */
public class eventsPerDate {

  /**
   * Return only the events that is happening on a single date.
   * 
   * @param eventsMonth - list of events that is happening in that month.
   * @param date        - the date of the event
   * 
   * @return an ArrayList of events that is happening on that date.
   * 
   * @author Triet Huynh
   */
  public static ArrayList<EventLocal> getEventOfDate(ArrayList<EventLocal> eventsMonth, int date) {
    ArrayList<EventLocal> eventsOfThatDate = new ArrayList<>();

    for (EventLocal event : eventsMonth) {
      if (event.getDayOfEvent().get(GregorianCalendar.DATE) == date)
        eventsOfThatDate.add(event);
    }
    return eventsOfThatDate;
  }
}
