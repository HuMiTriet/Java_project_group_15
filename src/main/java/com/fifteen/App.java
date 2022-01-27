package com.fifteen;

import javax.swing.UIManager;

import com.fifteen.events.local.localDb;
import com.formdev.flatlaf.FlatDarculaLaf;

public class App {
  public static void main(String[] args) throws Exception {
    // create the object of Login Page to test if it's working

    try {
      UIManager.setLookAndFeel(new FlatDarculaLaf());
    } catch (Exception ex) {
      System.err.println("Failed to initialize LaF");
    }

    // Added functionality to the Login page
    // Available user: email: t@g.com | password: B
    // closeConnection();
    // new LoginPage();
    // localDb.initializeLocalDatabase();
    // localDb.closeConnection();

    // mailUtils.sendMail("javacomtwentyone@gmail.com");
  }
}
