package me.koszteltamas.game;

import me.koszteltamas.model.Move;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BoardManager {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private final char[][] board;

    public BoardManager(String filename) {
        this.board = loadGame(filename);
    }

    private char[][] loadGame(String filename) {
        char[][] newBoard = new char[ROWS][COLUMNS];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            for (int row = 0; row < ROWS; row++) {
                String line = reader.readLine();
                if (line != null && line.length() == COLUMNS) {
                    for (int col = 0; col < COLUMNS; col++) {
                        newBoard[row][col] = line.charAt(col);
                    }
                }
            }
        } catch (IOException e) {
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLUMNS; col++) {
                    newBoard[row][col] = '.';
                }
            }
        }
        return newBoard;
    }

    public void saveGame(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (char[] row : board) {
                for (char cell : row) {
                    writer.write(cell);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving the game: " + e.getMessage());
        }
    }

    public void displayBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public boolean applyMove(Move move) {
        int col = move.getColumn();
        char player = move.getPlayer();

        if (col < 0 || col >= COLUMNS || board[0][col] != '.') {
            return false;
        }

        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == '.') {
                board[row][col] = player;
                return true;
            }
        }
        return false;
    }

    public boolean checkWinCondition(Move move) {
        int row = -1;
        int col = move.getColumn();
        char player = move.getPlayer();

        for (int r = 0; r < ROWS; r++) {
            if (board[r][col] == player) {
                row = r;
                break;
            }
        }
        if (row == -1) return false;

        return checkDirection(row, col, player, 0, 1) ||
                checkDirection(row, col, player, 1, 0) ||
                checkDirection(row, col, player, 1, 1) ||
                checkDirection(row, col, player, 1, -1);
    }

    private boolean checkDirection(int row, int col, char player, int dRow, int dCol) {
        int count = 1;

        for (int i = 1; i < 4; i++) {
            int r = row + i * dRow;
            int c = col + i * dCol;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLUMNS && board[r][c] == player) {
                count++;
            } else {
                break;
            }
        }

        for (int i = 1; i < 4; i++) {
            int r = row - i * dRow;
            int c = col - i * dCol;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLUMNS && board[r][c] == player) {
                count++;
            } else {
                break;
            }
        }
        return count >= 4;
    }

    public List<Integer> getValidColumns() {
        List<Integer> validColumns = new ArrayList<>();
        for (int col = 0; col < COLUMNS; col++) {
            if (board[0][col] == '.') {
                validColumns.add(col);
            }
        }
        return validColumns;
    }

    // ÚJ metódus: A játéktábla lekérése
    public char[][] getBoard() {
        return board;
    }
}
