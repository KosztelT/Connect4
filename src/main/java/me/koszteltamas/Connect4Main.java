package me.koszteltamas;

import java.util.Scanner;

public class Connect4Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connect4Game game = new Connect4Game("connect4.txt");
        boolean isGameOver = false;

        System.out.println("Connect4 játék betöltése");
        game.printBoard();

        while (true) {
            // Játékos lépése
            System.out.println("Te következel! Írj egy számot (0-6) vagy írd be, hogy 'vege', hogy kilépj:");
            String input = scanner.next();

            // Ellenőrizni, hogy a játékos kilépést kér
            if (input.equalsIgnoreCase("vege")) {
                System.out.println("Kilépés a játékból és az eddigi pálya elmentése...");
                game.saveGame("connect4.txt");
                System.out.println("Játék elmentve.");
                break;
            }

            // Ellenőrizzük, hogy a bemenet szám-e
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
                isGameOver = true;
                break;
            }

            // AI lépése
            Move aiMove = game.getAIMove();
            if (aiMove != null) {
                game.makeMove(aiMove);
                System.out.println("AI következik:");
                game.printBoard();
                if (game.isWinningMove(aiMove)) {
                    System.out.println("AI nyert! Majd legközelebb.");
                    isGameOver = true;
                    break;
                }
            } else {
                System.out.println("Döntetlen!");
                isGameOver = true;
                break;
            }
        }

        // Csak akkor menti a játékállapotot, ha a játékos nem nyert vagy vesztett
        if (!isGameOver) {
            game.saveGame("connect4.txt");
            System.out.println("Játék elmentve.");
        } else {
            System.out.println("Játék vége. Nem került sor mentésre.");
        }

        System.out.println("Viszlát!");
    }
}