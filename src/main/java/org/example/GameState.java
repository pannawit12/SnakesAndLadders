package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

interface GameStateI {
    void run() throws IOException;
}

public class GameState implements GameStateI {
    @Override
    public void run() throws IOException {
        System.out.println("Initializing game...");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.print("Enter size of table : ");
        int n = Integer.parseInt(reader.readLine());
        System.out.print("Enter number of players : ");
        int p = Integer.parseInt(reader.readLine());
        Table table = new Table(n);
        Player[] players = new Player[p];
        for (int i=0; i<p; i++) {
            players[i] = new Player("Player"+(i+1));
        }

        System.out.println("Running game...");

        int turn = 0;

        table.printTable();
        String read = "";

        while(true) {
            System.out.println(players[turn%p].getName()+"'s turn");
            if(!read.equals("skip")) read=reader.readLine();
            players[turn%p].rollDice(table);
            if(players[turn%p].getLocation() == n*n) break;
            turn++;
        }

        System.out.println(players[turn%p].getName()+" Win!!!");
    }

}
