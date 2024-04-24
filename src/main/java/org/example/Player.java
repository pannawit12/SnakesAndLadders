package org.example;

public class Player {
    private final String name;
    private int location = 1;

    public Player(String name) { //finish: delete space bar before (
        this.name = name;
    }

    public int rollDice(Dice dice) {
        return dice.roll();
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
