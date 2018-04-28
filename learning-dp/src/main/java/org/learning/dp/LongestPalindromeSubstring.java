package org.learning.dp;



import org.common.StringUtility;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/articles/longest-palindromic-substring/
 *
 * Problem:
 *  Give a string, return the longest palindromic substring.
 *
 *  A substring is a contiguous subsequence of characters in a string
 *
 *  For example:
 *    "forgeeksskeegfor" ==> 'geeksskeeg"
 *    "abaaba" => "abaaba"
 *    "abababa" => "abababa"
 */
public class LongestPalindromeSubstring {

    public  static  void main(String[] args) {
        System.out.printf("%s\n", LongestPalindromeSubstring.class.getName());

       test("a", "a");

        test("aa", "aa");

        test("abc", "a");

        test("hieen", "ee");

        test("civic", "civic");
        test("civica", "civic");
        test("acivic", "civic");

       test("forgeeksskeegfor","geeksskeeg");

       test("abaaba", "abaaba");
        test("abababa", "abababa");

        test("xdeedz", "deed");
        test("codeedoc", "codeedoc");
        test("codeedoca", "codeedoc");
        test("acodeedoc", "codeedoc");

        test("acodeedocd", "codeedoc");

        test("banana", "anana");
    }

    private static void test(String str, String expectedString) {
        System.out.printf("====== string: %s, expected: %s  =======\n",str, expectedString);

        // brute force
        String bruteForceStr = bruteForce(str);
        System.out.printf("brute force: '%s'\n", bruteForceStr);

        // center
        String centerStr = palindromeCenter(str);
        System.out.printf("center     : '%s'\n", centerStr);

        Assert.assertEquals(expectedString, centerStr);


        // Dynamic programming
        String dpStr = palindromeDP(str);
        System.out.printf("dp         : '%s'\n", dpStr);

        Assert.assertEquals(expectedString, dpStr);
    }


    /**
     * This approach uses DP because of the following recurrence
     *   pl(i,j) = pl(i+1, j-1) && a[i] == [j]
     *
     * So if we store the palindrome state of pl(i+1, j-1), then we can use if for
     * pl(i,j)
     *
     * We will need a table of dimension table[str.len][str.len]
     *
     * Run time will be O(N^2) and also require addition space of O(N^2)
     *
     * For each of string length from 1 to n
     *   For each substring from 0 to n
     *     detect if it is a palindrome based on two conditions
     *       pl(i+1,j-1) && a[i] == a[j]
     *
     * @param str
     * @return
     */
    private static String palindromeDP(String str) {
        if (str == null) {
            return null;
        }

        if (str.length() == 1 || (str.length() == 2 && str.charAt(0) == str.charAt(1))) {
            return str;
        }


        // will need some bookkeeping
        boolean[][] table = new boolean[str.length()][str.length()];
        int startPos = 0;
        int plLen = 1;


        int strLen = str.length();

        // special cases of len of 1 and len of 2

        // one character len string is by definition palindrome
        for (int i = 0; i < strLen; i++) {
            table[i][i] = true;
        }

        for (int i = 0; i < strLen-1; i++) {
            if (str.charAt(i) == str.charAt(i+1)) {
                table[i][i+1] = true;
                startPos = i;
                plLen=2;
            }
        }

        // populate the table as we go from length from 1 to n
        for (int currLen = 3; currLen <= strLen; currLen++) {
            // for each of substring len, compare character at either end and on smaller substring
            for (int i = 0; i < strLen-currLen+1; i++) {
                // make sure j is the offset of i
                int j = i+currLen-1;
                if (str.charAt(i) == str.charAt(j) && table[i+1][j-1]) {
                    // build up the table from small to large
                    table[i][j] = true;
                    startPos = i;
                    plLen = currLen;
                }
            }
        }

        //ArrayUtils.printBooleanMatrix(table);

       /* if (strLen > 1 && (plLen == 1)) {
            return null;
        }*/

        // substring(inclusive, exclusive)
        // watch out for index of by 1
        return str.substring(startPos, startPos+plLen);

    }


