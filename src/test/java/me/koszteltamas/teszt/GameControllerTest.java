package me.koszteltamas.teszt;

import me.koszteltamas.game.AIPlayer;
import me.koszteltamas.game.Connect4Game;
import me.koszteltamas.main.GameController;
import me.koszteltamas.model.Move;
import me.koszteltamas.score.ScoreManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameControllerTest {

    private Connect4Game mockGame;
    private AIPlayer mockAIPlayer;
    private Scanner mockScanner;
    private GameController gameController;

    @BeforeEach
    void setUp() {
        mockGame = mock(Connect4Game.class);
        mockAIPlayer = mock(AIPlayer.class);
        mockScanner = mock(Scanner.class);
        gameController = new GameController(mockGame, mockAIPlayer, mockScanner);
    }

    @Test
    void testStartGame_PlayerWins() {
        when(mockScanner.next())
                .thenReturn("Player") // Name input
                .thenReturn("0") // Player's move
                .thenReturn("vege"); // Exit game

        when(mockGame.makeMove(any())).thenReturn(true);
        when(mockGame.isWinningMove(any())).thenReturn(true);

        gameController.startGame();

        verify(mockGame, times(1)).makeMove(new Move(0, 'S'));
        verify(mockGame, times(1)).isWinningMove(new Move(0, 'S'));
        assertEquals("Player", gameController.getPlayerName());
    }

    @Test
    void testStartGame_InvalidMove() {
        when(mockScanner.next())
                .thenReturn("Player")
                .thenReturn("invalid") // Invalid input
                .thenReturn("0") // Valid input
                .thenReturn("vege"); // Exit game

        when(mockGame.makeMove(any())).thenReturn(false);

        gameController.startGame();

        verify(mockGame, times(1)).makeMove(new Move(0, 'S'));
        verify(mockGame, never()).isWinningMove(any());
    }

    @Test
    void testStartGame_AILoses() {
        when(mockScanner.next())
                .thenReturn("Player")
                .thenReturn("0") // Player's move
                .thenReturn("vege"); // Exit game

        when(mockGame.makeMove(any())).thenReturn(true);
        when(mockAIPlayer.generateMove(any())).thenReturn(new Move(1, 'A'));
        when(mockGame.isWinningMove(new Move(1, 'A'))).thenReturn(false);

        gameController.startGame();

        verify(mockAIPlayer, times(1)).generateMove(any());
        verify(mockGame, times(1)).makeMove(new Move(1, 'A'));
    }
}
