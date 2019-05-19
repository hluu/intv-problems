import org.common.ArrayUtils;
import org.testng.Assert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * You are given an M by N matrix consisting of booleans that represents a board.
 * Each True boolean represents a wall. Each False boolean represents a tile you
 * can walk on.
 *
 * Given this matrix, a start coordinate, and an end coordinate, return the minimum
 * number of steps required to reach the end coordinate from the start. If there
 * is no possible path, then return null. You can move up, left, down, and right.
 * You cannot move through walls. You cannot wrap around the edges of the board.
 *
 * For example, given the following board:
 *
 * {[f, f, f, f],
 *  [t, t, f, t],
 *  [f, f, f, f],
 *  [f, f, f, f]}
 *
 * and start = (3, 0) (bottom left) and end = (0, 0) (top left), the minimum
 * number of steps required to reach the end is 7, since we would need to go
 * through (1, 2) because there is a wall everywhere else on the second row.
 *
 * Observation:
 *  - DFS recursion approach - start from start point
 *    - 1 + min(up, down, left, right)
 *    - base case when reaching end coord
 *    - or edges of the matrix or wall
 *    - a few things keep in mind
 *      - need to make sure we don't get into a loop by keeping track of where
 *        which cells are already explored
 *      - maybe use an board w/ same size with following values
 *        - -1 means has not explored
 *        - -2 means the wall
 *        - INTEGER_MAX when reach board boundary
 *  - BFS approach is a bit easier
 *    - treating the end point as the center of universse
 *    - expanding circle out 1 step at a time
 *    - when one of the nodes is the start, we we are done
 *    - implementation wise, BFS with a for loop to explore one level at a time
 */
public class SteppingThroughMaze {
    public static void main(String[] args) {
        boolean[][] matrix1 = {
                {false, false, false, false},
                {true, true, false, true},
                {false, false, false, false},
                {false, false, false, false}
        };

        test(matrix1, new int[] {3,0}, new int[] {0,0}, 7);

        boolean[][] matrix2 = {
                {false, false, false, false},
                {true, true, false, true},
                {true, true, false, false},
                {false, true, false, false}
        };
        test(matrix2, new int[] {3,0}, new int[] {0,0}, -1);

        boolean[][] matrix3 = {
                {false, false, false, false},
                {true, true, true, false},
                {true, true, true, false},
                {false, false, false, false}
        };
        test(matrix3, new int[] {3,0}, new int[] {0,0}, 9);

        boolean[][] matrix4 = {
                {false, false, true, false},
                {true, false, false, true},
                {true, true, false, true},
                {false, false, false, false}
        };
        test(matrix4, new int[] {0,0}, new int[] {3,3}, 6);

        boolean[][] matrix5 = {
                {false, false, false, false},
                {false, false, false, false},
                {false, true, true, false},
                {false, false, false, false}
        };
        test(matrix5, new int[] {3,1}, new int[] {0,1}, 5);
    }

    private static void test(boolean[][] matrix, int[] start, int[] end, int expected) {
        System.out.println("\n======= testing =======");
        ArrayUtils.printMatrix(matrix);

        int actual1 = findMinSteps(matrix, start, end);
        int actual2 = findMinStepsBFS(matrix, start, end);

        System.out.printf("expected: %d, actual1: %d, actual2: %d\n",
                expected, actual1, actual2);

        Assert.assertEquals(actual1, expected);
        Assert.assertEquals(actual2, expected);
    }

    /**
     *
     * @param matrix
     * @param start
     * @param end
     * @return minStep or -1
     */
    private static int findMinSteps(boolean[][] matrix, int[] start, int[] end) {

        int[][] bookKeeper = new int[matrix.length][matrix[0].length];

        for (int[] row : bookKeeper) {
            Arrays.fill(row, -1);
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col]) {
                    bookKeeper[row][col] = -2;
                }
            }
        }

        int top = findMinStepHelper(matrix, end, start[0]-1, start[1],
                bookKeeper);

        int bottom = findMinStepHelper(matrix, end, start[0]+1, start[1],
                bookKeeper);

        int left = findMinStepHelper(matrix, end, start[0], start[1]-1,
                bookKeeper);

        int right = findMinStepHelper(matrix, end, start[0], start[1]+1,
                bookKeeper);

        int minStep = Math.min(Math.min(top,bottom), Math.min(left, right));

        if(minStep == Integer.MAX_VALUE) {
            return -1;
        } else {
            return 1 + minStep;
        }
    }

    private static int findMinStepHelper(boolean[][] matrix, int[] end,
                                         int row, int col,
                                         int[][] bookkeeper ) {

        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            return Integer.MAX_VALUE;
        }

        // if wall
        if (matrix[row][col]) {
            return Integer.MAX_VALUE;
        }

        // if we found the end
        if (end[0] == row && end[1] == col) {
            return 0;
        }

        int cacheValue = bookkeeper[row][col];
        if (cacheValue > 0) {
            return cacheValue;
        }

        // need to set the value of current location, we don't go
        // in circle
        bookkeeper[row][col] = Integer.MAX_VALUE;

        int top = findMinStepHelper(matrix, end, row-1, col,
                bookkeeper);
        int bottom = findMinStepHelper(matrix, end, row+1, col,
                bookkeeper);

        int left = findMinStepHelper(matrix, end, row, col-1,
                bookkeeper);

        int right = findMinStepHelper(matrix, end, row, col+1,
                bookkeeper);

        int minStep = Math.min(Math.min(top,bottom), Math.min(left, right));

        int result = -1;
        if(minStep != Integer.MAX_VALUE) {
            result = 1 + minStep;
        } else {
            result = minStep;
        }

        bookkeeper[row][col] = result;
        return bookkeeper[row][col];
    }

    /**
     * This implementation uses a BFS approach
     *
     * @param matrix
     * @param start
     * @param end
     * @return
     */
    private static int findMinStepsBFS(boolean[][] matrix, int[] start, int[] end) {
        Queue<Coord> queue = new LinkedList<>();

        int numSteps = 0;

        queue.offer(new Coord(start[0], start[1]));

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int queueSize = queue.size();

            for (int i = 0; i < queueSize; i++) {
                Coord currCoord = queue.poll();

                if (currCoord.row == end[0] && currCoord.col == end[1]) {
                    return numSteps;
                }

                // mark as visited
                visited[currCoord.row][currCoord.col] = true;

                // expand out from currCoord and only add to queue if
                // not wall and has not visited yet
                for (int[] delta : TDLR) {
                    int newRow = currCoord.row + delta[0];
                    int newCol = currCoord.col + delta[1];

                    // check for valid coord
                    if (newRow >= 0 && newRow < matrix.length &&
                        newCol >= 0 && newCol < matrix[0].length) {
                       if (!matrix[newRow][newCol] && !visited[newRow][newCol]) {
                           queue.offer(new Coord(newRow, newCol));
                       }
                    }
                }

            }

            numSteps++;
        }

        return -1;
    }

    private static int[][] TDLR = new int[][] {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };

    private static class Coord {
        int row, col;
        public Coord(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
