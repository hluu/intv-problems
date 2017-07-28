package org.learning.backtracking;

import org.testng.Assert;

/**
 * Created by hluu on 7/28/17.
 *
 * Problem:
 *  Implement a function to check whether there is a path for a string in a matrix
 *  of characters?
 *
 *  It moves to left, right, up and down in a matrix.
 *
 *  The path can start from any cell in a matrix.  If a cell is occupied by a character
 *  of a string on the path, it cannot be occupied by another character again.
 *
 * Example:
 *   a b c e
 *   s f c s
 *   a d e e
 *
 *  There is a path for string "bcced"
 *  There is no path for string "abcb"
 *
 * Approach:
 *  What is being asked whether there is a path.
 *
 *  The path can start at any cell, so it is necessary to try out every cell.
 *  The is a notion of a path, so once there is a match of the first character, then
 *  it keeps exploring left, right, up, and down.
 *
 *  Two possible scenarios:
 *    * Can extend the path
 *      * Then keep exploring (t,b,r,l)
 *    * Can't extend the path
 *      * Terminate the exploration
 *      * Undo the bookkeeping
 *
 *  Need a way to bookkeeping the path to avoid going down that path again.
 *
 *  Exploring with following conditions:
 *  1) Valid coordinate
 *  2) Haven't visited yet
 *  3) Match the next letter
 *
 *
 *
 */
public class StringPathInMatrix {
    public static void main(String[] args) {
        System.out.printf("%s\n", StringPathInMatrix.class.getName());

        char[][] matrix = {
                {'a','b','c','e'},
                {'s','f','c','s'},
                {'a','d','e','e'},
        };

        test(matrix, "bcced", true);
        test(matrix, "fdeese", true);
        test(matrix, "adfcce", true);

        test(matrix, "abcb", false);
        test(matrix, "escd", false);
    }

    private static void test(char[][] matrix, String letters, boolean expectedFoundIt) {
        System.out.printf("======= testing for %s =======\n", letters);
        boolean foundId = isThereApath(matrix, letters.toCharArray());

        System.out.printf("expectedFoundIt: %b, actual: %b\n", expectedFoundIt, foundId);
        Assert.assertEquals(foundId, expectedFoundIt);
    }

    private static boolean isThereApath(char[][] matrix, char[] letters) {
        boolean[][] visitedMatrix = new boolean[matrix.length][matrix[0].length];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (explorePath(matrix, row, col, letters, 0, visitedMatrix)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Using recursion to explore a path
     * @param row
     * @param col
     * @param letters
     * @param index
     * @param visitedMatrix
     * @return
     */
    private static boolean explorePath(char[][] matrix, int row, int col, char[] letters, int index,
                                       boolean[][] visitedMatrix) {
        // base case
        if (index == letters.length) {
            // no more characters to explore
            return true;
        }

        boolean foundIt = false;
        if (validCoordinate(matrix, row, col) &&
            !visitedMatrix[row][col]  &&
            matrix[row][col] == letters[index]) {

            visitedMatrix[row][col] = true;
            index++; // next character

            // continue to explore all possible ways (top, bottom, right, left)
            foundIt =
                    explorePath(matrix, row-1, col, letters, index, visitedMatrix) ||
                            explorePath(matrix, row+1, col, letters, index, visitedMatrix) ||
                            explorePath(matrix, row, col-1, letters, index, visitedMatrix) ||
                            explorePath(matrix, row, col + 1, letters, index, visitedMatrix);

            if (!foundIt) {
                // backtrack
                System.out.printf("backtracking.....\n");
                visitedMatrix[row][col] = false;
            }
        }

        return foundIt;
    }

    /**
     * Determine whether the row and col are within the boundary of the matrix
     * @param row
     * @param col
     * @return
     */
    private static boolean validCoordinate(char[][] matrix, int row, int col) {
        if (row < 0 || row >= matrix.length ||
                col < 0 || col >= matrix[0].length) {
            // out of bound
            return false;
        } else {
            return true;
        }
    }
}
