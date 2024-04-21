package org.example;

import java.util.*;

interface TableI {
    int getRow(int x);
//    int getCol(int x);
    int getIndex(int row, int col);
    void printTable();
    int moveCal(int prevLoc, int dice);
}

public class Table implements TableI {

    private final int n;
    private final Square[] table;

    public Table(int n) {
        this.n = n;
        table = new Blank[n*n];
        for (int i=0; i<n*n; i++) {
            table[i] = new Blank();
        }
        randomSnakesLadders();
    }

    @Override
    public int getRow(int x) {
        return (x-1)/n;
    }

//    @Override
//    public int getCol(int x) {
//        return ((x-1)%(n<<1)<n?x%n:n-1-(x-1)%n);
//    }

    @Override
    public int getIndex(int row, int col) {
        return row*n+(row%2==0?col:n-1-col)+1;
    }

    @Override
    public void printTable() {
        int len = (int)Math.log10(n*n)+1;
        for (int i=n-1; i>=0; i--) {
            System.out.print("| ");
            for (int j=0; j<n; j++) {
                int index = getIndex(i, j);
                System.out.printf("%" + (-len-1) + "d", index);
                table[index-1].print(len+2);
                System.out.print(" | ");
            }
            System.out.println();
        }
        System.out.println("-".repeat(n*(6+2*len)+1));
    }

    @Override
    public int moveCal(int prevLoc, int dice) {
        int loc = prevLoc+dice;
        Collection<Integer> h = new HashSet<>();
        if (loc > n*n) loc = (n*n<<1)-loc;
        while (table[loc-1].getNum()!=0 && !h.contains(loc)) {
            h.add(loc);
            loc = table[loc-1].getNum();
        }
        return loc;
    }

    private void randomSnakesLadders() {
        int[] startPoints = uniqueRandomNumbers((n*n+9)/10);
//        int[] startPoints = make6snakes();
        Random r = new Random();
        int snakeCount;
        do {
            for (int startPoint : startPoints) {
                int endPoint = r.nextInt(n * n) + 1;
                int startRow = getRow(startPoint);
                int endRow = getRow(endPoint);
                while (startRow == endRow) {
                    endPoint = r.nextInt(n * n) + 1;
                    endRow = getRow(endPoint);
                }
                if (startRow > endRow)
                    table[startPoint - 1] = new Snake(endPoint);
                else
                    table[startPoint - 1] = new Ladder(endPoint);
//            System.out.println(i+": "+startPoint+" "+endPoint+" "+table[startPoint-1].getNum());
            }
//            printTable();
            snakeCount = 0;
            for (int i = 2; i < n * n && snakeCount != 6; i++) {
//                System.out.println(i);
//                System.out.println(moveCal(i, 0));
                if (moveCal(i, 0)<i) {
                    snakeCount++;
                }
                else
                    snakeCount = 0;
//                System.out.println(i+": "+snakeCount);
            }
        } while (snakeCount>=6);
    }

    private int[] uniqueRandomNumbers(int k) {
        int i; // index for elements in stream[]

        // reservoir[] is the output array. Initialize it
        // with first k elements from stream[]
        int[] reservoir = new int[k];
        for (i = 0; i < k; i++) {
            reservoir[i] = i+2;
        }

        Random r = new Random();

        // Iterate from the (k+1)th element to nth element
        for (; i < n*n-2; i++) {
            // Pick a random index from 0 to i.
            int j = r.nextInt(i);

            // If the randomly  picked index is smaller than
            // k, then replace the element present at the
            // index with new element from stream
            if (j < k)
                reservoir[j] = i+2;
        }

        return reservoir;
    }

//    private int[] make6snakes() {
//        int[] arr = new int[n*n-3];
//        for (int i=2; i<n*n-1;i++) {
//            arr[i-2] = i;
//        }
//        return arr;
//    }
}
