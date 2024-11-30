package me.koszteltamas.validation;

import me.koszteltamas.game.Board;

public class MoveValidator {

    public boolean isMoveValid(Board board, int col) {
        return col >= 0 && col < 7 && board.getCell(0, col) == '.';
    }

    public boolean applyMove(Board board, int row, int col, char player) {
        if (board.getCell(row, col) == '.') {
            board.setCell(row, col, player);
            return true;
        }
        return false;
    }
}
