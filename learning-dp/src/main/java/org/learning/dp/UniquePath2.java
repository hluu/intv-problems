package org.learning.dp;

/**
 * This problem is an extension of the UniquePath problem and the extension is
 * the obstacles matrix.  An obstacle is represented by the value of 1 in the given
 * matrix.
 *
 * First, clearly understand the unique path problem first, then include the obstracle matrix
 * when computing the # of unique paths
 *
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 */
public class UniquePath2 {
    public static void main(String[] args) {
        System.out.println(UniquePath2.class.getName());

        int[][] obstracles1 = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };

        test(obstracles1, 2);

        int[][] obstracles2 = { {1}};
        test(obstracles2, 0);
    }

    private static void test(int[][] obstacleGrid, int expectedcount) {
        int actualCount = dpBottomUp(obstacleGrid);

        System.out.printf("expected: %d, actual: %d\n", expectedcount, actualCount);

    }

    private static int dpBottomUp(int[][] obstacles) {

        if (obstacles.length == 0 || obstacles[0].length == 0) {
            return 0;
        }

        if (obstacles.length > 100 || obstacles[0].length > 100) {
            return -1;
        }

        int row = obstacles.length;
        int col = obstacles[0].length;

        int[][] cache = new int[row][col];

        // all elements of row 1 should be 1, except cells that are after an obstacle
        boolean foundObstracle = false;
        for (int i = 0; i < col; i++) {
            if (obstacles[0][i] == 1) {
                foundObstracle = true;
            }
            if (!foundObstracle) {
                cache[0][i] = 1;
            }
        }

        // all elements of col 1 should be 1
        foundObstracle = false;
        for (int i = 0; i < row; i++) {
            if (obstacles[i][0] == 1) {
                foundObstracle = true;
            }
            if (!foundObstracle) {
                cache[i][0] = 1;
            }
        }

        for (int rowIdx = 1; rowIdx < row; rowIdx++) {
            for (int colIdx = 1; colIdx < col; colIdx++) {
                //count(row,col) = count(row-1,col) + count(row, col-1)
                int totalCnt = 0;
                if (obstacles[rowIdx][colIdx] == 0) {
                    // if no obstacle, the do the usual, else use the count of 0;
                    totalCnt = cache[rowIdx - 1][colIdx] + cache[rowIdx][colIdx - 1];
                }

                cache[rowIdx][colIdx] = totalCnt;
            }
        }

        return cache[row-1][col-1];
    }
}
