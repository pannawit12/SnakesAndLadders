package org.example;

public class Snake implements Square {
    int number;

    public Snake(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public String getName() {
        return "S-"+ getNumber();
    }
}
