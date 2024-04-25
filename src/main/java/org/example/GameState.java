package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameState {
    private final Table table;
    private final Player[] players;
    private final int numberOfPlayers;
    private final Dice dice;

    private final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        GameState game = new GameState();
        game.run();
    }

    public GameState() throws IOException {
        System.out.println("Initializing game...");

        System.out.print("Enter length of table : ");
        int length = Integer.parseInt(reader.readLine());

        System.out.print("Enter number of players : ");
        numberOfPlayers = Integer.parseInt(reader.readLine());

        System.out.print("Enter number of dice faces : ");
        int numberOfDiceFaces = Integer.parseInt(reader.readLine());

        table = new Table(length);
        table.randomSnakesAndLadders(numberOfDiceFaces);
        players = new Player[numberOfPlayers];
        dice = new Dice(numberOfDiceFaces);

        for (int playerIndex = 0; playerIndex < numberOfPlayers; playerIndex++) {
            players[playerIndex] = new Player("Player" + (playerIndex + 1));
        }
    }

    // Finish: gameRun => run
    private void run() throws IOException {
        System.out.println("Running game...");

        int turnCount = 0;
        Player currentPlayer = players[0];
        boolean isGameEnd = false;

        while (!isGameEnd) {
            currentPlayer = players[turnCount++ % numberOfPlayers];
            executeTurn(currentPlayer);
            isGameEnd = isPlayerWin(currentPlayer);
        }

        System.out.println(currentPlayer.getName() + " Win!!!");
    }

    // Finish: turn=>?
    private void executeTurn(Player currentPlayer) throws IOException {
        System.out.println(table.getTableToString());

        for (Player player:players) {
            System.out.println(player.getName() + " is at " + player.getLocation());
        }

        String name = currentPlayer.getName();

        System.out.println("\n" + name + "'s turn");
        System.out.print("press Enter to roll a dice");
        reader.readLine();

        int diceNumber = currentPlayer.rollDice(dice);
        int moveLocation = table.moveCalculate(currentPlayer.getLocation(), diceNumber);
        currentPlayer.setLocation(moveLocation);

        System.out.println(name + " rolled a " + diceNumber);
        System.out.println(name + " moved to square " + moveLocation);
    }

    private boolean isPlayerWin(Player player) {
        return player.getLocation() == table.getFinishSquareIndex();
    }
}
