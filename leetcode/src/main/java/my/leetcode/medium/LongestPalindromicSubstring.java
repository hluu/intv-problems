package my.leetcode.medium;

import org.junit.Assert;

/**
 * Problem:
 * https://leetcode.com/problems/longest-palindromic-substring/description/
 *
 * Explanation:
 * https://medium.com/@bhprtk/longest-palindromic-substring-a8190fab03ff
 *
 * Example:
 *
 * input:
 *  * "babad" => "bab"
 *  * "cbbd" => "bb"
 *  * "monday" => "y"
 *
 * Definition of a palindrome -
 *  * Reading backward is the same as reading forward, i.e "civic"
 *  * Observations:
 *    * Substring is a contiguous set of characters
 *    * The center of a palindrome could be a single or 2 characters
 *
 * Approach:
 * * Brute force - generate all substrings for a given a string, then check
 *   each of those strings to determine which one is the longest palindrome
 *   * Runtime: all substrings -> O(n^2), check for palindrome O(n)
 *     * total of O(n^3)
 *
 * * Expand around the center
 *   * Every palindrome has a center, which can either be a single character
 *     or 2 characters
 *   * Loop over each character in the string, treat that as the center,
 *     and expand outward to left and right until the contraint is violated
 *
 *
 *
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        test("","");
        test("x","x");
        test("babad","bab");
        test("civic","civic");
        test("cbbd","bb");
        test("cbb","bb");
        test("bbc","bb");
        test("monday","m");

    }

    private static void test(String input, String expected) {

        String actual = longestPalindrome(input);

        System.out.printf("input: %s, expected: %s, actual: %s \n",
                input, expected, actual);

        Assert.assertEquals(expected, actual);
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) {
            return s;
        }

        int left, right = 0;

        String result = "";
        for (int i = 0; i < s.length(); i++) {
            String tmpResult = expandCenter(s, i, i);
            if (tmpResult.length() > result.length()) {
                result = tmpResult;
            }

            tmpResult = expandCenter(s, i, i+1);
            if (tmpResult.length() > result.length()) {
                result = tmpResult;
            }
        }

        return result;
    }

    private static String expandCenter(String s, int left, int right) {
        // keep expanding left and right outward if the characters at
        // location left and right are the same

        // beware of the boundaries (0, s.length() - 1)
        while (left >= 0 && right < s.length() &&
                s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }


        return s.substring(left+1, right);
    }
}
