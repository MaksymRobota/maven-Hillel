package org.example.service;

import org.example.model.Game;
import org.example.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    private GameService gameService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        gameService = new GameService(gameRepository);
    }

    @Test
    public void testAddGame() throws SQLException {
        Game game = new Game(0, "Game 1", LocalDate.now(), 4.5, 29.99, "A cool game", "Action", LocalDate.now());

        gameService.addGame(new Scanner("Game 1\n2025-01-01\n4.5\n29.99\nA cool game\nAction"));

        verify(gameRepository, times(1)).addGame(any(Game.class));
    }

    @Test
    public void testRemoveGame() throws SQLException {
        gameService.removeGame(new Scanner("Game 1"));

        verify(gameRepository, times(1)).removeGame("Game 1");
    }


    @Test
    public void testShowAllGames() throws SQLException {
        Game game1 = new Game(1, "Game 1", LocalDate.now(), 4.5, 29.99, "A cool game", "Action", LocalDate.now());
        Game game2 = new Game(2, "Game 2", LocalDate.now(), 3.8, 19.99, "Another cool game", "Adventure", LocalDate.now());
        List<Game> gameList = Arrays.asList(game1, game2);

        when(gameRepository.getAllGames()).thenReturn(gameList);

        gameService.showAllGames();

        verify(gameRepository, times(1)).getAllGames();
    }

    @Test
    public void testSearchGame() throws SQLException {
        Game game = new Game(1, "Game 1", LocalDate.now(), 4.5, 29.99, "A cool game", "Action", LocalDate.now());

        when(gameRepository.searchGameByName("Game 1")).thenReturn(game);

        gameService.searchGame(new Scanner("Game 1"));

        verify(gameRepository, times(1)).searchGameByName("Game 1");
    }

    @Test
    public void testSearchGameByPrice() throws SQLException {
        Game game1 = new Game(1, "Game 1", LocalDate.now(), 4.5, 29.99, "A cool game", "Action", LocalDate.now());
        Game game2 = new Game(2, "Game 2", LocalDate.now(), 3.8, 19.99, "Another cool game", "Adventure", LocalDate.now());
        List<Game> gameList = Arrays.asList(game1, game2);

        when(gameRepository.searchGameByPrice(30.0)).thenReturn(gameList);

        gameService.searchGameByPrice(new Scanner("30"));

        verify(gameRepository, times(1)).searchGameByPrice(30.0);
    }

    @Test
    public void testSearchGameByType() throws SQLException {
        Game game1 = new Game(1, "Game 1", LocalDate.now(), 4.5, 29.99, "A cool game", "Action", LocalDate.now());
        Game game2 = new Game(2, "Game 2", LocalDate.now(), 3.8, 19.99, "Another cool game", "Action", LocalDate.now());
        List<Game> gameList = Arrays.asList(game1, game2);

        when(gameRepository.searchGameByType("Action")).thenReturn(gameList);

        gameService.searchGameByType(new Scanner("Action"));

        verify(gameRepository, times(1)).searchGameByType("Action");
    }

    @Test
    public void testSearchGameByNameNotFound() throws SQLException {
        when(gameRepository.searchGameByName("NonExistentGame")).thenReturn(null);

        gameService.searchGame(new Scanner("NonExistentGame"));

        verify(gameRepository, times(1)).searchGameByName("NonExistentGame");
    }

    @Test
    public void testSearchGameByPriceNoResults() throws SQLException {
        when(gameRepository.searchGameByPrice(10.0)).thenReturn(List.of());

        gameService.searchGameByPrice(new Scanner("10"));

        verify(gameRepository, times(1)).searchGameByPrice(10.0);
    }

    @Test
    public void testSearchGameByTypeNoResults() throws SQLException {
        when(gameRepository.searchGameByType("Strategy")).thenReturn(List.of());

        gameService.searchGameByType(new Scanner("Strategy"));

        verify(gameRepository, times(1)).searchGameByType("Strategy");
    }
}
