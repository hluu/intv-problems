package org.learning.numbers;

import java.util.Arrays;

/**
 * Created by hluu on 10/18/17.
 *
 * Give a decimal number, determin if it is digits are palindrome
 *
 * For example:
 *  * 1234321, 1221.  These numbers are considered palindrome.
 *  * 12345678, 1231. These numbers are considered not palindrome.
 *
 * Approach:
 *  1) Convert the number into a list of digits. And then walk from both ends
 *     into the middle.  Make sure to handle odd and even # of digits.
 *  2) If the the digits are palindrome, then two lists should be identical
 *     * we can reduce down to 1 list and walk from both ends.
 *
 *
 */
public class NumberPalindrome {
    public static void main(String[] args) {
        System.out.println(NumberPalindrome.class.getName());

        test(1);
        test(12);
        test(1221);
        test(1234321);
    }

    private static void test(int n) {
        int[] digits = convertNumberToDigits(n);
        System.out.printf("%d => %s\n", n, Arrays.toString(digits));
    }

    private static int[] convertNumberToDigits(int n) {
        int size = 0;
        int tmp = n;

        while (tmp > 0) {
            tmp = tmp / 10;
            size++;
        }

        int[] digits = new int[size];

        int idx = size - 1;
        tmp = n;
        while (idx >= 0) {
            digits[idx] = tmp % 10;
            tmp = tmp / 10;
            idx = idx - 1;
        }

        return digits;
    }
}
