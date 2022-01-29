package com.fifteen;

import java.util.GregorianCalendar;

import com.fifteen.events.local.CheckDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DateValidatorTest {
  @Test
  void testDate() {
    GregorianCalendar calendar = CheckDate.validateDate("14/02/2002");
    System.out.println(calendar.get(GregorianCalendar.DATE));
    System.out.println(calendar.get(GregorianCalendar.MONTH));
    System.out.println(calendar.get(GregorianCalendar.YEAR));
  }

  @Test
  void testTime() {
    GregorianCalendar time = CheckDate.validateTime("14:15");
    System.out.println(time.get(GregorianCalendar.HOUR_OF_DAY));
    System.out.println(time.get(GregorianCalendar.MINUTE));
  }

  // @Test
  // void testTimeReturnNull() {
  // GregorianCalendar time = CheckDate.validateTime("14:15");
  // assertTrue(CheckDate.validateTime(timeString))
  // }
}
