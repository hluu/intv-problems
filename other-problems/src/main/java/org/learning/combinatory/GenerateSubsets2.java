package org.learning.combinatory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This implementation passes down the collector
 */
public class GenerateSubsets2 {
    public static void main (String[] args) {
        System.out.println(GenerateSubsets2.class.getName());

        test(new int[] {1,2,3});
    }

    private static void test(int[] input) {
        System.out.println("input: " + Arrays.toString(input));


        List<List<Integer>> result = generateCombos(input);
        System.out.println("==== combinations ====");
        int count = 1;
        for (List<Integer> list : result) {
            System.out.println(count++ + " : " + list);
        }
    }

    private static List<List<Integer>> generateCombos(int[] input) {
        List<List<Integer>> collector = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        if (input == null || input.length < 1)
        {
            return collector;
        }

        generateComboHelper(input, 0, collector, path);

        return collector;
    }

    private static void generateComboHelper(int[] input,
                                            int idx,
                                            List<List<Integer>> collector,
                                            List<Integer> path) {
        // base case
        if (idx == input.length) {
            collector.add(path);
            return;
        }

        List<Integer> newPath = new ArrayList<>(path);
        newPath.add(input[idx]);

        generateComboHelper(input, idx+1, collector, path);
        generateComboHelper(input, idx+1, collector, newPath);


    }
}
