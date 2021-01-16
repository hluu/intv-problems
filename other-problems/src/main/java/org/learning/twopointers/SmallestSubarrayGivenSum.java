package org.learning.twopointers;

import org.testng.Assert;

import java.util.Arrays;

/**
 * Given an array of integers, return the smallest sub-array size that
 * is equal or greater than the given sum
 *
 * For example:
 *  input: [4,2,2,7,8,1,2,8,1,0] and given is 8
 *  output: 2  [7,8]
 */
public class SmallestSubarrayGivenSum {

    public static void main(String[]  args) {
        System.out.println("SmallestSubarrayGivenSum.main");
        test(new  int[] {4,2,2,7,8,1,2,8,1,0}, 8, 1);
        test(new  int[] {4,2,2,7,8,1,2,8,1,0}, 7, 1);
        test(new  int[] {4,2,2,7,8,1,2,8,1,0}, 6, 1);
        test(new  int[] {4,2,2,7,8,1,2,8,1,0}, 15, 2);
        test(new  int[] {4,2,2,7,8,1,2,8,1,0}, 16, 3);
        test(new  int[] {4,2,2,7,8,1,2,8,1,0}, 35, 9);
        test(new  int[] {4,2,2,7,8,1,2,8,1,0}, 34, 8);
        test(new  int[] {4,2,2,7,8,1,2,8,1,0}, 10000000, Integer.MAX_VALUE);
    }

    private static void test(int[] input, int sum, int expected)  {
        System.out.printf("input: %s,  sum: %d\n", Arrays.toString(input),
                sum);

        int actual = smallestSubArray(input, sum);
        System.out.printf("expected: %d, actual: %d\n", expected, actual);
        System.out.println();

        Assert.assertEquals(actual, expected);
    }

    /**
     * Using 2-pointer approach to maintain a dynamic sized window (start, end)
     * - expand window while window sum is less than target sum
     *   - once that condition is met
     *     - while window sum is greater than or equal target sum
     *       - record the window size
     *
     *
     * @param input
     * @param sum
     * @return
     */
    private static int smallestSubArray(int[]  input, int sum) {
        int runningSum = 0;
        int startW = 0;
        int windowSize = Integer.MAX_VALUE;

        for (int endW = 0; endW < input.length; endW++) {
            runningSum += input[endW];
            // can we do better by reducing the window size with moving the startW to the right side
            while (runningSum >= sum) {
                windowSize = Math.min(windowSize, endW - startW + 1);
                runningSum -= input[startW++];
            }

        }

        return windowSize;
    }
}
