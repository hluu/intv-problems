package org.learning.numbers;

/**
 * Created by hluu on 1/23/16.
 * <p>
 * Problem statement:
 * Given an integer array nums, return the number of range sums that
 * lie in [lower, upper] inclusive.
 * <p>
 * Range sum S(i, j) is defined as the sum of the elements in nums
 * between indices i and j (i ≤ j), inclusive.
 * <p>
 * A naive algorithm of O(n2) is trivial
 * <p>
 * For example:
 * Given nums = [-2, 5, -1], lower = -2, upper = 2,
 * Return 3.
 * The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
 * <p>
 * Approach:
 *   * Notice the numbers can be both positive and negative, so the sum will increase as well
 *     as decrease.
 *   * Can we solve this problem by iterating through the array once - O(n)?
 *   * As we iterate to a number
 *     1) If lower <= number + runningSum =< upper, then include that number in range
 *     2) If not, close that range if there was a range, start a new range
 *     3)
 */
public class CountRangeNumbers {

    public static void main(String[] args) {
        int[] input = {-2,5,1};



        System.out.println("numRanges: " + countRangeSum(input, -2, 2));

        int[] input2 = {-3,5,4,-7,1,6};
        System.out.println("numRanges: " + countRangeSum(input2, 0, 7));

    }


    public static int countRangeSum(int[] nums, int lower, int upper) {
        int otherSum = 0;
        int otherSumRange = -1;

        int partialSum = 0;
        int partialRange = -1;

        int numRanges = 0;

        for (int i = 0; i < nums.length; i++) {
             int tmp = partialSum + nums[i];

             if (lower <= tmp && tmp <= upper) {
                 // between bounds then keep going
                 partialSum = tmp;
                 if (partialRange == -1) {
                     partialRange = i;
                 }
             } else {
                 if (partialRange >= 0) {
                     numRanges++;
                 }
                 // check to see if value at element is is between bounds or not
                 if (lower <= nums[i] && nums[i] <= upper) {
                     partialSum = nums[i];
                     partialRange = i;
                 } else {
                     // reset
                     partialSum = 0;
                     partialRange = -1;
                 }
             }
        }

        // need to handle the case of the last element
        if (lower <= partialSum && partialSum <= upper) {
            numRanges++;
        }
        return numRanges;
    }
}
