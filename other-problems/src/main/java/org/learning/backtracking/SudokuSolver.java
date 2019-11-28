package org.learning.backtracking;

import org.common.ArrayUtils;
import org.common.Tuple;

/**
 * Use backtracking approach to solve a sudoku board.
 *
 */
public class SudokuSolver {
    private static int UNASSIGED_SPOT = 0;

    public static void main(String[] args) {
        System.out.println(SudokuSolver.class.getName());

        /*
        int[][] board = new int[][] {
                {3,0,6, 5,0,8, 4,0,0},
                {5,2,0, 0,0,0, 0,0,0},
                {0,8,7, 0,0,0, 0,3,1},

                {0,0,3, 0,1,0, 0,8,0},
                {9,0,0, 8,6,3, 0,0,5},
                {0,5,0, 0,9,0, 6,0,0},

                {1,3,0, 0,0,0, 2,5,0},
                {0,0,0, 0,0,0, 0,7,4},
                {0,0,5, 2,0,6, 3,7,4},
        };

        int[][] board = new int[][] {
                {0,3,0, 9,0,0, 8,0,0},
                {0,4,0, 0,2,0, 0,0,6},
                {6,8,0, 3,0,7, 2,0,0},

                {2,0,0, 5,3,8, 6,7,0},
                {0,0,5, 0,0,0, 1,0,0},
                {0,6,7, 2,1,9, 0,0,4},

                {0,0,3, 6,0,4, 0,8,7},
                {9,0,0, 0,5,0, 0,6,0},
                {0,0,6, 0,0,3, 0,1,0},
        };
        */

        int[][] board = new int[9][9];

        ArrayUtils.printMatrix(board);

        boolean result = solveSudoku(board);

        System.out.println("******************************: result: " + result);
        ArrayUtils.printMatrix(board);
    }

    private static boolean solveSudoku(int[][] board) {
        // see if any remaining spot to assign a number to
        Tuple<Integer, Integer> spot = findUnassignedSpot(board);

        if (spot == null) {
            // no empty spot on the board, so we are done
            return true;
        }

        int row = spot.first;
        int col = spot.second;

        // try all possible digit at the found empty spot
        for (int digit = 1; digit <= 9; digit++) {
            if (!isThereConflict(board, row, col, digit)) {
                // make a choice if no conflict
                System.out.printf("==> assigning spot: %s with digit: %d\n", spot, digit);
                board[row][col] = digit;

                // solve the remaining empty spots on the board
                if (solveSudoku(board)) {
                    // if able to do so, then we are done
                    return true;
                }

                // backtrack - oops not able to solve the sudoko with the digit
                // at that empty spot, try another one is available
                System.out.printf("<== backtrack spot: (%d,%d) - digit: %d\n", row,col,digit);
                board[row][col] = UNASSIGED_SPOT;
            }
        }

        // we've tried everything and didn't work out, so return false;
        return false;
    }

    /**
     * Determine if there is a conflict of assigned the given digit at the specified row and col
     *
     * @param board
     * @param row
     * @param col
     * @param digit
     * @return boolean - true for conflict, false otherwise
     */
    private static  boolean isThereConflict(int[][] board, int row, int col, int digit) {
        // check for row - meaning iterating through the columns
            for (int cl = 0; cl < board[row].length; cl++) {
            if (board[row][cl] == digit) {
                return true;
            }
        }

        // check for column - meaning iterating through the rows
        for (int rw = 0; rw < board.length; rw++) {
            if (board[rw][col] == digit) {
                return true;
            }
        }

        // check for the box that (row, col) is in
        // each box is a 3x3 grid, and has a starting row,col offset
        // row=3 => is the first row, row=4 is second row, row=5 is third row : offset 3 (row - row & 3)
        // row=6 => is the first row, row=7 is second row, row=8 is third row : offset 6

        int rowOffset = row - (row % 3);

        int colOffset = col - (col % 3);

        //System.out.printf("col: %d, colOffset: %d, \n", col, colOffset);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                //System.out.printf("rowOffset: %d, colOffset: %d, row: %d, col: %d\n",
                  //      rowOffset, colOffset, r, c);
                if (board[rowOffset + r][colOffset + c] == digit) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Find a spot in board that has not assigned a number yet and return
     * the coordinate (row, col).
     *
     * If unable to find one, return null
     * @param board
     * @return Tuple(row, col), null if none found
     */
    private static Tuple<Integer, Integer> findUnassignedSpot(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == UNASSIGED_SPOT) {
                    return new Tuple<>(row, col);
                }
            }
        }

        return null;
    }
}
