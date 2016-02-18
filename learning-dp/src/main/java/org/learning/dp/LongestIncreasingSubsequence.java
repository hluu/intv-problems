package org.learning.dp;

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
 *      {3,4-1,5,8,2,3,12,7,9,10} => {-1,2,3,7,9,10}  with size of 6
 *
 *      {3,4,-1,0,6,2,3} => {-1,0,2,3} with size of 4
 *
 *      {3,2,6,4,5,1} => {2,4,5}  with size of 3
 *      What are all the increasing subsequences?
 *        {3,6},{2,6},{2,4,5},{4,5},{1}
 *
 *      {9,5,2,8,7,3,1,6,4}  => {2,3,6} or {2,6,4}
 *
 *      {7,3,8,4,2,6} => {3,4,6} with size of 3
 *
 * Approach:
 *  For each value in the array, find all the subsequences starting with that value.
 *
 *  Let LIS[i] be the longest increasing subsequence at 0 to i
 *
 *  What are subproblems?
 *    * Can we solve for LIS[0 to i-1] and extend to LIS[i]?
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
}
