package org.example.service;

import org.example.model.Game;
import org.example.repository.GameRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void addGame(Scanner scanner) throws SQLException {
        System.out.print("Enter game name: ");
        String name = scanner.nextLine();

        System.out.print("Enter release date (yyyy-mm-dd): ");
        LocalDate releaseDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter rating: ");
        double rating = scanner.nextDouble();

        System.out.print("Enter cost: ");
        double cost = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter type: ");
        String type = scanner.nextLine();

        Game game = new Game(0, name, releaseDate, rating, cost, description, type, LocalDate.now());
        gameRepository.addGame(game);
        System.out.println("Game added successfully!");
    }

    public void removeGame(Scanner scanner) throws SQLException {
        System.out.print("Enter game name to remove: ");
        String name = scanner.nextLine();

        gameRepository.removeGame(name);
        System.out.println("Game removed successfully!");
    }

    public void showAllGames() throws SQLException {
        List<Game> games = gameRepository.getAllGames();
        if (games.isEmpty()) {
            System.out.println("No games available.");
        } else {
            games.forEach(System.out::println);
        }
    }

    public void searchGame(Scanner scanner) throws SQLException {
        System.out.print("Enter game name: ");
        String name = scanner.nextLine();

        Game game = gameRepository.searchGameByName(name);
        if (game != null) {
            System.out.println(game);
        } else {
            System.out.println("Game not found.");
        }
    }

    public void searchGameByPrice(Scanner scanner) throws SQLException {
        System.out.print("Enter price to search: ");
        double price = Double.parseDouble(scanner.nextLine());

        List<Game> games = gameRepository.searchGameByPrice(price);
        if (!games.isEmpty()) {
            games.forEach(System.out::println);
        } else {
            System.out.println("No games found for the given price.");
        }
    }

    public void searchGameByType(Scanner scanner) throws SQLException {
        System.out.print("Enter type to search: ");
        String type = scanner.nextLine();

        List<Game> games = gameRepository.searchGameByType(type);
        if (!games.isEmpty()) {
            games.forEach(System.out::println);
        } else {
            System.out.println("No games found for the given type.");
        }
    }
}
