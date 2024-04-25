package org.example;

public class Ladder implements Square {
    //Finish: number=>tail
    private final int tail;
    private static final String ladderPrefix = "L-";

    public Ladder(int tail) {
        this.tail = tail;
    }

    @Override
    public int getTail() {
        return tail;
    }

    @Override
    public String getName() {
        return ladderPrefix + getTail();
    }

    public static int getPrefixLength() {
        return ladderPrefix.length();
    }
}
