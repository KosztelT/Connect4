package me.koszteltamas.main;


import me.koszteltamas.score.ScoreManager;


public class Connect4Main {
    public static void main(String[] args) {
        String filename = "connect4.txt";
        GameController controller = new GameController(filename);
        ScoreManager.load();
        controller.startGame();
    }
}
