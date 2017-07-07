package org.learning.dp;

/**
 * Created by hluu on 1/8/16.
 *
 * Write a function to compute the maximum length palindromic sub-sequence of an array.
 * A palindrome is a sequence which is equal to its reverse.
 *
 * A sub-sequence of an array is a sequence which can be constructed by removing
 * elements of the array.
 *
 * In other words, it doesn't have to be contiguous.
 *
 * Ex: Given [4,1,2,3,4,5,6,5,4,3,4,4,4,4,4,4,4] should return 10 (all 4's)
 *
 * For example:
 *   str = "ABBDCACB" ==> "BCACB", which is length of 5
 *
 *
 * Resource:
 *  * http://www.techiedelight.com/longest-palindromic-subsequence-using-dynamic-programming/
 */
public class LongestPalindromeSubsequence {
    public static void main(String[] args) {

        System.out.println("LongestPalindromeSubsequence.main");

        test("aba");

        String str1 = "ABBDCACB";

        test(str1);

        test("BBABCBCAB");

        test("bacdecmba");

        test("bacdeb");

        test("z");
    }

    private static void test(String str) {
        System.out.printf("========= test '%s' =======\n", str);

        System.out.println("bruteforce: " + bruteForce(str, 0, str.length()-1));

        System.out.println("dp: " + dp(str));
    }

    /**
     * Given a string, return the longest palindrome subsequence length
     *
     * Given a string, compare the first and last character represent by (i,j)
     *
     * Approach:
     *   pl(str)
     *     * if (a[i] == a[j])
     *     *  include in palindrome
     *     * then pl(substr(i+1, j-1)) + 2
     *     * else
     *         max { pl(substr(i+1,j), pl(substr(i, j-1)}
     *
     * Runtime is O(2^N) - exponential.
     *
     * There is a lot of redundant computation of string with same (i,j).
     * If we can memoize the result, store, and result them, then that would speed things
     * up.  That is where DP comes in.  Recognizing the redundant computation is the key.
     *
     * @param str
     * @return
     */
    private static int bruteForce(String str, int i, int j) {
        if (i > j) {
            return 0;
        }

        if (i == j) {
            return 1;
        }

        //System.out.printf("(%d,%d)\n", i, j);

        if (str.charAt(i) == str.charAt(j)) {
            return bruteForce(str, i+1,j-1) + 2;
        } else {
            return Math.max(bruteForce(str, i+1, j),
                    bruteForce(str, i, j-1));
        }
    }

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

        return dpHelper(str, 0, str.length()-1, table);
    }

    private static final int NOT_SEEN = -1;
    private static int dpHelper(String str, int i, int j, int[][]table) {

        if (i > j) {
            return 0;
        }

        if (i == j) {
            return 1;
        }

        // check table before computing
        if (table[i][j] == NOT_SEEN) {
            if (str.charAt(i) == str.charAt(j)) {
                table[i][j] = dpHelper(str, i+1,j-1, table) + 2;
            } else {
                table[i][j] = Math.max(bruteForce(str, i+1, j),
                        bruteForce(str, i, j-1));
            }
        }

        return table[i][j];
    }
}
