package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionSingleton {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "dockerStrongPwd123";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            initializeDatabase();
        }
        return connection;
    }

    private static void initializeDatabase() {
        String sql = """
                    CREATE TABLE IF NOT EXISTS games (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        release_date DATE NOT NULL,
                        rating DOUBLE PRECISION,
                        cost DOUBLE PRECISION,
                        description TEXT,
                        type VARCHAR(100),
                        creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    );
                """;

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table 'games' is ready.");
        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }
}
