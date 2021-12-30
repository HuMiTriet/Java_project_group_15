package com.twenty.one.database;

import com.twenty.one.security.HashPassword;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * A class that stores all of the User information. the userID will be assigned
 * by the Oracle sever. 
 * Password is automatically hashed after the text password is passed in the constructor
 * @author PJ
 */
@Getter @ToString
public class User {
	private String email;
	private String username;
	private String hashedPassword;
	private @Setter String userID = "NA";
	private int isAdmin; 
	public User (String email, String username, String textPassword, int isAdmin) {
		this.email = email;
		this.username = username;
		this.hashedPassword = HashPassword.sha2(textPassword);
		this.isAdmin = isAdmin;
	}
}
