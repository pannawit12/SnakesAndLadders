package org.example;

import java.util.*;

interface TableInterface {
    int getFinishSquareIndex();
    StringBuilder getTableToString();
    void randomSnakesLadders(int numberOfDiceFaces);
    int moveCalculation(int previousLocation, int diceNumber); //finish: change prevLoc to previousLocation, change moveCal to moveCalculation
}

public class Table implements TableInterface {
    private final int length;
    private final Square[] table;
    private final int finishSquareIndex;

    public Table(int length) {
        this.length = length;
        finishSquareIndex = length * length;
        table = new Square[finishSquareIndex];
    }

    @Override
    public int getFinishSquareIndex() {
        return finishSquareIndex;
    }

    private int getRow(int squareIndex) {
        return (squareIndex - 1) / length;
    }

    private int getSquareIndex(int row, int column) {  //finish: change col to column
        return row * length + (row % 2 == 0 ? column : length - 1 - column) + 1;
    }

    private boolean isSpecialSquare(int squareIndex) {
        return table[squareIndex - 1] != null;
    }

    @Override
    public StringBuilder getTableToString() {
        StringBuilder tableString = new StringBuilder();

        int maxSquareIndexLength = (int)Math.log10(finishSquareIndex) + 1;
        int spacesAfterIndex = 1;
        int squareIndexLength = maxSquareIndexLength + spacesAfterIndex;
        int maxPrefixLength = Math.max(Snake.getPrefixLength(), Ladder.getPrefixLength());
        int squareNameLength = maxPrefixLength + maxSquareIndexLength ;

        for (int row = length - 1; row >= 0; row--) {
            tableString.append("| ");

            for (int col = 0; col < length; col++) {
                int squareIndex = getSquareIndex(row, col);  //finish: change SquareIndex to squareIndex
                String squareName = "";

                if (isSpecialSquare(squareIndex)) {
                    squareName = table[squareIndex - 1].getName();
                }

                String formattedSquareIndex = String.format("%" + (- squareIndexLength) + "d", squareIndex);
                String formattedSquareName = String.format("%" + squareNameLength + "s", squareName);

                tableString.append(formattedSquareIndex);
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
    public int moveCalculation(int previousLocation, int diceNumber) {
        int location = previousLocation + diceNumber;
        Collection<Integer> passedSquareIndexes = new HashSet<>();

        while (location > finishSquareIndex || location < 1) {
            if (location > finishSquareIndex) {
                location = (finishSquareIndex << 1) - location;
            } else {
                location = 2 - location;
            }
        }

        while (isSpecialSquare(location) && !passedSquareIndexes.contains(location)) {
            passedSquareIndexes.add(location);
            location = table[location - 1].getNumber();
        }

        return location;
    }

    private final Random random = new Random();

    @Override
    public void randomSnakesLadders(int numberOfDiceFaces) {
        int numberOfSnakesLadders = (finishSquareIndex + 9) / 10;  //finish: change tenPerCentOfSquaresRoundedUp to numberOfSnakesLadders
        int[] heads = uniqueRandomHead(numberOfSnakesLadders);
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

            for (int i = 2; i < length * length && snakeCount != numberOfDiceFaces; i++) {
                if (moveCalculation(i, 0) < i) {
                    snakeCount++;
                } else {
                    snakeCount = 0;
                }
            }
        } while (snakeCount >= numberOfDiceFaces);
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
