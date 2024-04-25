package org.example;

public class Snake implements Square {
    //Finish: number=>tail
    private final int tail;
    private static final String snakePrefix = "S-";

    public Snake(int tail) {
        this.tail = tail;
    }

    @Override
    public int getTail() {
        return tail;
    }

    @Override
    public String getName() {
        return snakePrefix + getTail();
    }

    public static int getPrefixLength() {
        return snakePrefix.length();
    }
}
