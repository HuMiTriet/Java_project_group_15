package com.twenty.one;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

/**
 * Unit test for simple App.
 * @see https://www.vogella.com/tutorials/JUnit/article.html
 */
public class CalculatorTest 
{
    /**
     */
	Calculator cal;

	@BeforeEach
	public void setUp()
	{
		cal = new Calculator();
	}

    @Test
    @DisplayName("Test multipying 2*3")
    void shouldAnswerWithTrue()
    {
        Assertions.assertEquals( 6 , cal.multi(2, 3));
    }

    @RepeatedTest(5)                                    
    @DisplayName("Ensure correct handling of zero")
    void testMultiplyWithZero() {
        Assertions.assertEquals(0, cal.multi(0, 5), "Multiple with zero should be zero");
        Assertions.assertEquals(0, cal.multi(5, 0), "Multiple with zero should be zero");
    }
}

