package com.fifteen;

import java.sql.SQLException;

import javax.swing.UIManager;

import com.fifteen.auth.admin.AdminPage;
import com.fifteen.database.DBMethod;
import com.fifteen.database.User;
import com.fifteen.database.UserDao;
import com.fifteen.database.UserDaoImp;
import com.fifteen.events.CalendarView;
import com.fifteen.events.local.localDb;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.fifteen.auth.login.LoginPage;

public class App {
  public static void main(String[] args) throws Exception {
    // create the object of Login Page to test if it's working

    try {
      UIManager.setLookAndFeel(new FlatIntelliJLaf());
    } catch (Exception ex) {
      System.err.println("Failed to initialize LaF");
    }
    localDb.initializeLocalDatabase();

    localDb.loadSqliteDriver();

    try {
      localDb.createLocalConncetion();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    // Added functionality to the Login page
    // Available user: email: t@g.com | password: t
    // Available admin: email: f@g.com | password: f
     //DBMethod.closeConnection();
     //new LoginPage();

    //UserDao userHandler = new UserDaoImp();
    //User user = userHandler.createUserFromLogin("t@g.com");
    //new CalendarView(user);

    new AdminPage();

    // mailUtils.sendMail("javacomtwentyone@gmail.com");
  }

}
