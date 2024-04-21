package org.example;

public class Blank implements Square {
    private final int num;

    protected Blank(int num) {
        this.num = num;
    }

    public Blank() {
        this.num = 0;
    }

    @Override
    public void print(int len) {
        System.out.printf("%" + len + "s", "");
    }

    @Override
    public int getNum() {
        return num;
    }
}
