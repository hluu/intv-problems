package org.learning.dp;

import org.common.ArrayUtils;
import org.testng.Assert;

/**
 * Created by hluu on 2/9/18.
 *
 * This class is about the journey of solving the longest common subsequence problem,
 * which can be solved using DP.  Therefore it makes it interesting to start solving
 * it using recursion with inefficiency first, then refine the recursion approach
 * with memoization to speed run time up by adding space.  Finally, we will switch
 * the top-down approach with bottom approach.
 *
 *
 * Problem definition:
 *   Given two strings, find the longest common subsequence between them.  Since
 *   it is a subsequence, the letters don't need to be consecutive.
 *
 *   It could be two strings or two array of numbers.
 *
 *   The output that we are after is the length rather than the actual subsequence string,
 *   which is the first step in getting to the actual string.
 *
 *   Let's take the following example:
 *
 *   t h i s c u p i s a m a z i n g
 *     h       u         m a     n
 *
 *   We can walk through both string from left to right.  If the first letter of both string
 *   is the same then we  can use that as the start of the subsequence.  If they are not,
 *   then it is fairly obvious that it is not possible for them to be a part of a common
 *   subsequence. One or both of them will need to be removed.
 *
 *   Once we are done with the first letter, then solving the remaining letters of both strings
 *   is identical to the original problem, therefore recursion works well.
 *
 *   In the case the two letters are different, then we can determine which subproblem gives
 *   the correct solution by solving both and taking the max of the results.
 *
 *   https://www.ics.uci.edu/~eppstein/161/960229.html
 *
 *
 */
public class LCS {
    public static void main(String[] args) {
        String input1a = "thiscupisamazing";

        String input2a = "tipsme";
        String input2b = "imix";

        test(input1a, input2a, 5);
        test(input1a, input2b, 3);

        String input1b = "pal";
        String input2c = "apple";


        test(input1b, input2c, 2);

        String input1c = "ape";
        test(input1c, input2c, 3);
    }

    private static void test(String input1, String input2, int expectedLength) {
        System.out.printf("input1: %s, input2: %s\n", input1, input2);

        char[] inputCharArr1 = input1.toCharArray();
        char[] inputCharArr2 = input2.toCharArray();
        int resultFromBruteForce = lcsBruteforceRecursion(inputCharArr1, inputCharArr2, 0,0);
        System.out.println("resultFromBruteForce: " + resultFromBruteForce);

        Assert.assertEquals(resultFromBruteForce, expectedLength);

        int[][] cache = new int[input1.length()][input2.length()];
        for (int i = 0; i < input1.length(); i++) {
            for (int j = 0; j < input2.length(); j++) {
                cache[i][j] = -1;
            }
        }
        int resultFromMemoization = lcsDPWithMemoization(inputCharArr1, inputCharArr2, 0,0,
                cache);
        System.out.println("resultFromMemoization: " + resultFromMemoization);

        Assert.assertEquals(resultFromBruteForce, expectedLength);

        int resultFromBottomUp = lcsBottomUpDP(inputCharArr1, inputCharArr2);
        System.out.println("resultFromBottomUp: " + resultFromBottomUp);

        Assert.assertEquals(resultFromBottomUp, expectedLength);
        System.out.println();
    }

    /**
     * Brute force approach
     *   If reach the end of either string, then the longest subsequence is 0
     *   If characters are matched then return 1 + the longest subsequence of the
     *     remaining characters of both strings
     *   If characters don't match
     *     Try both path
     *       * One with removing one character from first string
     *       * One with removing one character from second string
     *       * Take the longest subsequence of the two
     *
     *   Run time - (if m=n) are close to 2^n
     *
     * @param input1
     * @param input2
     * @param index1
     * @param index2
     * @return the longest subsequence at index1 and index2
     */
    private static int lcsBruteforceRecursion(char[] input1, char[] input2, int index1, int index2) {
        //System.out.printf("index1: %d, index2: %d\n", index1, index2);

        if (index1 == input1.length || index2 == input2.length) {
            return 0;
        }

        if (input1[index1] == input2[index2]) {
            return 1 + lcsBruteforceRecursion(input1, input2, index1+1, index2 + 1);
        } else {
            return Math.max(
                    lcsBruteforceRecursion(input1, input2, index1+1, index2),
                    lcsBruteforceRecursion(input1, input2, index1, index2+1)
                    );
        }

    }

    /**
     * This is an extension of the brute force solution, which has redundant computations.
     * Notice the method signature of lcsBruteforceRecursion method, the only parameters
     * that are changing are index1 and index2.
     *
     * Run time is O(mn)
     *
     * @param input1
     * @param input2
     * @param index1
     * @param index2
     * @param cache
     * @return
     */
    private static int lcsDPWithMemoization(char[] input1, char[] input2, int index1, int index2,
                                            int[][] cache) {
        if (index1 == input1.length || index2 == input2.length) {
            return 0;
        }

        if (cache[index1][index2] != -1) {
            return cache[index1][index2];
        }

        if (input1[index1] == input2[index2]) {
            cache[index1][index2] = 1 + lcsDPWithMemoization(input1, input2, index1+1, index2 + 1, cache);
        } else {
            cache[index1][index2] = Math.max(
                    lcsDPWithMemoization(input1, input2, index1+1, index2, cache),
                    lcsDPWithMemoization(input1, input2, index1, index2+1, cache)
            );
        }

        return cache[index1][index2];

    }

    /**
     * Top down approach goes from left to right to fill the cache.  But notice it fills the
     * array from the bottom up.  With the bottom up approach, we will iterate the strings
     * from RIGHT TO LEFT.
     *
     * The important thing to note is when filling the 2-D array, each cell L[i,j] depends on
     * 3 already know values,
     *    namely L[i+1, j+1] (diagonal), L[i+1, j] (lower row), L[i, j+1] (right column)
     *
     * This approach fills the array backward, from last row to first row, from last column
     * to first column.
     *
     * What happens if we fill the cache from right to left.
     *
     * We don't need to initialize the matrix to -1.
     *
     *
     * @param input1
     * @param input2
     * @return
     */
    private static int lcsBottomUpDP(char[] input1, char[] input2) {
        int[][] cache = new int[input1.length+1][input2.length+1];

        for (int row = input1.length; row >= 0; row--) {
            for (int col = input2.length; col >= 0; col--) {
                if (row == input1.length || col == input2.length) {
                    cache[row][col] = 0;
                } else if (input1[row] == input2[col]) {
                    cache[row][col] = 1 + cache[row+1][col+1];
                } else {
                    cache[row][col] = Math.max(cache[row+1][col], cache[row][col+1]);
                }
            }
        }

        ArrayUtils.printMatrix(cache);
        return cache[0][0];
    }
}
