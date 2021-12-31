package com.twenty.one.database;

import java.sql.SQLException;

import com.twenty.one.security.PasswordHasher;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * A class that stores all of the User information. the userID will be automatically assigned
 * by the Oracle sever.
 * Password is automatically hashed after the text password is passed in the constructor
 * @author PJ
 */
@Getter @ToString @Setter
public class User {
	private String email;
	private String username;
	private String hashedPassword;
	private String userID = "NA";
	private int isAdmin; 

	private User() {};

	public static User createUserFromSignUp (String email, String username, String textPassword, int isAdmin) {
		User newUser = new User();
		newUser.setEmail(email);
		newUser.setUsername(username);
		newUser.setHashedPassword(PasswordHasher.sha2(textPassword));
		newUser.setIsAdmin(isAdmin);
		try {
			DBMethod.signUp(newUser);
			newUser.setUserID(DBMethod.getUserId(newUser));
		} catch (SQLException e) {
			System.err.println("Failed to create user from sign up");
			e.printStackTrace();
		}
		return newUser;
	}
}
