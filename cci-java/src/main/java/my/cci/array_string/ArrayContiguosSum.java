package my.cci.array_string;

import org.testng.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Compute the number of contiguous set of numbers that are added up to
 * the given sum
 */
public class ArrayContiguosSum {

    public static void main(String[] args) {
        System.out.println(ArrayContiguosSum.class.getName());

        int input[] = new int[] {8, 10,5,1,2,-1,7,1};
       // testContiguousSum(input, 8, 4);

        int input2[] = new int[] {1, 4, 5, -2, 9, 6, 3, -9};
        testContiguousSum(input2, 9, 4);

    }


    private static void testContiguousSum(int[] input, int target, int expected) {
        System.out.printf("\n==== %s, target: %d\n", Arrays.toString(input), target);

        int result2 = contiguousSumOptimized(input, target);

        System.out.println("expected: " + expected + " result2: " + result2);

        Assert.assertEquals(result2, expected);
    }



    /**
     * This version uses additional space to improve runtime to be O(n).
     *
     * First we need a map to maintain the running count and number of times
     * we've seen this running count
     *
     *  map<sum, count>
     *
     * The idea is to iterate through each element,
     *   * keep a running sum
     *   * perform a lookup of map.get(running sum - target)
     *      * if exists, add one to it, else set the value as 0
     *   * add the running sum to map with value as 0
     *
     *
     * @param input
     * @param target
     * @return
     */
    private static int contiguousSumOptimized(int[] input, int target) {
        Map<Integer, Integer> sumToCount = new HashMap<>();
        // seed the 0 with 0 to handle case when first element matches the target
        sumToCount.put(0, 0);

        int runningSum = 0;
        int count = 0;
        for (int value : input) {
            runningSum += value;

            int lookupSum = runningSum-target;
            Integer tmpCnt = sumToCount.get(lookupSum);
            if (tmpCnt == null) {
                // first time
                tmpCnt = 0;
            } else {
                tmpCnt = tmpCnt + 1;
            }

            sumToCount.put(lookupSum, tmpCnt);
            // don't overwrite when it already exists
            if (!sumToCount.containsKey(runningSum)) {
                sumToCount.put(runningSum, 0);
            }
        }


        System.out.println(sumToCount);
        for (Integer v : sumToCount.values()) {
            count += v;
        }

        return count;

    }
}
