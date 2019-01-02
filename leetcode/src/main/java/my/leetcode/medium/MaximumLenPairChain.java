package my.leetcode.medium;

import org.junit.Assert;

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
 *   second elements are sorted.
 *
 * - for each pair in the input, treat that as the beginning of the chain
 *   search through remaining pairs
 *   - chain them if possible and keep running count
 *   - if not, move to next unchained pairs
 *
 * Dynamic programming:
 *  - optimal subproblem
 *    If a chain of length k ends at some pairs[i], and pairs[i][1] < pairs[j][0],
 *    we can extend this chain to a chain of length k+1.
 *  - overlapping subproblem
 *
 */
public class MaximumLenPairChain {
    public static void main(String[] args) {

        test(new int[][] {{1,2}, {2,3}, {3,4}}, 2);
        test(new int[][] {{3,4}, {1,2}, {2,3} }, 2);
        test(new int[][] {{-10,-8},{8,9},{-5,0},{6,10},{-6,-4},{1,7},{9,10},{-4,7}}, 4);
        test(new int[][] {{7,9},{4,5},{7,9},{-7,-1},{0,10},{3,10},{3,6},{2,3}}, 4);
        test(new int[][] {{5,9},{-1,8},{-6,-2},{8,9},{4,8},{3,6},{2,6},{5,9},{-1,6},{-8,-7}}, 4);
    }

    private static void test(int[][] input, int expected) {

        int actual = findLongestChainBUDP(input);
        System.out.printf("expected: %d, actual: %d ",
                expected, actual);

        Assert.assertEquals(expected, actual);

        int actual2 = findLongestChainOther(input);
        System.out.printf("actual2: %d ", actual2);

        Assert.assertEquals(expected, actual2);

        int actualDP2 = findLongestChainDP2(input);
        System.out.printf("actualDP2: %d\n", actualDP2);

        Assert.assertEquals(expected, actualDP2);
    }

    private static int findLongestChainBUDP(int[][] pairs) {

        // this is pretty cool about sorting an array
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));

        for (int[] pair : pairs) {
            System.out.printf("%s, ", Arrays.toString(pair));
        }
        int pairCount = pairs.length;

        int maxCount = 0;

        int[] countCache = new int[pairs.length];
        // every single element is a chain of 1
        Arrays.fill(countCache,1);
        //System.out.printf("countCache: %s\n", Arrays.toString(countCache));

        for (int curIdx = 1; curIdx < pairCount; curIdx++) {
            int prevIdx = curIdx-1;
            // going back until we find (c > b) or while (c <= b) && prevIdx >= 0
            while ((prevIdx >= 0) && (pairs[curIdx][0] <= pairs[prevIdx][1])) {
                prevIdx--;
            }

            if (prevIdx >= 0) {
                // do this only when inside the boundary of first element
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

    /**
     * Runtime: O(n^2), space: O(n)
     * @param pairs
     * @return
     */
    public static int findLongestChainDP2(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);

        int N = pairs.length;
        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        //(0,1), (0,2), (0,3), (0,4)... (0,N)
        for (int j = 1; j < N; ++j) {
            // going from 0 to j
            for (int i = 0; i < j; ++i) {
                if (pairs[i][1] < pairs[j][0])
                    // update dp[j]
                    dp[j] = Math.max(dp[j], dp[i] + 1);
            }
        }

        int ans = 0;
        for (int x: dp) {
            if (x > ans) ans = x;
        }
        return ans;
    }


    /**
     * This approach sorts the pairs by the second element in the array.
     *
     * @param pairs
     * @return
     */
    public static int findLongestChainOther(int[][] pairs) {
        if(pairs.length == 0) return 0;
        Arrays.sort(pairs, (a,b)->a[1]-b[1]);

        System.out.printf("\n*** findLongestChainOther ***\n");
        for (int[] pair : pairs) {
            System.out.printf("%s, ", Arrays.toString(pair));
        }

        int prevB = pairs[0][1];
        int count = 1;

        for(int i = 1 ; i < pairs.length; i++){
            int nextC = pairs[i][0];
            int nextD = pairs[i][1];

            if(nextC > prevB){
                count++;
                prevB = nextD;
            }
        }
        return count;
    }
}
