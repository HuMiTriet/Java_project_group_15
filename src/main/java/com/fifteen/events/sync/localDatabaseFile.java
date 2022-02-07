package com.fifteen.events.sync;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fifteen.database.DBConnection;
import com.fifteen.database.DBMethod;
import com.fifteen.database.User;
import com.fifteen.events.local.localDb;

import org.apache.commons.io.FileUtils;

public class localDatabaseFile {

  private static byte[] convertFileToBlob(String filePath) throws IOException {
    byte[] fileContent = null;
    try {
      fileContent = FileUtils.readFileToByteArray(new File(filePath));
    } catch (IOException e) {
      throw new IOException("Unable to convert file to byte array. " +
          e.getMessage());
    }
    return fileContent;
  }

  public static void uploadLocalDatabase(User user) throws IOException, SQLException {
    byte[] localDb = convertFileToBlob("local.db");

    PreparedStatement preparedStatement = DBConnection.getConnection()
        .prepareStatement("UPDATE userdb SET local_data = ? WHERE user_id = ?");
    preparedStatement.setBytes(1, localDb);
    preparedStatement.setString(2, user.getUserID());

    preparedStatement.executeUpdate();
    DBMethod.closeConnection();
  }

  private static void downloadLocalDatabase(User user) throws IOException, SQLException {
    PreparedStatement preparedStatement = DBConnection.getConnection()
        .prepareStatement("SELECT local_data FROM userdb WHERE user_id = ?");
    preparedStatement.setString(1, user.getUserID());

    ResultSet rs = preparedStatement.executeQuery();

    while (rs.next()) {
      byte[] localDb = rs.getBytes("local_data");
      FileUtils.writeByteArrayToFile(new File("local.db"), localDb);
    }

  }

  public static void localDatabase(User user) throws IOException, SQLException {
    // FileUtils.deleteQuietly(new File("local.db"));
    downloadLocalDatabase(user);
  }

}
