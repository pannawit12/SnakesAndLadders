package org.example;

import java.util.*;

// Finish: enter before return
// Finish: length * length => finishSquareIndex
// Finish: change method name to verb
// Finish: edit line exceed 80 characters
interface TableInterface {
    int getFinishSquareIndex();
    StringBuilder getTableToString();
    int moveCalculate(int previousLocation, int diceNumber);
    void randomSnakesAndLadders(int numberOfDiceFaces);
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

    private int getSquareIndex(int row, int column) {
        return row * length +
                (row % 2 == 0 ? column : length - 1 - column) + 1;
    }

    // Finish: isSpecialSquare => ?
    private boolean isSnakeOrLadderHead(int squareIndex) {
        return table[squareIndex - 1] != null;
    }

    @Override
    public StringBuilder getTableToString() {
        StringBuilder tableString = new StringBuilder();

        int maxSquareIndexLength = (int)Math.log10(finishSquareIndex) + 1;
        int spacesAfterIndex = 1;
        int squareIndexLength = maxSquareIndexLength + spacesAfterIndex;
        int maxPrefixLength = Math.max(Snake.getPrefixLength(),
                Ladder.getPrefixLength());
        int squareNameLength = maxPrefixLength + maxSquareIndexLength;
        int marginSquare = 2;
        int squareLength = squareIndexLength + spacesAfterIndex +
                squareNameLength + marginSquare;
        int tableLength = length * squareLength + 1;

        tableString.append("-".repeat(tableLength));

        for (int row = length - 1; row >= 0; row--) {
            tableString.append("\n| ");

            // Finish: col=>column
            for (int column = 0; column < length; column++) {
                int squareIndex = getSquareIndex(row, column);
                String squareName = "";

                if (isSnakeOrLadderHead(squareIndex)) {
                    squareName = table[squareIndex - 1].getName();
                }

                String formattedSquareIndex = String.format(
                        "%" + (-squareIndexLength) + "d",
                        squareIndex
                );
                String formattedSquareName = String.format(
                        "%" + squareNameLength + "s",
                        squareName
                );

                tableString.append(formattedSquareIndex);
                tableString.append(formattedSquareName);
                tableString.append(" | ");
            }
        }

        tableString.append("\n").append("-".repeat(tableLength));

        return tableString;
    }

    @Override
    public int moveCalculate(int previousLocation, int diceNumber) {
        int location = previousLocation + diceNumber;
        location = handleBoundary(location);
        location = handleSnakesAndLadders(location);

        return location;
    }

    private int handleBoundary(int location) {
        while (location > finishSquareIndex || location < 1) {
            if (location > finishSquareIndex) {
                location = (finishSquareIndex << 1) - location;
            } else {
                location = 2 - location;
            }
        }

        return location;
    }

    private int handleSnakesAndLadders(int location) {
        Collection<Integer> passedSquareIndexes = new HashSet<>();
        while (isSnakeOrLadderHead(location) &&
                !passedSquareIndexes.contains(location)) {
            passedSquareIndexes.add(location);
            location = table[location - 1].getTail();
        }

        return location;
    }

    private final Random random = new Random();

    @Override
    public void randomSnakesAndLadders(int numberOfDiceFaces) {
        // Finish: numberOfSnakesLadders ควรเปลี่ยนให้บอกได้ว่าเป็นของทั้งหมด
        int totalNumberOfSnakesAndLadders = (finishSquareIndex + 9) / 10;
        int[] heads = uniqueRandomHeads(totalNumberOfSnakesAndLadders);
        boolean isGameWinnable;

        do {
            randomTails(heads);
            isGameWinnable = isGameWinnable(numberOfDiceFaces);
        } while (!isGameWinnable);
    }

    private int[] uniqueRandomHeads(int totalNumberOfSnakesAndLadders) {
        int[] heads = new int[totalNumberOfSnakesAndLadders];
        int currentIndex = 0;
        int squareIndex = 2;

        for (; currentIndex < totalNumberOfSnakesAndLadders;
             currentIndex++, squareIndex++) {
            heads[currentIndex] = squareIndex;
        }

        for (; squareIndex < finishSquareIndex;
             currentIndex++, squareIndex++) {
            int randomHeadIndex = random.nextInt(currentIndex);

            if (randomHeadIndex < totalNumberOfSnakesAndLadders) {
                heads[randomHeadIndex] = squareIndex;
            }
        }

        return heads;
    }

    private void randomTails(int[] heads) {
        for (int head : heads) {
            int tail = random.nextInt(finishSquareIndex) + 1;
            int headRow = getRow(head);
            int tailRow = getRow(tail);

            while (headRow == tailRow) {
                tail = random.nextInt(finishSquareIndex) + 1;
                tailRow = getRow(tail);
            }

            if (headRow > tailRow) {
                table[head - 1] = new Snake(tail);
            } else {
                table[head - 1] = new Ladder(tail);
            }
        }
    }

    private boolean isGameWinnable(int numberOfDiceFaces) {
        // Finish: snakeCount=>?
        int adjacentSnakesCount = 0;

        for (int squareIndex = 2; squareIndex < finishSquareIndex; squareIndex++) {
            if (moveCalculate(squareIndex, 0) < squareIndex) {
                adjacentSnakesCount++;
                boolean isUnPassable = adjacentSnakesCount >= numberOfDiceFaces;

                if (isUnPassable) {
                    return false;
                }
            } else {
                adjacentSnakesCount = 0;
            }
        }

        return true;
    }
}
