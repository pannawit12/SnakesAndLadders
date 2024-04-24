package org.example;

public class Ladder implements Square {
    //TODO: number=>tail
    private final int number;
    private static final String ladderPrefix = "L-";

    public Ladder(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public String getName() {
        return ladderPrefix + getNumber();
    }

    public static int getPrefixLength() {
        return ladderPrefix.length();
    }
}
