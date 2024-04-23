package org.example;

import java.util.Random;

public class Player {
    private final String name;
    private int location = 1;
    Random random = new Random(); //todo: เปลี่ยนเป็น private ลองย้ายไปใน rollDice

    public Player(String name) {
        this.name = name;
    }

    public int rollDice(int diceFaces) {
        return random.nextInt(diceFaces) + 1;
    }

    public String getName(){
        return name;
    }

    public int getLocation(){
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
