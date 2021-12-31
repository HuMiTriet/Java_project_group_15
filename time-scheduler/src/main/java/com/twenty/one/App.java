package com.twenty.one;


import com.twenty.one.GUI.LoginPage;
import com.twenty.one.database.DBMethod;
import com.twenty.one.database.User;
import com.twenty.one.mailAPI.mailUtils;

public class App 
{
    public static void main( String[] args ) throws Exception {
        //create the object of Login Page to test if it's working
        new LoginPage();

        //Sending an email to recipient
//        mailUtils.sendMail("javacomtwentyone@gmail.com");
        String email = "S";
        String username= "S";
        String textPassword= "S";
        int isAdmin = 1;
        User newUser = User.createUserFromSignUp(email, username, textPassword, isAdmin);
        System.out.println(newUser.getEmail());
        System.out.println(newUser.getHashedPassword());
        System.out.println(newUser.getIsAdmin());
        System.out.println(newUser.getUserID());
        System.out.println(newUser.getUsername());

        String email2 = "2312312";
        String username2= "31232";
        String textPassword2= "312312";
        int isAdmin2 = 0;
        User newUser2 = User.createUserFromSignUp(email2, username2, textPassword2, isAdmin2);
        System.out.println(newUser2.getEmail());
        System.out.println(newUser2.getHashedPassword());
        System.out.println(newUser2.getIsAdmin());
        System.out.println(newUser2.getUserID());
        System.out.println(newUser2.getUsername());

        DBMethod.closeConnection();
    }
}
