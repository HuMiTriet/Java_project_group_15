package com.fifteen.auth.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChecker {

  private static Pattern pattern;
  private static Matcher matcher;

  private static final String EMAIL_PATTERN = "^[A-Za-z0-9-\\+]+(\\.[A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

  public static boolean checkEmail(String proposeEmail) {
    pattern = Pattern.compile(EMAIL_PATTERN);
    matcher = pattern.matcher(proposeEmail);

    return matcher.matches();
  }
}
