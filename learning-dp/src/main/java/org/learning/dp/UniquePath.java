package org.learning.dp;

import org.testng.Assert;

/**
 * Problem:
 *   A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *   The robot can only move either down or right at any point in time. The robot is trying to reach
 *   the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *   How many possible unique paths are there?
 *
 *   -----------
 *   |    |    |
 *   ----------
 *   |    |    |
 *   -----------
 *
 *  Approach:
 *      * Give a 2x2 square, there are two 2 ways get to the last cell. One for from top right, and the
 *        other from bottom left
 */
public class UniquePath {
    public static void main(String[] args) {
        System.out.println(UniquePath.class.getName());

        test(1, 1, 1);
        test(1, 5, 1);
        test(1, 2, 1);
        test(2, 2, 2);
        test(3, 3, 6);
        test(2, 6, 6);

        test(3, 3, 6);
        test(4, 4, 20);
        test(4, 6, 56);
    }

    private static void test(int row, int col, int expectedCount) {
        System.out.printf("\n ===== test: row: %d, col:%d\n", row, col);

        int actualCntBF = bruteForce(row, col);

        System.out.printf("expected cnt: %d, actualBF: %d\n", expectedCount, actualCntBF);

        Assert.assertEquals(actualCntBF, expectedCount);

        int actualCntDP = dpTopDown(row, col);

        System.out.printf("expected cnt: %d, actualDP: %d\n", expectedCount, actualCntDP);

        Assert.assertEquals(actualCntDP, expectedCount);

        int actualCntDPBottomUp = dpBottomUp(row, col);
        System.out.printf("expected cnt: %d, actualDP bottom up: %d\n", expectedCount, actualCntDPBottomUp);

        Assert.assertEquals(actualCntDP, actualCntDPBottomUp);
    }

    /**
     * Using top-down recursion.
     * * Number of unique paths at each location is count(row-1, col) + count(row, col-1)
     * * Base case count = 0 when row or col = 1
     *
     * Notice the value for row and col parameters are getting smaller as the recursion goes deeper.
     * This means they are changing, meaning they represent subproblems, and there is an
     * overlapping issue with these subproblems.  This creates redundancy. In order to
     * eliminate the redundancy, then we can store the results in a way that we can look it up
     * using the row and col.  This means we need a 2-d array.
     *
     *
     * * Runtime O(2^(row+col)
     * * Space (m+n) => stack size
     *
     * @param row
     * @param col
     * @return
     */
    private static int bruteForce(int row, int col) {

        if (row < 1 || col < 1) {
            return 0;
        }
        if (row == 1 || col == 1) {
            return 1;
        }

        return bruteForce(row -1, col) + bruteForce(row, col - 1);
    }

    /**
     * Using memory to compute previously computed value.
     *
     * This is top-down DP
     *
     * Runtime: O(m*n)
     * Space(m*n)
     *
     * @param row
     * @param col
     * @return
     */
    private static int dpTopDown(int row, int col) {
        if (row < 1 || col < 1) {
            return 0;
        }

        if (row == 1 || col == 1) {
            return 1;
        }

        int[][] cache = new int[row][col];

        dpTopDownHelper(cache, row-1, col-1);
        return cache[row-1][col-1];
    }

    private static int dpTopDownHelper(int[][] cache, int row, int col) {
        if (row == 0 || col == 0) {
            return 1;
        }

        if (cache[row][col] == 0) {
            // compute the value and then cache
            int cnt = dpTopDownHelper(cache, row-1, col) + dpTopDownHelper(cache, row, col-1);
            cache[row][col] = cnt;
        }

        return cache[row][col];


    }


    /**
     * Using bottom up DP, which means iterative rather than recursion.
     *
     * Runtime is O(row*col)
     * Space is O(row*col)
     *
     * @param row
     * @param col
     * @return
     */
    private static int dpBottomUp(int row, int col) {
        int[][] cache = new int[row][col];

        // all elements of row 1 should be 1
        for (int i = 0; i < col; i++) {
            cache[0][i] = 1;
        }

        // all elements of col 1 should be 1
        for (int i = 0; i < row; i++) {
            cache[i][0] = 1;
        }

        for (int rowIdx = 1; rowIdx < row; rowIdx++) {
            for (int colIdx = 1; colIdx < col; colIdx++) {
                //count(row,col) = count(row-1,col) + count(row, col-1)
                cache[rowIdx][colIdx] = cache[rowIdx-1][colIdx] + cache[rowIdx][colIdx-1];
            }
        }

        return cache[row-1][col-1];
    }

}
