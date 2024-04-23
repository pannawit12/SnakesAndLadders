package org.example;

import java.util.*;

interface TableInterface {
    int getLength();
    int getFinishSquareIndex();
    int getRow(int squareIndex);
    int getSquareIndex(int row, int col);
    void printTable(int diceFaces);
    int moveCal(int prevLoc, int dice);
}

public class Table implements TableInterface {

    private final int length;
    private final Square[] table;
    private final int finishSquareIndex;

    public Table(int length, int diceFaces) {
        this.length = length;
        finishSquareIndex = length * length;
        table = new Square[finishSquareIndex];

        for (int squareIndex = 0; squareIndex < finishSquareIndex; squareIndex++) {
            table[squareIndex] = new Blank();
        }

        randomSnakesLadders(diceFaces);
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public int getFinishSquareIndex() {
        return finishSquareIndex;
    }

    @Override
    public int getRow(int squareIndex) {
        return (squareIndex - 1)/ length;
    }

    @Override
    public int getSquareIndex(int row, int col) {
        return row * length + (row % 2 == 0 ? col : length - 1 - col) + 1;
    }

    @Override
    public void printTable(int diceFaces) {
        int maxSquareIndexLength = (int)Math.log10(finishSquareIndex) + 1;

        for (int row = length - 1; row >= 0; row--) {
            System.out.print("| ");

            for (int col = 0; col < length; col++) {
                int SquareIndex = getSquareIndex(row, col);
                System.out.printf("%" + (- maxSquareIndexLength - 1) + "d", SquareIndex);
                int len = maxSquareIndexLength + 2;
                String squareName = table[SquareIndex - 1].getName();
                System.out.printf("%" + len + "s", squareName);
//                "%" + len + "s", "S-"+super.getNum());
                System.out.print(" | ");
            }

            System.out.println();
        }

        System.out.println("-".repeat(length * (diceFaces + 2 * maxSquareIndexLength) + 1));
    }

    @Override
    public int moveCal(int prevLoc, int dice) {
        int loc = prevLoc + dice;
        Collection<Integer> passedSquareIndexes = new HashSet<>();

        if (loc > finishSquareIndex) {
            loc = (finishSquareIndex << 1) - loc;
        }

        while (table[loc - 1].getNum() != 0 && !passedSquareIndexes.contains(loc)) {
            passedSquareIndexes.add(loc);
            loc = table[loc - 1].getNum();
        }

        return loc;
    }

    Random random = new Random();

    private void randomSnakesLadders(int diceFaces) {
        int[] heads = uniqueRandomHead((finishSquareIndex + 9) / 10);
        int snakeCount;

        do {
            for (int head : heads) {
                int tail = random.nextInt(length * length) + 1;
                int startRow = getRow(head);
                int endRow = getRow(tail);

                while (startRow == endRow) {
                    tail = random.nextInt(length * length) + 1;
                    endRow = getRow(tail);
                }

                if (startRow > endRow) {
                    table[head - 1] = new Snake(tail);
                } else {
                    table[head - 1] = new Ladder(tail);
                }
            }

            snakeCount = 0;

            for (int i = 2; i < length * length && snakeCount != diceFaces; i++) {
                if (moveCal(i, 0)<i) {
                    snakeCount++;
                } else {
                    snakeCount = 0;
                }
            }
        } while (snakeCount >= diceFaces);
    }

    private int[] uniqueRandomHead(int numberOfSnakesLadders) {
        int squareIndex;
        int[] heads = new int[numberOfSnakesLadders];

        for (squareIndex = 2; squareIndex < numberOfSnakesLadders + 2; squareIndex++) {
            heads[squareIndex - 2] = squareIndex;
        }

        for (; squareIndex < finishSquareIndex ; squareIndex++) {
            int randomHeadIndex = random.nextInt(squareIndex - 2);

            if (randomHeadIndex < numberOfSnakesLadders) {
                heads[randomHeadIndex] = squareIndex;
            }
        }

        return heads;
    }
}
