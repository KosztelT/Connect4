package me.koszteltamas.main;

public class Connect4Main {
    public static void main(String[] args) {
        String filename = "connect4.txt";
        GameController controller = new GameController(filename);
        controller.startGame();
    }
}
