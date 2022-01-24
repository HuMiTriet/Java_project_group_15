package com.fifteen.events.local;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.Getter;

@Getter
public class Event {
  private String eventID;
  private String eventName;
  private Calendar calendar;
  private Set<String> participants = new HashSet<String>();
  private Location location;
  private Priority priority;

  public Event(String userEmail, String eventName, Calendar calendar, Set<String> participants,
      Location location, Priority priority) {
    this.eventID = userEmail + "_" + UUID.randomUUID().toString();
    this.eventName = eventName;
    this.calendar = calendar;
    this.location = location;
    this.participants.addAll(participants);
    this.priority = priority;
  }

  public void addParticipant(String[] participantsEmails) {
    for (int i = 0; i < participantsEmails.length; i++) {
      this.participants.add("\"" + participantsEmails[i] + "\"");
    }

  }
}
