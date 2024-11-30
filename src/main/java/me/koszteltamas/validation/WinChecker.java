package me.koszteltamas.validation;

import me.koszteltamas.game.Board;

public class WinChecker {

    public boolean checkWin(Board board, int row, int col, char player) {
        return checkDirection(board, row, col, player, 0, 1) ||
                checkDirection(board, row, col, player, 1, 0) ||
                checkDirection(board, row, col, player, 1, 1) ||
                checkDirection(board, row, col, player, 1, -1);
    }

    private boolean checkDirection(Board board, int row, int col, char player, int dRow, int dCol) {
        int count = 1;

        for (int i = 1; i < 4; i++) {
            int r = row + i * dRow;
            int c = col + i * dCol;
            if (r >= 0 && r < 6 && c >= 0 && c < 7 && board.getCell(r, c) == player) {
                count++;
            } else {
                break;
            }
        }

        for (int i = 1; i < 4; i++) {
            int r = row - i * dRow;
            int c = col - i * dCol;
            if (r >= 0 && r < 6 && c >= 0 && c < 7 && board.getCell(r, c) == player) {
                count++;
            } else {
                break;
            }
        }
        return count >= 4;
    }
}
