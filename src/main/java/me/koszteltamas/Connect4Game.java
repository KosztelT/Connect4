package me.koszteltamas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Connect4Game {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private final char[][] board;
    private final Random random = new Random();

    public Connect4Game(String filename) {
        this.board = loadGame(filename);
    }

    // Betöltés a fájlból, vagy üres pálya létrehozása
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
            // Ha nem található a fájl, létrehozunk egy üres táblát
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLUMNS; col++) {
                    newBoard[row][col] = '.';
                }
            }
        }
        return newBoard;
    }

    // Játékállapot mentése
    public void saveGame(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLUMNS; col++) {
                    writer.write(board[row][col]);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving the game: " + e.getMessage());
        }
    }

    // A pálya kirajzolása
    public void printBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    // Ellenőrizni, hogy az adott lépés győzelmet eredményez-e
    public boolean isWinningMove(Move move) {
        int row = -1;
        int col = move.getColumn();
        char player = move.getPlayer();

        // Megkeressük, melyik sorba került a korong
        for (int r = 0; r < ROWS; r++) {
            if (board[r][col] == player) {
                row = r;
                break;
            }
        }
        if (row == -1) return false;

        // Ellenőrizzük a különböző irányokat
        return checkDirection(row, col, player, 0, 1) // Vízszintes
                || checkDirection(row, col, player, 1, 0) // Függőleges
                || checkDirection(row, col, player, 1, 1) // Átlós (jobb felé le)
                || checkDirection(row, col, player, 1, -1); // Átlós (bal felé le)
    }

    private boolean checkDirection(int row, int col, char player, int dRow, int dCol) {
        int count = 1;
        // Ellenőriz balra/lefelé
        for (int i = 1; i < 4; i++) {
            int r = row + i * dRow;
            int c = col + i * dCol;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLUMNS && board[r][c] == player) {
                count++;
            } else {
                break;
            }
        }
        // Ellenőriz jobbra/felfelé
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

    // AI lépés kiválasztása véletlenszerűen
    public Move getAIMove() {
        List<Integer> validColumns = getValidColumns();

        // Ha nincsenek érvényes oszlopok, a játék döntetlen
        if (validColumns.isEmpty()) {
            return null;
        }

        // Véletlenszerű oszlop választása az elérhetőek közül
        int column = validColumns.get(random.nextInt(validColumns.size()));
        return new Move(column, 'A');
    }

    // Érvényes oszlopok listája
    private List<Integer> getValidColumns() {
        List<Integer> validColumns = new ArrayList<>();
        for (int col = 0; col < COLUMNS; col++) {
            if (board[0][col] == '.') { // Ellenőrizzük, hogy a felső sor üres-e
                validColumns.add(col);
            }
        }
        return validColumns;
    }

    // Lépés végrehajtása
    public boolean makeMove(Move move) {
        int col = move.getColumn();
        char player = move.getPlayer();

        // Ellenőrizzük, hogy a mozgás érvényes-e
        if (col < 0 || col >= COLUMNS || board[0][col] != '.') {
            return false;
        }

        // Az adott oszlop legalsó üres helyére helyezzük a játékos korongját
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == '.') {
                board[row][col] = player;
                return true;
            }
        }
        return false;
    }
}