package com.twenty.one;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

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
        assertEquals( 6 , cal.multi(2, 3));
    }

    @RepeatedTest(5)                                    
    @DisplayName("Ensure correct handling of zero")
    void testMultiplyWithZero() {
        assertEquals(0, cal.multi(0, 5), "Multiple with zero should be zero");
        assertEquals(0, cal.multi(5, 0), "Multiple with zero should be zero");
    }
}

