package me.koszteltamas.score;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ScoreManager {
    private static final HashMap<String, Integer> scores = new HashMap<>();
    public static void load() {
        try {
            File file = new File("scores.txt");
            file.createNewFile();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                String line = br.readLine();
                String[] split = line.split(";");
                if (split.length != 2) continue;
                    scores.put(split[0], Integer.parseInt(split[1]));
            }
            fr.close();
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static HashMap<String, Integer> getScores() {
        return scores;
    }
    public static List<Map.Entry<String, Integer>> getSortedScores() {
        return scores.entrySet().stream().sorted(Map.Entry.comparingByValue()).toList().reversed();
    }
    public static int getScoreOf(String name) {
        return scores.getOrDefault(name, 0);
    }
    public static Integer addPoint(String name) {
        int score = scores.getOrDefault(name, 0) + 1;
        scores.put(name, score);
        return score;
    }
    public static void save() {
        try {
            FileWriter fw = new FileWriter("scores.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                bw.write(entry.getKey() + ";" + entry.getValue());
                bw.write("\n");
            }
            bw.close();
            fw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void printLeaderboard() {
        System.out.println("Toplista:");
        var sortedScores = ScoreManager.getSortedScores();
        int leaderboardSize = sortedScores.size();
        for (int i = 0; i < leaderboardSize; i++) {
            var pos = sortedScores.get(i);
            System.out.println((i + 1) + ". " + pos.getKey() + " - " + pos.getValue() + " pont");
        }
    }
}