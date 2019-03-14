package my.leetcode.difficult;

import org.common.ArrayUtils;
import org.testng.Assert;

import java.util.*;

/**
 * Given a string S and a string T, count the number of distinct subsequences of S
 * which equals T.
 *
 * A subsequence of a string is a new string which is formed from the original
 * string by deleting some (can be none) of the characters without disturbing the
 * relative positions of the remaining characters. (ie, "ACE" is a subsequence of
 * "ABCDE" while "AEC" is not).
 *
 * Input: S = "rabbbit", T = "rabbit"
 * Output: 3
 * Explanation:
 *
 * As shown below, there are 3 ways you can generate "rabbit" from S.
 * (The caret symbol ^ means the chosen letters)
 *
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 *
 * Input: S = "babgbag", T = "bag"
 * Output: 5
 * Explanation:
 *
 * As shown below, there are 5 ways you can generate "bag" from S.
 * (The caret symbol ^ means the chosen letters)
 *
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 *   ^  ^^
 * babgbag
 *     ^^^
 *
 * babgbagembag
 *
 * Observations:
 * - the orders of letters in T matters, which makes things a bit easier
 * - there can be duplicate letters in T, make sure to handle that case
 * - finding a boundary based on left and right indices that contains all the letters in T
 *
 * Approach:
 * - moving from left to right, find a boundary that contains all the letters in T
 *   - find first letter starts with position 0
 *   - find second and subsequence letter starts after the prev. letter was found
 * - now moving letters from last to first to the right
 *
 */
public class DistinctSubsequences {

    public static void main(String[] args) {
        System.out.println(DistinctSubsequences.class.getName());

        test("a", "b", 0);
        test("rabbbit", "dog", 0);
        test("rabbbit", "rabbbit", 1);
        test("rabbbit", "rabbit", 3);
        test("babgbag", "bag", 5);
        test("acdabefbc", "ab", 4);
        //test("adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeabcddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc",
          //       "dad", 8789);

    }

    private static void test(String input, String pattern, int expected) {
        System.out.printf("\ninput: %s, pattern: %s, expected: %d\n",
                input,pattern, expected);

        int actual = findDistinctSub(input, pattern);

        int actualtopDownDP = topDownDP(input, pattern);

        int actualbottomUpDPRows = bottomUpDPUsingPatternAsRows(input, pattern);

        System.out.printf("actual:  %d, actualtopDownDP: %d, actualbottomUpDP: %d\n",
                actual,actualtopDownDP, actualbottomUpDPRows);

        Assert.assertEquals(actual, expected);
    }

    /**
     * Bottom up DP
     *  - patterns as rows, and input as columns
     *
     * Input:  babgbag, pattern: bag
     *          b    a    b    g    b    a    b
     *   ========================================
     *   | 1 |  1 |  1 |  1 |  1 |  1 |  1 |  1 |
     * b | 0 |  1 |  1 |  2 |  2 |  3 |  3 |  3 |
     * a | 0 |  0 |  1 |  1 |  1 |  1 |  4 |  4 |
     * g | 0 |  0 |  0 |  0 |  1 |  1 |  1 |  5 |
     *   ========================================
     *
     * if (s(n) == t(n))
     *   mem[r,c] =  mem[r-1, c-1] + m[r, c-1]
     * else
     *   mem[r,c] =  mem[r, c-1]
     *
     * @param input
     * @param pattern
     * @return
     */
    private static int bottomUpDPUsingPatternAsRows(String input, String pattern) {

        int[][] mem = new int[pattern.length()+1][input.length()+1];

        // filling the first row: with 1s
        for(int col=0; col <= input.length(); col++) {
            mem[0][col] = 1;
        }

        // the first column is 0 by default in every other rows but the first, which we need.

        for(int row=0; row< pattern.length(); row++) {

            for(int col=0; col<input.length(); col++) {

                if(pattern.charAt(row) == input.charAt(col)) {
                    mem[row+1][col+1] = mem[row][col] + mem[row+1][col];
                } else {
                    mem[row+1][col+1] = mem[row+1][col];
                }
            }
        }


        System.out.println("=== dp bottom up ==");
        ArrayUtils.printMatrix(mem);

        return mem[pattern.length()][input.length()];
    }

