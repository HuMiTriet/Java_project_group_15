package com.twenty.one;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.twenty.one.beforeLogin.login.LoginPage;
<<<<<<< Updated upstream
import com.twenty.one.afterLogin.EventPageButton;
import com.twenty.one.afterLogin.EventPageTextField;
=======
import com.twenty.one.beforeLogin.security.RegexChecker;
>>>>>>> Stashed changes

public class App {
  public static void main(String[] args) throws Exception {
    // create the object of Login Page to test if it's working

    try {
<<<<<<< Updated upstream
        UIManager.setLookAndFeel( new FlatDarkLaf() );
    } catch( Exception ex ) {
        System.err.println( "Failed to initialize LaF" );
    }
        //new LoginPage();
        
        //new EventPageButton();
        new EventPageTextField();
        //Sending an email to recipient
//        mailUtils.sendMail("javacomtwentyone@gmail.com");
=======
      UIManager.setLookAndFeel(new FlatDarkLaf());
    } catch (Exception ex) {
      System.err.println("Failed to initialize LaF");
>>>>>>> Stashed changes
    }
    // new LoginPage();

    System.out.println(RegexChecker.checkEmail("tr@gmail.com"));
    // System.out.println(true);

    // new EventPage();
    // Sending an email to recipient
    // mailUtils.sendMail("javacomtwentyone@gmail.com");
  }
}
