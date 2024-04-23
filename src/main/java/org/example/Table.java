package org.example;

import java.util.*;

interface TableInterface {
    int getFinishSquareIndex();
    StringBuilder getTableToString();
    int moveCal(int prevLoc, int dice); //todo: อย่าย่อคำ เปลี่ยน dice
}

public class Table implements TableInterface {

    private final int length;
    private final Square[] table;
    private final int finishSquareIndex;

    public Table(int length, int diceFaces) { //todo: แก้ชื่อ diceFaces
        this.length = length;
        finishSquareIndex = length * length;
        table = new Square[finishSquareIndex];

        for (int squareIndex = 0; squareIndex < finishSquareIndex; squareIndex++) {
            table[squareIndex] = new Blank();
        }

        randomSnakesLadders(diceFaces);
    }

    @Override
    public int getFinishSquareIndex() {
        return finishSquareIndex;
    }

    private int getRow(int squareIndex) {
        return (squareIndex - 1)/ length;
    } //todo: ลืม space bar หน้า /

    private int getSquareIndex(int row, int col) { //todo: เปลี่ยน col เป็น column
        return row * length + (row % 2 == 0 ? col : length - 1 - col) + 1;
    }

    @Override
    public StringBuilder getTableToString() {
        StringBuilder tableString = new StringBuilder();

        int maxSquareIndexLength = (int)Math.log10(finishSquareIndex) + 1;
        int spacesAfterIndex = 1;
        int squareIndexLength = maxSquareIndexLength + spacesAfterIndex;

        int stringInSquareNameLength = 2;
        int squareNameLength = maxSquareIndexLength + stringInSquareNameLength;

        for (int row = length - 1; row >= 0; row--) {
            tableString.append("| ");

            for (int col = 0; col < length; col++) {
                int SquareIndex = getSquareIndex(row, col); //todo: เปลี่ยนเป็น s เล็ก
                String formattedSquareIndex = String.format("%" + (- squareIndexLength) + "d", SquareIndex);
                tableString.append(formattedSquareIndex);

                String squareName = table[SquareIndex - 1].getName();
                String formattedSquareName = String.format("%" + squareNameLength + "s", squareName);
                tableString.append(formattedSquareName);

                tableString.append(" | ");
            }

            tableString.append("\n");
        }

        int marginSquare = 2;
        int squareLength = squareIndexLength + spacesAfterIndex + squareNameLength + marginSquare;
        int tableLength = length * squareLength + 1;
        tableString.append("-".repeat(tableLength));
        return tableString;
    }

    @Override
    public int moveCal(int prevLoc, int dice) {
        int location = prevLoc + dice; //todo: เปลี่ยน prevLoc ให้ไม่ย่อ
        Collection<Integer> passedSquareIndexes = new HashSet<>();

        while (location > finishSquareIndex || location < 1) {
            if (location > finishSquareIndex) {
                location = (finishSquareIndex << 1) - location;
            } else {
                location = 2 - location;
            }
        }

        while (table[location - 1].getNum() != 0 && !passedSquareIndexes.contains(location)) {
            passedSquareIndexes.add(location);
            location = table[location - 1].getNum();
        }

        return location;
    }

    Random random = new Random();

    private void randomSnakesLadders(int diceFaces) {
        int tenPerCentOfSquaresRoundedUp = (finishSquareIndex + 9) / 10; //todo: เปลี่ยนชื่อเป็น numberOfSnakesLadders
        int[] heads = uniqueRandomHead(tenPerCentOfSquaresRoundedUp);
        int snakeCount;

        do {
            for (int head : heads) {
                int tail = random.nextInt(length * length) + 1;
                int headRow = getRow(head);
                int tailRow = getRow(tail);

                while (headRow == tailRow) {
                    tail = random.nextInt(length * length) + 1;
                    tailRow = getRow(tail);
                }

                if (headRow > tailRow) {
                    table[head - 1] = new Snake(tail);
                } else {
                    table[head - 1] = new Ladder(tail);
                }
            }

            snakeCount = 0;

            for (int i = 2; i < length * length && snakeCount != diceFaces; i++) {
                if (moveCal(i, 0) < i) {
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
