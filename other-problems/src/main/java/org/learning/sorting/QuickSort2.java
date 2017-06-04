package org.learning.sorting;

import java.util.Arrays;
import org.common.NumberUtility;


/**
 * Created by hluu on 5/28/17.
 */
public class QuickSort2 {

  public static void main(String[] args) {
    System.out.printf("%s\n", QuickSort2.class.getName());

    int[] array = {9,7,5,200,11,2,14,3};

    System.out.printf("before: %s\n", Arrays.toString(array));
    quickSort(array, 0, array.length-1);

    System.out.printf("after: %s\n", Arrays.toString(array));

    NumberUtility.assertSortedArray(array);
  }

  private static void quickSort(int[] array, int left, int right) {

    // base case
    if (left >= right) {
      return;
    }

    int pivotIndex = partition(array, left, right);

    quickSort(array, left, pivotIndex-1);
    quickSort(array, pivotIndex+1, right);
  }

  /**
   * Partition the given array into two parts where values on the left side are smaller than
   * a pivot value and right side are greater than the pivot value
   *
   * @param array
   * @param left
   * @param right
   * @return the index of the pivot value
   */
  private static int partition(int[] array, int left, int right) {
    int pivotValue = array[right];

    while (true) {
      while ((left < right) && array[left] < pivotValue) {
        left++;
      }

      while ((right > left) && array[right] > pivotValue) {
        right--;
      }

      if (left >= right) {
        break;
      }

      NumberUtility.swap(array, left, right);
    }

    // put the pivot value in place
    NumberUtility.swap(array, left, right);
    return right;
  }


}
