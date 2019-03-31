package org.learning.numbers;

import java.util.Arrays;

/**
 * Given an array of integers, return a new array such that each element at index i
 * of the new array is the product of all the numbers in the original array except
 * the one at i.
 *
 * For example,
 *  input: [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24].
 *  input: [3, 2, 1], the expected output would be [2, 3, 6]
 *
 * Approach:
 *  - product of a[0...n] => p
 *  - output array[p/a[0].....p/a[n]] => this require division
 *
 * Follow-up: what if you can't use division?
 */
public class ProductWithoutElementAtIndex {

    public static void main(String[] args) {
        System.out.println(ProductWithoutElementAtIndex.class.getName());

        test(new int[] {3,2,1}, new int[] {2,3,6});
        test(new int[] {1,2,3,4,5}, new int[] {120, 60, 40, 30, 24});
    }

    private static void test(int[] input, int[] expected) {
        System.out.printf("\n**** input: %s\n", Arrays.toString(input));

        int actual1[] = bruteForce(input);
        int actual2[] = withoutDivision(input);

        System.out.printf("expected: %s, actual1: %s, , actual2: %s\n",
                Arrays.toString(expected),
                Arrays.toString(actual1),
                Arrays.toString(actual2));
    }

    /**
     * Brute force O(n) and use division
     *
     * @param input
     * @return
     */
    private static int[] bruteForce(int[] input) {
        if (input == null || input.length < 2) {
            return  input;
        }


        int product = 1;

        for (int v : input) {
            product = product * v;
        }

        int[] output = new int[input.length];

        // need index
        for (int i = 0; i < output.length; i++) {
            output[i] = product / input[i];
        }

        return output;
    }

    /**
     * The brute force solutions uses the division operator to remove it value from
     * the product.  What can we do when that is not allowed.
     *
     * Well, if we know the product of numbers before and after the i prod[0..i-1] and
     * prod[i+1...n] then we can just multiply them together.
     *
     * How do we build that?  one is built from left to right, and the other is build from
     * right to left
     *
     *
     * @param input
     * @return
     */
    private static int[] withoutDivision(int[] input) {
        int[] leftToRight = leftToRight(input);
        int[] rightToLeft = rightToLeft(input);

        int[] output = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            output[i] = leftToRight[i] * rightToLeft[i];
        }
       return output;
    }

    private static int[] leftToRight(int[] input) {
        int[] output = new int[input.length];

        // [3,2,1]  => [1,3,6]
        output[0] = 1;

        for (int i = 1; i < input.length; i++) {
            output[i] = output[i-1] * input[i-1];
        }

        return output;
    }

    private static int[] rightToLeft(int[] input) {
        int[] output = new int[input.length];

        // [3,2,1]  => [2,1,1]
        output[input.length-1] = 1;

        for (int i = input.length-2; i >= 0; i--) {
            output[i] = output[i+1] * input[i+1];
        }

        return output;
    }
}
