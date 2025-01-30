package org.calculator;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

class CalculatorTest {

    @Test
    public void methodAddTest() {
        double firstOperation = 1;
        double secondOperation = 1;
        double expectedResult = 2;

        double result = Calculator.add(firstOperation, secondOperation);

        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testSubtract() {
        assertEquals(1.0, Calculator.subtract(4.0, 3.0), 0.0001);
    }

    @Test
    public void testMultiply() {
        assertEquals(6.0, Calculator.multiply(2.0, 3.0), 0.0001);
    }

    @Test
    public void testDivide() {
        assertEquals(2.0, Calculator.divide(6.0, 3.0), 0.0001);
    }

    @ParameterizedTest
    @CsvSource({
            "10.0, 2.0, 5.0",
            "7.5, 2.5, 3.0",
            "-6.0, 2.0, -3.0",
            "0.0, 1.0, 0.0"
    })
    void testDivide(double a, double b, double expectedResult) {
        var result = Calculator.divide(a, b);
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> Calculator.divide(10.0, 0.0));
    }
}
