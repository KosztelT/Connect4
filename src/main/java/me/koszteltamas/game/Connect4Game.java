package me.koszteltamas.game;

import me.koszteltamas.board.BoardManager;
import me.koszteltamas.model.Move;

public class Connect4Game {
    private final BoardManager boardManager;

    public Connect4Game(String filename) {
        this.boardManager = new BoardManager(filename);
    }

    public BoardManager getBoardManager() {
        return boardManager;
    }

    public boolean makeMove(Move move) {
        return boardManager.applyMove(move);
    }

    public boolean isWinningMove(Move move) {
        return boardManager.checkWinCondition(move);
    }

    public void printBoard() {
        boardManager.displayBoard();
    }

    public void saveGame(String filename) {
        boardManager.saveGame(filename);
    }
}
