package org.learning.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.IntBinaryOperator;

/**
 *
 *
 * Problem statement:
 *  Give an array of unsorted integers, find the longest increasing subsequence in the array.
 *  A[0...n]
 *
 *  A subsequence is not necessary contiguous
 *
 *  For example:
 *      {3,4,-1,5,8,2,3,12,7,9,10} => {-1,2,3,7,9,10}  with size of 6
 *
 *      {3,4,-1,0,6,2,3} => {-1,0,2,3} with size of 4
 *
 *      {3,2,6,4,5,1} => {2,4,5}  with size of 3
 *
 *      What are all the increasing subsequences?
 *        {3,6},{2,6},{2,4,5},{4,5},{1}
 *
 *      {9,5,2,8,7,3,1,6,4}  => {2,3,6} or {2,3,4}
 *
 *      {7,3,8,4,2,6} => {3,4,6} with size of 3
 *
 *      {2,4,3,5,1,7,6,9,8} => {2,3,5,6,8} size of 5
 *
 *      {5,2,8,6,3,6,9,7} => {2,3,6,9} size of 4
 *
 * Approach:
 *  * For each value in the array, find all the subsequences starting with that value.
 *  * What information about first n-1 elements would help us to find the answer of element at n?
 *    * The length of longest increasing subsequence in s1,s2,..,s(n-1) would be useful
 *    * Need to know the length of the longest sequence that any possible value for Sn can extend
 *    * The LIS contain nth element will be formed by appending it to the LIS to the left of n that
 *      ends on a number small than s(n)
 *    * What auxiliary information will we need to store to reconstruct the actual sequence
 *
 *  Let LIS[i] be the longest increasing subsequence at 0 to i
 *
 *  What are subproblems?
 *    * Can we solve for LIS[0 to i-1] and extend to LIS[i]?
 *    * Given a node, we need to know the longest increasing subsequence of previous nodes to left
 *
 *  Have an array with same length as the input array to store the max subsequence at each index
 *
 *  Recurrence:
 *      LIS[j] = 1 + max(LIS[i] : i < j and si < sj}
 *
 *  What is the runtime? O(N^2)
 *
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        System.out.println("LongestIncreasingSubsequence.main");

        int arr[] = {3,2,6,4,5,1,3,5,7,9,11,4,13};
        //int arr[] = {3,4,-1,5,8,2,3,12,7,9,10};
        //int arr[] = {2,4,3,5,1,7,6,9,8};
        //int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60, 80 };
        //int arr[] = {9,5,2,8,7,3,1,6,4};

        int arr10[] = {5,2,8,6,3,6,9,7};

        ///System.out.println("LIS of: " + Arrays.toString(arr) + " is " + lis(arr));

        //System.out.println("LIS of: " + Arrays.toString(arr) + " is " + lis2(arr));

        test(arr);
        test(arr10);
    }

    private static void test(int[] arr) {
        System.out.printf("==== test - arr: %s\n ====", Arrays.toString(arr));
        System.out.println("LIS is " + lis(arr));
    }


    /**
     *  l[i] - LIS of D that ends at i
     *  l[0] = {D[0]}
     *
     *  Looking at every l[j] where j is less than i
     *  l[i] = Max(l[j] | j < i, (the tail of every l[j] < D[i]) + D[i]
     *
     *  Example: {3,2,6,4,5,1}
     *      l[0] = {3}
     *      l[1] = {2}
     *      l[2] = {2,6}
     *      l[3] = {2,4}
     *      l[4] = {2,4,5}
     *      l[5] = {1}
     *
     *
     * @param arr
     * @return
     */
    public static int lis2(int[] arr) {
        List<List<Integer>> lics = new ArrayList<>(arr.length);

        // initialization
        List<Integer> first = new ArrayList<>();
        first.add(arr[0]);
        lics.add(first);

        // going from left to right
        for (int i = 1; i < arr.length; i++) {
            // tmp for storing list with max size
            List<Integer> listWithMaxSize = null;
            // going from right to left
            for (int j = i-1; j >= 0; j--) {
                //System.out.println("j: " + j);
                List<Integer> tmp2 = lics.get(j);

                // if arr[i] > the last element of lics[j]
                if (arr[i] > tmp2.get(tmp2.size()-1)) {
                    if (listWithMaxSize == null ||
                            (tmp2.size() > listWithMaxSize.size()))  {
                        listWithMaxSize = tmp2;
                    }
                }
            }

            // clone list of not null
            if (listWithMaxSize == null) {
                listWithMaxSize = new ArrayList<>();
            } else {
                listWithMaxSize = new ArrayList<>(listWithMaxSize);
            }
            // don't forget to extend
            listWithMaxSize.add(arr[i]);
            lics.add(listWithMaxSize);
        }

        List<Integer> result = lics.get(0);
        for (int i = 1; i < lics.size(); i++) {
            if (lics.get(i).size() > result.size()) {
                result = lics.get(i);
            }
        }

        System.out.printf("LCS2: %s\n", result );
        return result.size();
    }


    public static int lis(int[] inputArr) {
        int lis[] = new int[inputArr.length];  // keep the LIS at i
        int predecessor[] = new int[inputArr.length];

        // initialization
        for (int i = 0; i < lis.length; i++) {
            lis[i] = 1;
            predecessor[i] = -1;

        }

        for (int i = 0; i < inputArr.length; i++) {
            int max = 0;
            // going from i to 0 (backward or previous LIS)
            for (int j = i-1; j >= 0; j--) {
                // if value at i can extend the sequence
                // and max LIS for all these smaller values
                if (inputArr[i] > inputArr[j] && lis[j] > max) {
                    max = lis[j];
                    predecessor[i] = j;
                }
            }

            lis[i] = max + 1;

        }

        if (inputArr[1] > inputArr[0]) {
            predecessor[0] = 0;
        }

        System.out.println("LIS: " + Arrays.toString(lis));
        System.out.println("predecessors: " + Arrays.toString(predecessor));

        List<Integer> lisValues = getElements(inputArr, predecessor);


        System.out.println("(" + lisValues.toString() + ")");

        int maxLIS = 0;
        for (int i = 0; i < lis.length; i++) {
            if (lis[i] > maxLIS) {
                maxLIS = lis[i];
            }
        }
        return maxLIS;
    }

    private static List<Integer> getElements(int[] arr, int[] predecessor) {
        LinkedList<Integer> lisValues = new LinkedList<>();
        int index = predecessor.length-1;
        if (predecessor[index] != -1) {
            lisValues.add(arr[index]);
            index = predecessor[index];
        } else {
            index--;
        }


        // from the end to 0
        while (index > 0) {
            if (predecessor[index] != -1) {
                lisValues.addFirst(arr[index]);
                index = predecessor[index];
            } else {
                index--;
                index = predecessor[index];
            }
        }
        if (index == 0) {
            lisValues.addFirst(arr[index]);
        }
        return lisValues;
    }


}
