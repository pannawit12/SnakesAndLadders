package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

//    private static void bounceMoveTest() throws IOException {
//        Table t = new Table(10);
//        t.printTable();
//        int location = 1;
//        int dice = -1;
//        while (dice!=0) {
//            BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(System.in));
//
//            dice = Integer.parseInt(reader.readLine());
//            location = t.moveCal(location, dice);
//            System.out.println(location);
//        }
//    }

    public static void main(String[] args) throws IOException {
        new GameState().run();
    }
}