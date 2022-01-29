package com.fifteen;

import static com.fifteen.database.DBMethod.closeConnection;

import javax.swing.UIManager;

import com.fifteen.auth.login.LoginPage;
import com.fifteen.database.User;
import com.fifteen.database.UserDao;
import com.fifteen.database.UserDaoImp;
import com.fifteen.events.EventPageMain;
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
    UserDao userHandler = new UserDaoImp();
    User user = userHandler.createUserFromLogin("t@g.com");
    new EventPageMain(user);

    // localDb.initializeLocalDatabase();
    // localDb.closeLocalConnection();

    // mailUtils.sendMail("javacomtwentyone@gmail.com");
  }

}
