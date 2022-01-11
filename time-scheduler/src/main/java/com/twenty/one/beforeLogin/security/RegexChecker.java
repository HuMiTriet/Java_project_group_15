package com.twenty.one.beforeLogin.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChecker {

  private Pattern pattern;
  private Matcher matcher;

  private static final String EMAIL_PATTERN = "^[A-Za-z0-9-\\+]+(\\.[A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

  public boolean checkEmail(String proposeEmail) {
    pattern = Pattern.compile(EMAIL_PATTERN);
    matcher = pattern.matcher(proposeEmail);

    return matcher.matches();
  }
}
