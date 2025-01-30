package org.calculator;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java -jar Calculator.jar <num1> <operator> <num2>");
            return;
        }

        try {
            double num1 = Double.parseDouble(args[0]);
            double num2 = Double.parseDouble(args[2]);
            String operator = args[1];

            double result;
            switch (operator) {
                case "+":
                    result = Calculator.add(num1, num2);
                    break;
                case "-":
                    result = Calculator.subtract(num1, num2);
                    break;
                case "x":
                    result = Calculator.multiply(num1, num2);
                    break;
                case "/":
                    result = Calculator.divide(num1, num2);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }

            System.out.println("Result: " + result);
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numbers.");
        } catch (ArithmeticException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
