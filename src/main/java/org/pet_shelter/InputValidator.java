package org.pet_shelter;

import java.util.Scanner;

public class InputValidator {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getValidString(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.matches("^[a-zA-Z]+$")) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter only letters.");
            }
        }
    }

    public static int getValidAge(String prompt) {
        int age;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                age = Integer.parseInt(input);
                if (age > 0) {
                    return age;
                } else {
                    System.out.println("Age must be a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Please enter a number.");
            }
        }
    }
}
