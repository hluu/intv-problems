package org.learning.backtracking;

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
public class WordSearch2 {
    public static  void main(String[] args) {
        System.out.println(WordSearch2.class.getName());

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

        System.out.println();
    }


    /**
     * Iterate through each cell and perform search.
     *
     * 1) Stop when found
     * 2) Need to maintain state so we don't get into infinite loop, but
     *    this is for only each search instance.  Need to reset
     * 3)
     *
     *
     *
     * @param board
     * @param word
     * @return
     */
    private static boolean wordSearch(char[][] board, String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }

        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }

        boolean[][] exploredState = new boolean[board.length][board[0].length];

        // this is the key portion here, to search from each of the position in the board
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                // only make sense to start searching if first matching
                // first character
                if (word.charAt(0) == board[row][col]) {
                    if (findWordInBoard(board, row, col, word, 0, exploredState)) {
                        return true;
                    }
                }

            }
        }

        return false;
    }

    private static boolean findWordInBoard(char[][] board, int row, int col,
                                           String word, int soFar, boolean[][] exploredState) {

        if (soFar == word.length()) {
            // found all the character in the word
            return true;
        }

        // boundary
        if (row < 0 || row == board.length || col < 0 || col == board[0].length) {
            return false;
        }

        // if already explored
        if (exploredState[row][col]) {
            return false;
        }

        // not matching character
        if (board[row][col] != word.charAt(soFar)) {
            return false;
        }


        // mark at visited
        exploredState[row][col] = true;

        // now expand from here
        boolean foundIt =
                findWordInBoard(board, row-1, col, word, soFar+1, exploredState) ||
                findWordInBoard(board, row+1, col, word, soFar+1, exploredState) ||
                findWordInBoard(board, row, col-1, word, soFar+1, exploredState) ||
                findWordInBoard(board, row, col+1, word, soFar+1, exploredState);

        if (foundIt) {
            return true;
        }

        // back track
        exploredState[row][col] = false;

        return false;
    }
}
