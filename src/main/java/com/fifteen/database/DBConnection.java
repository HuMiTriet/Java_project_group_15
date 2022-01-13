package com.fifteen.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 * A singleton class
 * Connects to the frankfurt UAS remote SQL server using PJ's account 
 * Database Architecture:  
 * {@code  
  create table UserDB (
    email    varchar2(100) not null unique,
    username varchar2(100) not null unique,
    hashed_password varchar2(64) not null,
    user_id raw(16) default sys_guid() constraint userdb_userid_pk primary key,
    is_admin number(1) not null
)
}
 * @author PJ
 */
public class DBConnection {
	private static Connection connection = null;

	
	private DBConnection(){};

	/**
	 * Establish the connection to the remote database server
	 * @author PJ
	 */
    static
    {
        String url = "jdbc:oracle:thin:@db1.fb2.frankfurt-university.de:1521:info01";
        String username = "S1_student2_52";
        String password = "Soltum/14032002";

        try {
            connection = DriverManager.getConnection(url, username, password);
			// System.out.println("Connection established");
        } catch (SQLException e) {
            e.printStackTrace();
			// System.out.println("connection failed");
        }

    }
	static Connection getConnection() {
		return connection;
	}

}
