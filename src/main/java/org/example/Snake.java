package org.example;

public class Snake implements Square {
    int num;

    public Snake(int num) {
        this.num = num;
    }

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public String getName() {
        return "S-"+getNum();
    }
}
