package org.learning.numbers;

import org.testng.Assert;

/**
 *
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 4, 6, 9, 10, 35, 70};


        for (int i = 0; i < arr.length; i++) {
            test(arr, arr[i], i);
        }

    }

    private static void test(int[] arr, int value, int expected) {
        int actual = binarySearch(arr, value);

        System.out.printf("Expected: %s, actual: %s\n", expected, actual);

        Assert.assertEquals(actual, expected);
    }

    public static int binarySearch(int[] input, int val) {
        int left = 0;
        int right = input.length - 1;

        while (left <= right) {
            // there are 2 ways to calculate the mid-index
            // the first one is more safe because no overflow
            // the second may run into overflow
            int mid = left + (right - left) / 2;
            //int mid = (left + right)/2;

            if (input[mid] == val) {
                return mid;
            }

            if (input[mid] < val) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
