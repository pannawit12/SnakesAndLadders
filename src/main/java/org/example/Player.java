package org.example;

import java.util.Random;

public class Player {
    String name;
    int location = 0;
    Random random = new Random();

    public Player (String name) {
        this.name = name;
    }
    public void rollDice(Table t) {
        location = t.move(location, random.nextInt(6)+1);
    }
}
