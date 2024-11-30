package me.koszteltamas.teszt;

import static org.junit.jupiter.api.Assertions.*;

import me.koszteltamas.board.BoardSaver;
import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import me.koszteltamas.game.Board;
import static org.mockito.Mockito.*;

public class BoardSaverTest {

    @Test
    public void testSaveBoardToFile() throws IOException {
        // Mock-oljuk a Board osztályt
        Board mockBoard = mock(Board.class);

        // A mockBoard getCell metódusa úgy viselkedik, mintha minden cella 'X' karaktert adna vissza
        when(mockBoard.getCell(anyInt(), anyInt())).thenReturn('X');

        // A fájl neve, ahová mentjük az adatokat
        String filename = "testFile.txt";

        // Mentjük el a tábla adatokat a fájlba
        BoardSaver.saveBoardToFile(mockBoard, filename);

        // Ellenőrizzük, hogy a fájl létrejött-e
        File file = new File(filename);
        assertTrue(file.exists(), "The file should be created");

        // Olvassuk be a fájlt és ellenőrizzük a tartalmát
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            for (int row = 0; row < 6; row++) {
                String line = reader.readLine();
                assertEquals("XXXXXXX", line, "Each row should be 'XXXXXXX'");
            }
        }

        // Töröljük a fájlt a teszt után
        file.delete();
    }
}
