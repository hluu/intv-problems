package org.learning.others;

import org.testng.Assert;

import java.util.Arrays;

/**
 * Created by hluu on 8/5/17
 *
 * Problem:
 *  Given a bar graph represented by an array of positive numbers.  Find the maximum amount of
 *  water that can be stored in the bar graph.
 *
 *  For example:  barGraph = {5,1,3,4}, the amount of water can be held is 4
 *
 *   __
 *  |  |      __
 *  |  |   __|  |
 *  |  |  |  |  |
 *  |  |__|  |  |
 *  |  |  |  |  |
 *  ---------------------
 *  -1  5  5  5         <== highest left bar
 *   4  4  4  -1        <== highest right bar
 *
 *  Approach:
 *      * Figure out the amount of water can be held for each bar
 *          * amount = Min(highest left bar, highest right bar) - bar height
 *          * Determine the left highest bar for each column
 *          * Determine the right highest bar for each column
 *          * Use two arrays - one for storing the highest left bar, the other for
 *            storing highest right bar
*       * Sum up the amount for each bar
 */
public class WaterInBarGraph {
    public static void main(String[] args) {
        System.out.printf("%s\n", WaterInBarGraph.class.getName());

        test(new int[]{5,1,3,4}, 4);
        test(new int[]{1,1,1,1,1}, 0);
        test(new int[]{2,1,1,1,1}, 0);
        test(new int[]{1,1,1,1,2}, 0);
        test(new int[]{2,1,1,1,2}, 3);
        test(new int[]{2,1,1,1,100}, 3);
        test(new int[]{5,1,6,1,5}, 8);
    }

    private static void test(int[] bars, int expectedWaterContentSize) {
        int actualWaterContentSize = computeWaterAmount(bars);
        System.out.printf("bars: %s, expected: %d, actual: %d\n",
                Arrays.toString(bars), expectedWaterContentSize, actualWaterContentSize);

        Assert.assertEquals(actualWaterContentSize, expectedWaterContentSize);
    }


    private static int computeWaterAmount(int[] bars) {
        int[] leftHighestBar = new int[bars.length];
        int[] rightHighestBar = new int[bars.length];

        // compute left and right highest bars
        int leftSide = bars[0];
        leftHighestBar[0] = -1;

        for (int i = 1; i < bars.length; i++) {
            if (bars[i] > leftSide) {
                leftSide = bars[i];
            }
            leftHighestBar[i] = leftSide;
        }

        System.out.println("left: " + Arrays.toString(leftHighestBar));

        int rightSide = bars[bars.length-1];
        rightHighestBar[bars.length-1] = -1;

        for (int i = bars.length-2; i >= 0; i--) {
            if (bars[i] > rightSide) {
                rightSide = bars[i];
            }
            rightHighestBar[i] = rightSide;
        }

        System.out.println("right: " + Arrays.toString(rightHighestBar));

        // compute the water content for reach column
        int[] waterContent = new int[bars.length];
        int sum = 0;
        for (int i = 0; i < bars.length; i++) {
            if (leftHighestBar[i] == -1 || rightHighestBar[i] == -1) {
                waterContent[i] = 0;
            } else {
                waterContent[i] = Math.min(leftHighestBar[i], rightHighestBar[i]) - bars[i];
            }
        }

        // sum up
        for (int i = 0; i < bars.length; i++) {
            sum += waterContent[i];
        }

        return sum;
    }
}
