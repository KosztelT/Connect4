package me.koszteltamas.teszt;

import me.koszteltamas.game.Connect4Game;
import me.koszteltamas.model.Move;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Connect4GameTest {

    @Test
    void testIsWinningMoveHorizontal() {
        Connect4Game game = new Connect4Game("testEmpty.txt");

        // Hozzunk létre egy vízszintes győzelmi helyzetet
        game.makeMove(new Move(0, 'S'));
        game.makeMove(new Move(1, 'S'));
        game.makeMove(new Move(2, 'S'));
        game.makeMove(new Move(3, 'S')); // Győztes lépés

        boolean result = game.isWinningMove(new Move(3, 'S'));

        assertTrue(result, "The move should result in a win horizontally.");
    }
}
