package me.koszteltamas.teszt;

import me.koszteltamas.game.BoardManager;
import me.koszteltamas.model.Move;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardManagerTest {

    @Test
    void testApplyMoveValid() {
        BoardManager boardManager = new BoardManager("testEmpty.txt");
        Move move = new Move(3, 'S'); // 'S' a játékos jele

        boolean result = boardManager.applyMove(move);

        assertTrue(result, "The move should be valid.");
        assertEquals('S', boardManager.getBoard()[5][3], "The player's token should be placed in the correct column.");
    }
}