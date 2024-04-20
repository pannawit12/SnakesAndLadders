package org.example;

interface GameStateI {
}

public class GameState implements GameStateI {
    final Table table;

    public GameState(int n) {
        table = new Table(n);
    }
}
