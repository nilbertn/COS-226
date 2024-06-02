import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // private boolean[][] grid; // creates array of grid
    private boolean[] grid; // creates array of grid
    private int n; // the size of the grid
    private int count; // number of open sites
    private WeightedQuickUnionUF f; // creates a Weighted Quick Union f
    private WeightedQuickUnionUF b; // second quick union to deal with backwash

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) // check n in constructor to ensure that it is valid
            throw new IllegalArgumentException("Invalid entry");
        // this.grid = new boolean[n][n]; // initialize grid
        this.grid = new boolean[n * n]; // initialize grid
        this.n = n; // initialize n
        f = new WeightedQuickUnionUF(n * n + 1); // QU with top ghost node
        b = new WeightedQuickUnionUF(n * n + 2); // backwash with top + bottom

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int c = convert(row, col); // holds 1D coordinate value
        // checks if site isn't open and opens
        if (!isOpen(row, col)) {
            // grid[row][col] = true;
            grid[c] = true;
            count++;
        }
        if (row == 0) { // connecting top tow to top virtual node
            f.union(n * n, c);
            b.union(n * n, c);
            // System.out.println("1 Connection has been made" + f.find(c));
        }
        if (row == n - 1) { // connecting bottom row to bottom virtual node
            // only perform this for the backwash QU
            b.union(n * n + 1, c);
            // System.out.println("2 Connection has been made" + b.find(c));
        }

         /*
         // loop to connect all the arrays to the top node and bottom node
        for (int i = 0; i < n - 1; i++) {
            f.union(n * n, convert(0, i));
            StdOut.println(f.find(convert(0, i)));
            f.union(n * n + 1, convert(n - 1, i));
            StdOut.println(f.find(convert(n - 1, i)));
        }

          */

        // ensure that the coordinates are not on the edge
        if (row < n - 1 && isOpen(row + 1, col)) {
            f.union(c, convert(row + 1, col)); // down
            b.union(c, convert(row + 1, col)); // backwash down
        }
        // down
        if (row > 0 && isOpen(row - 1, col)) {
            f.union(c, convert(row - 1, col)); // up
            b.union(c, convert(row - 1, col)); // backwash up
        }
        if (col < n - 1 && isOpen(row, col + 1)) {
            f.union(c, convert(row, col + 1)); // right
            b.union(c, convert(row, col + 1)); // backwash right
        }

        if (col > 0 && isOpen(row, col - 1)) {
            f.union(c, convert(row, col - 1)); // left
            b.union(c, convert(row, col - 1)); // backwash left
        }
        // StdOut.println("Parent of C" + f.find(c));

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        int c = convert(row, col); // holds 1D coordinate value
        // return grid[row][col];
        return grid[c];
    }

    // is the site (row, col) full (connect to the top)?
    public boolean isFull(int row, int col) {
        validate(row, col);
        /*
        System.out.println("Parent test before is full" + f.find(convert(row, col)));
         */
        return f.find(convert(row, col)) == f.find(n * n); //&& (isOpen(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return b.find(n * n + 1) == b.find(n * n);
    }

    // converts two dimensional coordinates into one dimensional coordinates
    private int convert(int row, int col) {
        int coordinate = row * n + col;
        return coordinate;
    }

    // checks whether the coordinates are in the grid or not
    private void validate(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n)
            throw new IllegalArgumentException("The coordinates are out of the grid");
    }

    // unit testing (required)
    public static void main(String[] args) {
        Percolation p = new Percolation(2); // creates a 2x2 n grid
        p.open(0, 1); // open first site
        p.open(1, 1); // open second site
        StdOut.print("Is the site (0, 1) open? ");
        if (p.isOpen(0, 1)) StdOut.println("YES!");
        StdOut.print("Is the site (0, 0) open? ");
        if (p.isOpen(1, 1)) StdOut.println("YES!");
        StdOut.print("Is the site (0, 0) open? ");
        if (p.isOpen(0, 0)) StdOut.println("YES!");
        else StdOut.println("NO!");
        StdOut.println();
        StdOut.println("Number of open sites: " + p.numberOfOpenSites());
        StdOut.print("Is the site (0, 1) full? (It should be) ");
        if (p.isFull(0, 1)) StdOut.println("YES!");
        StdOut.print("Does the system Percoalte? (It should percolate) ");
        if (p.percolates()) StdOut.print("YES!");


    }

}


