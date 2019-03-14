package my.leetcode.easy;

import org.testng.Assert;

import java.util.Arrays;

public class MaximumSubarray {

    public static void main(String[] args) {
        System.out.println(MaximumSubarray.class.getName());

        test(new int[] {-2,1,-3,4,-1,2,1,-5,4}, 6);
        test(new int[] {-1}, -1);
        test(new int[] {-1,-3}, -1);
        test(new int[] {-1,2,-1}, 2);
        test(new int[] {-3, -2, -1}, -1);
        test(new int[] {-3, -2, -1, 1}, 1);
    }

    private static void test(int[] nums, int expected) {
        int actual = maxSubArrayLinear(nums);

        System.out.printf("input: %s, expected: %d, actual: %d\n",
                Arrays.toString(nums), expected, actual);

        Assert.assertEquals(actual, expected);
    }

    private static int maxSubArrayLinear(int[] nums) {
        int globalMax = nums[0];
        int runningMax = globalMax;

        for (int i = 1; i < nums.length; i++) {

            runningMax =((runningMax > 0) ? runningMax : 0) + nums[i];

            globalMax = Math.max(globalMax, runningMax);
        }

        return globalMax;
    }

    /**
     * Using DP approach
     *
     * If we know the max sum up to A(0..i), then
     *
     * maxSubArray(A, i) = A[i] + maxSubArray(A, i - 1) > 0 ? maxSubArray(A, i - 1) : 0;
     *
     * In terms of coming up with a solution, first identy the subproblem.  In this case
     * we are saying if we know the max sum up i-1, then we can come up with max sum at i.
     * A few things to consider:
     *
     * max-sum(i-1) can be either positive or negative:
     * - when positive, adding to num[i] will increase the value
     * - when negative, if num[i] is positive, then the sum will be less
     * - when negative, if num[i] is negative, then the sum will be less
     * - therefore we ignore max-sum(i-1) in that case
     *
     * example:
     *   nums = {-2, 1,-3, 4,-1, 2, 1,-5, 4}
     *   dp   = {-2, 1,-2, 4, 3, 5, 6, 1, 5}
     *   max = 6
     *
     * @param nums
     * @return
     */
    private static int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = nums[0];
        int max = dp[0];

        for(int i = 1; i < n; i++){
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
