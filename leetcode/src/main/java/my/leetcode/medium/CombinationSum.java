package my.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and
 * a target number (target), find all unique combinations in candidates
 * where the candidate numbers sums to target.
 *
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 * Approach:
 *  - This is a classic backtracking problem with repeat
 *
 */
public class CombinationSum {

    public static void main(String[] args) {
        System.out.println(CombinationSum.class.getName());

        test(new int[] {2,3,6,7}, 10);
        test(new int[] {2,3,6,7}, 7);
    }

    private static void test(int[] input, int target) {
        System.out.println("\ninput: " + Arrays.toString(input));

        List<List<Integer>> result = combinationSum(input, target);

        System.out.println(result);
    }

    private static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList();

        if (candidates == null || candidates.length == 0) {
            return result;
        }

        combinationSumHelper(candidates, target, 0, new ArrayList<Integer>(), result);

        return result;

    }

    private static void combinationSumHelper(int[] candidates, int target,  int idx,
                                             List<Integer> tmpResult,
                                             List<List<Integer>> collector) {

        if (idx >= candidates.length) {
            return;
        }

        if (target == 0) {
            collector.add(new ArrayList(tmpResult));
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            if (target - candidates[i] >= 0) {
                tmpResult.add(candidates[i]);

                combinationSumHelper(candidates, target - candidates[i], i, tmpResult, collector);

                tmpResult.remove(tmpResult.size() - 1);
            }
        }
    }

}
