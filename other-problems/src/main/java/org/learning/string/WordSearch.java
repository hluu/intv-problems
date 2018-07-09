package org.learning.string;

import org.common.ArrayUtils;
import org.testng.Assert;

import java.util.Arrays;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent"
 * cells are those horizontally or vertically neighboring. The same letter cell may not be
 * used more than once.
 *
 * Example:
 * {
 *  {'A','B','C','E'},
 *  {'S','F','C','S'},
 *  {'A','D','E','E'}
 * }
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 *
 * Approach:
 *  *) First find the where to start based on the first character
 *  *) From here following the following path
 *     *) Look at neighboring cells (left, right, top, bottom)
 *     *) If one of them matches, then recurse
 *     *) else return false
 *     *) If reach end of string, return true
 *  *) Things to note, don't want to look at neighboring cell is already visited
 *
 *  Edge cases:
 *  *) duplicate letters
 *  *) null string
 *  *) string length longer than the number of cells in the grid
 *
 */
public class WordSearch {
    public static  void main(String[] args) {
        System.out.println(WordSearch.class.getName());

        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        test(board, "X", false);
        test(board, "A", true);
        test(board, "ABC", true);
        test(board, "ABCCED", true);
        test(board, "ABCD", false);

        char[][] board2 = {
                {'b','b'},
                {'a','b'},
                {'b','a'}
        };

        test(board2, "a", true);

        // test of going back to the left aab
        char[][] board3 = {
                {'c','a', 'a'},
                {'a','a', 'a'},
                {'b','c', 'd'}
        };

        test(board3, "aab", true);

        // test of going down and then up
        char[][] board4 = {
                {'A','B', 'C', 'E'},
                {'S','F', 'E', 'S'},
                {'A','D', 'E', 'E'}
        };
        test(board4, "ABCESEEEFS", true);
        test(board4, "ABCESEEDASFE", true);

    }


    private static void test(char[][] board, String word, boolean expectedResult) {
        System.out.println("**** test ***");
        char[][] boardTmp = ArrayUtils.deepCopyCharMatrix(board);

        for (char[] row : boardTmp) {
            System.out.println(Arrays.toString(row));
        }

        System.out.printf("word: %s\n", word);

        boolean actualResult = wordSearch(boardTmp, word);

        System.out.printf("expected: %b, actual: %b\n", expectedResult, actualResult);

        Assert.assertEquals(actualResult, expectedResult);
    }


    private static boolean wordSearch(char[][] board, String word) {
        if (word == null || word.length() > board.length * board[0].length) {
            return  false;
        }

        boolean[][] state = new boolean[board.length][board[0].length];

        // THE FOLLOWING 2 FOR LOOPS DICTATES THE ORDER OF THE
        // STARTING POINT TO EXPLORE
        // THERE MAY BE CASES WHERE WE HAVE TO EXPLORE BACKWARD TO
        // FIND THE WORD IN THE GRID
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {

                // there could be multiple starting points
                if (word.charAt(0) == board[row][col]) {
                    boolean result = wordSearchHelper(board, word, 0, row, col, state);
                    if (result) {
                        return true;
                    } else {
                        state[row][col] = false;
                    }
                }
            }
        }

        return false;
    }


    private static void resetState(boolean[][] state) {

        for (int row = 0; row < state.length; row++) {
            for (int col = 0; col < state[0].length; col++) {
                state[row][col] = false;
            }
        }
    }

    private static boolean wordSearchHelper(char[][] board, String word, int currIdx,
                                            int row, int col, boolean[][] state) {
        // base case
        if (currIdx == word.length()-1) {
            return true;
        }

        state[row][col] = true;
        currIdx++;


        boolean result = false;

        // AFTER EXPLORING EACH PATH, IT IS IMPORTANT TO RESET IF
        // THINGS DIDN'T WORK OUT TO ALLOW FOR EXPLORING PATHS THAT
        // ARE GOING FORWARD AND BACKWARD

        // up
        if (shouldGoThisWay(board, word, currIdx, row-1, col, state)) {
            result = wordSearchHelper(board, word, currIdx, row-1, col, state);
            if (result) {
                return  result;
            } else {
                // have to reset state
                state[row-1][col] = false;
            }
        }

        // down
        if (shouldGoThisWay(board, word, currIdx,row+1, col, state)) {
            result = wordSearchHelper(board, word, currIdx, row+1, col, state);
            if (result) {
                return  result;
            } else {
                // have to reset state
                state[row+1][col] = false;
            }
        }

        // left
        if (shouldGoThisWay(board, word, currIdx, row, col-1, state)) {
            result = wordSearchHelper(board, word, currIdx, row, col-1, state);
            if (result) {
                return  result;
            } else {
                // have to reset state
                state[row][col-1] = false;
            }
        }

        // right
        if (shouldGoThisWay(board, word, currIdx, row, col+1, state)) {
            result = wordSearchHelper(board, word, currIdx, row, col+1, state);
            if (result) {
                return  result;
            } else {
                // have to reset state
                state[row][col+1] = false;
            }
        }

        return false;
/*

        result = shouldGoThisWay(board, word, currIdx,row+1, col, state) ||
                shouldGoThisWay(board, word, currIdx,row-1, col, state) ||
                shouldGoThisWay(board, word, currIdx, row, col+1, state) ||
                shouldGoThisWay(board, word, currIdx, row, col-1, state);

        state[row][col] = false;

        return result;
        */
    }

    private static boolean shouldGoThisWay(char[][] board, String word, int currIdx,
                                           int row, int col, boolean[][] state) {

        return isValidCoord(board, row, col) &&
                !state[row][col] &&
                board[row][col] == word.charAt(currIdx);

    }

    private static boolean isValidCoord(char[][] board, int row, int col) {
        // this is looking at the true case
        //return (row >= 0 && row < board.length && col >= 0 && col < board[0].length);

        // looking at the negative case
        if (row < 0 || col < 0 || row == board.length || col == board[0].length) {
          return false;
        }

        return true;
    }


}
