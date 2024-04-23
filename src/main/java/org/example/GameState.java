package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameState {

    Table table;
    Player[] players;
    int numbersOfPlayers;
    int diceFaces = 6;

    BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        GameState game = new GameState();
        game.initial();
        game.gameRun();
    }

    private void initial() throws IOException {

        System.out.println("Initializing game...");

        System.out.print("Enter size of table : ");
        int length = Integer.parseInt(reader.readLine());

        System.out.print("Enter number of players : ");
        numbersOfPlayers = Integer.parseInt(reader.readLine());

        table = new Table(length, diceFaces);
        players = new Player[numbersOfPlayers];

        for (int playerIndex = 0; playerIndex< numbersOfPlayers; playerIndex++) {
            players[playerIndex] = new Player("Player" + (playerIndex + 1));
        }
    }

    private void gameRun() throws IOException {
        System.out.println("Running game...");

        int turn = 0;
        String read = "";

        while(true) {
            table.printTable(diceFaces);

            for (Player player:players) {
                System.out.println(player.getName() + " is at " + player.getLocation());
            }

            if(!read.equals("skip")) {
                read = reader.readLine();
            }

            Player player = players[turn % numbersOfPlayers];
            String name = player.getName();
            int dice = player.rollDice(diceFaces);
            int moveLocation = table.moveCal(player.getLocation(), dice);
            player.setLocation(moveLocation);

            System.out.println("\n" + name + "'s turn");
            System.out.println("press Enter to roll a dice");
            System.out.println(name + " rolled a " + dice);
            System.out.println(name + " moved to square " + moveLocation);

            if(players[turn % numbersOfPlayers].getLocation() == table.getFinishSquareIndex()) {
                break;
            }

            turn++;
        }

        System.out.println(players[turn % numbersOfPlayers].getName() + " Win!!!");
    }

}
