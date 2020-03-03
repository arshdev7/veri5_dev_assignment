package com.veri5.backend.developer_assignment;

import com.veri5.backend.developer_assignment.Game.Player;
import com.veri5.backend.developer_assignment.Game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DeveloperAssignmentApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DeveloperAssignmentApplication.class, args);
    }

    @Autowired
    Game game;

    @Override
    public void run(String... args) throws Exception {
        int exit = 0;
        int option = 0;
        Scanner scan = new Scanner(System.in);
        //Game starts
        game.startTwoPersonGame();
        System.out.println("****Game Started****");
        game.showAllPlayers();
        while (exit == 0) {

            System.out.println("\n1. Show cards of players, check winner and pick cards again.");
            System.out.println("2. Add/Remove player.");
            System.out.println("3. Start picking two cards.");
            System.out.println("4. Print all the cards in Deck");
            System.out.println("0. Exit and show winner.\n ");

            option = scan.nextInt();
            switch (option) {
                case 1: {
                    game.showCardsOfAllPlayers();
                    Player winner = game.decideWinnerOfMatch();
                    System.out.println("\n--- Winner is : " + winner.getName() + " ---\n");
                    game.pickRandomCardsForAllPlayers();
                    break;
                }
                case 2: {
                    System.out.println("\n1. Add Player");
                    System.out.println("2. Remove Player");
                    int editPlayer = scan.nextInt();
                    game.showAllPlayers();
                    boolean isSuccess = false;
                    if (editPlayer == 1) {
                        isSuccess = game.addPlayer();
                    } else {
                        System.out.println("\nEnter Player number you want to remove:-");
                        int playerNumber = scan.nextInt();
                        game.removeSelectedPlayer(playerNumber);
                    }
                    if (isSuccess) {
                        System.out.println("Operation succeeded");
                    } else {
                        System.out.println("Wrong input. Operation failed..!");
                    }
                    break;
                }
                case 3: {
                    System.out.println("Feature not supported yet");
                    break;
                }
                case 4: {
                    game.printEntireDeck();
                    break;
                }
                case 0: {
                    Player winner = game.decideWinnerOfMatch();
                    if (winner != null) {
                        System.out.println("Winner of game is : " + winner.getName());
                    }
                    System.out.println("\n..exiting game");
                    System.out.println("****Thanks for playing****");
                    exit = 1;
                    break;
                }
                default:
                    System.out.println("Please enter a valid input");
                    break;
            }
        }
    }
}
