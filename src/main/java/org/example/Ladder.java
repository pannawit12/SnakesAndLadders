package org.example;

public class Ladder extends Blank {
    public Ladder(int num) {
        super(num);
    }

    @Override
    public void print(int len) {
        System.out.printf("%" + len + "s", "L-"+super.getNum());
    }
}
