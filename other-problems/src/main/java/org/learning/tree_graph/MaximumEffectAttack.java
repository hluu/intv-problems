package org.learning.tree_graph;

import org.common.ArrayUtils;

import java.util.*;

/**
 *
 *
 * Problem:
 *  Give a matrix with 0s and 1s, where the 1s represent the enemy territory.
 *  The 1s are connected together to indicate territory size.
 *  Find all the territories in order of size to use to determine where to
 *  attack enemy territory to gain maximum effect.
 *
 * For example:
 *   | 1 1 0 1 |
 *   | 1 0 0 1 |
 *   | 1 0 0 1 |
 *   | 1 1 0 1 |
 *
 * Expected output:
 *   * Two attack zones, and each zone should have two pieces of info:
 *     * starting coordinate
 *     * size
 *   *zone-1(0,0):6, zone-2(0,3):4
 *
 * Approach:
 *   * Find a starting position defined as cell with value of 1
 *   * Change cell valued to EXPLORED state
 *   * Explore all 4 directions (top, bottom, left, right)
 *   * Maintain an object to hold starting position
 *   * Bump the count as enemy land is discovered
 *   * Stop when run not enemy land
 *
 *
 */
public class MaximumEffectAttack {
    public static void main(String[] args) {
        System.out.println(MaximumEffectAttack.class.getName());

        int[][] matrix = {
                {1, 1, 0, 1},
                {1, 0, 0, 1},
                {1, 0, 0, 1},
                {1, 1, 0, 1},
        };

        int[][] matrix2= {
                {1, 1, 0, 0},
                {1, 0, 1, 0},
                {1, 0, 0, 1},
                {0, 1, 0, 1},
        };

        int[][] matrix3 = {
                {1, 0, 1, 0},
                {0, 0, 1, 0},
                {1, 0, 0, 1},
                {0, 1, 1, 1},
        };

        test(matrix);
        test(matrix2);
        test(matrix3);
    }

    private static void test(int[][] matrix) {
        System.out.printf("======== test ========\n");
        ArrayUtils.printMatrix(matrix);

        List<AttackZone> result = findMaximumAttackZone(matrix);

        System.out.println("result: " + result);
        System.out.println("");
    }

    private static int UNEXPLORED_STATE = 1;
    private static int EXPLORED_STATE = -1;

    private static List<AttackZone> findMaximumAttackZone(int[][] matrix) {
        List<AttackZone> result = new ArrayList<>();

        if (matrix == null || matrix.length < 1) {
            return result;
        }

        // treat each cell as a starting point, so go through each one of them
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                // however, only explore the ones that haven't been explored yet
                if (matrix[r][c] == UNEXPLORED_STATE) {
                    AttackZone az = new AttackZone(r,c);
                    exploreTerritory(matrix, az, r, c);
                    result.add(az);
                }
            }
        }

        Collections.sort(result, new Comparator<AttackZone>() {
            public int compare(AttackZone o1, AttackZone o2) {
                return o2.size - o1.size;
            }
        });

        return result;
    }

    /**
     * Recursion
     * @param matrix
     * @param az
     * @param row
     * @param column
     */
    private static void exploreTerritory(int[][] matrix, AttackZone az, int row, int column) {
        /*if (row < 0 || column < 0
                || row >= matrix.length || column >= matrix[0].length
                || (matrix[row][column] == EXPLORED_STATE)
                || (matrix[row][column] == 0)) {
            return;
        }*/

        if (!isValidCoord(row, column, matrix)) {
            return;
        }

        if (matrix[row][column] == UNEXPLORED_STATE) {
            az.incrementSize();
            // update so, we don't explore it again - **** VERY IMPORTANT ****
            matrix[row][column] = EXPLORED_STATE;

            // Flood fill
            exploreTerritory(matrix, az, row+1, column);
            exploreTerritory(matrix, az, row-1, column);
            exploreTerritory(matrix, az, row, column+1);
            exploreTerritory(matrix, az, row, column-1);
        }
    }

    private static boolean isValidCoord(int row, int col, int[][] matrix) {
        if (row < 0
                || col < 0
                || row >= matrix.length
                || col >= matrix[0].length
                || (matrix[row][col] == EXPLORED_STATE)
                || (matrix[row][col] == 0)) {
            return false;
        } else {
            return true;
        }


    }

    private static class AttackZone {
        public AttackZone(int r, int c) {
            this.row = r;
            this.column = c;
        }

        private int row, column;
        private int size;

        public void incrementSize() {
            size++;
        }

        public String toString() {
            return String.format("{(%d,%d) => %d} ", row, column, size);
        }
    }
}
