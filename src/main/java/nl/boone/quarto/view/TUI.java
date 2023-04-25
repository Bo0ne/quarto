package nl.boone.quarto.view;

import java.util.Scanner;

public class TUI {
    public void run() {
        System.out.println("Welcome to Quarto!\n");
        System.out.println("This is a game for two players. The goal of the game is to get four pieces in a row that have something in common.\n");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Player 1, what is your name?");
        String player1Name = scanner.nextLine();
        System.out.println("Player 2, what is your name?");
        String player2Name = scanner.nextLine();


    }
}
