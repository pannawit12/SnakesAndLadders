package org.example;

import java.util.Random;

interface TableI {
    int getRow(int x);
    int getCol(int x);
    int getIndex(int row, int col);
    void printTable();
    int move(int prevLoc, int dice);
}

public class Table implements TableI {

    final int n;
    final int[] table;

    public Table(int n) {
        this.n = n;
        table = new int[n*n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                int index = getIndex(i, j);
                table[index] = index;
            }
        }
        randomSnakesLadders();
    }

    @Override
    public int getRow(int x) {
        return x/n;
    }

    @Override
    public int getCol(int x) {
        return (x%(n<<1)<n?x%n:n-1-x%n);
    }

    @Override
    public int getIndex(int row, int col) {
        return row*n+(row%2==0?col:n-1-col);
    }

    @Override
    public void printTable() {
        for (int i=n-1; i>=0; i--) {
            for (int j=0; j<n; j++) {
                System.out.print((table[getIndex(i, j)] + "\t\t"));
            }
            System.out.println();
        }
        System.out.println("---------------------------------------------------");
//        for (int i=n-1; i>=0; i--) {
//            for (int j=0; j<n; j++) {
//                int x = table[getIndex(i, j)];
//                System.out.print(getRow(x) + ", " + getCol(x) + "\t");
//            }
//            System.out.println();
//        }
    }

    @Override
    public int move(int prevLoc, int dice) {
        int loc = prevLoc+dice;
        if (loc >= n*n) loc = (n*n<<1)-loc-2;
        while (table[loc]!=loc) {
            loc = table[loc];
        }
        return loc;
    }

    public void randomSnakesLadders() {
        int[] startPoint = uniqueRandomNumbers(n);
        Random r = new Random();
        for (int i=0; i<n; i++) {
            table[startPoint[i]] = r.nextInt(n*n);
        }
    }

    public int[] uniqueRandomNumbers(int k) {
        int i; // index for elements in stream[]

        // reservoir[] is the output array. Initialize it
        // with first k elements from stream[]
        int[] reservoir = new int[k];
        for (i = 0; i < k; i++)
            reservoir[i] = i;

        Random r = new Random();

        // Iterate from the (k+1)th element to nth element
        for (; i < n*n; i++) {
            // Pick a random index from 0 to i.
            int j = r.nextInt(i + 1);

            // If the randomly  picked index is smaller than
            // k, then replace the element present at the
            // index with new element from stream
            if (j < k)
                reservoir[j] = i;
        }

        return reservoir;
    }


}
