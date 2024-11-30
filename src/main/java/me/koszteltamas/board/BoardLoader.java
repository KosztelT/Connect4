package me.koszteltamas.board;

import me.koszteltamas.game.Board;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BoardLoader {

    public static Board loadBoardFromFile(String filename) {
        Board board = new Board();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            for (int row = 0; row < 6; row++) {
                String line = reader.readLine();
                for (int col = 0; col < 7; col++) {
                    board.setCell(row, col, line.charAt(col));
                }
            }
        } catch (IOException e) {
            // Ha hiba történik, akkor a tábla üres lesz
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 7; col++) {
                    board.setCell(row, col, '.');
                }
            }
        }
        return board;
    }
}
