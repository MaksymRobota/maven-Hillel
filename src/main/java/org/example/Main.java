package org.example;

import org.example.repository.GameRepository;
import org.example.service.GameService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameService gameService = new GameService(new GameRepository());
        Scanner scanner = new Scanner(System.in);
        var isRunning = true;
        while (isRunning) {
            System.out.println("\nGame Store Console");
            System.out.println("1. Add Game");
            System.out.println("2. Remove Game");
            System.out.println("3. Search Game by Name");
            System.out.println("4. Search Game by Price");
            System.out.println("5. Search Game by Type");
            System.out.println("6. Show All Games");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> gameService.addGame(scanner);
                    case 2 -> gameService.removeGame(scanner);
                    case 3 -> gameService.searchGame(scanner);
                    case 4 -> gameService.searchGameByPrice(scanner);
                    case 5 -> gameService.searchGameByType(scanner);
                    case 6 -> gameService.showAllGames();
                    case 7 -> {
                        System.out.println("Exiting...");
                        isRunning = false;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
                isRunning = false;
            }
        }
    }
}
