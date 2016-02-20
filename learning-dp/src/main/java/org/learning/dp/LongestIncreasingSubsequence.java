package org.learning.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hluu on 2/5/16.
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
 *
 *
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        System.out.println("LongestIncreasingSubsequence.main");

        //int arr[] = {3,2,6,4,5,1};
        //int arr[] = {3,4,-1,5,8,2,3,12,7,9,10};
        //int arr[] = {2,4,3,5,1,7,6,9,8};
        int arr[] = {9,5,2,8,7,3,1,6,4};

        System.out.println("list of: " + Arrays.toString(arr) + " is " + lis(arr));
    }

    public static int lis(int[] arr) {
        int lis[] = new int[arr.length];
        int predecessor[] = new int[arr.length];

        for (int i = 0; i < lis.length; i++) {
            lis[i] = 1;
            predecessor[i] = -1;

        }

        for (int i = 0; i < arr.length; i++) {
            int max = 0;
            for (int j = i-1; j >= 0; j--) {
                // if value at i can extend the sequence
                // and max LIS for all these smaller values
                if (arr[i] > arr[j] && lis[j] > max) {
                    max = lis[j];
                    predecessor[i] = j;
                }
            }
            //if (max != Integer.MIN_VALUE) {
                lis[i] = max + 1;
            //}
        }

        if (arr[1] > arr[0]) {
            predecessor[0] = 0;
        }

        System.out.println("LIS: " + Arrays.toString(lis));
        System.out.println("predecessors: " + Arrays.toString(predecessor));

        List<Integer> lisValues = getElements(arr, predecessor);


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
        List<Integer> lisValues = new ArrayList<>();
        int index = predecessor.length-1;
        if (predecessor[index] != -1) {
            lisValues.add(arr[index]);
            index = predecessor[index];
        } else {
            index--;
        }


        while (index > 0) {
            if (predecessor[index] != -1) {
                lisValues.add(arr[index]);
                index = predecessor[index];
            } else {
                index--;
                index = predecessor[index];
            }
        }
        if (index == 0) {
            lisValues.add(arr[index]);
        }
        return lisValues;
    }


}
