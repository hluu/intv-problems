package org.learning.numbers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Created by hluu on 6/2/17.
 *
 * Given an array of unsorted number, determine the number of contiguous subsequences
 * with a sum equals to a target sum
 *
 * For example:
 *  int a[] =      {10, 5,  1,  2, -1, -1,  7, 1, 2 } and target = 8
 *
 *  running sum => {10, 15, 16, 18, 17, 16, 23, 24, 26}
 *              =>   2,  7,  8, 10,  9,  8, 15, 16, 18}
 *  output: 3 => {5,1,2}, {7,1}
 */
public class ContiguousSequenceSum {
  public static void main(String[] args) {
    System.out.println("ContiguousSequenceSum.main");

    int[] array = {10, 5, 1, 2, -1, -1, 7, 1, 2 };
    int k = 8;

    System.out.println("array: " + Arrays.toString(array) + " k: " + k);

    System.out.println("result (brute force):\t " + numSubSumBruteForce(array, k));
    System.out.println("result (optimized):\t " + numSubSumOptimized(array, k));

  }

  /**
   * this will use addition space to help speed things up
   * @param a
   * @param target
   * @return
   */
  private static int numSubSumOptimized(int[] a, int target) {
    Set<Integer> state = new HashSet<>();

    int count = 0;
    int runningSum = 0;
    for (int i = 0; i < a.length; i++) {
      runningSum += a[i];
      state.add(runningSum);

      if (state.contains(runningSum - target)) {
        count++;
      }

    }

    return count;
  }

  /**
   * Brute force version with using two for loops.  Trying out every possible
   * subsequence.
   *
   * @param a
   * @param target
   * @return
   */
  private static int numSubSumBruteForce(int[] a, int target) {
    int count = 0;
    for (int i = 0; i < a.length; i++) {
      // reset on each new starting element
      int sumSoFar = 0;
      for (int j = i; j < a.length; j++) {
        sumSoFar += a[j];
        if (sumSoFar == target) {
          count++;
        }
      }
      //System.out.printf("i: %d, count: %d\n", i, count);
    }
    return count;
  }
}
