package org.learning.numbers;

import java.util.Arrays;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as
 * one sorted array.
 *
 * Note:
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n)
 * to hold additional elements from nums2.
 *
 *Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * Output: [1,2,2,3,5,6]
 */
public class MergeSortedArray {

    public static void main(String[] args) {
        System.out.println(MergeSortedArray.class.getName());

        int[] nums1 = new int[7];
        nums1[0] = 1;nums1[1] = 2;nums1[2] = 3;

        int[] nums2 = new int[4];
        nums2[0] = 2;nums2[1] = 5;nums2[2] = 6; nums2[3] = 9;

        test(nums1, 3, nums2, 4);


        int[] nums3 = new int[1];

        int[] nums4 = new int[1];
        nums4[0] = 1;


        test(nums3, 0, nums4, 1);
    }

    private static void test(int[] nums1, int m, int[] nums2, int n) {
        System.out.printf("nums1: %s, m: %d, num2: %s, n: %d\n",
                Arrays.toString(nums1), m, Arrays.toString(nums2), n);

        merge(nums1, m, nums2, n);

        System.out.println("\nresult: " + Arrays.toString(nums1));
    }

    private static void merge(int[] nums1, int m, int[] nums2, int n) {
        int idx = m + n - 1;

        int idx1 = m - 1;
        int idx2 = n - 1;

        while (idx1 >= 0 && idx2 >= 0) {
            if (nums1[idx1] >= nums2[idx2]) {
                nums1[idx] = nums1[idx1];
                idx1--;
            } else {
                nums1[idx] = nums2[idx2];
                idx2--;
            }

            idx--;
        }

        while (idx1 >= 0) {
            nums1[idx] = nums1[idx1];
            idx1--;
            idx--;
        }

        while (idx2 >= 0) {
            nums1[idx] = nums2[idx2];
            idx2--;
            idx--;
        }
    }
}
