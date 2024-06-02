import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    private static final double C = 1.96; // confidence interval
    private int trials; // number of trials
    private double[] stats; // array to keep percolation stats


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) // checks whether grid and trials are valid
            throw new IllegalArgumentException("Invalid entry");
        this.trials = trials;
        this.stats = new double[trials];
        int row, col; // coordinate variable
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                row = StdRandom.uniformInt(n); // avoid deprecation
                col = StdRandom.uniformInt(n); // avoid deprecation
                if (!p.isOpen(row, col)) {
                    p.open(row, col); // opens point if not already open
                }
            }
            double temp = p.numberOfOpenSites()
                    / Math.pow(n, 2); // percolation threshold
            this.stats[i] = temp; // fill stats array
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(stats);
    }


    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(stats);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - (C * stddev() / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + (C * stddev() / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]); // grid size
        int trials = Integer.parseInt(args[1]); // number of trials

        Stopwatch st = new Stopwatch(); // create a stopwatch
        PercolationStats ps = new PercolationStats(n, trials); // create percolation
        double t = st.elapsedTime(); // elapsed time

        StdOut.printf("mean()           = %.6f", ps.mean());
        System.out.println();
        StdOut.printf("stddev()         = %.6f", ps.stddev());
        System.out.println();
        StdOut.printf("confidenceLow()  = %.6f", ps.confidenceLow());
        System.out.println();
        StdOut.printf("confidenceHigh() = %.6f", ps.confidenceHigh());
        System.out.println();
        StdOut.printf("elapsed time     = %.3f", t);

    }

}


