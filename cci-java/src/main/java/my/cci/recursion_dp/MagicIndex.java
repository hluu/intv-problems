package my.cci.recursion_dp;

import java.util.Arrays;
import org.testng.Assert;


/**
 * Created by hluu on 5/27/17.
 *
 * Problem statement:
 *  Given an array A[i...n-1] of sorted number, the magic index is where a[i] = i.
 *  Write a method to find magic index
 *
 * Example:
 *  index:  0    1   2   3  4  5  6  7  8  9   10
 *  -----------------------------------------------
 *  a[] = {-40, -20, -1, 1, 2, 3, 5, 7, 9, 12, 13}
 *
 *  magic index is 7: a[7] = 7
 *
 * Analysis:
 *  * Brute force was is using a for loop and check when a[i] = i
 *  * Since the array is sorted, then can we use binary search technique to achieve O(logn)
 *  * Array length is 11, mid-point is 5 => a[5] = 3
 *  * Based on the value of a[5], what can we conclude?
 *    * If we move to left by 1 => a[4], then the value has to be less than 3 or more
 *    * So we can conclude that the magic index is not on the left hand side
 *    * Therefore it has to be on the right hand side
 *
 * What if there are duplicate elements in the array?
 *
 * For example:  a[] = {-10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13}
 * * In this situation, it could be on the left or right
 * * So we need to recursively search on both sides
 *
 */
public class MagicIndex {
  public static void main(String[] args) {
    System.out.println("MagicIndex.main");

    int array1[] = {-40, -20, -1, 1, 2, 3, 5, 7, 9, 12, 13};
    int array2[] = {-40, 1, 9, 10, 12, 20, 25, 27, 29, 32, 33};
    int array3[] = {-40, 0, 9, 10, 12, 20, 25, 27, 29, 32, 33};

    test(array1, 7);
    test(array2, 1);
    test(array3, -1);

  }

  private static void test(int[] array, int expectedMagicIndex) {
    int actualMagicIndex = findMagicIndex(array, 0, array.length-1);
    System.out.printf("magic index for %s is %d\n", Arrays.toString(array),
        actualMagicIndex);

    Assert.assertEquals(actualMagicIndex, expectedMagicIndex);

    int actualMagicIndexFromDups = findMagicIndexWithDups(array, 0, array.length-1);
    System.out.printf("magic index for %s is %d\n", Arrays.toString(array),
        actualMagicIndexFromDups);
    Assert.assertEquals(actualMagicIndexFromDups, expectedMagicIndex);
  }

  private static void testWithDups(int[] array, int expectedMagicIndex) {
    int actualMagicIndex = findMagicIndexWithDups(array, 0, array.length-1);
    System.out.printf("magic index for %s is %d\n", Arrays.toString(array),
        actualMagicIndex);

    Assert.assertEquals(actualMagicIndex, expectedMagicIndex);
  }

  private static int findMagicIndex(int[] array, int left, int right) {
    if (left > right) {
      return -1;
    }

    int mid = (left + right) / 2;
    // three possible cases

    if (array[mid] == mid) {
      return mid;
    } else if (array[mid] < mid) {
      // go right
      return findMagicIndex(array, mid+1, right);
    } else {
      // go left
      return findMagicIndex(array, left, mid-1);
    }

  }

  private static int findMagicIndexWithDups(int[] array, int left, int right) {
    if (left > right) {
      return -1;
    }

    int mid = (left + right) / 2;
    // three possible cases

    if (array[mid] == mid) {
      return mid;
    }

    int result = -1;
    // search on left side
    int rightIndex = Math.min(mid-1, array[mid]);
    result = findMagicIndex(array, left, rightIndex);
    if (result != -1) {
      return  result;
    }


    // search on right side
    int leftIndex = Math.max(mid+1, array[mid]);
    return findMagicIndex(array, leftIndex, right);

  }
}
