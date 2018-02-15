package org.learning.dp;

import org.common.ArrayUtils;
import org.testng.Assert;

import java.util.Arrays;

/**
 * Created by hluu on 2/10/18.
 *
 * Given a set of coins and a change amount, determine the number of way
 * to be make to make the change.
 *
 * We have infinite number of coins per denomination.
 *
 * For example:
 *  coins = {1,2,3}  change = 4
 *
 *  There are 4 possible way of making changes
 *
 *  {1,1,1,1}, {1,1,2}, {2,2}, {1,3}
 *
 * Approach:
 *  Try all possible combination of the coins that can make the change.
 *  This means try with the current coin and without the current coin
 *
 */
public class CoinChangeNumWay {
    public static void main(String[] args) {
        int coins[] = {1,2,3};
        int change = 4;

        test(coins,change);
    }

    private static void test(int[] coins, int change) {
        System.out.printf("coins: %s, change:%d\n", Arrays.toString(coins), change);
        int resultFromBruteForce = numWayBruteForce(coins, change, 0);

        System.out.println("resultFromBruteForce: " + resultFromBruteForce);

        int resultFromMemoization = numWayMemoization(coins, change, 0);

        System.out.println("resultFromMemoization: " + resultFromMemoization);

        Assert.assertEquals(resultFromBruteForce, resultFromMemoization);


        int resultFromBottomUp = bottomUpDP(coins, change);
        System.out.println("resultFromBottomUp: " + resultFromBottomUp);

        Assert.assertEquals(resultFromBottomUp, resultFromMemoization);
    }

    private static int bruteCountCounter = 0;
    private static int numWayBruteForce(int[] coins, int change, int idx) {
        if (change < 0) {
            return 0;
        }

        System.out.printf("trace: num:%d, change:%d, coin: %d, idx:%d\n",
                ++bruteCountCounter, change, idx < coins.length ? coins[idx] : -1, idx);
        if (change == 0) {
            return 1;
        }

        if (idx == coins.length) {
            return 0;
        }

        // include the coin and not include the current coin at idx
        // branch 2 way out
        return numWayBruteForce(coins, change - coins[idx], idx) +
                numWayBruteForce(coins, change,  idx+1);

    }

    private static int numWayMemoization(int[] coins, int change, int idx) {
        int[][] cache = new int[change+1][coins.length];

        return numWayMemoizationHelper(coins,change, idx, cache);
    }

    private static int memoizationCounter = 0;
    private static int numWayMemoizationHelper(int[] coins, int change, int idx, int[][] cache) {
        if (change < 0) {
            return 0;
        }

        if (idx == coins.length) {
            return 0;
        }

        if (change == 0) {
            return 1;
        }


        if (cache[change][idx] > 0) {
            return cache[change][idx];
        }

        System.out.printf("memoiz: num:%d, change:%d, coin: %d, idx:%d\n",
                ++memoizationCounter, change, idx < coins.length ? coins[idx] : -1, idx);


        cache[change][idx] = numWayMemoizationHelper(coins, change - coins[idx], idx, cache) +
                numWayMemoizationHelper(coins, change,  idx+1, cache);

        return cache[change][idx];

    }

    /**
     * Bottom up approach iterates from smallest change amount to the specified change
     * per each coin.
     *
     * Using additional space to speed up the process.
     *
     * Runtime is change x number of coins.
     *
     *
     * @param coins
     * @param change
     * @return
     */
    private static int bottomUpDP(int[] coins, int change) {
        int[] cache = new int[change+1];

        cache[0] = 1;
        for (int coinIdx = 0; coinIdx < coins.length; coinIdx++) {
            int coinAmt = coins[coinIdx];
            // go from 1 to change amount
            for (int amt = 1; amt <= change; amt++) {

                if (amt >= coinAmt) {
                    cache[amt] += cache[amt - coinAmt];
                }
            }
            System.out.println(Arrays.toString(cache));
        }

        return cache[change];
    }
}
