package org.example;

public class Snake extends Blank {

    public Snake(int num) {
        super(num);
    }

    @Override
    public void print(int len) {
        System.out.printf("%" + len + "s", "S-"+super.getNum());
    }
}
