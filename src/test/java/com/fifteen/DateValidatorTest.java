package com.fifteen;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.DateValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * for checking the date format
 */
// DateValidator a = new DateValidator();
// Date b = a.validate("02/02/1994 23:59", "dd/MM/yyyy HH:mm");

// System.out.println(b.toString());
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DateValidatorTest {
  private DateValidator dateValidator;

  private static final String PATTERN = "dd/MM/yyyy HH:mm";

  @BeforeAll
  void setUpValidator() {
    dateValidator = new DateValidator();
  }

  @Test
  void checkDate() {
    assertTrue(GenericValidator.isDate("02/02/1994 13:00", PATTERN, true));
  }

  // @Test
  // void convertToDateClassIfTrue() {
  // }
}
