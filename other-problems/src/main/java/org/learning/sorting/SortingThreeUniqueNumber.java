package org.learning.sorting;

import org.common.ArrayUtils;
import org.testng.Assert;

import java.util.Arrays;

/**
 * Given an array of integers where each value can be one of the three values {0,1,2}
 *
 * Sort them using in-place approach
 *
 * Example:
 *   {2,1,0,1,0}  => {0,0,1,1,2}
 *
 *   {0,0,0} => {0,0,0}
 *   {1,1,1} => {1,1,1}
 *
 * Approach:
 *   * Simplify the problem - WHAT IF THERE ARE ONLY TWO UNIQUE VALUES {0,1}
 *      * In this case, we can just keep two pointers - left and right
 *      * Left would move from left to right, and right would move from right to left
 *      * 1) For left side, we are looking for value > 0, and stop when it gets there
 *      * 2) For right side, we are looking for < 1, and stop when it gets there
 *      * 3) If left < right, then swap
 *      * Repeat step 1

 * What is the runtime for this?
 *   * Worse case scenarios, when all values are the same.
 *     * We iterate through all of them once
 *     * Then iterate through them again
 *     * So O(2n) ==> O(n)
 */
public class SortingThreeUniqueNumber {

    public static void main(String[] args) {

        test(ArrayUtils.randomArray(10, 2));
        test(ArrayUtils.randomArray(10, 2));
        test(ArrayUtils.randomArray(10, 2));
        test(ArrayUtils.randomArray(10, 2));

        test(new int[] {2,2,2} );
        test(new int[] {0,0,0} );
        test(new int[] {1,1,1} );

        test(new int[] {0} );
        test(new int[] {1} );
        test(new int[] {2} );
    }

    private  static void test(int[] input) {
        System.out.println("********* test ********");
        System.out.println("before: " + Arrays.toString(input));
        sortInPlace(input);
        System.out.println("after: " + Arrays.toString(input));

        assertIncreasingOrder(input);
        System.out.println();
    }

    private static void sortInPlace(int[] input) {
        int leftIdx = 0;
        int rightIdx = input.length - 1;

        while (leftIdx < rightIdx) {

            // increment leftIdx while value is 0
            while (leftIdx < input.length && input[leftIdx] == 0) {
                leftIdx++;
            }

            // decrement rightIdx while value >= 1
            while (rightIdx >= 0 && input[rightIdx] > 0) {
                rightIdx--;
            }

            // only swap when needed
            if (leftIdx < rightIdx) {
                ArrayUtils.swap(input, leftIdx, rightIdx);
            }
        }

        // when we exit the loop, leftIdx == rightIdx;
        leftIdx = rightIdx +1;
        rightIdx = input.length - 1;

        while (leftIdx < rightIdx) {

            // increment leftIdx while value is 0
            while (leftIdx < input.length && input[leftIdx] == 1) {
                leftIdx++;
            }

            // decrement rightIdx while value >= 1
            while (rightIdx >= 0 && input[rightIdx] == 2) {
                rightIdx--;
            }

            // only swap when needed
            if (leftIdx < rightIdx) {
                ArrayUtils.swap(input, leftIdx, rightIdx);
            }
        }
    }

    private static void assertIncreasingOrder(int[] input) {
        // [0, 0, 1, 2, 2]
        // make sure current and previous values are either
        // 1) same or prev < current
        // 2) mot prev > current

        for (int i = 1; i < input.length; i++) {
            Assert.assertFalse(input[i-1] > input[i]);
        }
    }

}
