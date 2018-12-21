package org.learning.dp;

/**
 * Given an sorted array, find the number of subsequences that are added to given
 * target.
 *
 * For example: {2,4,6,10}, target = 16
 * Output: 2 => {2,4,10}, {6,10}
 *
 * Questions to ask:
 * 1) Can have negative numbers? < 0
 * 2) Can have duplicated numbers?
 * 3) Can have value of 0?
 * 4) Can target be 0 value?
 *
 * Approach:
 * 1) Brute force: create all possible subsequences (2^n) and see if any added up to
 *    target value
 * 2)
 *
 */
public class NumSubsequenceSum {

    public static void main(String[] args) {
        System.out.println(NumSubsequenceSum.class);
    }

    private static int topDownBruteforce(int[] arr, int target) {
        return -1;
    }
}
