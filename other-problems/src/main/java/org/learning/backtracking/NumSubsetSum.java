package org.learning.backtracking;

import java.util.Arrays;

/**
 * Created by hluu on 12/10/16.
 *
 * Problem: Given a set of numbers (in an integer array), this problem is
 * to find subsets whose elements add up to a specific target number.
 * For example,
 *  {1, 3, 4, 5} whose elements add up to 5.
 *  Output: {1, 4}, {5}
 *
 * By contrast, there is no subset of {1, 3, 4, 5} whose elements add up to 11.
 *
 * numSubsets(arr, 5) => 2, numSubsets(arr, 11) => 0
 *
 * Approach:
 *  Brute force:
 *      * subsets are not contiguous
 *      * subset can contain 2 or more numbers
 *      * for element either include or exclude in the search
 *          * include f(a, target-element, index+1);
 *          * exclude f(a, target, index+1)
 *          * based case is when it is 0 or negative
 *
 *  Runtime analysis for brute force
 *      * Since there are two branches at each index,
 *      * so it becomes O(2^n), n is # of elements in the array
 *
 *
 *
 */
public class NumSubsetSum {
    public static void main(String[] args) {

        System.out.println("NumSubsetSum.main");

        int[] arr = {1, 2, 3, 4, 5,6};
        numSubsets(arr, 7, 0, "");


        int[] arr2 = {3, 34, 4, 12, 5, 2};
        System.out.printf("****** numSubsets: %s\n", Arrays.toString(arr2));
        numSubsets(arr2, 9, 0, "");

        numSubsetsDP(arr, 7);
    }

    private static int counter = 1;

    private static String numSubsets(int[] array, int target, int idx, String soFar) {


        System.out.printf("counter: %d, target: %d, idx: %d, soFar: %s\n",
                counter++, target, idx, soFar);

        if (target == 0) {
            return soFar;
        }

        if (target < 0 || idx >= array.length) {
            return null;
        }

        String result = numSubsets(array, target - array[idx],
                idx+1, soFar + ", " + array[idx]);

        if (result != null) {
            System.out.printf("result: %s\n", result);
        }

        result = numSubsets(array, target, idx+1, soFar);

        if (result != null) {
            System.out.printf("result: %s\n", result);
        }

        return result;
    }

    /**
     * This approach is bottom up and using table to record decisions
     *
     *
     * @param array
     * @param target
     * @return
     */
    private static boolean numSubsetsDP(int[] array, int sum) {
        boolean[][] state = new boolean[sum+1][array.length+1];

        for (int i = 0; i <= array.length; i++) {
            state[0][i] = true;
        }

        for (int i = 1; i <= sum; i++) {
            state[i][0] = false;
        }

        printTable(state, array, sum);

        for (int sumValue = 1; sumValue <= sum; sumValue++) {
            for (int idx = 1; idx <= array.length; idx++) {
                state[sumValue][idx] = state[sumValue][idx-1];

                if (sumValue >= array[idx-1]) {
                    state[sumValue][idx] = state[sumValue][idx] ||
                            state[sumValue - array[idx-1]][idx-1];
                }
            }
        }

        printTable(state, array, sum);

        return false;
    }

    private static void printTable(boolean[][] state,
                                   int[] array,
                                   int sum) {

        System.out.printf("===================\n");
        for (int i = 0; i <= sum; i++)
        {
            for (int j = 0; j <= array.length; j++) {
                System.out.printf("%6b", state[i][j]);
            }
            System.out.printf("\n");
        }
    }
}
