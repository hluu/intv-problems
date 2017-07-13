package org.learning.numbers;

/**
 * Created by hluu on 1/7/16.
 *
 * Problem:
 *  Give an array of integers or floating points, which contains both positive and negative numbers, find the
 *  largest product of contiguous numbers.
 *
 *  For example:
 *      {-2.5, 4, 0, 3, 0.5, 8, -1} ==> 12 {3, 0.5, 8}
 *      {6, -3, -10, 0, 2}          ==> 180 {6, -3, -10}
 *      {-1, -3, -10, 0, 60}        ==> 60 {60}
 *      {-2, -3, 0, -2, -40}        ==> 80 {-2, -40}
 *
 *
 *  Approach:
 *      A few key points to note:
 *        1) This is a contiguous subsequence
 *        2) Even number of negative values become positive number
 *        3) 0 will create a new contiguous subsequence
 *
 *      Maintain positive maximum
 *      Maintain negative maximum
 *
 *      maxPos = Max((maxNeg * v), (curMax * v)) if v is not zero.
 *
 *      if v = 0, reset all maxPos, maxNeg, curMax to 1;
 *
 */
public class LargestConsecutiveProduct {
    public static void main(String[] args) {
        System.out.println("LargestConsecutiveProduct.main");

        //int[] arr = {6, -3, -10, 0, 2};

        //int[] arr = {-1, -3, -10, 0, 60};

        int[] arr = {-2, -3, 0, -2, -40};

        //int[] arr = {1, 2, -4, 1, 3, -2, 3, -1};

        System.out.println("max prod: " + maxContiguousProduct(arr));
    }

    public static int maxContiguousProduct(int[] values) {
        int maxPos = 1;
        int maxNeg = 1;
        int curMax = 1;

        for (int i = 0; i < values.length; i++) {
            int v = values[i];

            if (v > 0) {
                // positive
                curMax *= v;
                maxNeg *= v;
                if (curMax > maxPos) {
                    maxPos = curMax;
                }
            } else if (v == 0) {
                maxNeg = 1;
                curMax = 1;
            } else {
                // negative
                maxPos = Math.max(maxNeg * v, curMax);
                maxNeg = Math.min(maxNeg * v, v * curMax);

                curMax = maxPos;
            }
        }
        return maxPos;
    }
}
