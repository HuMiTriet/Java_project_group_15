package com.fifteen.events.local;

import java.util.Calendar;
import java.util.UUID;

import lombok.Getter;

@Getter
public class Event {
  private String eventID;
  private String eventName;
  private Calendar calendar;
  private String[] participants;
  private Location location;
  private Priority priority;

  public Event(String userEmail, String eventName, Calendar calendar, String[] participants,
      Location location, Priority priority) {
    this.eventID = userEmail + "_" + UUID.randomUUID().toString();
    this.eventName = eventName;
    this.calendar = calendar;
    this.location = location;
    this.participants = participants;
    this.priority = priority;

  }
}
