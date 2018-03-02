package my.cci.recursion_dp;

import org.testng.Assert;

import java.util.Arrays;
import java.util.Collections;


/**
 * Given an array of integers (not sorted), find the length of the longest nondecreasing subsequences in
 * the array.
 *
 * For example:
 *
 *   {0, 8, 4, 12, 2, 10, 6, 14, 1, 9}
 *
 * There are 2 longest nondecreasing subsequences: {0,4,10,14}, {0,2,6,9}
 *
 * Approach:
 *  * Brute force - to enumerate all possible subsequences and test each one for being nondecreasing and
 *                  figure out longest ones.  This requires 2^n run time
 *  * DP way - let L[j] be the longest subsequence of element j, which is defined as
 *                L[j] = 1 + max(L[i] if (i < j and A[i] < A[j])
 *             meaning the length at elememt j is based on the max length of all the elements before j
 *     * So we need a table to store the longest subsequence length at each element
 *     * This implies the run time is n^2 - n being the length of the array
 *
 *
 */
public class LongestNondecreasingSubsequence {

    public static void main(String[] args) {
        System.out.println(LongestNondecreasingSubsequence.class.getName());

        //int[] input = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9};
        //int[] input = {5, 4, 10, 6, 15, 3, 16, 1, 17};
        int[] input = {1, 1};

        test(new int[] {1,1},1);
        test(new int[] {2,1},1);
        test(new int[] {1,2},2);

        test(new int[] {3,2,5},2);
        test(new int[] {3,2,5,4},2);

        test(new int[] {3,2,5,4,7},3);
        test(new int[] {3, 10, 2, 1, 20},3);

        test(new int[] {50, 3, 10, 7, 40, 80},4);
        test(new int[] {0, 8, 4, 12, 2, 10, 6, 14, 1, 9},4);

        test(new int[] {10, 22, 9, 33, 21, 50, 41, 60, 80},6);



    }

    private static void test(int[] input, int expectedLen) {
        System.out.printf("input: %s\n", Arrays.toString(input));

        int actualLenFromBF = bruteForce(input);

        int actualLenFromDP = dpWay(input);

        System.out.printf("expected len: %d : actualLenFromBF: %d, actualLenFromDP: %d \n",
                expectedLen, actualLenFromBF, actualLenFromDP);

        Assert.assertEquals(actualLenFromBF, expectedLen);
        Assert.assertEquals(actualLenFromDP, expectedLen);
    }

    /**
     * Use a temporary table to store the length of each element.
     * The length of next element is based on the max length of each of the previous element
     * provided that (i < j and A[i] < A[j])
     *
     * Runtime is O(n^2) and space is O(n)
     *
     *   {3,2,5,4}
     * @param input
     * @return
     */
    private static int dpWay(int[] input) {
        int[] lenTable = new int[input.length];

        // first element, length is of one by default => initial state
        Arrays.fill(lenTable, 1);

        for (int i = 1; i < input.length; i++) {
            // looking back from 0 to i - 1;
            int maxLenSoFar = 0;
            for (int j = 0; j < i; j++) {
                if (input[i] > input[j]) {
                    lenTable[i] = Math.max(lenTable[i], lenTable[j] + 1);
                }
            }
            System.out.println("lenTable: " + Arrays.toString(lenTable));
        }

        return lenTable[lenTable.length-1];
        /*int max = -1;
        for (int len : lenTable) {
            if (len > max) {
                max = len;
            }
        }

        return max;*/
    }


    /**
     * Enumerate all the possible subsequences.
     *
     * ASSUMPTION: the values in the array are all positive integers
     *
     * How to do that?
     *
     * 1) One approach is to use the binary number approaches
     *    * For an array of length n - enumerate from 0 to 2^n (all the bit combinations)
     *    * Binary goes from 0 to 2^n-1, otherwise you will run into array index out of bound
     *    * For each binary number, copy value at index i bit position, for those i bit that is 1
     *    * Make sure to clear out the array with 0 each time
     *
     *
     * @param input
     * @return
     */
    private static int bruteForce(int[] input) {
        if (input == null || input.length == 0) {
            return 0;
        }

        // Math.pow returns double
        int numSubsequences = (int)Math.pow(2, input.length);

        int tmpArr[] = new int[input.length];
        Arrays.fill(tmpArr, Integer.MIN_VALUE);

        //System.out.println("numSubsequences: " + numSubsequences);
        int maxLen = 0;
        for (int i = 0; i < numSubsequences; i++) {

            int tmpNum = i;
            int index = 0;
            // for each bit in tmpNum that is one, copy the
            // value at that index over to tmpArr
            while (tmpNum > 0) {
                if ((tmpNum & 1) == 1) {
                    tmpArr[index] = input[index];
                }
                index++;
                // shift to the right by one bit;
                tmpNum = tmpNum >> 1;
            }

            //System.out.println("maxLen: " + maxLen + " - " + Arrays.toString(tmpArr));

            maxLen = Math.max(maxLen, maxIncreasing(tmpArr));

            Arrays.fill(tmpArr,Integer.MIN_VALUE);

        }
        return maxLen;
    }

    private static int maxIncreasing(int[] tmpArr) {
        // figure out the increase subsequence in the tmpArr;
        int maxLen = 0;
        int tmpLen = 0;
        int prevValue = Integer.MIN_VALUE;
        for (int j = 0; j < tmpArr.length; j++) {
            if (tmpArr[j] == Integer.MIN_VALUE) {
                continue;
            }
            if (tmpArr[j] > prevValue) {
                tmpLen++;
            } else {
                maxLen = Math.max(maxLen, tmpLen);
                tmpLen = 0;
            }
            prevValue = tmpArr[j];
        }
        maxLen = Math.max(maxLen, tmpLen);

        return maxLen;
    }
}
