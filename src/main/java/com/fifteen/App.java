package com.fifteen;

import javax.swing.UIManager;

import com.fifteen.auth.admin.AdminPage;
import com.fifteen.auth.login.LoginPage;
import com.fifteen.database.User;
import com.fifteen.database.UserDao;
import com.fifteen.database.UserDaoImp;
import com.fifteen.events.CalendarView;
import com.fifteen.events.local.localDb;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;

public class App {
  public static void main(String[] args) throws Exception {
    // create the object of Login Page to test if it's working

    try {
      UIManager.setLookAndFeel(new FlatIntelliJLaf());
    } catch (Exception ex) {
      System.err.println("Failed to initialize LaF");
    }

    // Added functionality to the Login page
    // Available user: email: t@g.com | password: B
    // closeConnection();
    // new LoginPage();

    // localDb.initializeLocalDatabase();

    UserDao userHandler = new UserDaoImp();
    User user = userHandler.createUserFromLogin("t@g.com");
    new CalendarView(user);

    // new AdminPage();
    // mailUtils.sendMail("javacomtwentyone@gmail.com");
  }

}
