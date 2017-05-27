package org.learning.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by hluu on 5/20/17.
 *
 * Given a set of numbers, find a subset whose sum is equivalent to target value k
 *
 * For example:
 *   array = {15, 22, 14, 26, 32, 9, 16, 8}
 *   k = 53
 *
 * Return a sub set of numbers or -1 when not possible
 *
 * Analysis:
 *  * This is a subset problem where we will try different combination of numbers.
 *  * For each number in the set, determine to let it be in the subset or not
 *  * This will be 2^N
 *
 */
public class SubSet {
  public static void main(String[] args) {
    System.out.printf("%s\n", SubSet.class.getName());

    int array[] = {3, 1, 6, 5, 10}; int k = 12;

    //int array[] = {15, 22, 14, 26, 32, 9, 16, 8}; int k = 53;

    List<Integer> collector = new ArrayList<>();
    if (findSubSet(array, 0, 0, k, collector)) {
      int subSetSum = collector.stream().mapToInt(Integer::intValue).sum();
      System.out.printf("Found a subset: %s, subSetSum: %d\n", collector, subSetSum);
    } else {
      System.out.printf("DID NOT Find a subset: %s\n", collector);
    }
  }

  private static boolean findSubSet(int[] array, int index, int sumSoFar, int targetSum,
      List<Integer> subSet) {

    if (sumSoFar == targetSum) {
      return true;
    }

    if (index >= array.length) {
      return false;
    }

    for (int i = index; i < array.length; i++) {
      sumSoFar += array[i];
      subSet.add(array[i]);
      if (findSubSet(array, index+1, sumSoFar, targetSum, subSet)) {
        return true;
      }
      subSet.remove(subSet.size()-1);
      sumSoFar -= array[i];
      if (findSubSet(array, index+1, sumSoFar, targetSum, subSet)) {
        return true;
      }
    }

    return false;
  }
}
