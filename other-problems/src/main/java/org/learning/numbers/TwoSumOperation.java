package org.learning.numbers;

import org.testng.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Max Number of K-Sum Pairs
 *
 * You are given an integer array nums and an integer k.
 *
 * In one operation, you can pick two numbers from the array whose sum equals k and
 * remove them from the array.
 *
 * Return the maximum number of operations you can perform on the array.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4], k = 5
 * Output: 2
 * Explanation: Starting with nums = [1,2,3,4]:
 * - Remove numbers 1 and 4, then nums = [2,3]
 * - Remove numbers 2 and 3, then nums = []
 * There are no more pairs that sum up to 5, hence a total of 2 operations.
 *
 * Example 2:
 *
 * Input: nums = [3,1,3,4,3], k = 6
 * Output: 1
 * Explanation: Starting with nums = [3,1,3,4,3]:
 * - Remove the first two 3's, then nums = [1,4,3]
 * There are no more pairs that sum up to 6, hence a total of 1 operation.
 */
public class TwoSumOperation {
    public static void main(String[] args) {
        System.out.println("TwoSumOperation.main");

        test(new int[] {5,5,5}, 14, 0);
        test(new int[] {5,5,5}, 10, 1);
        test(new int[] {4,5,5,5,5}, 10, 2);
        test(new int[] {3,1,3,4,3}, 6, 1);
        test(new int[] {1,2,3,4}, 5, 2);
        test(new int[] {1,1,1,1,1}, 2, 2);
    }

    private static void test(int[]  nums,  int k, int expected) {
        System.out.printf("nums: %s, k: %d\n", Arrays.toString(nums), k);
        int actual = twoSumOperation(nums, k);
        int actual2 = twoSumOperation2(nums, k);
        int actual3 = twoSumOperation3(nums, k);

        System.out.printf("expected: %d, actual: %d, actual2: %d, actual3: %d\n",
                expected, actual, actual2, actual3);

        System.out.println();

        Assert.assertEquals(actual, expected);
        Assert.assertEquals(actual2, expected);
        Assert.assertEquals(actual3, expected);
    }

    /**
     * Approach:
     * - build a hasmap of val -> count
     * - iterate through nums, look for other = k - val
     *   - if found remove v and other
     *   - things to watch out [5,5,5] => 10
     *
     * @param nums
     * @param k
     * @return
     */
    private static int twoSumOperation(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int v  :nums) {
            map.put(v, map.getOrDefault(v, 0)+1);
        }

        int numOp = 0;
        for (int v : nums) {
            int other = k - v;
            if  (other >= 0) {
                if (map.containsKey(v)) {
                    if (map.get(v) == 1) {
                        map.remove(v);
                    } else {
                        map.put(v, map.get(v)-1);
                    }

                    if (map.containsKey(other)) {
                        numOp++;
                        if (map.get(other) == 1) {
                            map.remove(other);
                        } else {
                            map.put(other, map.get(other)-1);
                        }
                    }
                }
            }
        }
        return numOp;
    }

    /**
     * Checking out adding to the map.
     *  3,1,3,4,3 k = 6  =>  1,3,3,3,4
     *
     *
     * @param nums
     * @param k
     * @return
     */
    private static int twoSumOperation2(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        int numOp = 0;
        for (int v  :nums) {
            int other = k - v;
            if  (other >= 0) {
                if (map.containsKey(other)) {
                    numOp++;
                    if (map.get(other) == 1) {
                        map.remove(other);
                    } else {
                        map.put(other, map.get(other)-1);
                    }
                } else {
                    // when can't find the other
                    map.put(v, map.getOrDefault(v, 0) + 1);
                }
            } else {
                // when other is less than 0
                map.put(v, map.getOrDefault(v, 0) + 1);
            }
        }
        return numOp;
    }

    /**
     * Sort the nums first and using 2 pointers (left, right) and move them
     * toward each other.
     * - since the nums are sorted, we can take advantage of that insight
     * - there are 3 scenarios
     *   1) sum equals to k       =>  left++, right--
     *   2) sum is less than k    =>  left++
     *   3) sum is greater than k =>  right--;
     * @param nums
     * @param k
     * @return
     */
    private static int twoSumOperation3(int[] nums, int k) {
        int numOp = 0;

        Arrays.sort(nums);
        int left = 0;
        int right =  nums.length-1;

        while (left <  right) {
            int  sum = nums[left] + nums[right];
            // 3 scenarios -
            if (sum == k) {
                numOp++;
                left++; right --;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }

        return numOp;
    }
}
