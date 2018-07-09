package org.learning.dp;

import org.common.ArrayUtils;
import org.common.NumberUtility;

/**
 * Given a 2D 0 and 1 array, find the largest square subarray of 1s.
 * The return value should be the size of the largest square subarray
 *
 * -----------------
 * | 0 | 1 | 0 | 0 |
 * | 1 | 1 | 1 | 1 |
 * | 0 | 1 | 1 | 0 |
 * -----------------
 *
 * Given the above example, the largest square subarray of 1s is size of 2x2
 *
 * ---------------------
 * | 1 | 1 | 1 | 1 | 1 |
 * | 1 | 1 | 1 | 1 | 0 |
 * | 1 | 1 | 1 | 1 | 0 |
 * | 1 | 1 | 1 | 1 | 0 |
 * | 1 | 0 | 0 | 0 | 0 |
 * ---------------------
 * Given the above example, the largest square subarray of 1s is size of 3x3
 *
 * A square of 2x2 is built of top of a square of 1x1
 *
 * Each cell of value of 1 is a square subarray of 1
 * ---------
 * | 0 | 1 |
 * | 1 | 1 |
 * ---------
 * ---------
 * | 1 | 0 |
 * | 1 | 1 |
 * ---------
 *
 * ---------
 * | 2 | 1 |
 * | 1 | 1 |
 * ---------
 *
 * Based on the above example, if a cell has a value of 1, then
 * the size square subarray is determine by
 *
 *   s(cell) = 1 + min(min(right cell, bottom cell), bottom right cell)
 *

 */
public class SquareSubMatrix {

    public static void main(String[] args) {
        int [][] matrixSmall = new int[][] {
                {0,1,0,1},
                {1,1,1,1},
                {0,1,1,0}
        };

        int [][] matrixMedium = new int[][] {
                {1,1,1,1,1},
                {0,1,1,1,0},
                {1,1,1,1,0},
                {1,1,1,1,0},
                {1,0,0,0,0},
        };

        int [][] matrixLarge = new int[][] {
                {1,1,1,1,1},
                {1,1,1,1,0},
                {1,1,1,1,0},
                {1,1,1,1,0},
                {1,0,0,0,0},
        };

        System.out.println(SquareSubMatrix.class.getName());

        test(matrixSmall, 2);
        test(matrixMedium, 3);
        test(matrixLarge, 4);
    }

    private static void test(int[][] matrix, int expectedSize) {
        System.out.println("\n=====> test <======");

        ArrayUtils.printMatrix(matrix);

        int actualSizeBF = largestSquareSubmatrixBF(matrix);

        System.out.printf("brute force expected: %d, actual: %d\n",
                expectedSize, actualSizeBF);

        System.out.println("bruteForceCounter: " + bruteForceCounter);
        bruteForceCounter = 0;

        int actualSizeTD = largestSquareSubmatrixTD(matrix);
        System.out.printf("\ntop down DP expected: %d, actual: %d\n",
                expectedSize, actualSizeTD);

        System.out.println("topDownCounter: " + topDownCounter);
        topDownCounter = 0;


        // bottom-up
        int actualSizeBU = largestSquareSubmatrixBU(matrix);
        System.out.printf("\nbottom-up DP expected: %d, actual: %d\n",
                expectedSize, actualSizeBU);

    }

    /**
     * A brute force approach is for each cell, find the biggest square submatrix
     * for which it is the upper left-hand corner of.
     *
     * @param matrix
     * @return
     */
    private static int largestSquareSubmatrixBF(int[][] matrix) {
        int maxSize = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                maxSize = Math.max(maxSize, lssHelper(row, col, matrix));
            }
        }

        return  maxSize;
    }

    static int bruteForceCounter = 0;
    /**
     * Helper for branching 3 ways using recursion
     *
     * 1) Depth of recursion is (n+m)
     * 2) Perform recursion call for each cell
     *
     * Runtime: O((n*m) * 3^(n+m))
     * Space: stack of depth (n+m)
     *
     * @param row
     * @param col
     * @param matrix
     * @return
     */
    private static int lssHelper(int row, int col, int[][] matrix) {
        bruteForceCounter++;

        // base cases
        if (row == matrix.length || col == matrix.length) {
            // row and col are increasing only, so only worry about when
            // reaching the edge of the wall
            return 0;
        }

        if (matrix[row][col] != 1) {
            return 0;
        }

        // branching 3 ways
        int rightCellSize = lssHelper(row, col+1, matrix);
        int bottomCellSize = lssHelper(row+1, col, matrix);
        int bottomRightCellSize = lssHelper(row+1, col+1, matrix);

        return 1 + Math.min(rightCellSize, Math.min(bottomCellSize, bottomRightCellSize));
    }

    static int topDownCounter = 0;
    /**
     * Top Down DP approach.
     *
     * Notice lssHelper helper method above, the parameters w/ changing values are
     * row and col.  The inefficiency comes from repeated calculation of (row,col) pair,
     * so if we have cache based on (row,col) pair, we can eliminate the redundancy.
     *
     * Runtime: O(n*m) -> still need to iterate through each time
     * Space: O(n*m)   -> due to the cache
     *
     * @param matrix
     * @return
     */
    private static int largestSquareSubmatrixTD(int[][] matrix) {
        int maxSize = 0;

        int[][] cache = new int[matrix.length][matrix[0].length];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                maxSize = Math.max(maxSize, lssTDHelper(row, col, matrix, cache));
            }
        }

        System.out.println("****** cache *******");
        ArrayUtils.printMatrix(cache);
        return  maxSize;
    }

    private static int lssTDHelper(int row, int col, int[][] matrix, int[][] cache) {
        topDownCounter++;

        // base cases
        if (row == matrix.length || col == matrix.length) {
            // row and col are increasing only, so only worry about when
            // reaching the edge of the wall
            return 0;
        }

        if (matrix[row][col] != 1) {
            return 0;
        }

        if (cache[row][col] > 0) {
            return cache[row][col];
        }

        // branching 3 ways
        int rightCellSize = lssHelper(row, col+1, matrix);
        int bottomCellSize = lssHelper(row+1, col, matrix);
        int bottomRightCellSize = lssHelper(row+1, col+1, matrix);

        int result = 1 + Math.min(rightCellSize, Math.min(bottomCellSize, bottomRightCellSize));

        // update the cache
        cache[row][col] = result;
        return cache[row][col];
    }

    /**
     * This is the bottom-up approach using iteration rather than
     * recursion.
     *
     * Recursion recurses all the way down to the right-bottom most cell
     * and build the solution from there.
     *
     * Instead of that, the bottom-up does the opposite of that by build
     * the solution that start from most up-left cell
     *
     * Runtime: O(n*m) -> iterate through every single cell, do constant work
     * Space: O(n*m)   -> the cache
     *
     *
     * @param matrix
     * @return
     */
    private static int largestSquareSubmatrixBU(int[][] matrix) {
        int max = 0;

        int[][] cache = new int[matrix.length][matrix[0].length];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {

                if (row == 0 || col == 0) {
                    cache[row][col] = matrix[row][col];
                } else if (matrix[row][col] == 1) {
                    int leftCellSize = cache[row][col-1];
                    int upperCellSize = cache[row-1][col];
                    int upperLeftCellSize = cache[row-1][col-1];

                    int result = 1 + Math.min(leftCellSize,
                            Math.min(upperCellSize, upperLeftCellSize));

                    cache[row][col] = result;

                    max = Math.max(max, cache[row][col]);
                }

            }
        }

        System.out.println("******* bottom up cache *******");
        ArrayUtils.printMatrix(cache);
        return max;
    }
}
