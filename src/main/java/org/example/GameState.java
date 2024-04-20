package org.example;

import java.util.Random;

import static org.example.State.*;

interface GameStateI {
}

public class GameState implements GameStateI {
    final Table table;
    State state = INIT;
    public GameState(int n) {
        table = new Table(n);
        state = PLAY;
    }
}
