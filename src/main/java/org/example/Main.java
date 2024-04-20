package org.example;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    private static void moveTest() {
        Table t = new Table(10);
        t.printTable();
        Player p = new Player("p1");
        System.out.println(p.location);
        while (p.location!=99) {
            p.rollDice(t);
            System.out.println(p.location);
        }
    }

    private static void randomNumbersTest() {
        Table t = new Table(10);
        for(int i=0; i<10; i++)
            System.out.println(Arrays.toString(t.uniqueRandomNumbers(10)));
    }

    private static void snakesLaddersTest() {
        Table t = new Table(10);
        t.randomSnakesLadders();
        t.printTable();
    }

    public static void main(String[] args) throws IOException {
//        randomNumbersTest();
//        snakesLaddersTest();
        moveTest();


//        while (dice!=0) {
//            BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(System.in));
//
//            dice = Integer.parseInt(reader.readLine());
//            location = t.move(location, dice);
//            System.out.println(location);
//        }
    }
}