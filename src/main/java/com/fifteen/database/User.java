package com.fifteen.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A class that stores all of the User information. the userID will be
 * automatically assigned
 * by the Oracle sever.
 * Password is automatically hashed after the text password is passed in the
 * constructor
 * 
 * @author Triet Huynh
 */
@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private String email;
  private String username;
  private String hashedPassword;
  private String userID = "NA";
  private int isAdmin;

}
