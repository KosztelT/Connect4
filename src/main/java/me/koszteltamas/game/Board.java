package me.koszteltamas.game;

public class Board {
    private final char[][] board;

    public Board() {
        this.board = new char[6][7];  // Alapértelmezett méret (6x7)
    }

    public char[][] getBoard() {
        return board;
    }

    public void setCell(int row, int col, char player) {
        board[row][col] = player;
    }

    public char getCell(int row, int col) {
        return board[row][col];
    }
}
