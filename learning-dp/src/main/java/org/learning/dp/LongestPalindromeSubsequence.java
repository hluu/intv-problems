package org.learning.dp;

import org.common.ArrayUtils;
import org.testng.Assert;

/**
 *
 * Write a function to compute the maximum length palindromic sub-sequence of an array.
 * A palindrome is a sequence which is equal to its reverse.
 *
 * A sub-sequence of an array is a sequence which can be constructed by removing
 * elements of the array. A sequence that appears in the same relative order,
 * but not necessarily contiguous
 *
 * In other words, it doesn't have to be contiguous.
 *
 * Ex: Given [4,1,2,3,4,5,6,5,4,3,4,4,4,4,4,4,4] should return 10 (all 4's)
 *
 * For example:
 *   str = "ABBDCACB" ==> "BCACB", which is length of 5
 *   str = "AABCDEBAZ" ==> ABCBA or ABDBA or ABEBA
 *
 *
 * Resource:
 *  * http://www.techiedelight.com/longest-palindromic-subsequence-using-dynamic-programming/
 *  * https://algorithms.tutorialhorizon.com/longest-palindromic-subsequence/
 */
public class LongestPalindromeSubsequence {

    public static void main(String[] args) {

        System.out.println("LongestPalindromeSubsequence.main");

        test("aba", 3);


        test("ABBDCACB", 5);

        test("BBABCBCAB",7);

        test("bacdecmba", 5);

        test("bacdeb", 3);

        test("z", 1);
    }

    private static void test(String str, int expectedLen) {
        System.out.printf("========= test '%s', expected: %d =======\n", str, expectedLen);

        int actualBF = bruteForce(str, 0, str.length()-1);
        System.out.printf("bruteforce - actual: %d\n",  actualBF);

        int actualDP = dp(str);
        System.out.printf("dp - actual: %d\n", actualDP);


        Assert.assertEquals(actualBF, expectedLen);
        Assert.assertEquals(actualDP, expectedLen);
        System.out.println();


    }

    /**
     * Given a string, return the longest palindrome subsequence length
     *
     * Given a string, compare the first and last character represent by (i,right)
     *
     * Approach:
     *   pl(str)
     *     * if (a[i] == a[right])
     *     *  include in palindrome
     *     * then pl(substr(i+1, right-1)) + 2
     *     * else
     *         max { pl(substr(i+1,right), pl(substr(i, right-1)}
     *
     * Runtime is O(2^N) - exponential.
     *
     * There is a lot of redundant computation of string with same (left, right).
     * If we can memoize the result, store, and result them, then that would speed things
     * up.  That is where DP comes in.  Recognizing the redundant computation is the key.
     *
     * @param str
     * @return
     */
    private static int bruteForce(String str, int left, int right) {
        if (left > right) {
            return 0;
        }

        if (left == right) {
            return 1;
        }

        //System.out.printf("(%d,%d)\n", i, right);

        if (str.charAt(left) == str.charAt(right)) {
            return bruteForce(str, left+1,right-1) + 2;
        } else {
            return Math.max(bruteForce(str, left+1, right),
                            bruteForce(str, left, right-1));
        }
    }

    private static final int NOT_SEEN = -1;

    /**
     * DP approach of using a table to store the intermediate result.
     *
     * This is a top down approach.
     *
     * @param str
     * @return
     */
    private static int dp(String str) {
        if (str == null) {
            return -1;
        }

        // create the table
        int[][] table = new int[str.length()][str.length()];

        // initialize all cells to -1
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                table[i][j] = NOT_SEEN;
            }
        }

        int result = dpHelper(str, 0, str.length()-1, table);

        ArrayUtils.printMatrix(table);

        return  result;
    }


    private static int dpHelper(String str, int left, int right, int[][]table) {

        if (left > right) {
            return 0;
        }

        if (left == right) {
            return 1;
        }

        // check table before computing
        if (table[left][right] == NOT_SEEN) {
            if (str.charAt(left) == str.charAt(right)) {
                table[left][right] = dpHelper(str, left+1,right-1, table) + 2;
            } else {
                table[left][right] = Math.max(bruteForce(str, left+1, right),
                                       bruteForce(str, left, right-1));
            }
        }

        return table[left][right];
    }
}
