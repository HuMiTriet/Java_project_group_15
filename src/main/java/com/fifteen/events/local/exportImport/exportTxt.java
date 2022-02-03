package com.fifteen.events.local.exportImport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class exportTxt {

  public static void writeEvents(File file, String data) throws IOException {
    FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

    bufferedWriter.write(data);

    bufferedWriter.flush();

    fileWriter.close();
    bufferedWriter.close();
  }

}
