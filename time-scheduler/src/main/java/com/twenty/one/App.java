package com.twenty.one;


import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.twenty.one.beforeLogin.login.LoginPage;
import com.twenty.one.afterLogin.EventPageButton;
import com.twenty.one.afterLogin.EventPageTextField;

public class App 
{
    public static void main( String[] args ) throws Exception {
        //create the object of Login Page to test if it's working

    try {
        UIManager.setLookAndFeel( new FlatDarkLaf() );
    } catch( Exception ex ) {
        System.err.println( "Failed to initialize LaF" );
    }
        //new LoginPage();
        
        //new EventPageButton();
        new EventPageTextField();
        //Sending an email to recipient
//        mailUtils.sendMail("javacomtwentyone@gmail.com");
    }
}