    /**
     *
     * Model this as a DP problem and build the table
     *
     * Build a table of input vs pattern
     *  - each letter in input occupies a row
     *  - each letter in pattern occupies a column
     *
     *        b a g
     * --------------
     *   | 1  0 0 0
     * b | 1
     * a | 1
     * b | 1
     * g | 1
     * b | 1
     * a | 1
     * g | 1
     *        b    a    g
     *   ====================
     *   | 1 |  0 |  0 |  0 |
     * b | 1 |  1 |  0 |  0 |
     * a | 1 |  1 |  1 |  0 |
     * b | 1 |  2 |  1 |  0 |
     * g | 1 |  2 |  1 |  1 |
     * b | 1 |  3 |  1 |  1 |
     * a | 1 |  3 |  4 |  1 |
     * g | 1 |  3 |  4 |  5 |
     *   ====================
     *
     * the first column must be filled with 1. That's because the empty string is a
     * subsequence of any string but only 1 time. So mem[0][j] = 1 for every j. So
     * with this we not only make our lives easier, but we also return correct value
     * if T is an empty string.
     *
     * the first row of every rows except the first must be 0. This is because an empty
     * string cannot contain a non-empty string as a substring -- the very first item
     * of the array: mem[0][0] = 1, because an empty string contains the empty string 1 time
     *
     * @param input
     * @param pattern
     * @return
     */
    private static int bottomUpDPUsingPatternAsColumns(String input, String pattern) {
        int inputLen = input.length();
        int patternLen = pattern.length();
        int[][] dp = new int[inputLen + 1][patternLen + 1];

        // dp[i][0] = 1, there is 1 way to form an empty string
        for (int i = 0; i <= inputLen; i++) {
            // populate column 0 with 1 for evey row
            dp[i][0] = 1;
        }

        // start at row 1 and column 1
        // row 0 represents empty pattern, so no match
        for (int row = 1; row <= inputLen; row++) {
            for (int col = 1; col <= patternLen; col++) {
                int prevRow = row-1;
                int prevCol = col-1;

                if (input.charAt(prevRow) == pattern.charAt(prevCol)) {
                    dp[row][col] = dp[prevRow][col] + dp[prevRow][prevCol];
                } else {
                    dp[row][col] = dp[prevRow][col];
                }
            }
        }

        System.out.println("=== dp bottom up 2 ==");
        ArrayUtils.printMatrix(dp);

        return dp[inputLen][patternLen];
    }

    /**
     * Using top down DP
     * @param input
     * @param pattern
     * @return
     */
    private static int topDownDP(String input, String pattern) {
        int[][] dp = new int[input.length() + 1][pattern.length() + 1];
        for(int [] arr : dp) {
            Arrays.fill(arr, -1);
        }

        int result = topDownDPHelper(input, pattern, dp, 0, 0);

        //System.out.println("=== dp topdown ==");
        //ArrayUtils.printMatrix(dp);

        return result;
    }

    public static int topDownDPHelper(String s, String t, int[][] dp, int sIdx, int tIdx){
        if(tIdx >= t.length()) return 1;
        if(sIdx >= s.length()) return 0;
        if(dp[sIdx][tIdx] != -1) return dp[sIdx][tIdx];

        int sol = topDownDPHelper(s, t, dp, sIdx + 1, tIdx);
        if(s.charAt(sIdx) == t.charAt(tIdx))
            sol += topDownDPHelper(s, t, dp,sIdx + 1, tIdx + 1);

        dp[sIdx][tIdx] = sol;
        return sol;
    }

    private static int findDistinctSub(String input, String pattern) {

        Map<Character, List<Integer>> charToPositionMap = buildCharToPosMap(input);

        System.out.println("charToPositionMap: " + charToPositionMap);


        // check to ensure pattern exists in input
        for (char c : pattern.toCharArray()) {
            if (!charToPositionMap.containsKey(c)) {
                return 0;
            }
        }

        return computePatternCount(pattern, 0, charToPositionMap,
                -1);

    }

    /**
     * Recursive approach of search at successive character in the pattern
     *
     * @param pattern
     * @param charIdx
     * @param charToPositionMap
     * @param prevCharLoc
     *
     * @return
     */
    private static int computePatternCount(String pattern, int charIdx, Map<Character,
            List<Integer>> charToPositionMap, int prevCharLoc) {

        char c = pattern.charAt(charIdx);
        List<Integer> charLocList = charToPositionMap.get(c);

        //System.out.printf("** char: %c, charIdx: %d, chaLocList: %s\n",
          //      c, charIdx, charLocList);

        int result = 0;
        // our base case
        if (charIdx == pattern.length()-1) {
            for (Integer charLoc : charToPositionMap.get(c)) {
                // there is an opportunity optimize here because
                // we now subsequent values will be bigger after this point
                if (charLoc > prevCharLoc) {
                    result += 1;
                }
            }
            return result;
        }

        // for each charLoc of a character in the pattern
        for (Integer charLoc : charToPositionMap.get(c)) {
            // only check for location that is > than prevCharLoc
            if (charLoc > prevCharLoc) {
                result += computePatternCount(pattern, charIdx + 1, charToPositionMap,
                        charLoc);
            }
        }

        return result;
    }

    private static Map<Character, List<Integer>> buildCharToPosMap(String input) {
        Map<Character, List<Integer>> charToPositionMap = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            List<Integer> idxList = charToPositionMap.get(c);
            if (idxList == null) {
                idxList = new LinkedList<>();
            }

            idxList.add(i);

            charToPositionMap.putIfAbsent(c, idxList);
        }

        return charToPositionMap;
    }


}
