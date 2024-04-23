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

    String read = "";

    private void gameRun() throws IOException {
        System.out.println("Running game...");

        int turn = 0;

        while(true) {
            System.out.println(table.getTableToString());

            for (Player player:players) {
                System.out.println(player.getName() + " is at " + player.getLocation());
            }


            Player player = players[turn % numbersOfPlayers];

            turnRun(player);

            if(player.getLocation() == table.getFinishSquareIndex()) {
                break;
            }

            turn++;
        }

        System.out.println(players[turn % numbersOfPlayers].getName() + " Win!!!");
    }

    private void turnRun(Player player) throws IOException {
        String name = player.getName();
        int dice = player.rollDice(diceFaces);
        int moveLocation = table.moveCal(player.getLocation(), dice);
        player.setLocation(moveLocation);

        System.out.println("\n" + name + "'s turn");
        System.out.print("press Enter to roll a dice");


        if(!read.equals("skip")) {
            read = reader.readLine();
        }

        System.out.println(name + " rolled a " + dice);
        System.out.println(name + " moved to square " + moveLocation);
    }
}
