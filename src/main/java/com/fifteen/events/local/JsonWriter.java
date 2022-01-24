package com.fifteen.events.local;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * 
 */
public class JsonWriter {

  private static void createLocalData() {
    try {
      File localData = new File("localData.json");
      if (!localData.exists()) {
        localData.createNewFile();
      }
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Failled to create a local data file");
      e.printStackTrace();
    }
  }

  private static void writeToFile(String data) {
    try {
      FileWriter localFile = new FileWriter("localData.json", true);
      BufferedWriter bufferedWriterLocal = new BufferedWriter(localFile);
      bufferedWriterLocal.write(data);
      bufferedWriterLocal.close();
      localFile.close();
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Failled to create a local data file");
      e.printStackTrace();
    }
  }

  public static void addEventToLocalData(Event event) {
    JSONObject time = new JSONObject();
    time.put("hour", event.getCalendar().get(Calendar.HOUR_OF_DAY));
    time.put("minute", event.getCalendar().get(Calendar.MINUTE));
    time.put("dayOfWeek", event.getCalendar().get(Calendar.DAY_OF_WEEK));
    time.put("date", event.getCalendar().get(Calendar.DATE));
    time.put("month", event.getCalendar().get(Calendar.MONTH) + 1); // because January starts at 0
    time.put("year", event.getCalendar().get(Calendar.YEAR)); // because January starts at 0
    // time.put("", value)

    JSONObject eventValues = new JSONObject();
    eventValues.put("time", time);
    eventValues.put("eventName", event.getEventName());

    JSONArray participantsList = new JSONArray();
    participantsList.addAll(Arrays.asList(event.getParticipants()));
    eventValues.put("participants", participantsList);

    JSONObject location = new JSONObject();
    location.put("locationName", event.getLocation().getName());
    location.put("latitude", event.getLocation().getLatitude());
    location.put("longtitude", event.getLocation().getLongitude());

    eventValues.put("location", location);
    eventValues.put("priority", event.getPriority().toString());

    JSONObject finalEvent = new JSONObject();
    finalEvent.put(event.getEventID(), eventValues);

    createLocalData();
    writeToFile(finalEvent.toJSONString());

  }
}
