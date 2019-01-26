package org.learning.numbers;

import org.testng.Assert;

import java.util.Arrays;

/**
 *
 * Give a decimal number, determine if it is digits are palindrome
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

        test(1, true);
        test(12, false);
        test(1221, true);
        test(723427, false);
        test(1234321, true);

        test(5005, true);
        test(101, true);
    }

    private static void test(int n, boolean expected) {
        int[] digits = convertNumberToDigits(n);
        System.out.printf("%d => %s\n", n, Arrays.toString(digits));

        boolean actual = isPalindrome(n);

        System.out.printf("num: %d, pal: %b\n", n, actual);

        Assert.assertEquals(actual, expected);
        System.out.println();

    }

    /**
     * This one using math.
     * 1) Find out the number digits
     * 2) Create an array with the size as the number digits
     * 3) Copy each digit into an array
     *
     *
     * @param input
     * @return
     */
    private static int[] convertNumberToDigits(int input) {
        int size = 0;
        int tmpNum = input;

        // compute the number of digits
        while (tmpNum > 0) {
            tmpNum = tmpNum / 10;
            size++;
        }

        int[] digits = new int[size];

        int idx = size - 1;  // zero based index (0.. size-1)
        tmpNum = input;
        // least significant digit at the end of the array
        while (idx >= 0) {
            digits[idx] = tmpNum % 10;
            tmpNum = tmpNum / 10;
            idx = idx - 1;
        }

        return digits;
    }

    /**
     * This approach doesn't need to convert to string, but simply
     * using math to extract the MSD (most significant digit) and
     * LSD (least significant digit) and compare them.
     *
     * @param num
     * @return
     */
    private static boolean isPalindrome(int num) {

        // for example 12321
        //             1232
        //              232
        //               3

        // for example 123321
        //              2332
        //               33

        // what if it is 5005

        // 1221

        int numDigit = 0;
        int tmpNum = num;
        while (tmpNum > 0) {
            numDigit++;
            tmpNum = tmpNum / 10;
        }

        int numTimes = numDigit / 2;

        while (numTimes > 0) {

            int msd = getMSD(num, numDigit);
            int lsd = getLSD(num);

            if (msd != lsd) {
                return false;
            }

            // remove LSD
            num = num / 10;
            numDigit--;

            // remove the MSD
            num = shaveOffMSD(msd, num, numDigit);
            numDigit--;

            numTimes--;
        }
        // if we get here, then it means input is a palindrome
        return true;
    }

    private static int shaveOffMSD(int msd, int num, int numDigit) {
        // 425 => 25
        // 425 - 400 = 25
        // 400

        int mask = (int)Math.pow(10, numDigit-1);
        mask = mask * msd;

        return num - mask;
    }

    private static int getLSD(int num) {
        return num % 10;
    }

    /**
     * To get the MSD, it needs the # of digits
     *
     * @param num
     * @param numDigit
     * @return
     */
    private static int getMSD(int num, int numDigit) {
        // 423 ==> 4
        // 423/100 => 4


        int mask = (int)Math.pow(10, numDigit-1);

        return num / mask;
    }
}
