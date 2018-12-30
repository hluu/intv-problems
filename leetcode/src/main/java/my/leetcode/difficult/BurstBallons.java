package my.leetcode.difficult;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with
 * a number on it represented by array nums. You are asked to burst all
 * the balloons. If the you burst balloon i you will get
 * nums[left] * nums[i] * nums[right] coins. Here left and right are
 * adjacent indices of i. After the burst, the left and right then
 * becomes adjacent.
 *
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Note:
 *   You may imagine nums[-1] = nums[n] = 1. They are not real
 *   therefore you can not burst them.
 *
 *   0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->  [3,8]   -->  [8]  --> []
 *              coins =  3*1*5     +  3*5*8    +  1*3*8     + 1*8*1   = 167
 *
 * Let C[i] be the maxCoins at element i
 *
 * Approaches:
 * 1) Brute force
 * 2) Divide and conquer - divide into independent subproblems and solve them
 * 3) Backtracking - each available options, try it, if it doesn't work,
 *    back out, try another option
 * 3) Dynamic programming
 *    * optimal substructure (subproblems & recursion)
 *    * overlapping sub problem (redundant computation of subproblems)
 *
 *
 * Brute force:
 * - among all the different ways of selecting the ballons to burst,
 *   which one gives us the maximum coins?  Since we don't know, try all
 *   the different ways?  How many different ways are there?
 *   There are n elements, it would be O(n!) (similar to permutation)
 * - what happens when the array nums contains only a single element?
 *   the max coin is the value of this single element
 * - what happens when the array nums contains only 2 elements?
 *   try the first, then second
 *   try the second, then first
 *   Which would gives us a different result i.e 58,
 *     - burst the first one first we get, 48
 *     - burst the second one first we get, 45
 *
 * - what happens when the array nums contains only 3 elements?
 *   this when it becomes interesting
 *
 *   recursion tree:
 *                                358
 *                     /           |           \
 *                   58            38           35
 *                  / \           / \           / \
 *                5   8          3   8         3   5
 *
 *
 * DP approach:
 *  - http://easyleetcode.blogspot.com/2016/02/leetcode-312-burst-balloons.html
 *  - The key point here is that every time you burst a balloon, the number
 *    of coin you gain depends on your previous steps of bursting coins.
 *  - The hard part is to define the subproblem.
 *  - Let's scale this problem down.
 *  - What is the fact you know for sure?
 *  - Say if the array has only 1 balloon. The maximum coin would be the coin inside
 *    this ballon.
 *  - Since we don't know the pattern of optimal. We just blindly iterate each balloon and check what's
 *    total gain if it's the last ballon.
 *
 *  - foreach k in i to j: (i = 0, j = array size)
 *      dp[j][i] = max(array[j-1]*array[k]*array[i+1] + dp[j][k-1] + dp[k+1][i],
 *                     dp[j][i]);
 *
 * Resources:
 *  1) http://www.gorecursion.com/algorithm/2017/01/07/burstboolon.html
 *  2) https://www.youtube.com/watch?v=IFNibRVgFBo
 */
public class BurstBallons {
    public static void main(String[] args) {


        test(new int[]{3}, 3);
        test(new int[]{5,8}, 48);
        test(new int[]{8,5}, 48);
        test(new int[]{8,3}, 32);
        test(new int[]{3,5,8}, 152);
        test(new int[]{3,1,5,8}, 167);
        test(new int[]{2,4,3,5}, 115);




        // will a little while to run
        test(new int[]{7,9,8,0,7,1,3,5,5,2,3}, 1654);
        //test(new int[]{8,2,6,8,9,8,1,4,1,5,3,0,7,7,0,4,2,2}, 115);
    }

