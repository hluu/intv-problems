package my.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 *
 * Explanation: The longest increasing subsequence is [2,3,7,101],
 *              therefore the length is 4.
 *
 * Resources:
 *  * https://leetcode.com/problems/longest-increasing-subsequence/discuss/74824/JavaPython-Binary-search-O(nlogn)-time-with-explanation
 *  * https://www.cs.princeton.edu/courses/archive/spring13/cos423/lectures/LongestIncreasingSubsequence.pdf
 *  * https://segmentfault.com/a/1190000003819886
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {

        //test(null, 0);
        //test(new int[] {1}, 1);
        test(new int[] {10,9,2,5,3,7,101,18}, 4);
        //test(new int[] {1,0,7,2,8,3,4,9}, 5);
        //test(new int[] {4, 2, 4, 5, 3, 7}, 4);
        //test(new int[] {1,3,5,2,8,4,6}, 4);

    }

    private static void test(int[] input, int expected) {
        System.out.printf("\ninput: %s ", (input != null) ? Arrays.toString(input) : "null");
        int actual = lis(input);

        int actual2 = lengthOfLIS(input);

        int actual3 = lengthOfLISUsingCollection(input);

        System.out.printf("expected: %d, actual: %d, actual2: %d, actual3: %d\n",
                 expected, actual, actual2, actual3);
    }

    /**
     * This is an O(n^2) algorithm.
     *
     * Keeping track of the LIS at each index.
     *
     * Moving from each idx to its left and update the LIS appropriately
     *
     * @param input
     * @return
     */
    private static int lis(int[] input) {
        if (input == null || input.length == 0) {
            return 0;
        }

        int[] lis = new int[input.length];
        Arrays.fill(lis,1);

        for (int i = 1; i < input.length; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (input[i] > input[j]) {
                    if (lis[j] >= lis[i]) {
                        lis[i] = lis[j] + 1;
                    }
                }
            }
        }

        //System.out.printf("%s\n", Arrays.toString(lis));
        int max = -1;
        for (int v : lis) {
            max = Math.max(max, v);
        }

        return max;
    }

    /**
     * This is an O(nlogn) runtime algorithm.  Based on the patience sorting algorithm.
     *
     *
     * tails is an array storing the smallest tail of all increasing subsequences with length i+1 in tails[i].
     * For example, say we have nums = [4,5,6,3], then all the available increasing subsequences are:
     *
     * len = 1   :      [4], [5], [6], [3]   => tails[0] = 3
     * len = 2   :      [4, 5], [5, 6]       => tails[1] = 5
     * len = 3   :      [4, 5, 6]            => tails[2] = 6
     * We can easily prove that tails is a increasing array. Therefore it is possible to do a binary search in tails array to find the one needs update.
     *
     * Each time we only do one of the two:
     *
     * (1) if x is larger than all tails, append it, increase the size by 1
     * (2) if tails[i-1] < x <= tails[i], update tails[i]
     * Doing so will maintain the tails invariant. The the final answer is just the size.
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int left = 0, right = size;
            while (left != right) {
                int mid = (left + right) / 2;
                if (tails[mid] < x)
                    left = mid + 1;
                else
                    right = mid;
            }
            tails[left] = x;
            System.out.println("tail[" + left + "] =" + x);
            if (left == size) ++size;
        }
        return size;
    }

    /**
     * Anther O(nlogn) solution of Patience algorithm
     * using Collections APIs
     *
     * @param nums
     * @return
     */
    public static int lengthOfLISUsingCollection(int[] nums) {
        List<Integer> piles = new ArrayList<>(nums.length);

        for (int num : nums) {
            int idx = Collections.binarySearch(piles, num);

            if (idx < 0) {
                idx = ~idx;
            }
            if (idx == piles.size()) {
                piles.add(num);
            } else {
                piles.set(idx, num);
            }
        }
        return piles.size();
    }
}
