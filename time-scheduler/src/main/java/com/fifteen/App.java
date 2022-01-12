package com.fifteen;

import javax.swing.UIManager;

import com.fifteen.afterLogin.EventPageMain;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.fifteen.beforeLogin.signUp.SignUpPage;

public class App {
  public static void main(String[] args) throws Exception {
    // create the object of Login Page to test if it's working

    try {
      UIManager.setLookAndFeel(new FlatIntelliJLaf());
    } catch (Exception ex) {
      System.err.println("Failed to initialize LaF");
    }
    // new LoginPage();
    new SignUpPage();

    //new EventPageMain();

    // Sending an email to recipient
    // mailUtils.sendMail("javacomtwentyone@gmail.com");
  }
}
