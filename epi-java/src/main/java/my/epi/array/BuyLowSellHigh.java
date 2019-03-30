package my.epi.array;


import org.testng.Assert;

import java.util.Arrays;

/**
 *
 * Problem:
 *  Give a set of stock prices in the order of time.  Determine the
 *  maximum profit by buying low and sell high
 *
 *  For example: {30, 50, 60, 20, 40, 50, 45, 70, 40}
 *
 *  Approach:
 *      Brute force:
 *          for element at position j, find the max value among
 *          all elements > j and compute the difference. Keep a running
 *          max value.  This will be O(n^2)
 *
 *      What would be a fast solution - like O(n)
 *          Is there a way that involves scanning the array multiple times?
 *            - but not in nested loop
 *
 *          Another way of looking at this is treating the value of element as
 *          the maximum value, then find the small element among all the elements
 *          to the left of it.

 *          ori: {30, 50, 60, 20, 40, 50, 45, 70, 40}
 *          min: {30, 30, 30, 20, 20, 20, 20, 20, 20}
 *          max: {70, 70, 70, 70, 70, 70, 70, 70, 40}
 */
public class BuyLowSellHigh {

    public static void main(String[] args) {
        System.out.println(BuyLowSellHigh.class.getName());

        int arr[] = {30, 50, 60, 20, 40, 50, 45, 70, 40};
        test(arr, 50);
    }

    private static void test(int[] prices, int expected) {
        System.out.printf("Input: %s\n",
                Arrays.toString(prices));

        int actual = maxProfit(prices);

        System.out.printf("expected: %d, actual: %d\n",
                expected, actual);

        Assert.assertEquals(actual, expected);

    }
    public static int maxProfit(int[] arr) {
        int[] minArr = new int[arr.length];

        // finding the min
        minArr[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
           minArr[i] = Math.min(minArr[i-1], arr[i]);
        }

        System.out.printf("min arr: %s\n", Arrays.toString(minArr));

        int max = -1;

        // now subtracting the values and find the max
        for (int i = 0; i < arr.length; i++) {
            int diff = arr[i] - minArr[i];
            if (diff > max) {
                max = diff;
            }
        }
        return max;
    }
}
