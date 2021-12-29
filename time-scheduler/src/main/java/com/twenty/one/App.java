package com.twenty.one;

import com.twenty.one.database.DataBaseConnection;
import com.twenty.one.database.DatabaseMethod;
import com.twenty.one.database.User;

public class App 
{
    public static void main( String[] args )
    {
        User user = new User("email12", "username12", "hashedPassword12",0);
        DataBaseConnection.getConnection();
        DatabaseMethod.signUp(user);
    }
}
