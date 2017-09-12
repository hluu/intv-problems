package org.learning.others;

import org.testng.Assert;

import java.util.Arrays;

/**
 * Created by hluu on 8/5/17.
 *
 * Problem:
 *  Give an an array of positive numbers, where each value represent the height of a tower, as
 *  well as the maximum # of steps one can jump to.
 *
 *  For example: towers = {4,2,0,0,2,0}
 *
 *  |
 *  |__
 *  |  |
 *  |  |__      __
 *  |  |  |    |  |
 *  |  |  |____|  |__
 *  ---------------------
 *   0  1  2  3  4  5
 *
 *  The value of tower at index 0 is 4, therefore from there one can jump to any of the next towers
 *  that is 1,2,3,4 towers away.  If the tower height is 2, then from there one can jump to next tower
 *  and the next next tower.  If the tower height is 0, then one is stuck, can't jump anywhere.
 *
 *  The question is given the tower heights as an array, determine if it is possible to jump off pass
 *  the end, meaning able to jump past the length of the array.
 *
 *  For the above given set of towers, the answer is YES. Jump from column 0, to 4, and jump off pass
 *  the end of the array.
 *
 *  Another example:
 *    towers = {1,3,5,3,1}
 *
 *  |      __
 *  |     |  |
 *  |   __|  |__
 *  |  |  |  |  |
 *  |__|  |  |  |__
 *  |  |  |  |  |  |
 *  ---------------------
 *   0  1  2  3  4  5
 *
 *  Again, the answer to this given tower array, should be YES.
 *
 *  Approach:
 *    1) Brute force:
 *       * We know the height of tower that one is one
 *       * We know the length of the array
 *       * Explore all the possible ways that one can jump off to
 *         * If the current tower height one is on is 5, then there are 5 possible choices to jump from
 *         * Explore every single one of those choices to see where it leads to using recursion
 *
 *    2) A more optimized version
 *       * Given all the possible towers to jump to, is there a way to figure which one is more optimal
 *         given the information so far.
 *       * Or can we use DP (memoization to help speeding things up)
 */
public class TowerHopper {

    public static void main(String[] args) {
        System.out.printf("%s\n", TowerHopper.class.getName());

        test(new int[] {4,2,0,0,2,0}, true);
        test(new int[] {4,2,0,0,1,0}, false);
        test(new int[] {1,3,5,3,1}, true);
        test(new int[] {1,3,5,3,1,0,4,1,0,0}, true);
        test(new int[] {1,3,5,3,1,0,2,1,0,0}, false);
    }

    private static void test(int[] towers, boolean expectedAnswer) {
        boolean actualAnswer = bruteForce(towers, 0);
        System.out.printf("Towers: %s, expected answer: %b, actual answer: %b\n",
                Arrays.toString(towers), expectedAnswer, actualAnswer);

        Assert.assertEquals(actualAnswer, expectedAnswer);

        boolean dpActualAnswer = dp(towers);
        System.out.printf("dpActualAnswer: %b\n", dpActualAnswer);

        Assert.assertEquals(dpActualAnswer, expectedAnswer);
    }

    /**
     * The brute force way to explore every possible paths.  Run time would
     * be really bad
     * @param towers
     * @param towerIndex
     * @return
     */
    private static boolean bruteForce(int[] towers, int towerIndex) {

        if (towerIndex >= towers.length) {
            return false;
        }

        int towerHeight = towers[towerIndex];
        if (towerHeight == 0) {
            return false;
        }

        if ((towerHeight + towerIndex) >= towers.length) {
            return true;
        }

        //System.out.println("bruteForce: " + towerIndex);
        // for each possible value of tower height, try them out
        for (int i = 1; i <= towerHeight; i++) {
            boolean isPossible = bruteForce(towers, towerIndex+i);
            if (isPossible) {
                return true;
            }
        }
        return false;
    }

    /**
     * The brute force way to explore every possible paths.  Run time would
     * be really bad
     * @param towers
     * @return
     */
    private static boolean dp(int[] towers) {
        int[] memoi = new int[towers.length];
        Arrays.fill(memoi, -1);
        return dpHelper(towers, 0, memoi);
    }

    private static boolean dpHelper(int[] towers, int towerIndex, int[] memoi) {

        if (towerIndex >= towers.length) {
            return false;
        }

        int towerHeight = towers[towerIndex];
        if (towerHeight == 0) {
            return false;
        }

        if ((towerHeight + towerIndex) >= towers.length) {
            return true;
        }

        if (memoi[towerIndex] != -1) {
            return (memoi[towerIndex] == 1);
        }

        System.out.println("dp: " + towerIndex);

        for (int i = 1; i <= towerHeight; i++) {
            boolean isPossible = dpHelper(towers, towerIndex+i, memoi);
            if (isPossible) {
                memoi[towerIndex] = 1;
                return true;
            }
        }

        memoi[towerIndex] = 0;
        return false;
    }
}
