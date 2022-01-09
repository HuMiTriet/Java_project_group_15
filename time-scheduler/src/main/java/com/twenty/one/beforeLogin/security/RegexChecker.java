package com.twenty.one.beforeLogin.security;

public class RegexChecker {
  public static boolean checkEmail(String proposeEmail) {
    String emailPattern = "[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]+";
    return emailPattern.matches(proposeEmail);
  }
}
