package my.leetcode.medium;

import org.common.ArrayUtils;
import org.testng.Assert;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 * Example 1:

 * Input:
 *
 * 0 0 0
 * 0 1 0
 * 0 0 0
 *
 * Output:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 *
 * Example 2:
 * Input:
 *
 * 0 0 0
 * 0 1 0
 * 1 1 1
 *
 * Output:
 * 0 0 0
 * 0 1 0
 * 1 2 1
 *
 * Example 3:
 * Input:
 *
 * 0 0 1
 * 0 1 1
 * 1 1 1
 *
 * Output:
 * 0 0 1
 * 0 1 2
 * 1 2 3

 *
 * Note:
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right.
 *
 * Observation:
 *  - Notice the rule about adjacent cells
 *  - Distance from a non-zero cell to a neighbor zero cell is 1
 *  - for each cell[r][c] = Math.min(cell[r][c-1], cell[r-1][c],
 *                               cell[r][c+1], cell[r+1][c]) + 1
 *  - DFS
 *    - if one of the neighboring cells returns 1, then do we still need
 *       to explore other neighboring cells???
 *      - base cases
 *      - if cell value is 0 - return 1
 *      - at the edge (left, top, right, bottom) return Integer.MAX because
 *        we are doing a min
 *
 *     - for only non-zero cell
 *     - use a visited matrix to record we've been there before
 *
 *
 */
public class Matrix01 {
    public static void main(String[] args) {
        System.out.printf("%s\n", Matrix01.class.getName());


        test(new int[][] {{0,0,0},
                          {0,1,0},
                          {0,0,0}},
                new int[][] {{0,0,0},
                             {0,1,0},
                             {0,0,0}});

        test(new int[][] {{0,0,0},
                        {0,1,0},
                        {1,1,1}},
                new int[][] {{0,0,0},
                             {0,1,0},
                             {1,2,1}});

        test(new int[][] {{0,0,1},
                          {0,1,1},
                          {1,1,1}},
                new int[][] {{0,0,1},
                             {0,1,2},
                             {1,2,3}});


        int[][] bigOne = new int[][] {
                {0,1,0,1,1},
                {1,1,0,0,1},
                {0,0,0,1,0},
                {1,0,1,1,1},
                {1,0,0,0,1}
        };

        int[][] bigOneExpected = new int[][] {
                {0,1,0,1,2},
                {1,1,0,0,1},
                {0,0,0,1,0},
                {1,0,1,1,1},
                {1,0,0,0,1}
        };

        test(bigOne, bigOneExpected);


        test(new int[][] {{0,1,1},
                          {0,0,1},
                          {0,1,0}},
                new int[][] {{0,1,2},
                             {0,0,1},
                             {0,1,0}});

        int[][] bigTwo = new int[][] {
                {0,1,0,1},
                {1,0,0,0},
                {1,1,1,1},
                {1,1,1,0}
        };

        int[][] bigTwoExpected = new int[][] {
                {0,1,0,1},
                {1,0,0,0},
                {2,1,1,1},
                {3,2,1,0}
        };

        test(bigTwo, bigTwoExpected);

        int[][] bigThree = new int[][] {
                {1,0,1,1,0,0,1,0,0,1},
                {0,1,1,0,1,0,1,0,1,1},
                {0,0,1,0,1,0,0,1,0,0},
                {1,0,1,0,1,1,1,1,1,1},
                {0,1,0,1,1,0,0,0,0,1},
                {0,0,1,0,1,1,1,0,1,0},
                {0,1,0,1,0,1,0,0,1,1},
                {1,0,0,0,1,1,1,1,0,1},
                {1,1,1,1,1,1,1,0,1,0},
                {1,1,1,1,0,1,0,0,1,1}
        };

        int[][] bigThreeExpected = new int[][] {
                {1,0,1,1,0,0,1,0,0,1},
                {0,1,1,0,1,0,1,0,1,1},
                {0,0,1,0,1,0,0,1,0,0},
                {1,0,1,0,1,1,1,1,1,1},
                {0,1,0,1,1,0,0,0,0,1},
                {0,0,1,0,1,1,1,0,1,0},
                {0,1,0,1,0,1,0,0,1,1},
                {1,0,0,0,1,2,1,1,0,1},
                {2,1,1,1,1,2,1,0,1,0},
                {3,2,2,1,0,1,0,0,1,1}
        };

        test(bigThree, bigThreeExpected);
    }

