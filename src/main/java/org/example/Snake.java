package org.example;

public class Snake implements Square {
    //TODO: number=>tail
    private final int number;
    private static final String snakePrefix = "S-";

    public Snake(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public String getName() {
        return snakePrefix + getNumber();
    }

    public static int getPrefixLength() {
        return snakePrefix.length();
    }
}
