package com.fifteen;

import static org.junit.jupiter.api.Assertions.*;

import com.fifteen.auth.security.RegexChecker;

import org.junit.jupiter.api.*;

/**
 * Unit test for the User sign up function from User
 */
public class RegexTest {
  private RegexChecker regexChecker = new RegexChecker();

  @Test
  void withAtsign() {
    assertEquals(true, regexChecker.checkEmail("lm@example.com"));
  }

  @Test
  void withoutAtsign() {
    assertEquals(false, regexChecker.checkEmail("lmexample.com"));
  }

  @Test
  void dash() {
    assertEquals(false, regexChecker.checkEmail("Bruh_lmao@example.com"));
  }

  @Test
  void period() {
    assertEquals(true, regexChecker.checkEmail("vip.pro.gammer@bruh.com"));
  }

  @Test
  void plusAndDash() {
    assertEquals(true, regexChecker.checkEmail("gamer+girl+bath+water@simps-united.uk"));
  }

  @Test
  void webmailEmail() {
    assertEquals(true, regexChecker.checkEmail("triet.huynhminh@stud.fra-uas.de"));
  }

}
