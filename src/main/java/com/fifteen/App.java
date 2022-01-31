package com.fifteen;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.UIManager;

import com.fifteen.auth.admin.AdminPage;
import com.fifteen.auth.login.LoginPage;
import com.fifteen.database.User;
import com.fifteen.database.UserDao;
import com.fifteen.database.UserDaoImp;
import com.fifteen.events.CalendarView;
import com.fifteen.events.local.EventLocal;
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
    // closeConnection();
    // new LoginPage();

    // UserDao userHandler = new UserDaoImp();
    // User user = userHandler.createUserFromLogin("t@g.com");
    // new CalendarView(user);

    ArrayList<EventLocal> jan = localDbMethod.buildEventLocal(0);
    localDb.closeLocalConnection();
    for (int i = 0; i < jan.size(); i++) {
      // System.out.println(jan.get(i).toString());
      System.out.println(jan.get(i).getDayOfEvent().get(GregorianCalendar.HOUR_OF_DAY));
      System.out.println(jan.get(i).getDayOfEvent().get(GregorianCalendar.MINUTE));
      System.out.println(jan.get(i).getDayOfEvent().get(GregorianCalendar.DATE));
      System.out.println(jan.get(i).getDayOfEvent().get(GregorianCalendar.MONTH));
      System.out.println(jan.get(i).getDayOfEvent().get(GregorianCalendar.YEAR));
    }

    // new AdminPage();

    // mailUtils.sendMail("javacomtwentyone@gmail.com");
  }

}
