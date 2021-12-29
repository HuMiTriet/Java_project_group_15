package com.twenty.one;

import com.twenty.one.security.ConnectToDB;
public class App 
{
    public static void main( String[] args )
    {

        ConnectToDB.connect();
        ConnectToDB.addUser("Helloaa", "worldaaa", "aaaa");
    }
}
