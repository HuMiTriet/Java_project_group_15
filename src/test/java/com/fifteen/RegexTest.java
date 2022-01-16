package com.fifteen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fifteen.auth.security.RegexChecker;

import org.junit.jupiter.api.Test;

/**
 * Unit test for the User sign up function from User
 */
public class RegexTest {
  // private RegexChecker regexChecker = new RegexChecker();

  @Test
  void withAtsign() {
    assertEquals(true, RegexChecker.checkEmail("lm@example.com"));
  }

  @Test
  void withoutAtsign() {
    assertEquals(false, RegexChecker.checkEmail("lmexample.com"));
  }

  @Test
  void dash() {
    assertEquals(false, RegexChecker.checkEmail("Bruh_lmao@example.com"));
  }

  @Test
  void period() {
    assertEquals(true, RegexChecker.checkEmail("vip.pro.gammer@bruh.com"));
  }

  @Test
  void plusAndDash() {
    assertEquals(true, RegexChecker.checkEmail("gamer+girl+bath+water@simps-united.uk"));
  }

  @Test
  void webmailEmail() {
    assertEquals(true, RegexChecker.checkEmail("triet.huynhminh@stud.fra-uas.de"));
  }

  @Test
  void shortEmail() {
    assertEquals(true, RegexChecker.checkEmail("t@g.com"));
  }
}
