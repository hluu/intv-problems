package my.leetcode.easy;


import org.testng.Assert;

/**
 * Given a non-empty string s, you may delete at most one character.
 * Judge whether you can make it a palindrome.
 *
 * For example:
 *   "aba"  -> true
 *   "abca" -> true
 */
public class ValidPalindrome2 {
    public static void main(String[] args) {
        test("aba", true);
        test("civic", true);
        test("abba", true);

        //  ====== extra character ======
        test("abca", true);
        test("adbca", false);
        test("civiac", true);
        test("ciavic", true);
        test("aciavic", false);

        test("eeccccbebaeeabebccceea", false);

        test("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga",
                true);

    }

    private static void test(String input, boolean expected) {
        boolean actual = isValidPalindrome(input);

        boolean actualIteraive = isValidPalindromeIterative(input, 0, input.length()-1, false);

        System.out.printf("input: %s, expected: %B, actual: %B, actualIteraive: %B\n",
                input, expected, actual, actualIteraive);


        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected, actualIteraive);
    }

    private static boolean isValidPalindrome(String input) {
        if (input == null || input.length() < 2) {
            return true;
        }

        return isValidPalindromeHelper(input, 0, input.length()-1, false);
    }

    private static boolean isValidPalindromeIterative(String input,
                                                      int left,
                                                      int right,
                                                      boolean alreadyDeleted) {

        // this is our base case
        if (left > right) {
            // if alreadyDeleted, then return false
            return true;
        }

        while ((left <= right) && (input.charAt(left) == input.charAt(right))) {
            left++; right--;
        }

        // when exiting out the while loop, either left > right or characters at
        // both end are not the same
        if (left > right) {
            return true;
        }


        if (alreadyDeleted) {
            return false;
        }
        return isValidPalindromeIterative(input, left+1, right, true)
                ||
                isValidPalindromeIterative(input, left, right-1, true);


    }

    private static boolean isValidPalindromeHelper(String input,
                                                     int left,
                                                     int right,
                                                     boolean alreadyDeleted) {

        // this is our base case
        if (left > right) {
            // if alreadyDeleted, then return false
            return true;
        }

        if (input.charAt(left) == input.charAt(right)) {
            // happy case
            return isValidPalindromeHelper(input, left+1, right-1, alreadyDeleted);
        } else {
            if (alreadyDeleted) {
                return false;
            }
            return isValidPalindromeHelper(input, left+1, right, true)
                    ||
                  isValidPalindromeHelper(input, left, right-1, true);
        }
    }
}
