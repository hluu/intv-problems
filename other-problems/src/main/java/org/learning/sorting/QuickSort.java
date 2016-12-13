package org.learning.sorting;

import junit.framework.Assert;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by hluu on 12/1/16.
 *
 * Quick sort is based on the idea of partitioning and recursively sort left and right
 * side of pivot point.
 *
 * The central part of quick sort is the partitioning scheme, where it selects
 * a pivot point and move all elements smaller pivot to the left of the pivot and
 * move all elements larger pivot to the right of the pivot.  Then return the index of
 * the pivot
 *
 */
public class QuickSort {

    public static void main(String[] args) {
        System.out.printf("%s\n", QuickSort.class.getName());

        //int[] arr = {7,1,2,5,4,9,3,6};
        //int[] arr = {9, 7, 5, 200,11, 12, 2, 14, 3, 10, 6};
        //int[] arr = {7,1};
        int[] arr = {1,7};
        //int[] arr = {7,3,9,1,4};
        //int[] arr = {8};
        //int[] arr = new int[0];

        System.out.printf("before: %s\n", Arrays.toString(arr));

        //testPartition(arr);

        quickSort(arr, 0, arr.length-1);

        System.out.printf("after: %s\n", Arrays.toString(arr));

        assertSortedArray(arr);
    }

    private static void testPartition(int[] arr) {
        int partitionIndex = partition2(arr, 0, 2);


        System.out.printf("partitionIndex: %d\n", partitionIndex);
        System.out.printf("after: %s\n", Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int start, int end) {

        counter++;
        if (counter == 100) {
            System.out.printf("s: %d, e:%d\n", start,end);
            return;
        }


        if (start >= end) {
            return;
        }
        int partitionIndex = partition2(arr, start, end);

        quickSort(arr, start, partitionIndex-1);
        quickSort(arr, partitionIndex+1, end);

    }

    static int counter = 0;

    /**
     * This is a simpler version.  Where the wall concept is used.
     *
     * Approach:
     *  Pick an element as pivot value (could be the last element)
     *  Establish the wall which start at start - 1 to make it easier
     *  Move from left to right
     *      * If (value at i > pivot value), moving on
     *      * If (value at i < pivot value), increment the wall, swap element at i with
     *        element at the wall
     *
     * One improvement we could have is the selection of of the pivot value
     *   * Could be random using a random generator
     *   * Median of 3 of a number odd number of elements (3,5,7)
     *
     * Resource:
     *  https://www.khanacademy.org/computing/computer-science/algorithms#quick-sort
     * @param arr
     * @param start
     * @param end
     * @return
     */
    private static int partition2(int[] arr, int start, int end) {
        int pi = end;
        int pv = arr[end];

        // start with left side because we will increment it first before using it
        int wallIndex = start - 1;

        for (int i = start; i < end; i++) {
            if (arr[i] < pv) {
                wallIndex++;
                if (wallIndex != i) {
                    //System.out.printf("wallIndex: %d, i:%d\n", wallIndex, i);
                    swap(arr, wallIndex, i);
                }
            }
        }

        // put the pivot value in the right place
        wallIndex++;
        if (wallIndex < end) {
            swap(arr, wallIndex, end);
        }

        return wallIndex;
    }

    /**
     * Make sure the start and end represent the indexes and they are inclusive
     * @param arr
     * @param start
     * @param end
     * @return
     */
    private static int partition(int[] arr, int start, int end) {

        Random rand = new Random(System.currentTimeMillis());
        int pivotIndex = start + rand.nextInt(end-start +1);
        pivotIndex = end;
        System.out.printf("pivotIndex: %d\n", pivotIndex);

        int pivotValue = arr[pivotIndex];
        System.out.printf("pivotValue: %d\n", pivotValue);

        int left = start;
        int right = end-1;
        // move pivot elm to the end
        swap(arr, pivotIndex, end);

        while (true) {
            // left to right, stop when find an element > pivot value and
            // left < right
            while (left < right && arr[left] < pivotValue) {
                left++;
            }

            // right to left, stop when find an element < pivot value and
            // left < right
            while (left < right && arr[right] > pivotValue) {
                right--;
            }

            if (left >= right) {
                break;
            }

            swap(arr, left, right);
        }

        if (left < right || right == start) {
            swap(arr, end, left);
        }

        return left;

    }

    private static void swap(int[] arr, int from, int to) {
        int tmp = arr[from];
        arr[from] = arr[to];
        arr[to] = tmp;
    }

    private static void assertSortedArray(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i-1] > arr[i]) {
                Assert.fail(String.format("Array %s not sorted\n", Arrays.toString(arr)));
            }
        }
    }

}
