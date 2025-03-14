package org.example.repository;

import org.example.ConnectionSingleton;
import org.example.model.Game;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GameRepository {

    public void addGame(Game game) throws SQLException {
        String sql = "INSERT INTO games (name, release_date, rating, cost, description, type, creation_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionSingleton.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, game.getName());
            statement.setDate(2, Date.valueOf(game.getReleaseDate()));
            statement.setDouble(3, game.getRating());
            statement.setDouble(4, game.getCost());
            statement.setString(5, game.getDescription());
            statement.setString(6, game.getType());
            statement.setDate(7, Date.valueOf(game.getCreationDate()));

            statement.executeUpdate();
        }
    }

    public void removeGame(String name) throws SQLException {
        String sql = "DELETE FROM games WHERE name = ?";
        try (Connection connection = ConnectionSingleton.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.executeUpdate();
        }
    }

    public List<Game> getAllGames() throws SQLException {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM games ORDER BY creation_date DESC";
        try (Connection connection = ConnectionSingleton.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                games.add(mapGame(resultSet));
            }
        }
        return games;
    }

    public Game searchGameByName(String name) throws SQLException {
        String sql = "SELECT * FROM games WHERE name = ?";
        try (Connection connection = ConnectionSingleton.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapGame(resultSet);
            }
        }
        return null;
    }

    public List<Game> searchGameByPrice(double maxPrice) throws SQLException {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM games WHERE cost <= ? ORDER BY cost ASC";
        try (Connection connection = ConnectionSingleton.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, maxPrice);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                games.add(mapGame(resultSet));
            }
        }
        return games;
    }
    
    public List<Game> searchGameByType(String type) throws SQLException {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM games WHERE type = ? ORDER BY name ASC";
        try (Connection connection = ConnectionSingleton.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, type);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                games.add(mapGame(resultSet));
            }
        }
        return games;
    }

    private Game mapGame(ResultSet resultSet) throws SQLException {
        return new Game(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getDate("release_date").toLocalDate(),
                resultSet.getDouble("rating"),
                resultSet.getDouble("cost"),
                resultSet.getString("description"),
                resultSet.getString("type"),
                resultSet.getDate("creation_date").toLocalDate()
        );
    }
}