    private static void test(int[][] matrix, int[][] expected) {
        System.out.println("\n *** matrix: ");
        ArrayUtils.printMatrix(matrix);

        int[][] actual1 = updateMatrixDFS(matrix);

        int[][] actual2 = updateMatrixBFS(matrix);

        System.out.println(" *** expected: ");
        ArrayUtils.printMatrix(expected);

        System.out.println(" *** actual1: ");
        ArrayUtils.printMatrix(actual1);

        System.out.println(" *** actual2: ");
        ArrayUtils.printMatrix(actual2);

        Assert.assertTrue(ArrayUtils.equals(expected, actual1));
        Assert.assertTrue(ArrayUtils.equals(expected, actual2));
    }

    private static int[][] updateMatrixDFS(int[][] matrix) {
        if (matrix == null) {
            return matrix;
        }

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        // initialize all the non-zero cells with max int value
        // because we are doing the min calculation
        // ThIS IS ABSOLUTELY NECESSARY IN ORDER FOR CALCULATE THE CORRECT
        // OUTPUT WHILE DFS IS HAPPENNING
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                if (matrix[r][c] != 0) {
                    matrix[r][c] = 100000;
                }
            }
        }

       // System.out.println("***** before running DFS ****");
       // ArrayUtils.printMatrix(matrix);

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                if (!visited[r][c] && matrix[r][c] != 0) {
                    updateMatrixDFSHelper(matrix, r, c, visited);
                }
            }
        }

        System.out.println("counter: " + dfsCounter + " matrix: "  + matrix.length * matrix.length);


        return  matrix;
    }

    private static int dfsCounter = 0;

    private static int updateMatrixDFSHelper(int[][] matrix, int r, int c,
                                             boolean[][] visited) {

        // if a cell is not in the matrix, then return MAX_VALUE
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length) {
            return Integer.MAX_VALUE;
        }

        if (visited[r][c]) {
            return matrix[r][c];
        }


        // now mark to indicate we've been at matrix[r][c]
        visited[r][c] = true;

        // distance from any other cell to this cell with 0 value is 1
       /* if (matrix[r][c] == 0) {
            return 0;
        }*/

        // compare cell(r,c) with its current neighbors
        // upper neighbor
        if (r-1 >= 0) {
            matrix[r][c] = Math.min(matrix[r][c], matrix[r-1][c] + 1);
        }
        // lower neighbor
        if (r+1 < matrix.length) {
            matrix[r][c] = Math.min(matrix[r][c], matrix[r+1][c] + 1);
        }
        // left  neighbor
        if (c - 1 >= 0) {
            matrix[r][c] = Math.min(matrix[r][c], matrix[r][c-1] + 1);
        }
        // right neighbor
        if (c + 1 < matrix[0].length) {
            matrix[r][c] = Math.min(matrix[r][c], matrix[r][c+1] + 1);
        }

        // expand the circle
        int upperCell = updateMatrixDFSHelper(matrix, r - 1, c, visited);
        int lowerCell = updateMatrixDFSHelper(matrix, r + 1, c, visited);

        int leftCell = updateMatrixDFSHelper(matrix, r, c - 1, visited);
        int rightCell =  updateMatrixDFSHelper(matrix, r, c + 1, visited);


        int possibleNewMin = Math.min(
                Math.min(leftCell, rightCell),
                Math.min(upperCell, lowerCell)
        )
                + 1;

        matrix[r][c] = Math.min(matrix[r][c], possibleNewMin);
        return matrix[r][c];
    }

    /**
     * Perform DFS start at matrix[r][c] and return the min distance to called
     *
     * @param matrix
     * @param r
     * @param c
     * @param visited
     * @return
     */
    private static int updateMatrixDFSHelper1(int[][] matrix, int r, int c,
                                             boolean[][] visited) {

        // if a cell is not in the matrix, then return MAX_VALUE
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length) {
            return Integer.MAX_VALUE;
        }

        if (visited[r][c]) {
            return matrix[r][c];
        }

        dfsCounter++;

        // distance from any other cell to this cell with 0 value is 1
        /*if (matrix[r][c] == 0) {
            System.out.println("******** visiting cell with 0 value *******");
            return 0;
        }*/

        // now mark to indicate we've been at matrix[r][c]
        visited[r][c] = true;

        // compare cell(r,c) with its current neighbors.
        // it is important to do this before expand to other neighbors
        // upper neighbor
        if (r-1 >= 0) {
            matrix[r][c] = Math.min(matrix[r][c], matrix[r-1][c] + 1);
        }
        // lower neighbor
        if (r+1 < matrix.length) {
            matrix[r][c] = Math.min(matrix[r][c], matrix[r+1][c] + 1);
        }
        // left  neighbor
        if (c - 1 >= 0) {
            matrix[r][c] = Math.min(matrix[r][c], matrix[r][c-1] + 1);
        }
        // right neighbor
        if (c + 1 < matrix[0].length) {
            matrix[r][c] = Math.min(matrix[r][c], matrix[r][c+1] + 1);
        }

        // expand the circle
        int upperCell = updateMatrixDFSHelper(matrix, r - 1, c, visited);
        int lowerCell = updateMatrixDFSHelper(matrix, r + 1, c, visited);

        int leftCell = updateMatrixDFSHelper(matrix, r, c - 1, visited);
        int rightCell =  updateMatrixDFSHelper(matrix, r, c + 1, visited);

        // we might need to update with new minimum
        int possibleNewMin = Math.min(
                                Math.min(leftCell, rightCell),
                                Math.min(upperCell, lowerCell)
                               )
                       + 1;

        matrix[r][c] = Math.min(matrix[r][c], possibleNewMin);
        return matrix[r][c];
    }

    /**
     * This method uses the BFS - expand from cell with value 0 outward.
     *  - initialize all non-zero cells with a NO_VALUE
     *  - the whole idea is to expand one level at a time
     *  - have a level counter initialize to 1
     *  - starting point - collect all cells with value 0
     *  - for each cell at current level, update all its  neighbors to level + 1
     *  - also collect all the neighboring cells that we haven't updated yet
     *    -- we know this because initialize them at the beginning
     *
     * @param matrix
     */
    private static int[][] updateMatrixBFS(int[][] matrix) {
        if (matrix == null) {
            return matrix;
        }

        Queue<int[]> queue = new LinkedList<>();

        int NO_VALUE = -1;
        // only cells is non-zero value will be updated to NO_VALUE
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                if (matrix[r][c] == 0) {
                    // each elm represent the coordinate of that cell
                    // collecting cells with 0 value as starting point to expand from
                    queue.offer(new int[] {r,c});
                } else {
                    matrix[r][c] = NO_VALUE;
                }
            }
        }

        int level = 1;

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            System.out.println("queue size: " + queueSize);
            ArrayUtils.printMatrix(matrix);
            // for each level
            for (int i = 0; i < queueSize; i++) {
                int[] coord = queue.poll();
                int row = coord[0];
                int col = coord[1];

                // update the neighbors of coord to level
                // upper
                int newRow = row-1;
                if (newRow >= 0) {
                    if (matrix[newRow][col] == NO_VALUE) {
                        matrix[newRow][col] = level;
                        queue.offer(new int[] {newRow, col});
                    }
                }

                // lower
                newRow = row + 1;
                if (newRow < matrix.length) {
                    if (matrix[newRow][col] == NO_VALUE) {
                        matrix[newRow][col] = level;
                        queue.offer(new int[] {newRow, col});

                    }
                }

                // left
                int newCol = col - 1;
                if (newCol >= 0) {
                    if (matrix[row][newCol] == NO_VALUE) {
                        matrix[row][newCol] = level;
                        queue.offer(new int[] {row, newCol});
                    }
                }

                newCol = col + 1;
                if (newCol < matrix[0].length) {
                    if (matrix[row][newCol] == NO_VALUE) {
                        matrix[row][newCol] = level;
                        queue.offer(new int[] {row, newCol});
                    }
                }

            }

            // update to next level
            level++;
        }

        return matrix;

    }


}
