package org.learning.twopointers;

import org.testng.Assert;

import java.util.Arrays;

/**
 * Given an unsorted array of integers and a fixed window size of k,
 * Return the maximum sum.
 *
 * Example:
 *    0  1  2  3  4  5  6  7  8  9
 *   ------------------------------
 *   [4, 2, 1, 7, 8, 1, 2, 8, 1, 0]
 */
public class MaxSumSubArray {
    public static void main(String[] args) {
        System.out.println("MaxSumSubArray.main");

        test(new int[]{1,2,3,4,5,6}, 3, 15);
        test(new int[]{1,2,-3,-4,4,5,-7}, 3, 5);
        test(new int[]{4, 2, 1, 7, 8, 1, 2, 8, 1, 0}, 3, 16);
    }

    private static void test(int[] input, int k, int expected) {
        System.out.println("");
        System.out.printf("input: %s, k: %d\n", Arrays.toString(input), k);

        int actual = maxSumSubArray(input, k);
        System.out.printf("expected: %d, actual: %d\n", expected, actual);

        Assert.assertEquals(actual, expected);
    }

    private static int maxSumSubArray(int[] input, int k) {
        int maxSum = Integer.MIN_VALUE;
        int runningSum = 0;

        for (int i = 0; i < k; i++) {
            runningSum += input[i];
        }
        maxSum = Math.max(runningSum, maxSum);

        // sliding window of size k and slide by 1
        // by removing one and adding one
        for (int i = k; i < input.length; i++) {
            runningSum -= input[i-k];  // remove the number outside of the window
            runningSum += input[i];    // add the new one into the window
            maxSum = Math.max(runningSum, maxSum);
        }

        return maxSum;
    }
}
