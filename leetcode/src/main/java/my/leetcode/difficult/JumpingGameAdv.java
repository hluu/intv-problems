package my.leetcode.difficult;

import org.testng.Assert;

import java.util.Arrays;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of
 * the array. Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 * For example:
 * Given array A = [2,3,1,1,4]
 *
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1,
 * then 3 steps to the last index.)
 *
 * http://www.allenlipeng47.com/blog/index.php/2016/09/12/jump-game-ii/
 * https://leetcode.com/problems/jump-game-ii/description/
 *
 * https://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
 * tech-queries.blogspot.com/2011/08/find-optimal-number-of-jumps-to-reach.html
 *
 * Observation:
 *  - this is a DP problem
 *  - subproblem definition???
 *  - overlapping subproblems???
 *
 */
public class JumpingGameAdv {
    public static void main(String[] args) {
        System.out.println(JumpingGameAdv.class.getName());


        test(new int[] {1} ,0);
        test(new int[] {2,3,1,1,4} ,2);

        test(new int[] {2,0,2,4,6,0,0,3} ,3);

        test(new int[] {2,2,0,1} ,2);

        test(new int[] {0} ,0);

        test(new int[] {1,1,1,1} ,3);

        test(new int[] {2,3,0,1,4} ,2);

        test(new int[] {3,1,1,1} ,1);
        test(new int[] {1,2,0,2} ,2);


        test(new int[] {1,2} ,1);
        test(new int[] {1,1,1,1} ,3);

        test(new int[] {2,3,0,1,4} ,2);

        test(new int[] {4,1,1,3,1,1,1} ,2);

        test(new int[] {1,3,6,3,2,3,6,8,9,5} ,4);

        test(new int[] {5,1,1,5,1,1,1,1,1} ,2);

        test(new int[] {2,0,8,0,3,4,7,5,6,1,0,0,5,9,7,5,3,6} ,4);
        test(new int[] {10,9,8,7,6,5,4,3,2,1,1,0} ,2);

        // time exceeding
        test(new int[] {7,8,4,2,0,6,4,1,8,7,1,7,4,1,4,1,2,8,2,7,3,7,8,2,4,4,5,3,5,6,8,5,4,4,7,4,3,4,8,1,1,9,0,8,2} ,7);
        /* */
    }

    private static void test(int[] input, int expectedCount) {
        System.out.printf("\ninput: %s\n", Arrays.toString(input));

        int actualCount = jumpOptimized(input);

        System.out.printf("expected count: %d, actual count: %d\n",
                expectedCount, actualCount);

        Assert.assertEquals(actualCount, expectedCount);

        /*int actualCount2 = jumpBF(input);

        System.out.printf("expected count: %d, actualCount2: %d\n",
                expectedCount, actualCount2);*/

        int actualCount3 = jumpDP(input);

        System.out.printf("expected count: %d, actualCount3: %d\n",
                expectedCount, actualCount3);

        Assert.assertEquals(actualCount3, expectedCount);

        int actualCount4 = jumpOptimizedBFS(input);
        System.out.printf("expected count: %d, actualCount4: %d\n",
                expectedCount, actualCount4);

        Assert.assertEquals(actualCount4, expectedCount);

    }


    /**
     * minJumps(start, end) = Min ( minJumps(k, end) ) for all k reachable from start
     *
     * @param input
     * @return
     */
    private static int jumpDP(int[] input) {

        if (input == null || input.length == 0) {
            return 0;
        }

        if (input.length == 1) {
            return 0;
        }

        if (input[0]== 0) {
            return 0;
        }

        int[] cache = new int[input.length];
        Arrays.fill(cache, -1);
        return jumpDPHelper(input, 0, cache);
    }



    /**
     *
     * @param input
     * @param currentIdx
     * @param cache - index to number of jumps using currentIdx
     * @return
     */
    private static int jumpDPHelper(int[] input, int currentIdx,
                                      int[] cache) {

        // return from cache if we have it
        if (cache[currentIdx] != -1) {
            return cache[currentIdx];
        }

        // if input[currentIdx] == 0, then return MAX_VALUE
        int noJumps = Integer.MAX_VALUE;

        if (currentIdx + input[currentIdx] >= (input.length-1)) {
            // if # of jumps from currentIdx equal or past the end,
            // then # of jumps is 1;
            noJumps = 1;
        } else if (input[currentIdx] != 0) {

            int start = currentIdx + 1;
            int end = currentIdx + input[currentIdx];

            for (int k = start; k <= end; k++) {

                int tmpJumps = jumpDPHelper(input, k, cache);
                if (tmpJumps != Integer.MAX_VALUE) {
                    // handle the edge case when tmpJumps is MAX_VALUE
                    tmpJumps = 1 + tmpJumps;
                }
                // take the min. # of jumps among all the options
                noJumps = Math.min(noJumps, tmpJumps);
            }
        }

        // put in the cache
        cache[currentIdx] = noJumps;

        return noJumps;

    }

    /**
     * minJumps(start, end) = Min ( minJumps(k, end) ) for all k reachable from start
     *
     * @param input
     * @return
     */
    private static int jumpBF(int[] input) {

        if (input == null || input.length == 0) {
            return 0;
        }

        if (input.length == 1) {
            return 0;
        }

        if (input[0]== 0) {
            return 0;
        }

        return jumpBFHelper(input, 0);
    }

    /**
     * Helper method - count number of jumps at currentIdx.
     * Explore all the different paths from currentIdx and pick the smallest
     * one.
     *
     * This is a from DFS.
     *
     * Notic there is a lot of overlapping and repeated computation
     *
     * @param input
     * @param currentIdx
     * @return minimum jump from the @currentIdx
     */
    private static int jumpBFHelper(int[] input, int currentIdx) {

        if (input[currentIdx] == 0) {
            return Integer.MAX_VALUE;
        }

        if (currentIdx + input[currentIdx] >= (input.length-1)) {
            return 1;
        }

        int start = currentIdx+1;
        int end = currentIdx + input[currentIdx];

        int noJumps = Integer.MAX_VALUE;
        for (int k = start; k <= end; k++) {

            int tmpJumps = jumpBFHelper(input, k);
            if (tmpJumps != Integer.MAX_VALUE) {
                tmpJumps = 1 + tmpJumps;
            }
            noJumps = Math.min(noJumps, tmpJumps);
        }

        return noJumps;
    }

    public static int jumpOptimized(int[] input) {
        int jumpCount = 0;
        int prevJumpToLoc = 0;
        int max = 0;

        for(int idx=0; idx< input.length-1; idx++) {

            max = Math.max(max, idx+input[idx]);

            if( idx == prevJumpToLoc ) {
                jumpCount++;
                prevJumpToLoc = max;
            }
        }
        return jumpCount;
    }


    /**
     * This is very cryptic, not easy to understand
     *
     * @param input
     * @return
     */
    private static int jumpOptimizedBFS(int input[]) {
        if(input.length <2) {
            return 0;
        }

        int size = input.length;

        int level=0, currentMax=0, idx=0, nextMax=0;

        while(currentMax-idx+1>0){		//nodes count of current level>0
            level++;

            for(; idx <= currentMax; idx++) {
                //traverse current level , and update the max reach of next level

                nextMax=Math.max(nextMax,input[idx]+idx);

                if(nextMax >= size-1) {
                    return level;   // if last element is in level+1,  then the min jump=level
                }
            }

            currentMax=nextMax;
        }
        return 0;
    }
}
