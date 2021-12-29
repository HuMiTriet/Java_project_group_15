package com.twenty.one.database;

import lombok.Getter;
import lombok.Setter;

/**
 * A class that stores all of the User information. the userID will be assigned
 * by the Oracle sever 
 * @author PJ
 */
@Getter
public class User {
	private String email;
	private String username;
	private String hashedPassword;
	private @Setter String userID;
	private int isAdmin; 
	public User (String email, String username, String hashedPassword, int isAdmin) {
		this.email = email;
		this.username = username;
		this.hashedPassword = hashedPassword;
		this.isAdmin = isAdmin;
	}
}
