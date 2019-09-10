package org.learning.backtracking;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Permute a list of integer
 *
 * [1,2,3]
 *
 * [1,2,3]
 * [1,3,2]
 * [2,1,3]
 * [2,3,1]
 * [3,1,2]
 * [3,2,1]
 *
 * - try the backtrack one
 *   * https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
 */
public class PermutationWithIntegerList {
    public static void main(String[] args) {
        System.out.println(PermutationWithIntegerList.class.getName());

        List<Integer> input = Arrays.asList(1,2,3);
        List<Integer> expect1 = Arrays.asList(1,2,3);
        List<Integer> expect2 = Arrays.asList(1,3,2);
        List<Integer> expect3 = Arrays.asList(2,1,3);
        List<Integer> expect4 = Arrays.asList(2,3,1);
        List<Integer> expect5 = Arrays.asList(3,1,2);
        List<Integer> expect6 = Arrays.asList(3,2,1);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(expect1);
        expected.add(expect2);
        expected.add(expect3);
        expected.add(expect4);
        expected.add(expect5);
        expected.add(expect6);

        test(input, expected);

        List<Integer> input2 = Arrays.asList(5,6);
        List<Integer> expect21 = Arrays.asList(5,6);
        List<Integer> expect22 = Arrays.asList(6,5);
        List<List<Integer>> expected2 = new ArrayList<>();
        expected2.add(expect21);
        expected2.add(expect22);

        test(input2, expected2);
    }

    private static void test(List<Integer> input, List<List<Integer>> expected) {
        System.out.println("\n===== input: " + input);

        List<List<Integer>> actual = permute(input);

        System.out.println("expected: " + expected);
        System.out.println("actual: " + actual);

        Assert.assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            Assert.assertEquals(expected.get(i), actual.get(i));
        }
    }

    private static List<List<Integer>> permute(List<Integer> input) {
        List<List<Integer>> output = new ArrayList<>();

        permuteHelper(input, output, new ArrayList<>());

        return output;
    }

    private static void permuteHelper(List<Integer> input,
                                      List<List<Integer>> collector,
                                      List<Integer> sofar) {

        if (input.isEmpty()) {
            collector.add(new ArrayList<>(sofar));
            return;
        }

        for (int i = 0; i < input.size(); i++) {
            Integer value = input.get(i);

            sofar.add(value);

            List<Integer> remainList = new ArrayList<>(input.subList(0,i));
            remainList.addAll(input.subList(i+1, input.size()));

            permuteHelper(remainList, collector, sofar);

            sofar.remove(sofar.size()-1);
        }

    }
}