    private static void test(int[] nums, int expected) {

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        System.out.printf("nums: %s, expected: %d ",
                Arrays.toString(nums), expected);


        long start = System.currentTimeMillis();
        int actual = bruteforce(list);
        long end = System.currentTimeMillis();

        long duration = (end - start);
        System.out.printf("actualBF: %d, duration: %d (ms) ",
                actual, duration);

        Assert.assertEquals(expected, actual);

        int actualDPTopdown = dpTopdown(nums);
        System.out.printf(" actualDPTopdown: %d ", actualDPTopdown);

        Assert.assertEquals(expected, actualDPTopdown);

        int actualDPBottomup = dpBottomup2(nums);
        System.out.printf(" actualDPBottomup: %d\n", actualDPBottomup);

        Assert.assertEquals(expected, actualDPBottomup);
    }

    private static int bruteforce(List<Integer> nums) {
        if (nums.size() == 1) {
            // base case, one element is just the value of that element
            return nums.get(0);
        }

        // keep track of the max
        int max = Integer.MIN_VALUE;

        // for each element in the list, treat that as a starting point
        for (int i = 0; i < nums.size(); i++) {
            // handle boundary conditions (left and right)
           int leftValue = (i == 0) ? 1 : nums.get(i-1);
           int rightVale = (i == nums.size()-1) ? 1 : nums.get(i+1);

           int tmpProd = leftValue * nums.get(i) * rightVale;

           // create a new list and remove element at index i
            // basically a smaller sub-problem
           List<Integer> newList = new ArrayList<>(nums);
           newList.remove(i);

           tmpProd += bruteforce(newList);

            // take the maximum at each level, which will give us the global maximum
           max = Math.max(max, tmpProd);
        }

        return max;
    }


    private static int dpTopdown(int[] nums) {
        System.out.printf("*** dpTopdown ");
        int[] newNums = new int[nums.length+2];
        newNums[0] = 1;
        newNums[newNums.length-1] = 1;

        // copy the values from nums to cache array
        for (int i = 0; i < nums.length; i++) {
           newNums[i+1] = nums[i];
        }

        // now the cache
        int size = newNums.length;
        int[][] cache = new int[size][size];

        return dpTopdownHelper(newNums, cache, 0, size-1);
    }

    private static int dpTopdownHelper(int[] nums, int[][] cache,
                                       int left, int right) {

        if (left +1 == right) {
            return 0;
        }

        if (cache[left][right] > 0) {
            return cache[left][right];
        }

        int maxCoinsSofar = 0;

        for (int k = left + 1; k < right; k++) {
            int coinsAtK = nums[left]*nums[k]*nums[right];
            maxCoinsSofar = Math.max(maxCoinsSofar,
                    coinsAtK + dpTopdownHelper(nums, cache, left, k) +
                            dpTopdownHelper(nums, cache, k, right));
        }

        return maxCoinsSofar;
    }

    private static int dpBottomup(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        // copy over with padding left most and right most with value of 1
        for (int x : iNums) if (x > 0) {
            nums[n++] = x;
        }
        nums[0] = nums[n++] = 1;


        int[][] dp = new int[n][n];
        for (int k = 2; k < n; ++k)
            for (int left = 0; left < n - k; ++left) {
                int right = left + k;
                for (int i = left + 1; i < right; ++i) {
                    int coinsAtI = nums[left] * nums[i] * nums[right];
                    dp[left][right] = Math.max(dp[left][right],
                            dp[left][i] + coinsAtI + dp[i][right]);
                }
            }

        return dp[0][n - 1];
    }


    private static int dpBottomup2(int[] nums) {
        // Extend list with head and tail (both are 1), index starts at 1
        int array[] = new int[nums.length + 2];
        array[0] = 1;
        array[array.length-1] = 1;
        for (int i = 0; i < nums.length; i++) {
            array[i+1] = nums[i];
        }

        // Initialize DP arrays, 1 index based
        //cache[i][j] stands for max coins at i step, burst j
        int cache[][] = new int[array.length][array.length];

        for (int i=1; i< array.length-1; i++) {
            for (int j=i; j >=1; j--) {
                // k as last
                for (int k=j; k <= i; k++) {
                    int left = cache[j][k-1];
                    int right = cache[k+1][i];
                    cache[j][i] = Math.max(left + array[j-1]*array[k]*array[i+1] + right,
                            cache[j][i]);
                }
            }
        }

        return cache[1][array.length-2];
    }

}
