package me.koszteltamas.board;

import me.koszteltamas.game.*;
import me.koszteltamas.model.Move;
import me.koszteltamas.validation.MoveValidator;
import me.koszteltamas.validation.WinChecker;

import java.util.List;

public class BoardManager {
    private final Board board;
    private final MoveValidator moveValidator;
    private final WinChecker winChecker;
    private final ColumnManager columnManager;

    public BoardManager(String filename) {
        this.board = BoardLoader.loadBoardFromFile(filename);
        this.moveValidator = new MoveValidator();
        this.winChecker = new WinChecker();
        this.columnManager = new ColumnManager();
    }

    public void displayBoard() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                System.out.print(board.getCell(row, col) + " ");
            }
            System.out.println();
        }
    }

    public boolean applyMove(Move move) {
        int col = move.getColumn();
        char player = move.getPlayer();

        if (!moveValidator.isMoveValid(board, col)) {
            return false;
        }

        for (int row = 5; row >= 0; row--) {
            if (board.getCell(row, col) == '.') {
                return moveValidator.applyMove(board, row, col, player);
            }
        }
        return false;
    }

    public boolean checkWinCondition(Move move) {
        int row = -1;
        int col = move.getColumn();
        char player = move.getPlayer();

        for (int r = 0; r < 6; r++) {
            if (board.getCell(r, col) == player) {
                row = r;
                break;
            }
        }

        if (row == -1) return false;

        return winChecker.checkWin(board, row, col, player);
    }

    public List<Integer> getValidColumns() {
        return columnManager.getValidColumns(board);
    }

    public void saveGame(String filename) {
        BoardSaver.saveBoardToFile(board, filename);
    }
    }
