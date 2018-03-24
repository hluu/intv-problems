package my.leetcode.medium;

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
 */
public class JumpingGameAdv {
    public static void main(String[] args) {
        System.out.println(JumpingGameAdv.class.getName());

        int[] input = {2,3,1,1,4};
        test(input ,2);


        test(new int[] {0} ,0);
       /* test(new int[] {1} ,1);

        test(new int[] {3,1,1,1} ,2);
        test(new int[] {1,2,0,2} ,2);

        test(new int[] {1} ,0);


        test(new int[] {1,2} ,1);
        test(new int[] {1,1,1,1} ,3);

        test(new int[] {2,3,0,1,4} ,2);
        */
        test(new int[] {4,1,1,3,1,1,1} ,2);

        test(new int[] {1,3,6,3,2,3,6,8,9,5} ,4);


    }

    private static void test(int[] input, int expectedCount) {
        System.out.printf("\ninput: %s\n", Arrays.toString(input));

        int actualCount = minJump(input);

        System.out.printf("expected count: %d, actual count: %d\n",
                expectedCount, actualCount);


        int actualCount2 = jump(input);

        System.out.printf("expected count: %d, actual count 2: %d\n",
                expectedCount, actualCount2);

    }

    /**
     * minJumps(start, end) = Min ( minJumps(k, end) ) for all k reachable from start
     *
     * @param input
     * @return
     */
    private static int minJump(int[] input) {

        if (input == null || input.length == 0) {
            return 0;
        }

        if (input.length == 1) {
            return 0;
        }

        int minSoFar = Integer.MAX_VALUE;

        for (int i = 0; i < input.length; i++) {
            if (input[i] > 0 && (i < minSoFar)) {
                int jumps = i + countJumps(input, i, 0);
                minSoFar = Integer.min(minSoFar, jumps);
            }


        }

        return minSoFar;
    }

    /**
     * Helper method - count number of jumps at startIdx
     *
     * @param input
     * @param startIdx
     * @param noJumps
     * @return
     */
    private static int countJumps(int[] input, int startIdx, int noJumps) {

        // using iterative instead of recursion to avoid stack overflow
        while (startIdx < (input.length - 1)) {
            // handle the case when value is 0
            if (input[startIdx] == 0) {
                return Integer.MAX_VALUE;
            } else {
                startIdx = startIdx + input[startIdx];
                noJumps++;
            }
        }

        return noJumps;


        // lesson learn here - recursion will cause stack overflow if each value is 1
        // and the input array is very large
        /*
        if (input[startIdx] == 0) {
            return noJumps;
        } else {
            return countJumps(input, startIdx + input[startIdx], noJumps + 1);
        }*/
    }

    public static int jump(int[] A) {
        int sc = 0;
        int e = 0;
        int max = 0;
        for(int i=0; i<A.length-1; i++) {
            max = Math.max(max, i+A[i]);
            if( i == e ) {
                sc++;
                e = max;
            }
        }
        return sc;
    }


}
