package my.leetcode.easy;

import org.testng.Assert;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a
 * certain amount of money stashed, the only constraint stopping you from robbing
 * each of them is that adjacent houses have security system connected and it will
 * automatically contact the police if two adjacent houses were broken into on the
 * same night.
 *
 * Given a list of non-negative integers representing the amount of money of each
 * house, determine the maximum amount of money you can rob tonight without alerting
 * the police.
 *
 * For example:
 *   [1,2,3,1], expected output: 4
 *   [2,7,9,3,1], expected output : 12
 *
 * This is an example of a dynamic problem problem. OK? why?
 * * Maximum amount of a set of choices, what are those choices?
 *
 */
public class HouseRobber {

    public static void main(String[] args) {
        test(new int[] {1,2,3,1}, 4);
    }

    private static void test(int[] houses, int expected) {
        int actual = robBottomUp(houses);
        System.out.printf("expected: %d, actual: %d\n", expected, actual);

        Assert.assertEquals(actual, expected);
    }


    private static int robBottomUp(int[] nums) {
        // make sure to handle both empty and empty array
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // since we are starting at 2, handle the case when array length is 1
        if (nums.length == 1) {
            return nums[0];
        }

        int[] cache = new int[nums.length+1];
        cache[1] = nums[0];

        int max = -1;
        for (int i = 2; i <= nums.length; i++) {
            cache[i] = Math.max(cache[i-2] + nums[i-1], cache[i-1]);
            max = Math.max(max, cache[i]);
        }

        return max;
    }
}
