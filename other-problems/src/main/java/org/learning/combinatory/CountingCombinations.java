package org.learning.combinatory;

import java.util.Arrays;

/**
 * Time complexity O(2^n),
 * Space complexity O(n) - just the height of the recursion
 */
public class CountingCombinations {
    public static void main(String[] args) {

        test(new int[] {1}, 2);
        test(new int[] {1,2,3}, 8);
        test(new int[] {1,2,3,4}, 16);

    }

    private static void test(int[] input, int expected) {
        System.out.println("\ninput: " + Arrays.toString(input));

        int acutal = countCombo(input);

        System.out.printf("expected: %d, actual: %d\n", expected, acutal);
    }
    private static int countCombo(int[] input) {
        if (input == null) {
            return 0;
        }
        return countComboHelper(input, 0);
    }

    private static int countComboHelper(int[] input, int idx) {
        if (idx == input.length) {
            return 1;
        }

        int exclude = countComboHelper(input, idx+1);
        int include = countComboHelper(input, idx+1);

        return exclude + include;
    }
}
