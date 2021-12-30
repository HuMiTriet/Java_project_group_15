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
	private String textPassword;
	private @Setter String userID;
	private int isAdmin; 
	public User (String email, String username, String textPassword, int isAdmin) {
		this.email = email;
		this.username = username;
		this.textPassword = textPassword;
		this.isAdmin = isAdmin;
	}
}
