package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

interface GameStateI {
    void run() throws IOException;
}

public class GameState implements GameStateI {

    Table table;
    Player[] players;
    int n,p;

    BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    @Override
    public void run() throws IOException {
        init();
        gameRun();
    }

    private void init() throws IOException {

        System.out.println("Initializing game...");

        System.out.print("Enter size of table : ");
        n = Integer.parseInt(reader.readLine());
        System.out.print("Enter number of players : ");
        p = Integer.parseInt(reader.readLine());

        table = new Table(n);
        players = new Player[p];

        for (int i=0; i<p; i++) {
            players[i] = new Player("Player"+(i+1));
        }
    }

    private void gameRun() throws IOException {
        System.out.println("Running game...");

        int turn = 0;

        String read = "";

        while(true) {
            table.printTable();

            for (Player player:players) {
                player.printLocation();
            }

            System.out.println("\n"+players[turn%p].getName()+"'s turn");
            System.out.println("press Enter to roll a dice");
            if(!read.equals("skip")) read=reader.readLine();
            players[turn%p].rollDice(table);
            if(players[turn%p].getLocation() == n*n) break;
            turn++;
        }

        System.out.println(players[turn%p].getName()+" Win!!!");
    }

}
