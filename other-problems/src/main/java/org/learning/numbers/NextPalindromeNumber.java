package org.learning.numbers;

import org.testng.Assert;

/**
 * Problem:
 *  * Given a positive integer, find the closest bigger integer that is
 *    palindrome
 *
 * Example:
 *  15  => 22
 *  123 => 131
 *  1034 => 1111
 *  1000 => 1001
 *  1011 => 1111
 *  999 => 1001
 *  12345 => 12421
 *
 * Approach:
 * * Brute force approach is to increment value by 1 and check for palindrome
 * * A more optimized approach using the palindrome property
 * * There is a slight treatment experience based number of digits (odd or even)
 * * If even (a simpler case)
 *   * split the # of digit into prefix and suffix
 *   * reverse the prefix and make that the suffix
 *   * now concat the prefix + suffix and make sure that is larger than original value
 *     * if it is, we are done
 *     * if it is not, bump up the prefix and suffix is the reverse of prefix
 * * If odd
 *   * prefix is half size of number string + 1
 *   * bump up by one
 *   * extract the first len/2 characters, reverse it assign to suffix
 *   * concat prefix and suffix
 *
 */
public class NextPalindromeNumber {

    public static void main(String[] args) {
        System.out.println(NextPalindromeNumber.class.getName());

        // odd
       test(292, 303);
        test(101, 111);
        test(100, 101);
        test(121, 131);
        test(12345, 12421);


        test(999, 1001);
        test(990, 999);

        // even
        test(15, 22);
        test(1015, 1111);

        test(50, 55);
        test(1000, 1001);
        test(1011, 1111);

        test(9999, 10001);
        test(9901, 9999);



    }

    private static void test(int n, int expectedNum) {
        System.out.printf("\ninput num: %d\n", n);

        int actual = nextPalindrome(n, n);

        System.out.printf("expected: %d, actual: %d", expectedNum, actual);

        Assert.assertEquals(actual, expectedNum);

        System.out.println();
    }

    private static int nextPalindrome(int originalNumber, int newNumber) {
        if (newNumber < 10 || newNumber == Integer.MAX_VALUE) {
            return -1;
        }

        System.out.println("num: " + newNumber);
        String nStr = Integer.toString(newNumber);
        int strLen = nStr.length();

        boolean isOdd = (strLen % 2 == 1);

        String prefix, suffix = "";

        int endIndexForPrefix = -1;
        int endIndexForReverse = -1;

        /*if (isOdd) {
            // odd - prefix (half + 1)
            prefix = nStr.substring(0, (strLen/2)+1);
            // the key here is reversing the prefix
            suffix = reverse(prefix.substring(0, (prefix.length() -1)));
            //suffix = reverse(prefix.substring(0, (prefix.length()/2)));

            int candNumber = toInt(prefix, suffix);
            if (originalNumber < candNumber) {
                return candNumber;
            } else {
                String newPrefix = Integer.toString(Integer.parseInt(prefix) + 1);
                if (newPrefix.length() > prefix.length()) {
                    return nextPalindrome(originalNumber, candNumber+1);
                } else {
                    String toBeReverse = newPrefix.substring(0, (newPrefix.length() - 1));
                    suffix = reverse(toBeReverse);
                    return nextPalindrome(originalNumber, toInt(newPrefix, suffix));
                }
            }

        } else {
            // even
            prefix = nStr.substring(0, strLen/2);
            // the key here is reversing the prefix
            suffix = reverse(prefix);

            int candNumber = toInt(prefix, suffix);
            if (originalNumber < candNumber) {
                return candNumber;
            } else {
                String newPrefix = Integer.toString(Integer.parseInt(prefix) + 1);
                if (newPrefix.length() > prefix.length()) {
                    return nextPalindrome(originalNumber,newNumber+1);
                } else {
                    suffix = reverse(newPrefix);
                    return toInt(newPrefix, suffix);
                }
            }
        }*/

        endIndexForPrefix = (isOdd) ? (strLen/2)+1 : (strLen/2);

        prefix = nStr.substring(0, endIndexForPrefix);

        endIndexForReverse = (isOdd) ? (prefix.length() -1) : prefix.length();
        suffix = reverse(prefix.substring(0, endIndexForReverse));
        int candNumber = toInt(prefix, suffix);
        if (originalNumber < candNumber) {
            return candNumber;
        } else {
            String newPrefix = Integer.toString(Integer.parseInt(prefix) + 1);
            if (newPrefix.length() > prefix.length()) {
                return nextPalindrome(originalNumber, candNumber+1);
            } else {
                int newPrefixEndIndex = (isOdd) ? newPrefix.length() - 1 : newPrefix.length();
                String toBeReverse = newPrefix.substring(0, newPrefixEndIndex);
                suffix = reverse(toBeReverse);
                return nextPalindrome(originalNumber, toInt(newPrefix, suffix));
            }
        }

    }

    private static String reverse(String str) {
        char[] charArr = str.toCharArray();

        int right = charArr.length-1;

        for (int i = 0; i < charArr.length/2; i++) {
            char tmp = charArr[i];
            charArr[i] = charArr[right];
            charArr[right] = tmp;
            right--;
        }

        return new String(charArr);
    }

    private static int toInt(String prefix, String suffix) {
        return Integer.parseInt(prefix + suffix);
    }
}
