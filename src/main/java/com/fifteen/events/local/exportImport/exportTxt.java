package com.fifteen.events.local.exportImport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * class to deal with exporting the local database file into a txt file
 * 
 * @author Triet Huynh
 */
public class exportTxt {

  /**
   * Using a BufferedWriter for better perfomance to write the local database
   * data to a txt file
   * 
   * @param file the file that will be written to
   * @param data the data from local database conveted to string that will be
   *             written to the file
   * 
   * @author Triet Huynh
   */
  public static void writeEvents(File file, String data) throws IOException {
    FileWriter fileWriter = new FileWriter(file.getAbsolutePath() + ".txt");
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

    bufferedWriter.write(data);

    bufferedWriter.flush();

    fileWriter.close();
    bufferedWriter.close();
  }

}
