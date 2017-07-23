package my.epi.misc;


import java.util.Arrays;

/**
 * Created by hluu on 11/8/16.
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
        System.out.printf("input: %s\n", Arrays.toString(arr));

        System.out.printf("max profit: %d", maxProfit(arr));
    }

    public static int maxProfit(int[] arr) {
        int[] minArr = new int[arr.length];

        // finding the min
        minArr[0] = arr[0];
        // maintaining a running min
        for (int i = 1; i < arr.length; i++) {
            // default update
            minArr[i] = minArr[i-1];
            // update if less than
           if (arr[i] < minArr[i]) {
               minArr[i] = arr[i];
           }
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
