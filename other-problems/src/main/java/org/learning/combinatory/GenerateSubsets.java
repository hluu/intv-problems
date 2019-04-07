package org.learning.combinatory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generate combinations
 */
public class GenerateSubsets {
    public static void main (String[] args) {
        System.out.println("Hello Java");

        //printNumbers(new int[] {1,2,3,4});

        printNumbers2(new int[] {1,2,3,4}, 0, "");

        generateCombs(new int[] {1,2,3,4});
    }

    /**
     * This one will create duplicates
     *
     * @param input
     * @param idx
     * @param prev
     */
    private static void printNumbers2(int[] input, int idx, String prev) {
        if (idx == input.length) {
            return;
        }

        System.out.println(prev);

        printNumbers2(input, idx+1, prev);
        printNumbers2(input, idx+1, prev + input[idx]);
    }

    /**
     * O(2^n) - generate subsets
     */
    private static void generateCombs(int[] input) {

        if (input == null || input.length == 0) {
            return;
        }

        List<List<Integer>> result = generateCombs(input, 0);

        System.out.println("=======================");
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

    private static List<List<Integer>> generateCombs(int[] input, int idx) {

        // base case
        if (idx == input.length) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }

        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> tmpList : generateCombs(input, idx+1)) {
            // exclude
            result.add(new ArrayList<>(tmpList));

            // include
            tmpList.add(0,input[idx]);
            result.add(new ArrayList<>(tmpList));
        }

        return result;
    }
}
