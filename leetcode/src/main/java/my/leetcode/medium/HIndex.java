package my.leetcode.medium;

import org.testng.Assert;

import java.util.Arrays;

/**
 * Given an array of citations (each citation is a non-negative integer)
 * of a researcher, write a function to compute the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: "A scientist has
 * index h if h of his/her N papers have at least h citations each, and
 * the other N − h papers have no more than h citations each."
 *
 * Example:
 *
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation:
 * [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
 * received 3, 0, 6, 1, 5 citations respectively.
 *
 * Since the researcher has 3 papers with at least 3 citations each and the remaining
 * two with no more than 3 citations each, her h-index is 3.
 *
 * Observation:
 * - the maximum h-index is the same as the number of papers an author has
 * - the minimum h-index is 0 - when has an author has no citation all his/her papers
 * - brute force solution - O(n^2)
 *   - iterate through each element in the array
 *   - h = min(len, arr[i])
 *   - scan (left and right) to see if there are at least (h-1) number of >= h
 *
 * - a more optimized O(nlogn) way
 *   - [3,0,6,1,5] => [6,5,3,1,0]
 *   - if the # of citations are sorted in reverse order, how can we take advantage of that
 *   - citations: => [6,5,3,1,0]
 *   -   indexes: => [0,1,2,3,4]
 *
 *
 *  - citations: => [9,5,3,1,0]
 *  -   indexes: => [0,1,2,3,4]
 *
 *  - citations: => [4,4,0,0,0]
 *  -   indexes: => [0,1,2,3,4]
 *
 *  - citations: => [0,1,3,6,6]
 *  -   indexes: => [4,5,3,1,0]
 */
public class HIndex {
    public static void main(String[] args) {
        System.out.println(HIndex.class.getName());

        test(new int[] {1,4,3,6,6}, 3);
        test(new int[] {0,3,3,6,6}, 3);
        test(new int[] {3,0,6,1,5}, 3);

        test(new int[] {5,5,5,5,5}, 5);
        test(new int[] {50,50,50,50,50}, 5);


        test(new int[] {3,0,600,1,5}, 3);
        test(new int[] {4,4,0,0}, 2);
        test(new int[] {4,0,0,4}, 2);
        test(new int[] {4,0,1,4}, 2);

        test(new int[] {}, 0);
        test(new int[] {0,0,0}, 0);

        test(new int[] {55}, 1);


        test(new int[] {1,3,4,6,6,6}, 4);
    }

    private static void test(int[] input, int expected) {
        System.out.println("\ninput: " + Arrays.toString(input));

        int actual = hIndexBF(input);

        System.out.printf("expected: %d, actual: %d\n", expected, actual);

        Assert.assertEquals(actual, expected);

        Arrays.sort(input);

        int actual2 = hIndexLinear(input);
        System.out.printf("expected: %d, actual2: %d\n", expected, actual2);
    }


    private static int hIndexBF(int[] input) {
        if (input == null || input.length == 0) {
            return 0;
        }

        // we are looking for max
        int hIndexSoFar = -1;

        for (int i = 0; i < input.length; i++) {
            if (input[i] == 0) {
                hIndexSoFar = Math.max(hIndexSoFar, 0);
                continue;
            }

            int count = 1;
            // max hindex is equivalent to the number papers
            // even if the numCitation is way larger thant he # of papers
            int numCitation = Math.min(input[i], input.length);

            // go left
            int idx = i-1;
            while (idx >= 0) {
                if (input[idx] >= numCitation) {
                    count++;
                }
                idx--;
            }

            // go right
            idx = i + 1;
            while (idx < input.length) {
                if (input[idx] >= numCitation) {
                    count++;
                }
                idx++;
            }

            // local hIndex is the min of the count and numCitation
            int localHIndex = Math.min(count, numCitation);

            hIndexSoFar = Math.max(hIndexSoFar, localHIndex);
            //System.out.printf("i: %d, hIndexSoFar: %d\n", i, hIndexSoFar);
        }

        return hIndexSoFar;
    }

    /**
     * Assuming the input is sorted in ascending order.
     *
     * hIndex must be in this range
     *
     *  0 <= hIndex <= len
     *
     * input => {1,3,4,6,6}
     * index => {4,3,2,1,0}
     *
     *  {1,3,4,6,6,6}
     *
     * @param input
     * @return
     */
    private static int hIndexLinear(int[] input) {
        if (input == null || input.length == 0) {
            return 0;
        }

        for(int i = 0; i<input.length; i++){
            // the first condition
            //  # of citation >= number of papers
            if(input[i] >= input.length - i){
                return input.length -i;
            }
        }
        return 0;
    }


}
