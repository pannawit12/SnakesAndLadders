package org.example;

import org.junit.jupiter.api.Test;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.Arrays;

//import static org.junit.jupiter.api.Assertions.*;

class TableTest {
//    @Test
//    public void bounceMoveTest() throws IOException {
//        Table t = new Table(10);
//        t.printTable();
//        int location = 1;
//        int dice = -1;
//        while (dice!=0) {
//            BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(System.in));
//
//            dice = Integer.parseInt(reader.readLine());
//            location = t.move(location, dice);
//            System.out.println(location);
//        }
//    }

    @Test
    public void moveTest() {
        Table t = new Table(10);
        t.printTable();
        Player p = new Player("p1");
        System.out.println(p.getLocation());
        while (p.getLocation()!=100) {
            p.rollDice(t);
            System.out.println(p.getLocation());
        }
    }

//    @Test
//    public void randomNumbersTest() {
//        Table t = new Table(10);
//        for(int i=0; i<10; i++)
//            System.out.println(Arrays.toString(t.uniqueRandomNumbers(10)));
//    }
//
//    @Test
//    public void snakesLaddersTest() {
//        Table t = new Table(10);
//        t.randomSnakesLadders();
//        t.printTable();
//    }
}