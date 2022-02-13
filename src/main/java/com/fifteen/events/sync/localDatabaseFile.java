package com.fifteen.events.sync;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fifteen.database.DBConnection;
import com.fifteen.database.DBMethod;
import com.fifteen.database.User;

import org.apache.commons.io.FileUtils;

/**
 * Provide a suit to handle the local database to sync with the remote database.
 * 
 * @author Triet Huynh
 */
public class localDatabaseFile {

  /**
   * convert the local database to a byte array to be store in the remote database
   * as a BLOB object.
   * 
   * @author Triet Huynh
   */
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

  /**
   * Create connection to the remote database and upload the local database file
   * to it
   * 
   * @author Triet Huynh
   */
  public static void uploadLocalDatabase(User user) throws IOException, SQLException {
    byte[] localDb = convertFileToBlob("local.db");

    PreparedStatement preparedStatement = DBConnection.getConnection()
        .prepareStatement("UPDATE userdb SET local_data = ? WHERE user_id = ?");
    preparedStatement.setBytes(1, localDb);
    preparedStatement.setString(2, user.getUserID());

    preparedStatement.executeUpdate();
    DBMethod.closeConnection();
  }

  /**
   * Create connection to the remote database and download the local database file
   * from it
   * 
   * @author Triet Huynh
   */
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

  /**
   * Wrapper function to download the local database file from the remote database
   * 
   * @author Triet Huynh
   */
  public static void localDatabase(User user) throws IOException, SQLException {
    // FileUtils.deleteQuietly(new File("local.db"));
    downloadLocalDatabase(user);
  }

}
