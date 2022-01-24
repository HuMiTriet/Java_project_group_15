package com.fifteen.events.local;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

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

  // public static void addEventToLocalData(Event event) {

  // }
}
