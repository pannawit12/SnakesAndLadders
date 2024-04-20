package org.example;

interface TableI {
    int getRow(int x);
    int getCol(int x);
    int getIndex(int row, int col);
    void printTable();
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
//        for (int i=n-1; i>=0; i--) {
//            for (int j=0; j<n; j++) {
//                int x = table[getIndex(i, j)];
//                System.out.print(getRow(x) + ", " + getCol(x) + "\t");
//            }
//            System.out.println();
//        }
    }
}
