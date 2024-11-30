package me.koszteltamas.board;

import me.koszteltamas.game.Board;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BoardSaver {

    public static void saveBoardToFile(Board board, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 7; col++) {
                    writer.write(board.getCell(row, col));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving the game: " + e.getMessage());
        }
    }
}
