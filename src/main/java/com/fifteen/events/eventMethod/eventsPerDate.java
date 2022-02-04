package com.fifteen.events.eventMethod;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import com.fifteen.events.local.EventLocal;

public class eventsPerDate {
  public static ArrayList<EventLocal> getEventOfDate(ArrayList<EventLocal> eventsMonth, int date) {
    ArrayList<EventLocal> eventsOfThatDate = new ArrayList<>();

    for (EventLocal event : eventsMonth) {
      if (event.getDayOfEvent().get(GregorianCalendar.DATE) == date)
        eventsOfThatDate.add(event);
    }
    return eventsOfThatDate;
  }
}