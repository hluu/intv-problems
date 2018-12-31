package my.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You are given n pairs of numbers. In every pair, the first number is
 * always smaller than the second number.
 *
 * Now, we define a pair (c, d) can follow another pair (a, b)
 * if and only if b < c. Chain of pairs can be formed in this fashion.
 *
 * Given a set of pairs, find the length longest chain which can be formed.
 * You needn't use up all the given pairs. You can select pairs in any order.
 *
 * Input: [[1,2], [2,3], [3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4]
 *
 * Brute force:
 * - if the pairs are sorted by the first element, then therefore the
 *   second elments are sorted.
 *
 * - for each pair in the input, treat that as the beginning of the chain
 *   search through remaining pairs
 *   - chain them if possible and keep running count
 *   - if not, move to next unchained pairs
 *
 */
public class MaximumLenPairChain {
    public static void main(String[] args) {

       // test(new int[][] {{1,2}, {2,3}, {3,4}}, 2);
        test(new int[][] {{3,4}, {1,2}, {2,3} }, 2);
        test(new int[][] {{-10,-8},{8,9},{-5,0},{6,10},{-6,-4},{1,7},{9,10},{-4,7}}, 4);
        test(new int[][] {{7,9},{4,5},{7,9},{-7,-1},{0,10},{3,10},{3,6},{2,3}}, 4);
        test(new int[][] {{5,9},{-1,8},{-6,-2},{8,9},{4,8},{3,6},{2,6},{5,9},{-1,6},{-8,-7}}, 4);
    }

    private static void test(int[][] input, int expected) {

        int actual = findLongestChain(input);
        System.out.printf("expected: %d, actual: %d\n",
                expected, actual);
    }

    private static int findLongestChain(int[][] pairs) {

        // this is pretty cool about sorting an array
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));

        for (int[] pair : pairs) {
            System.out.printf("%s, ", Arrays.toString(pair));
        }
        int pairCount = pairs.length;

        int maxCount = 0;

        int[] countCache = new int[pairs.length];
        Arrays.fill(countCache,1);
        //System.out.printf("countCache: %s\n", Arrays.toString(countCache));

        for (int curIdx = 1; curIdx < pairCount; curIdx++) {
            int prevIdx = curIdx-1;
            // going back until we find (c > b) or while (c <= b) && prevIdx >= 0
            while ((prevIdx >= 0) && (pairs[curIdx][0] <= pairs[prevIdx][1])) {
                prevIdx--;
            }

            if (prevIdx >= 0) {
                countCache[curIdx] = Math.max(countCache[curIdx], countCache[prevIdx]) + 1;
            }
            /*
            for (int j = i-1; j >= 0; j--) {
                if (pairs[i][0] > pairs[j][1]){
                    countCache[i] = Math.max(countCache[i], countCache[j]) + 1;
                    break;
                }
            }*/
            maxCount = Math.max(maxCount, countCache[curIdx]);
        }

        return maxCount;
    }
}
