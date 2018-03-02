package org.learning.combinatory;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hluu on 6/29/17.
 *
 * Problem:
 *  Given a set of numbers, determine the number of subsets whose sum is
 *  equal to a given target sum.
 *
 * For example:
 *  a = {1,3,4,5} and k = 5
 *  Expecting: {1,4}, {5} ==> 2
 *
 *  a = {1,3,4,5} and k = 11
 *  Expecting: {} => 0
 *
 * Approach:
 *  A subset can contain any # elements and not necessary contiguous.
 *
 *  An array {1,3,4,5} has 4 elements, there are 2^4 (15) possible combinations.  If
 *  we run sum up the elements in each combination to see which ones would add up to
 *  given target sum.
 *
 *  Runtime: O(2^n)
 *
 *  For each element, one path to include and another path not to include it.
 *
 *
 *
 */
public class NumSubset {
    public static void main(String[] args) {
        System.out.printf("%s\n", NumSubset.class.getName());

        int[] a = {1,3,4,5};
        int k = 5;
        test(a,k, 2);

        int[] a2 = {1,3,-2, 4,5, 2};
        int k2 = 5;
        //test(a2,k2,6);

        int[] a3= new int[]{1, 3, 5, 7, 2, -2};
        int k3 = 8;
        //test(a3, k3, 6);

    }

    private static void test(int[] array, int targetSum, int expectedNumSubsequences) {
        System.out.println("\n ******* Running tests **********" );

        System.out.printf("a: %s, k: %d, expectedNumSubsequences: %d\n",
                Arrays.toString(array), targetSum, expectedNumSubsequences);

        System.out.println("=====> Using running sum");

        int count1 = numSubsetsUsingSum(array, targetSum, 0, 0, 0);
        System.out.printf("count: %d\n", count1);

        System.out.println("=====> Using sum subtraction");
        int count2 = numSubsetsUsingSubtraction(array, targetSum, 0);
        System.out.printf("count: %d\n", count2);


        System.out.println("=====> Using findSubSetUsingBackTrack");
        int count3 = findSubSetUsingBackTrack(array, targetSum);


        int[] result4 = new int[1];
        numPartitions(array, 0, targetSum, result4);

        System.out.printf("count1: %d, count2: %d, count3: %d, count4: %d\n",
                count1, count2, count3, result4[0]);

        Assert.assertEquals(count1, count2);
        Assert.assertEquals(count2, count3);
        Assert.assertEquals(count3, expectedNumSubsequences);


        int[] result5 = new int[1];
        backTrackApproach(array, targetSum, 0, 0, result5);
        System.out.println("=====> Using backTrackApproach: " + result5[0]);
        Assert.assertEquals(result5[0], expectedNumSubsequences);

    }


    /**
     * This version uses the running sum
     * @param array
     * @param target
     * @param index
     * @param runningSum
     * @param numSubSets
     * @return
     */
    private static int numSubsetsUsingSum(int[] array, int target, int index,
                                  int runningSum, int numSubSets) {

        // based case
        if (index >= array.length) {
            return numSubSets;
        }


        int thisSum = 0;
        if (runningSum + array[index] == target) {
            thisSum = 1;
        }

        int includeElm = numSubsetsUsingSum(array, target, index+1, runningSum + array[index],
                numSubSets);

        int notIncludeElm = numSubsetsUsingSum(array, target, index+1, runningSum, numSubSets);

        return thisSum + includeElm + notIncludeElm;

    }


    /**
     * This one uses subtraction.  Seems like
     *
     * @param array
     * @param remainingSum
     * @param index
     * @return
     */
    private static int numSubsetsUsingSubtraction(int[] array, int remainingSum, int index) {

        // based case
        if (index >= array.length) {
            return (remainingSum == 0) ? 1 : 0;
        }

       return (array[index] == remainingSum) ? 1 : 0 +
               numSubsetsUsingSubtraction(array, remainingSum - array[index],  index+1) +
               numSubsetsUsingSubtraction(array, remainingSum, index+1);

    }

    private static int findSubSetUsingBackTrack(int[] arr, int target) {
        List<List<Integer>> collector = new ArrayList<>();


        backTrackHelper(arr, 0,  target, collector,
                new ArrayList<Integer>());

        for (List<Integer> list : collector) {
            System.out.println("list: " + list);
        }
        return collector.size();
    }

    /**
     * Looks like this is a backtracking approach
     * @param arr
     * @param index
     * @param remaining
     * @param collector
     * @param listSoFar
     */
    private static void backTrackHelper(int[] arr,
                                        int index, int remaining,
                                        List<List<Integer>> collector,
                                        List<Integer> listSoFar) {

        // this must go before checking index in order to hand the last element
        if (remaining == 0) {
            collector.add(new ArrayList<>(listSoFar));
        }

        // base case, don't continue when index is at the end
        if (index >= arr.length) {
            return;
        }

        // start from index to build the following
        // 1, 3, 4, 5
        //    3, 4, 5
        //       4, 5
        //          5
        for (int i = index; i < arr.length; i++) {
            listSoFar.add(arr[i]);
            System.out.println("index: " + index + " listSoFar: " + listSoFar);
            backTrackHelper(arr, i+1, remaining-arr[i],
                    collector, listSoFar);
            // remove last element, what was added earlier to
            listSoFar.remove(listSoFar.size() - 1);
        }
    }

    /**
     * Trying out the back track approach:
     *
     * Input: {1, 3, 4, 5}
     *
     * backTrackApproach(arr, target, 0, 0, result)
     *
     * @param arr
     * @param target
     * @param currentSum
     * @param idx
     * @param result
     */
    private static void backTrackApproach(int[] arr, int target, int idx, int currentSum, int[] result) {

        if (currentSum == target) {
            result[0] += 1;
        }

        for (int i = idx; i < arr.length; i++) {
            backTrackApproach(arr, target, i+1, currentSum + arr[i], result);
        }
    }

    private static void numPartitions(int [] arr, int start, int target, int [] result) {
        if (target == 0) result[0]++;
        for(int i = start; i < arr.length; i++) {
            numPartitions(arr, i + 1, target - arr[i], result);
        }
    }

}
