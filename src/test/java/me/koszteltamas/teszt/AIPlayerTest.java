package me.koszteltamas.teszt;

import me.koszteltamas.game.AIPlayer;
import me.koszteltamas.board.BoardManager;
import me.koszteltamas.model.Move;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AIPlayerTest {

    @Test
    void testGenerateMoveValidColumn() {
        BoardManager boardManager = new BoardManager("testEmpty.txt");
        AIPlayer aiPlayer = new AIPlayer();

        Move move = aiPlayer.generateMove(boardManager);
        List<Integer> validColumns = boardManager.getValidColumns();

        assertNotNull(move, "AI should generate a valid move.");
        assertTrue(validColumns.contains(move.getColumn()), "AI should choose a valid column.");
    }
}
