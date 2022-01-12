package com.twenty.one;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.twenty.one.beforeLogin.login.LoginPage;
import com.twenty.one.afterLogin.EventPageButton;

public class App {
  public static void main(String[] args) throws Exception {
    // create the object of Login Page to test if it's working

    try {
      UIManager.setLookAndFeel(new FlatIntelliJLaf());
    } catch (Exception ex) {
      System.err.println("Failed to initialize LaF");
    }
    // new LoginPage();

    new EventPageButton();
    // new EventPageTextField();
    // Sending an email to recipient
    // mailUtils.sendMail("javacomtwentyone@gmail.com");
  }
}
