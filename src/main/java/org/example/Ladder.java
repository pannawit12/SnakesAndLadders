package org.example;

public class Ladder implements Square {
    int number;

    public Ladder(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public String getName() {
        return "L-"+ getNumber();
    }
}
