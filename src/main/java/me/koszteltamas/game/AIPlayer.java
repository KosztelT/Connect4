package me.koszteltamas.game;

import me.koszteltamas.model.Move;

import java.util.List;
import java.util.Random;

public class AIPlayer {
    private final Random random = new Random();

    public Move generateMove(BoardManager boardManager) {
        List<Integer> validColumns = boardManager.getValidColumns();
        if (validColumns.isEmpty()) {
            return null; // Döntetlen, nincs érvényes lépés
        }

        int column = validColumns.get(random.nextInt(validColumns.size()));
        return new Move(column, 'A'); // 'A' az AI által használt jelölés
    }
}
