package me.koszteltamas;

public class Move {
    private final int column;
    private final char player;

    public Move(int column, char player) {
        this.column = column;
        this.player = player;
    }

    public int getColumn() {
        return column;
    }

    public char getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "Move{column=" + column + ", player=" + player + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Move move = (Move) obj;
        return column == move.column && player == move.player;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(column);
        result = 31 * result + Character.hashCode(player);
        return result;
    }
}
