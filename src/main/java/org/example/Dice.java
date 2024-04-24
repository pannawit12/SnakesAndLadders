package org.example;

import java.util.Random;

public class Dice {
    private final int numberOfDiceFaces;

    public Dice(int numberOfDiceFaces) {
        this.numberOfDiceFaces = numberOfDiceFaces;
    }

    public int roll() {
        Random random = new Random();
        return random.nextInt(numberOfDiceFaces) + 1;
    }
}
