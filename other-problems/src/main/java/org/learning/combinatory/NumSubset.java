package org.learning.combinatory;

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
        test(a,k);

        int[] a2 = {1,3,-2, 4,5, 2};
        int k2 = 5;
        test(a2,k2);

        int[] a3= new int[]{1, 3, 5, 7, 2, -2};
        int k3 = 8;
        test(a3, k3);

    }

    private static void test(int[] array, int targetSum) {
        System.out.println("\n ******* Running tests **********" );

        System.out.printf("a: %s, k: %d\n",
                Arrays.toString(array), targetSum);

        System.out.println("=====> Using running sum");

        int count1 = numSubsetsUsingSum(array, targetSum, 0, 0, 0);
        System.out.printf("count: %d\n", count1);

        System.out.println("=====> Using sum subtraction");
        int count2 = numSubsetsUsingSubtraction(array, targetSum, 0);
        System.out.printf("count: %d\n", count2);


        System.out.println("=====> Using findSubSetUsingLoopWithRecur");
        findSubSetUsingLoopWithRecur(array, targetSum);


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

    private static int findSubSetUsingLoopWithRecur(int[] arr, int target) {
        List<List<Integer>> collector = new ArrayList<>();


        findSubSetUsingLoopWithRecurHelper(arr, 0,  target, collector,
                new ArrayList<Integer>());

        for (List<Integer> list : collector) {
            System.out.println("list: " + list);
        }
        return collector.size();
    }

    private static void findSubSetUsingLoopWithRecurHelper(int[] arr,
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

            findSubSetUsingLoopWithRecurHelper(arr, i+1, remaining-arr[i],
                    collector, listSoFar);
            // remove last element, what was added earlier to
            listSoFar.remove(listSoFar.size() - 1);
        }
    }

}
