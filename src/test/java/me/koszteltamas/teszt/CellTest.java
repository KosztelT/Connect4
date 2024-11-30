package me.koszteltamas.teszt;

import me.koszteltamas.model.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void testConstructorAndGetters() {
        Cell cell = new Cell(3, 5);
        assertEquals(3, cell.getRow());
        assertEquals(5, cell.getCol());
    }

    @Test
    void testEquals() {
        Cell cell1 = new Cell(2, 4);
        Cell cell2 = new Cell(2, 4);
        Cell cell3 = new Cell(3, 5);

        assertEquals(cell1, cell2); // Azonos értékű objektumok
        assertNotEquals(cell1, cell3); // Különböző értékű objektumok
        assertNotEquals(cell1, null); // Null ellenőrzés
        assertNotEquals(cell1, "Not a Cell"); // Más típus ellenőrzés
    }

    @Test
    void testHashCode() {
        Cell cell1 = new Cell(2, 4);
        Cell cell2 = new Cell(2, 4);

        assertEquals(cell1.hashCode(), cell2.hashCode());
    }

    @Test
    void testToString() {
        Cell cell = new Cell(3, 5);
        assertEquals("Cell{row=3, col=5}", cell.toString());
    }
}
