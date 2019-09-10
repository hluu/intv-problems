package org.learning.numbers;

import java.util.*;

/**
 * Give an array of numbers, determine which 3 numbers added up the given value.
 *
 * Given an array nums of n integers, are there elements a, b, c in nums such that
 * a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 *
 * https://leetcode.com/problems/3sum/
 *
 * For example:
 *
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * Questions to ask:
 * - Are the numbers unique? or not
 *
 * Approach:
 * - use three for loops to iterate through the array
 * - treat this as a search problem
 * - for each value in the array, search for a pair number that are added up
 *   to be the negative of that value.
 *   - when searching is needed, think about a fast way to look up using a hashset
 * - what about sorting these numbers first and use two pointers approach
 *
 * So there are three possible solutions
 */
public class ThreeNumberSum {
    public static void main(String[] args) {
        System.out.println(ThreeNumberSum.class.getName());
        List<List<Integer>> expected1 = new ArrayList<>();
        expected1.add(Arrays.asList(-1, 0, 1));
        expected1.add(Arrays.asList(-1, -1, 2));

        test(new int[] {-1, 0, 1, 2, -1, -4}, expected1 );
    }

    private static void test(int[] input, List<List<Integer>> expected) {
        System.out.println("\n==== test =====");
        System.out.printf("input: %s\n", Arrays.toString(input));


        List<List<Integer>> actual1 = threeLoops(input);
        List<List<Integer>> actual2 = twoLoops(input);

        System.out.printf("expected: %s, actual1: %s, actual2: %s\n ",
                expected, actual1, actual2);
    }

    /**
     * this is essentially scanning the array. runtime O(n^3)
     *
     * we could easily reduce to O(n^2) by doing a lookup for c
     * once we know a and b
     *
     *
     * @param input
     * @return
     */
    private static List<List<Integer>> threeLoops(int input[]) {

        List<List<Integer>> result = new ArrayList<>();

        if (input == null || input.length == 0) {
            return result;
        }

        // this essentially is scanning through the array
        for (int i = 0; i < input.length; i++) {
            for (int j = i+1; j < input.length; j++) {
                for (int k = j+1; k < input.length; k++) {
                    int threeSum = input[i] + input[j] + input[k];
                    if (threeSum == 0) {
                        List<Integer> tmpResult = new ArrayList<>();
                        tmpResult.add(input[i]);
                        tmpResult.add(input[j]);
                        tmpResult.add(input[k]);

                        result.add(tmpResult);
                    }
                }
            }
        }

        return result;

    }

    /**
     * Use set to look up the last number or c and make sure it is not
     * the same element as i and j
     *
     * @param input
     * @return
     */
    private static List<List<Integer>> twoLoops(int input[]) {

        List<List<Integer>> result = new ArrayList<>();

        Set<Integer> numberSet = new HashSet<Integer>();
        for (int v : input) {
            numberSet.add(v);
        }

        if (input == null || input.length == 0) {
            return result;
        }

        // this essentially is scanning through the array
        for (int i = 0; i < input.length; i++) {
            for (int j = i+1; j < input.length; j++) {

                int c = -(input[i] + input[j]);
                if (numberSet.contains(c)) {
                    List<Integer> tmpResult = new ArrayList<>();
                    tmpResult.add(input[i]);
                    tmpResult.add(input[j]);
                    tmpResult.add(c);

                    result.add(tmpResult);
                }
            }
        }

        return result;

    }
}
