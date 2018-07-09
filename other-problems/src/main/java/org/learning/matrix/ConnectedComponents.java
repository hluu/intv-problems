package org.learning.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix, determine the size of each of the connect components.
 *
 * -----------------
 * | 1 | 1 | 1 | 1 |
 * | 1 | 0 | 0 | 0 |
 * | 1 | 0 | 0 | 1 |
 * -----------------
 *
 * With the example above, it should return
 *  * 6 at (0,0)
 *  * 5 at (1,1)
 *  * 1 at (2,3)
 *
 *  A connected component is defined as have same value
 *  * up,left,right,bottom
 *
 * General approach
 * 1) Iterate through each cell and treat it as a start point
 * 2) Use recursion
 *    * go up, bottom, left, right
 * 3) Need to keep track if already been there before either in-place update
 *    matrix cell with a unique value
 * 4) Need to check if out of matrix boundary
 * 5) Stop when can't find any more similar component
 *
 */
public class ConnectedComponents {

    public static void main(String[] args) {
        System.out.println(ConnectedComponents.class.getName());

        int [][] matrixSmall1 = new int[][] {
                {1,1,1,1},
                {1,0,0,0},
                {1,0,0,1}
        };

        List<CCInfo> result1 = findConnectedComponents(matrixSmall1);
        System.out.println(result1);

        int [][] matrixSmall2 = new int[][] {
                {1,1,1},
                {1,0,0},
                {1,0,1}
        };

        List<CCInfo> result2 = findConnectedComponents(matrixSmall2);
        System.out.println(result2);
    }

    /**
     * Assuming the values in the matrix are only positive values.
     * That way we can use the negative to indicate if a cell has already
     * explored.
     *
     *
     * @param matrix
     * @return
     */
    private static List<CCInfo> findConnectedComponents(int[][] matrix) {
        List<CCInfo> result = new ArrayList<>();

        int EXPLORED_CELL_VALUE = -1;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {

                if (EXPLORED_CELL_VALUE != matrix[row][col]) {
                    int cellValue = matrix[row][col];
                    int size = sizeHelper(row, col, cellValue, matrix, EXPLORED_CELL_VALUE);
                    result.add(new CCInfo(row, col, size, cellValue));
                }
            }
        }

        return  result;
    }

    private static int sizeHelper(int row, int col, int cellValue,
                                  int[][] matrix, int exploredCellValue) {
        if (row < 0 || row == matrix.length) {
            return 0;
        }

        if (col < 0 || col == matrix[0].length) {
            return 0;
        }

        // already explored
        if (matrix[row][col] == exploredCellValue) {
            return 0;
        }

        if (cellValue != matrix[row][col]) {
            return 0;
        }

        // mark as explored
        matrix[row][col] = exploredCellValue;

        int result = 1 +
                sizeHelper(row-1, col, cellValue,  matrix, exploredCellValue) +
                sizeHelper(row+1, col, cellValue,  matrix, exploredCellValue) +
                sizeHelper(row, col-1, cellValue,  matrix, exploredCellValue) +
                sizeHelper(row, col+1, cellValue,  matrix, exploredCellValue);

        return result;

    }

    private static class CCInfo {
        int row;
        int col;
        int size;
        int type;

        public CCInfo(int row, int col, int size, int type) {
            this.row = row;
            this.col = col;
            this.size = size;
            this.type = type;
        }

        public String toString() {
            return String.format("(%d,%d) type: %d size: %d",
                    row, col, type, size);

        }
    }
}
