package org.learning.numbers;

import org.testng.Assert;

import java.util.Arrays;

/**
 * Created by hluu on 8/22/16.
 *
 *  Problem statement:
 *      Given an array of stock prices in the time order.  Determine a transaction
 *      that gives the maximum profit
 *
 *      For example: [10,7,5,11,9], the max profit is 6
 *
 *  Approach:
 *      Brute force would be two loops to calculate the pair difference and
 *      maintain the max
 *      This would give us O(n^2)
 *
 *      What about O(nlogn) -> this implies divide and conquer.
 *      Does this work in this problem
 *      given there is the time constraint?
 */
public class BuyLowSellHigh {
    public static void main(String[] args ) {
        System.out.println("BuyLowSellHigh");

        int[] prices1 = {10,7,5,11,9};
        int[] prices2 = {10,7,5,11,9,17,4,18};

        //System.out.println("Max profit: " + buyLowSellHigh(prices));

        test(prices1, 6);
        test(prices2, 14);
    }

    private static void test(int[] prices, int expectedMaxProfit) {
        System.out.printf("*** %s\n", Arrays.toString(prices));
        int actualValue = buyLowSellHigh(prices);
        System.out.printf("expected value: %d, actual value: %d\n",
                expectedMaxProfit, actualValue);

        Assert.assertEquals(actualValue, expectedMaxProfit);
    }


    /**
     * As we iterating through the number, maintain two things:
     *  1) Minimum value seen so far
     *  2) Maximum profit seen so far
     *
     *  The reason this works is because the order matters due to increasing time
     *  We don't care about large stock prices that were in the past.
     *
     *  Always looking to the right.
     *
     * @param prices
     * @return
     */
    private static int buyLowSellHigh(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = Integer.MIN_VALUE;

        for (int v : prices) {
            if (minPrice == Integer.MAX_VALUE) {
                // update min price for the first time
                minPrice = v;
            } else if (v > minPrice) {
                // calculate profit since the stock price is > minPrice
                int profit = v - minPrice;
                maxProfit = Math.max(maxProfit, profit);
            } else {
                // either v is equal to or smaller than minPrice, then update it
                minPrice = v;
            }
        }
        System.out.printf("minPrice: %d\n", minPrice);
        return maxProfit;
    }
}
