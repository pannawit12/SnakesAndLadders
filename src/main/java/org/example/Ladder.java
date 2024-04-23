package org.example;

public class Ladder implements Square {
    int num;

    public Ladder(int num) {
        this.num = num;
    }

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public String getName() {
        return "L-"+getNum();
    }
}
