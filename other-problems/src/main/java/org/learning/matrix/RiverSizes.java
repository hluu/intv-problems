package org.learning.matrix;

import org.common.ArrayUtils;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Give a 2-d array of potentially unequal height and width containing only 0s or 1s.
 * Each 0 represent land, and each 1 represents part of a river.  A river
 * consists of any number of 1s and that are either horizontally or vertically adjacent
 * (but not diagonal).
 *
 * Write a function that returns an array of the sizes of all rivers in the input
 * matrix.  Order of the river sizes is not important.
 *
 * For example:
 *  {
 *      {1,0,0,1,0},
 *      {1,0,1,0,0},
 *      {0,0,1,0,1},
 *      {1,0,1,0,1},
 *      {1,0,1,1,0},
 *  }
 *
 *  expected output: {1,2,2,2,5}
 *
 * Observation:
 *  - we don't know where the river starts so assume it starts
 *    at each cell in the matrix
 *  - if a cell is 1, then that is the beginning of the river
 *    - we perform floodfill operation to explore that river
 *    - meaning turning it into another value
 *    - for the size 1 + top, bottom, left, right
 *  - stop exploring the river when left or right, or bottom, top is zero
 *    - or when reach beyond the border of the matrix
 *  - in order not to explore already explored river, we need to turn those
 *    cell value to something else or has a way to know that already been there
 *    - options
 *      - convert 1 into 0 or -1
 *      - use a bolean visited matrix
 *
 *
 */
public class RiverSizes {
    public static void main(String[] args) {
        System.out.println(RiverSizes.class.getName());

        test(matrix1(), Arrays.asList(1,2,2,2,5));
        test(matrix2(), Arrays.asList(1, 1, 2, 2, 5, 21));
        test(matrix3(), Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));

        test(new int[][]{}, Arrays.asList());

        test(new int[][]{{0,0,0}}, Arrays.asList());
        test(new int[][]{{1,1,1}}, Arrays.asList(3));
        test(new int[][]{{1,1,1}, {1,1}}, Arrays.asList(5));
        test(new int[][]{{1,1,1}, {1},{1,1}}, Arrays.asList(6));
        test(new int[][]{{1,1,1,0,1,1,0,0,0,1,0}}, Arrays.asList(1,2,3));
    }


    private static int[][] matrix1() {
        return new int[][] {
                {1,0,0,1,0},
                {1,0,1,0,0},
                {0,0,1,0,1},
                {1,0,1,0,1},
                {1,0,1,1,0}
        };

    }

    private static int[][] matrix2() {
        int[][] matrix = {
                {1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0},
                {1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0},
                {0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0},
                {1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1},
        };
        return matrix;
    }

    private static int[][] matrix3() {
        int[][] matrix = {
                {1, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 1},
        };
        return matrix;
    }
    private static void test(int[][] matrix, List<Integer> expected) {
        System.out.println("\n===== testing ======");

        ArrayUtils.printMatrix(matrix);

        List<Integer> actual = riverSizes(matrix);

        Collections.sort(expected);
        Collections.sort(actual);

        System.out.println("expected: " + expected);
        System.out.println("  actual: " + actual);

        Assert.assertEquals(actual, expected);
    }

    public static ArrayList<Integer> riverSizes(int[][] matrix) {

        // Write your code here.
        ArrayList<Integer> result = new ArrayList<>();

        if (matrix == null) {
            return result;
        }

        // initialize the visited matrix
        boolean[][] visited = new boolean[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            visited[row] = new boolean[matrix[row].length];
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {

                // start of the river if only if not visited yet and it is land
                if (!visited[row][col] && matrix[row][col] == 1) {
                    int riverSize = computeRiveSize(matrix, visited, row, col);
                    result.add(riverSize);
                }
            }
        }
        return result;
    }

    private static int computeRiveSize(int[][] matrix, boolean[][] visited,
                                       int row, int col) {

        if (!isValidCell(matrix, visited, row, col)) {
            return 0;
        }

        // make sure to mark it, so we don't visit it again
        visited[row][col] = true;

        return 1 + computeRiveSize(matrix, visited, row-1, col) +
                computeRiveSize(matrix, visited, row+1, col) +
                computeRiveSize(matrix, visited, row, col-1) +
                computeRiveSize(matrix, visited, row, col + 1);
    }

    /**
     * A cell is valid when coordinates are valid, has not visited, and land
     *
     * Can using or when violate the constraint
     *
     * @param matrix
     * @param visited
     * @param row
     * @param col
     * @return true when A cell is valid when it has not visited and land, else false
     */
    private static boolean isValidCell(int[][] matrix, boolean[][] visited,
                                       int row, int col) {
        // check the boundary
        if (row < 0 || row >= matrix.length) {
            return false;
        }

        if (col < 0 || col >= matrix[row].length) {
            return false;
        }

        // at this point, we know the coordinates are valid
        // check for water
        if (visited[row][col] || matrix[row][col] == 0) {
            return false;
        }

        return true;
    }


}
