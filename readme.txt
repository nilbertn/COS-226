Programming Assignment 1: Percolation

Answer these questions after you implement your solution.

/* *****************************************************************************
 *  Describe the data structures (i.e., instance variables) you used to
 *  implement the Percolation API.
 **************************************************************************** */


/* *****************************************************************************
 *  Briefly describe the algorithms you used to implement each method in
 *  the Percolation API.
 **************************************************************************** */
open(): run validate method ensure that the grid is within bounds of array
        Opens site by setting grid to true.
        Union operations on neighboring cells in the grid
        Wieghted quick union representing the site in the union-find data structure.
        Test for edge cases for top and bottom rows connecting to virtual top and
        bottom.
isOpen(): Check if site is open? and returns true or false to indicate if it is open
isFull(): validation check. Check connection if site is connected to virtual top node
          Uses union-find structure (f) to compare the root of the coordinate to the
          virtual top node
numberOfOpenSites(): method incrimented in open() method everytime a site is open
percolates(): Check if the virtual top and the virtual bottom node are connectted
Uses union-find structure including b which is used for backwash to efectively check at
least one of the bottom row is connnected to the top row. Utalizes union-find data
to efficiency solve percolation checking connectivity between sites and check
for fullness of sites. Incoperate path compression using wighted unions as the central
percolation method.







/* *****************************************************************************
 *  First, implement Percolation using QuickFindUF.
 *  What is the largest value of n that PercolationStats can handle in
 *  less than one minute on your computer when performing T = 100 trials?
 *
 *  Fill in the table below to show the values of n that you used and the
 *  corresponding running times. Use at least 5 different values of n.
 **************************************************************************** */

 T = 100

 n          time (seconds)
--------------------------
5           0.002
10          0.003
50          0.039
100         0.198
200         1.515

The largest value of n my computer can handle is 200, beyond that it takes forever
to run.

/* *****************************************************************************
 *  Describe the strategy you used for selecting the values of n.
 **************************************************************************** */
I first tried large growth but n of 2000 was failing, so I use a lower values
and increased the size by doubling it each time with 10 to 50 being the exception
in groth


/* *****************************************************************************
 *  Next, implement Percolation using WeightedQuickUnionUF.
 *  What is the largest value of n that PercolationStats can handle in
 *  less than one minute on your computer when performing T = 100 trials?
 *
 *  Fill in the table below to show the values of n that you used and the
 *  corresponding running times. Use at least 5 different values of n.
 **************************************************************************** */

 T = 100

 n          time (seconds)
--------------------------
100         0.054
200         0.148
500         0.854
1000        4.132
2000        30.594

The largest value of n is 2000 that ocund handle in less than a minute

/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
Known limitations is having two Weightedquickunion which is time expensive and
does not take less than 11n^2 time which would be ideal. Limitation is how
the backkwash couldn't be solved without making time too expensive





/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */
I encountered problems with backwash and trying to incoperate an efficient method
to solve it



/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
None, I LOVE COS226
