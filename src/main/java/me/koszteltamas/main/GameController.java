package me.koszteltamas.main;


import me.koszteltamas.game.*;
import me.koszteltamas.model.Move;
import me.koszteltamas.score.ScoreManager;


import java.util.Scanner;


public class GameController {
    private final Connect4Game game;
    private final AIPlayer aiPlayer;
    private final Scanner scanner = new Scanner(System.in);
    private String name;


    public GameController(String filename) {
        this.game = new Connect4Game(filename);
        this.aiPlayer = new AIPlayer();
    }


    public void startGame() {
        // Név bekérése
        System.out.println("Add meg a nevedet:");
        name = scanner.next();
        int points = ScoreManager.getScoreOf(name);
        System.out.println(name + " pontjai: " + points);


        boolean isGameOver = false;
        System.out.println("Connect4 játék betöltése");
        game.printBoard();


        while (!isGameOver) {
            // Játékos lépése
            System.out.println("Te következel! Írj egy számot (0-6) vagy írd be, hogy 'vege', hogy kilépj:");
            String input = scanner.next();


            if (input.equalsIgnoreCase("vege")) {
                System.out.println("Kilépés a játékból és az eddigi pálya elmentése...");
                game.saveGame("connect4.txt");
                System.out.println("Játék elmentve.");
                break;
            }


            int playerMove;
            try {
                playerMove = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Nem jó számot adtál meg. Írj egy számot (0-6) között vagy 'vege' szót, hogy kilépj.");
                continue;
            }


            if (!game.makeMove(new Move(playerMove, 'S'))) {
                System.out.println("Helytelen lépés. Próbáld újra.");
                continue;
            }


            game.printBoard();
            if (game.isWinningMove(new Move(playerMove, 'S'))) {
                System.out.println("Gratulálok! Nyertél!");
                ScoreManager.addPoint(name);
                ScoreManager.save();
                isGameOver = true;
                break;
            }


            // AI lépése
            Move aiMove = aiPlayer.generateMove(game.getBoardManager());
            if (aiMove != null) {
                game.makeMove(aiMove);
                System.out.println("AI következik:");
                game.printBoard();
                if (game.isWinningMove(aiMove)) {
                    System.out.println("AI nyert! Majd legközelebb.");
                    isGameOver = true;
                }
            } else {
                System.out.println("Döntetlen!");
                isGameOver = true;
            }
        }


        if (!isGameOver) {
            game.saveGame("connect4.txt");
            System.out.println("Játék elmentve.");
        } else {
            System.out.println("Játék vége. Nem került sor mentésre.");
        }


        ScoreManager.printLeaderboard();
        System.out.println("Viszlát!");
    }
}
