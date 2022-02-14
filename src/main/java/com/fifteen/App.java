package com.fifteen;

import javax.swing.UIManager;

import com.fifteen.auth.login.LoginPage;
import com.fifteen.database.DBMethod;
import com.formdev.flatlaf.FlatIntelliJLaf;

public class App {
  public static void main(String[] args) throws Exception {
    // create the object of Login Page to test if it's working

    try {
      UIManager.setLookAndFeel(new FlatIntelliJLaf());
    } catch (Exception ex) {
      System.err.println("Failed to initialize LaF");
    }
    // localDb.initializeLocalDatabase();

    // Added functionality to the Login page
    // Available user: email: t@g.com | password: t
    // Available admin: email: f@g.com | password: f
    DBMethod.closeConnection();
    new LoginPage();

    // UserDao userHandler = new UserDaoImp();
    // User user = userHandler.createUserFromLogin("t@g.com");
    // new CalendarView(user);

    // new AdminPage();
    // reminder();

    // mailUtils.sendMail("javacomtwentyone@gmail.com");
  }

}
