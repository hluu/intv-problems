package org.learning.backtracking;

import java.util.Arrays;


/**
 *
 * Give an array of not sorted number.  Determine whether it is feasible to break the array into
 * K sub arrays (not necessary even size) whose sums are the same value.
 *
 * For example:
 *   [1,5,2,3,3,4,2] and k is 4 ==> [5], [2,3], [2,3], [4,1]
 *   [4,3,3,3,3] and k is 4 => NOT POSSIBLE
 *
 * Analysis:
 *  * This problem is asking whether it is possible, it doesn't ask for the number of possible ways
 *  * If it is possible, then the sum of all the sub arrays would be divisible by K
 *    * This invariant can be used to detect upfront whether it is possible or not
 *  * This fact is already stated, but worth to point out that the sums of all sub-arrays must be the same
 *    * We don't necessary know what the sum is, but we can calculate that and use it in validation logic
 *  * Runtime is k^n (number of elements in the array to the K)
 *  * This is a combination problem using backtrack algorithm
 *
 * Solutions:
 *  * We can use backtracking technique to solve this problem.
 *  * It seems overwhelming at first, because how do we know which number to place in which bucket.
 *    * When you have this sense, then the next possible thing to do is to try every possible way
 *  * The backtracking pattern has the following steps:
 *    * check to see if condition is met when the condition is met, if so, return true
 *    * go into a loop
 *      * pick next candidate and to try it out
 *      * recursively call
 *      * if succeeded, then return else undo the previous attempt
 *
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets
 */
public class EqualPartitions {

  static int runTime = 0;

  public static void main(String[] args) {
    System.out.printf("%s\n", EqualPartitions.class.getName());

    //int[] array = {4, 3, 2, 3, 5, 2, 1, 7}; int k = 4;
    //int[] array = {5, 4, 3, 2, 2};  int k = 2;
    //int[] array = {4, 3, 2, 3, 5, 2, 1}; int k = 4;

    //int[] array = {3, 5, 1, 3};  int k = 2;
    //int[] array = {1,3,6,9,10};  int k = 3;

    //int[] array = {3, 5, 1, 3};  int k = 3;
    int[] array = {4,3,2,3,5,2,1};  int k = 4;

    isSumDivisibleByK(array, k);

    System.out.printf("====> runtime: %d\n", runTime);

    //int[] array2 = [5, 4, 3, 2, 2], k = 2

    isSumDivisibleByK(new int[] {5, 4, 3, 2, 2}, 2);

  }

  private static boolean isSumDivisibleByK(int[] array, int k) {
    // calculate the sum
    int total = Arrays.stream(array).sum();
    // see if total sum is divisible by k
    boolean isPossible = (total % k) == 0;

    if (!isPossible) {
      System.out.printf("not possible to partition array: %s into  %d partitions",
              Arrays.toString(array), k);
      return false;
    }

    System.out.printf("input array: %s and k: %d\n", Arrays.toString(array), k);
    System.out.printf("isPossible: %b: target: %f\n", isPossible, (total * 1.0/ k));

    boolean result = false;

    // here is the key, the target sum is the sum divided by k
    int targetSum = total / k;
    int bucketSum[] = new int[k];
    result = foundEqualPartitions(array, bucketSum, 0, targetSum);
    System.out.printf("foundEqualPartitions: %b\n", result);

    return result;
  }


  /**
   * This the recursion with backtracking approach
   *  *) For each bucket
   *  *)  pick it and add a candidate to that bucket
   *      check if all the buckets have same value
   *      if so return true
   *      if else back out the current value
   *
   *
   * @param array
   * @param buckets
   * @param index
   * @param target
   * @return whether found all partitions have equal value
   */
  private static boolean foundEqualPartitions(int[] array, int[] buckets, int index, int target) {
    // the base case when index reaches end of the array
    if (index == array.length) {
      // determine if the value in all buckets are equivalent to the target value
      // or another way of stating is if the value in any bucket is not equivalent to target,
      // then false
      return arePartitionsEqual(buckets, target);
    }

    // ======== the main recursion logic  ====
    // pick next candidate
    int nextCandidate = array[index];

    // try next candidate in each bucket
    for (int i = 0; i < buckets.length; i++) {
      runTime++;

      // try it
      buckets[i] += nextCandidate;
      printBucketSum(buckets);

      // test it, if succeeded return return true
      int nextCandidateIndex = index + 1;
      if (foundEqualPartitions(array, buckets, nextCandidateIndex, target)) {
        return true;
      }

      // else undo the attempt, and move on to next bucket
      buckets[i] -= nextCandidate;
    }

    // if we got here, that means we didn't succeed
    return false;
  }

  private static boolean arePartitionsEqual(int[] buckets, int target) {
    for (int bucketSum : buckets) {
      // one inequality violates the invariant we are looking for
      if (bucketSum != target) {
        // fail fast when not equal
        return  false;
      }
    }
    return true;
  }

  private static void printBucketSum(int[] buckets) {
    StringBuilder buf = new StringBuilder();
    for (int sum : buckets) {
      buf.append(sum + " ");
    }
    System.out.println("bucketSum: " + buf.toString());
  }
}
