package com.fifteen;

import java.sql.SQLException;

import javax.swing.UIManager;

import com.fifteen.auth.admin.AdminPage;
import com.fifteen.auth.login.LoginPage;
import com.fifteen.database.User;
import com.fifteen.database.UserDao;
import com.fifteen.database.UserDaoImp;
import com.fifteen.events.CalendarView;
import com.fifteen.events.local.localDb;
import com.fifteen.events.local.localDbMethod;
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
    localDb.initializeLocalDatabase();

    localDb.loadSqliteDriver();

    try {
      localDb.createLocalConncetion();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    // Added functionality to the Login page
    // Available user: email: t@g.com | password: B
    //closeConnection();
    new LoginPage();

<<<<<<< HEAD
    // UserDao userHandler = new UserDaoImp();
    // User user = userHandler.createUserFromLogin("t@g.com");
    // new CalendarView(user);
    localDbMethod.buildEventLocal(0);
    // localDb.closeLocalConnection();

    // new AdminPage();

=======
    localDb.initializeLocalDatabase();

    UserDao userHandler = new UserDaoImp();
    User user = userHandler.createUserFromLogin("t@g.com");
    //new CalendarView(user);

    //new AdminPage();
>>>>>>> c26308bec5139997dc96ab5e95af49da8d610c19
    // mailUtils.sendMail("javacomtwentyone@gmail.com");
  }

}
