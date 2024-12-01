package me.koszteltamas.teszt;

import me.koszteltamas.game.Board;
import me.koszteltamas.validation.WinChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WinCheckerTest {

    private WinChecker winChecker;
    private MockBoard board;

    @BeforeEach
    void setUp() {
        winChecker = new WinChecker();
        board = new MockBoard();
    }

    @Test
    void testHorizontalWin() {
        // X X X X
        board.setCell(0, 0, 'X');
        board.setCell(0, 1, 'X');
        board.setCell(0, 2, 'X');
        board.setCell(0, 3, 'X');

        assertTrue(winChecker.checkWin(board, 0, 0, 'X'));
        assertTrue(winChecker.checkWin(board, 0, 1, 'X'));
        assertTrue(winChecker.checkWin(board, 0, 2, 'X'));
        assertTrue(winChecker.checkWin(board, 0, 3, 'X'));
    }

    @Test
    void testVerticalWin() {
        // X
        // X
        // X
        // X
        board.setCell(0, 0, 'X');
        board.setCell(1, 0, 'X');
        board.setCell(2, 0, 'X');
        board.setCell(3, 0, 'X');

        assertTrue(winChecker.checkWin(board, 0, 0, 'X'));
        assertTrue(winChecker.checkWin(board, 1, 0, 'X'));
        assertTrue(winChecker.checkWin(board, 2, 0, 'X'));
        assertTrue(winChecker.checkWin(board, 3, 0, 'X'));
    }

    @Test
    void testDiagonalWinPositiveSlope() {
        //      X
        //    X
        //  X
        // X
        board.setCell(3, 0, 'X');
        board.setCell(2, 1, 'X');
        board.setCell(1, 2, 'X');
        board.setCell(0, 3, 'X');

        assertTrue(winChecker.checkWin(board, 3, 0, 'X'));
        assertTrue(winChecker.checkWin(board, 2, 1, 'X'));
        assertTrue(winChecker.checkWin(board, 1, 2, 'X'));
        assertTrue(winChecker.checkWin(board, 0, 3, 'X'));
    }

    @Test
    void testDiagonalWinNegativeSlope() {
        // X
        //   X
        //     X
        //       X
        board.setCell(0, 0, 'X');
        board.setCell(1, 1, 'X');
        board.setCell(2, 2, 'X');
        board.setCell(3, 3, 'X');

        assertTrue(winChecker.checkWin(board, 0, 0, 'X'));
        assertTrue(winChecker.checkWin(board, 1, 1, 'X'));
        assertTrue(winChecker.checkWin(board, 2, 2, 'X'));
        assertTrue(winChecker.checkWin(board, 3, 3, 'X'));
    }


    private static class MockBoard extends Board {
        private final char[][] grid = new char[6][7];

        @Override
        public char getCell(int row, int col) {
            return grid[row][col];
        }

        public void setCell(int row, int col, char value) {
            grid[row][col] = value;
        }
    }
}
