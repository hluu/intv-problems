package org.learning.sorting;

import java.util.Arrays;

/**
 * Implementation of merge sort, which runs in O(nlogn)
 *
 * Steps
 *   - divide the array into 2 parts
 *   - recursive sort each part
 *   - merge the two sorted parts
 */
public class MergeSortAlgorithm {
    public static void main(String[] args) {
        System.out.println(MergeSortAlgorithm.class.getName());


        test(new int[] {});
        test(new int[] {9});
        test(new int[] {5,2,6,1,4});
        test(new int[] {1,2,3,4});
        test(new int[] {4,3,2,1});

        //verify(new int[] {1,2,2,5,4});
    }

    private static void test(int[] input) {
        System.out.println("before: " + Arrays.toString(input));

        mergeSort(input);

        System.out.println("after: " + Arrays.toString(input));

        verify(input);
        System.out.println();
    }

    private static void verify(int[] input) {
        if (input.length < 2) {
            return;
        }

        for (int i = 0; i < input.length-1; i++) {
            if (input[i] > input[i+1]) {
                throw new RuntimeException("Numbers are not sorted at index " + i);
            }
        }
    }

    private static void mergeSort(int[] input) {
        if (input == null || input.length < 2) {
            return;
        }
        int[] tmpArr = Arrays.copyOf(input, input.length);

        mergeSortHelper(input, tmpArr, 0, input.length-1);
    }

    private static void mergeSortHelper(int[] input, int[] tmpArr,
                                        int left, int right) {
        if (left == right) {
            return;
        }

        int mid = (left + right) / 2;

        // merge sort left side - (left, mid)
        mergeSortHelper(input, tmpArr, left, mid);
        // merge sort right side - (mid+1, right)
        mergeSortHelper(input, tmpArr, mid+1, right);

        // mid represent the start of the right array
        merge(input, tmpArr, left, mid+1, right);
    }

    private static void merge(int[] input, int[] tmpArr,
                              int left, int mid, int right) {

        // need 3 counters
        int leftIdx = left;
        int rightIdx = mid;
        int outputIdx = left;

        while (leftIdx < mid && rightIdx <= right) {
            if (input[leftIdx] <= input[rightIdx]) {
                tmpArr[outputIdx++] = input[leftIdx++];
            } else {
                tmpArr[outputIdx++] = input[rightIdx++];
            }
        }

        while (leftIdx < mid) {
            tmpArr[outputIdx++] = input[leftIdx++];
        }

        while (rightIdx <= right) {
            tmpArr[outputIdx++] = input[rightIdx++];
        }

        for (int i = left; i <= right; i++) {
            input[i] = tmpArr[i];
        }
    }

}
