package me.koszteltamas.board;

import me.koszteltamas.game.Board;

import java.util.ArrayList;
import java.util.List;

public class ColumnManager {

    public List<Integer> getValidColumns(Board board) {
        List<Integer> validColumns = new ArrayList<>();
        for (int col = 0; col < 7; col++) {
            if (board.getCell(0, col) == '.') {
                validColumns.add(col);
            }
        }
        return validColumns;
    }
}
