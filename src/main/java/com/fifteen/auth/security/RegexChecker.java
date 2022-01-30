package com.fifteen.auth.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that provide a regex checker for user entered Strings
 * 
 * @author Triet Huynh
 */
public class RegexChecker {

  private static Pattern pattern;
  private static Matcher matcher;

  /**
   * A regex pattern that checks if an email pattern includes alphabetical
   * (also can have one plus sign) characters followed by the @ symbol. followed
   * by more characters and one dot, after the dot the Characters length must be
   * at least two.
   * 
   * @author Triet Huynh
   */
  private static final String EMAIL_PATTERN = "^[A-Za-z0-9-\\+]+(\\.[A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

  /**
   * A boolean function that does regex checking based on the pattern above and
   * return true if email matches that pattern, else false
   * 
   * @param proposeEmail the user entered email text
   * @author Triet Huynh
   */
  public static boolean checkEmail(String proposeEmail) {
    pattern = Pattern.compile(EMAIL_PATTERN);
    matcher = pattern.matcher(proposeEmail);

    return matcher.matches();
  }
}
