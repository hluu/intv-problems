package org.learning.numbers;


import org.testng.Assert;

import java.util.Arrays;

/**
 * Given an array of numbers that representing labels, find the smallest number of
 * segments given that a label can appear only one segment
 *
 *
 * For example:
 * arr = {1,2,3} ==> 3
 * arr = {1,2,3,1} ==> 1
 * arr = {1,2,3,1,2} ==> 1
 * arr = {1,2,3,1,2,3} ==> 1
 * arr = {1,2,1,3} ==> 2
 *
 * arr = [1,2,1,3,4,1,2,5,8,9,8,12,8] => 3 (1,2,1,3,4,1,2), (5), (8,9,8,12,8)
 *
 * Approach (Algorithm):
 * * For each value at index i
 * * See if it exists at another location j by looking from the end
 *   * if not, bump up segment count
 *   * if exists
 *     * see if any of the values between i and j exists beyond j
 *       * if exists, update j to new index
 *       * if not exist, check for next element i+1
 *         * stop when i == j
 *
 */
public class CountingSegments {

    public static void main(String[] args) {
        System.out.printf("%s\n", CountingSegments.class.getName());

       test(new int[] {1,2,3}, 3);
        test(new int[] {1,2,3,1}, 1);
        test(new int[] {1,2,3,1,2}, 1);


        test(new int[] {1,2,1,3,4,2,5}, 2);
        test(new int[] {1,2,1,3,4,1,2}, 1);
        test(new int[] {1,2,1,3,4,1,2,5,8,9,8,12,8}, 3);
        test(new int[] {1,2,1,3,4,4,5}, 4);

        test(new int[] {1,2,1,3,4,2,3,4}, 1);
        test(new int[] {8,1,2,1,3,4,2,3,1,4}, 2);

    }

    private static void test(int[] inputs, int expectedSegCnt) {
        System.out.println("===== " + Arrays.toString(inputs));
        int actualSegCnt = findSegment(inputs);

        System.out.printf("expected: %d, actual: %d\n", expectedSegCnt, actualSegCnt);
        System.out.println();

        Assert.assertEquals(actualSegCnt, expectedSegCnt);
    }

    public static int findSegment(int[] inputs) {

        int segCount = 0;
        int idx = 0;
        while (idx < inputs.length) {

            int value = inputs[idx];
            int right = findElm(value, inputs, idx);

            if (right == -1) {
                segCount++;
                idx++;
            } else {
                // extending the segment
                // for all the values between index i and right
                // look for them in starting right+1
                // if found then extend the right boundary
                int left = idx+1;

                while (left < right) {
                    value = inputs[left];
                    int tmp = findElm(value, inputs, left);
                    if (tmp != -1 && tmp > right) {
                        right = tmp;
                    }
                    left++;
                }

                segCount++;

                idx = left+1;
            }

        }

        return segCount;
    }

    /**
     * Find the index of value from the end and stop at stopAt
     * @param value
     * @param input
     * @param stopAt
     * @return elm index if found, otherwise -1
     */
    private static int findElm(int value, int[] input, int stopAt) {
        for (int i = input.length-1; i > stopAt; i--) {
            if (input[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
