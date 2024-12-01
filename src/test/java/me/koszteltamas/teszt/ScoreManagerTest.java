package me.koszteltamas.teszt;

import me.koszteltamas.score.ScoreManager;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ScoreManagerTest {

    private Path originalFile;
    private Path backupFile;

    @BeforeEach
    void setup() throws IOException {
        // Eredeti fájl áthelyezése
        originalFile = Path.of("scores.txt");
        backupFile = Path.of("scores_backup.txt");

        if (Files.exists(originalFile)) {
            Files.move(originalFile, backupFile, StandardCopyOption.REPLACE_EXISTING);
        }

        // Tesztadatok inicializálása
        try (BufferedWriter writer = Files.newBufferedWriter(originalFile)) {
            writer.write("Alice;10\n");
            writer.write("Bob;15\n");
        }

        // ScoreManager betöltése az új fájlból
        ScoreManager.load();
    }

    @AfterEach
    void tearDown() throws IOException {
        // Tesztfájl törlése
        Files.deleteIfExists(originalFile);

        // Eredeti fájl visszaállítása
        if (Files.exists(backupFile)) {
            Files.move(backupFile, originalFile, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @Test
    void testPrintLeaderboard() {
        assertDoesNotThrow(ScoreManager::printLeaderboard);
    }

    @Test
    void testAddPoint() {
        ScoreManager.addPoint("Charlie");
        assertEquals(1, ScoreManager.getScoreOf("Charlie"));
    }

    @Test
    void testSave() throws IOException {
        ScoreManager.addPoint("Alice");
        ScoreManager.save();

        // Ellenőrizzük, hogy a fájlban a várt értékek szerepelnek
        List<String> lines = Files.readAllLines(originalFile);
        assertTrue(lines.contains("Alice;11"));
        assertTrue(lines.contains("Bob;15"));
    }
}