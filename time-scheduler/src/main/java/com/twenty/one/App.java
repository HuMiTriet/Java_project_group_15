package com.twenty.one;


import com.twenty.one.mailAPI.mailUtils;


public class App 
{
    public static void main( String[] args ) throws Exception {
        //create the object of Login Page to test if it's working
        //new LoginPage();

        //Sending an email to recipient
        mailUtils.sendMail("javacomtwentyone@gmail.com");

    }
}
