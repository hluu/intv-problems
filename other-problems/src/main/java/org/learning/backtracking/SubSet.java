package org.learning.backtracking;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
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

    int array[] = {3, 1, 6, 5, 10, 2}; int k = 12;

    test(new int[] {4,1,2,6}, 3, true);
    test(new int[] {4,1,2,6}, 11, true);
    test(new int[] {4,1,2,6}, 4, true);
    test(new int[] {4,1,2,6}, 6, true);
    test(new int[] {4,1,2,6}, 15, false);
    test(array, k, true);
  }

  private static void test(int[] input, int targetSum, boolean expected) {
    System.out.println("==> test <===");
    System.out.printf("input: %s, targetSum: %d, expected:%b\n", Arrays.toString(input),
            targetSum, expected);

    List<Integer> collector = new ArrayList<>();

    boolean foundIt = findSubSet(input, 0, 0, targetSum, collector);

    if (foundIt) {
      System.out.printf("*** found at subset: %s\n", collector);
    } else {
      System.out.println("^^^ Nada");
    }

    Assert.assertEquals(foundIt, expected);
    System.out.println();
  }


  private static boolean findSubSet(int[] array, int index, int sumSoFar,
                                    int targetSum, List<Integer> collector) {

    if (sumSoFar == targetSum) {
      return true;
    }

    if (index >= array.length) {
      return false;
    }

    for (int i = index; i < array.length; i++) {
      sumSoFar += array[i];
      collector.add(array[i]);
      if (findSubSet(array, i+1, sumSoFar, targetSum, collector)) {
        return true;
      }
      collector.remove(collector.size()-1);
      sumSoFar -= array[i];

      return findSubSet(array, i+1, sumSoFar, targetSum, collector);
      /*
      if (findSubSet(array, i+1, sumSoFar, targetSum, collector)) {
        return true;
      }*/
    }

    return false;
  }

}

