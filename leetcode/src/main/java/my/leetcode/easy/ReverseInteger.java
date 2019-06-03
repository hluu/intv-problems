package my.leetcode.easy;

import org.testng.Assert;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 *
 * Input: 123
 * Output: 321
 * Example 2:
 *
 * Input: -123
 * Output: -321
 * Example 3:
 *
 * Input: 120
 * Output: 21
 *
 * Note:
 * Assume we are dealing with an environment which could only store
 * integers within the 32-bit signed integer range: [−2^31,  2^31 − 1].
 * For the purpose of this problem, assume that your function returns 0
 * when the reversed integer overflows.
 *
 * Observation:
 * - dealing with negative number
 * - dealing with last 0 digit (0 in the middle is OK, nothing special handling)
 * - dealing with potential overflow
 *
 * Approach:
 * - if less than zero, note that it is a negative number
 *   - negate the number to make it positive
 * - at this point on, we are dealing with positive number
 * - maintain a variable for the reversed number => result
 * - steps
 *   - while (input > 0)
 *     - int mod = input % 10
 *     - if (first digit) and value is 0, then do nothing
 *       else result = result * 10 + mod result
 *     - input = input / 10
 * if (!negative) return result else -result
 */
public class ReverseInteger {
    public static void main(String[] args) {
        System.out.println(ReverseInteger.class.getName());

        System.out.println("max positive: " + Integer.MAX_VALUE);
        System.out.println("max min: " + Integer.MIN_VALUE);

        test(0, 0);
        test(123, 321);
        test(-123, -321);
        test(120, 21);
        test(Integer.MAX_VALUE, 0);
        test(Integer.MIN_VALUE, 0);
        test(1463847412, 2147483641);
    }

    private static void test(int input, int expected) {
        System.out.println("\ninput: " + input);

        int actual = reverseNumber(input);

        System.out.printf("expected: %d, actual: %d\n", expected, actual);

        Assert.assertEquals(actual, expected);
    }

    private static int reverseNumber(int input) {
        if (input == 0 || input == Integer.MIN_VALUE) {
            return  0;
        }

        boolean isNegative = (input < 0);

        if (input < 0) {
            input = -input;
        }

        long result = 0;

        while (input > 0) {
            int lastDigit = input % 10;
            result = result * 10 + lastDigit;
            if (result > Integer.MAX_VALUE) {
                return 0;
            }
            input = input / 10;
        }

        return (!isNegative) ? (int)result : (int)-result;

    }
}
