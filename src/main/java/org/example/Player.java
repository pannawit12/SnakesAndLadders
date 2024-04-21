package org.example;

import java.util.Random;

public class Player {
    private final String name;
    private int location = 1;
    Random random = new Random();

    public Player (String name) {
        this.name = name;
    }
    public void rollDice(Table t) {
        int dice = random.nextInt(6)+1;
        location = t.moveCal(location, dice);
        System.out.println(name+" rolled a "+dice);
        t.printTable();
        System.out.println(name+" moved to square "+location);
    }

    public String getName(){
        return name;
    }

    public int getLocation(){
        return location;
    }
}
