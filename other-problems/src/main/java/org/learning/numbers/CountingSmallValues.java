package org.learning.numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hluu on 2/14/16.
 *
 * Problem statement:
 *  Give an array arr of unsorted values, design an algorithm to return an array
 *  with same size where each value in the array contains the number of values
 *  smaller than arr[i]
 *
 * Example:
 *  arr    = {5,2,6,1}
 *  result = {2,1,1,0}
 *
 * Approach:
 *  * One brute force is to iterate through the values to the right at each value
 *    and perform the count of values smaller an arr[i]
 *  * This would be O(n^2)
 *  * Can we come up with an O(n) or O(nlogn) solution with additional memory?
 *  * We know merge sort has O(nlogn), is there any way we can leverage the merge logic
 *    to count how many elements are smaller than arr[i]
 *
 */
public class CountingSmallValues {
    public static void main(String[] args) {
        System.out.println("CountingSmallValues.main");

        int[] arr = {10,5,2,6,3,8,9};
        System.out.println("Input: " + Arrays.toString(arr));
        System.out.println("Answer: " + countSmaller(arr));

    }

    public static List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Tuple[] arr = new Tuple[n];
        for (int i = 0; i < n; ++i) arr[i] = new Tuple(nums[i], i);
        sort(ans, arr, n);
        List<Integer> list = new ArrayList<>(n);
        for (int x : ans) list.add(x);
        return list;
    }

    private static Tuple[] sort(int[] ans, Tuple[] a, int n) {
        if (n > 1) {
            int i = n / 2, j = n - i;
            Tuple[] left = sort(ans, Arrays.copyOfRange(a, 0, i), i);
            Tuple[] right = sort(ans, Arrays.copyOfRange(a, i, n), j);
            for (int k = n - 1; k >= 0; --k)
                if (j == 0 || (i > 0 && left[i - 1].val > right[j - 1].val)) {
                    ans[left[i - 1].idx] += j;
                    a[k] = left[--i];
                } else
                    a[k] = right[--j];
        }
        return a;
    }
}


class Tuple {
    public int val;
    public int idx;

    public Tuple(int val, int idx) {
        this.val = val;
        this.idx = idx;
    }
}