package com.twenty.one;


import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.twenty.one.authenticate.login.LoginPage;
import com.twenty.one.event.EventPage;

public class App 
{
    public static void main( String[] args ) throws Exception {
        //create the object of Login Page to test if it's working

    try {
        UIManager.setLookAndFeel( new FlatDarkLaf() );
    } catch( Exception ex ) {
        System.err.println( "Failed to initialize LaF" );
    }
        // new LoginPage();
        
        EventPage test = new EventPage();
		
		test.setVisible(true);
        //Sending an email to recipient
//        mailUtils.sendMail("javacomtwentyone@gmail.com");
    }
}
