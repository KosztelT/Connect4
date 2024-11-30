package me.koszteltamas.teszt;

import me.koszteltamas.model.Move;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    @Test
    void testConstructorAndGetters() {
        Move move = new Move(3, 'X');
        assertEquals(3, move.getColumn());
        assertEquals('X', move.getPlayer());
    }

    @Test
    void testEquals() {
        Move move1 = new Move(2, 'O');
        Move move2 = new Move(2, 'O');
        Move move3 = new Move(3, 'X');
        Move move4 = new Move(2, 'X');

        assertEquals(move1, move2); // Azonos értékű objektumok
        assertNotEquals(move1, move3); // Különböző oszlop
        assertNotEquals(move1, move4); // Különböző játékos
        assertNotEquals(move1, null); // Null ellenőrzés
        assertNotEquals(move1, "Not a Move"); // Más típus ellenőrzés
    }

    @Test
    void testHashCode() {
        Move move1 = new Move(4, 'X');
        Move move2 = new Move(4, 'X');

        assertEquals(move1.hashCode(), move2.hashCode());
    }

    @Test
    void testToString() {
        Move move = new Move(1, 'O');
        assertEquals("Move{column=1, player=O}", move.toString());
    }
}
