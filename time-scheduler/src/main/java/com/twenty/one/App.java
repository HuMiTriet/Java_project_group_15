package com.twenty.one;


import com.twenty.one.GUI.LoginPage;
import com.twenty.one.database.DataBaseConnection;
import com.twenty.one.database.DatabaseMethod;
import com.twenty.one.database.User;


public class App 
{
    public static void main( String[] args )
    {
        User user = new User("email123", "username123", "hashedPassword123",0);
        DataBaseConnection.getConnection();
        DatabaseMethod.signUp(user);

        //create the object of Login Page to test if it's working
        new LoginPage();

    }
}