    /**
     * One unique property of a palindrome is it has a center
     *  * For an odd length palindrome, the center has only one character
     *  * For an event length palindrome, the center has two one characters
     *
     * Approach:
     *  1) With that observation, we can just iterate from left to right start at position 1
     *  2) Assume that is the center, then expand from there
     *  3) Stop when the invariant is violated and then check for even palindrome
     *  4) Otherwise we have a palindrome and keep expanding until constraint is violated
     *
     * This will require only O(N^2)
     *
     * https://www.youtube.com/watch?v=3bd-heXl48U
     * https://leetcode.com/articles/longest-palindromic-substring/
     * http://www.programcreek.com/2013/12/leetcode-solution-of-longest-palindromic-substring-java/
     *
     *
     * @param str
     * @return
     */
    private static String palindromeCenter(String str) {
        if (str == null) {
            return null;
        }

        if (str.length() == 1 || (str.length() == 2 && str.charAt(0) == str.charAt(1))) {
            return str;
        }

        int maxLen = 1;
        int start = 0;
        for (int i = 0; i < str.length(); i++) {
            int left = i - 1; int right = i + 1;
            // odd case
            while (left >= 0 && right < str.length() &&
                    str.charAt(left) == str.charAt(right)) {
                int tmpLen = right - left + 1;
                if (tmpLen > maxLen) {
                   maxLen = tmpLen;
                   start = left;
                }
                left--;
                right++;
            }

            // even case
            left = i; right = i+1;
            while (left >= 0 && right < str.length() &&
                    str.charAt(left) == str.charAt(right)) {
                int tmpLen = right - left + 1 ;
                if (tmpLen > maxLen) {
                    maxLen = tmpLen;
                    start = left;
                }
                left--;
                right++;
            }
        }

        return str.substring(start, start + maxLen);
    }

    private static String palindromeCenter2(String str) {

        if (str == null) {
            return null;
        }

        if (str.length() == 1 || (str.length() == 2 && str.charAt(0) == str.charAt(1))) {
            return str;
        }


        int left = 0; int right = 0;
        String longestPalindrome = "";

        // iterate through string starting at 1 and end at length-1
        // a palindrome can't start at either end of the string
        // treat each character as the center
        // two possible scenarios:
        //  * odd palindrome
        //  * even palindrome
        for (int i = 1; i < str.length() - 1; i++) {
            left = i - 1; right = i + 1;
            // the odd case
            String palStr = isPalindromeFromCenter(left, right, str);
            // try even len palindrome
            if ((palStr == null) && (str.charAt(i) == str.charAt(i+1))) {
                // start from these those characters and expand from there
                palStr = isPalindromeFromCenter(i, i+1, str);
            }

            if ((palStr != null) && (palStr.length() > longestPalindrome.length())) {
                longestPalindrome = palStr;
            }
        }

        return (longestPalindrome.length() > 0) ? longestPalindrome : null;
    }

    private static String isPalindromeFromCenter(int left, int right, String str) {
        if (left < 0 || right >= str.length()) {
            return null;
        }

        if (str.charAt(left) != str.charAt(right)) {
            return null;
        }

        // we have a palindrome, soo keep expanding
        while ((left > -1) && (right < str.length()) &&
                (str.charAt(left) == str.charAt(right))) {
            left--; right++;
        }

        // there two conditions when above while loop exist
        // 1) left and right are still within boundary and characters don't match
        // 2) when either one of them reaches the boundary

        // for #1, we need to adjust the left and right by one
        // for #2, if (left < 0) adjust left only
        left = left + 1;
        //right = (right > str.length()) ? right-1 : right;

        // substring is (include, exclusive)
        return str.substring(left, right);
    }

    /**
     * Brute force solution - O(n^3)
     *
     * 1) Build up a list of substring
     * 2) For each substring, determine if it is a palindrome and if so,
     *    track the length and str
     * 3) Return the max length palindrome.
     *
     * @param str
     * @return String - longest palindrome substring
     */
    private static String bruteForce(String str) {
        // generate the list of candidates
        List<String> subStrList = StringUtility.generateSubStrings(str);

        String longestPalindromeSub = "";

        // verify which candidate is an actual palindrome
        for (String subStr : subStrList) {
            if (isPalindromeSimple(subStr) && subStr.length() > longestPalindromeSub.length()) {
                longestPalindromeSub = subStr;
            }
        }

        return longestPalindromeSub;
    }

    /**
     * This implementation just uses a simple while loop
     *
     * @param str
     * @return
     */
    private static boolean isPalindromeSimple(String str) {
        int left = 0, right = str.length()-1;

        // definition of a palindrome the characters in a string can read back or forward
        // therefore we can just do a simple compare
        // the moment two characters are not the same, we can bail

        while (left <= right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
        }

        return true;
    }

    /**
     * This implementation uses 3 counters - left, right and numTime.
     *
     * We can do better with just a simple while loop
     *
     * @param str
     * @return
     */
    private static boolean isPalindrome(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        int rightSide = str.length() - 1;
        int leftSide = 0;
        int numTime = str.length() / 2;

        boolean isPalindrome = true;
        for (int i = 0; i < numTime; i++) {
            if (str.charAt(leftSide) != str.charAt(rightSide)) {
                isPalindrome = false;
                break;
            } else {
                leftSide++; rightSide--;
            }
        }

        return isPalindrome;
    }

    private static void testSubString(String str) {
        System.out.println("===== testSubString");
        List<String> subStrList = StringUtility.generateSubStrings(str);
        for (String s : subStrList) {
            System.out.println(s);
        }
    }



}
