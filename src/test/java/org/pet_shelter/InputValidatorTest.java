package org.pet_shelter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputValidatorTest {
    private final InputStream originalSystemIn = System.in;

    @AfterEach
    void restoreSystemInStream() {
        System.setIn(originalSystemIn);
    }

    private void setInputStream(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Test
    void testGetValidStringValidInput() {
        setInputStream("Rex");

        String result = InputValidator.getValidString("Enter a valid string: ");
        assertEquals("Rex", result);
    }

    @Test
    void testGetValidStringInvalidInput() {
        setInputStream("123\nTest");

        String result = InputValidator.getValidString("Enter a valid string: ");
        assertEquals("Test", result);
    }

    @Test
    void testGetValidAgeValidInput() {
        String input = "25";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        int result = InputValidator.getValidAge("Enter a valid age: ");
        assertEquals(25, result);
    }

    @Test
    public void testGetValidAgeInvalidInput() {
        String input = "abc\n30\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        int result = InputValidator.getValidAge("Enter a valid age: ");
        assertEquals(30, result);
    }

    @Test
    public void testGetValidAgeNegativeInput() {
        String input = "-10\n40\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        int result = InputValidator.getValidAge("Enter a valid age: ");
        assertEquals(40, result);
    }

}
