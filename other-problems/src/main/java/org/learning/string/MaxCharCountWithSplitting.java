package org.learning.string;

import java.util.Arrays;

/**
 * Give a string with only 3 types of character r,b,w and they can be repeated.
 * Character 'w' can be replaced with either 'r' or 'b'
 *
 * For example: "rbrrwwbbbb"
 *
 * Split the characters in the a string at a point that gives use the maximum combined # of
 * consecutive characters on either side of the split.
 *
 * For the example:
 *   input "rbrrwwbbbb", ==> 8
 *   input "rbrbrwbw", ==> 3
 *   input "rbbbrwbw", ==> 5
 *
 * Approach:
 *  1) Brute force, to split at each character, count the # of consecutive characters on the left and the right
 *     Maintain the running max and update it if a large one is found. O(n^2)
 *
 *  2) Since there are only 2 unique characters, how can we leverage that info.?
 *     Essentially we want to have a table of count of consecutive letter 'r' from left to right
 *     and another table of consecutive letter 'b' from right to left.
 *     Then
 *
 *     "rbrrwwbbbb"
 *
 *     r = {1,0,1,2,3,4,0,0,0,0}  left -> right
 *     b = {0,1,0,0,6,5,4,3,2,1} right -> left
 *
 */
public class MaxCharCountWithSplitting {
    public static void main(String[] args) {

       //test("rbrrwwbbbb", 8);

        test("rbbbrwbw", 5);
        //test("brrrbwrw", 5);
    }

    private static void test(String input, int expectedCount) {

        System.out.println("****** test ********");
        int actualCount = countConsecChars(input);

        System.out.printf("input: %s, expected: %d, actual: %d\n", input, expectedCount, actualCount);

        int acctualCountFromBF = bruteForce(input);

        System.out.printf("bruteforce: expected %d, actual: %d\n", expectedCount, acctualCountFromBF);
    }

    /**
     * Brute force - for each split point count number of consecutive characters before and after
     *
     * @param input
     * @return
     */
    private static int bruteForce(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }

        if (input.length() == 1) {
            return 1;
        }

        // 012
        //"rbb"
        int maxCount = 0;
        for (int i = 1; i <= input.length()-1; i++) {
            int leftCount = countBackward(input, i-1, 0);
            int rightCount = countForward(input, i, input.length());

            int sum = leftCount + rightCount;
            if (sum > maxCount) {
                maxCount = sum;
            }
        }

        return maxCount;
    }

    /**
     * "rbrrwwbbbb"
     * @param input
     * @param from
     * @param to
     * @return
     */
    private static int countForward(String input, int from, int to) {
        int count = 0;
        char prevChar = input.charAt(from);

        for (int i = from; i < to; i++) {
            if (prevChar == input.charAt(i) || input.charAt(i) == 'w' || prevChar == 'w') {
                if (prevChar == 'w') {
                    prevChar = input.charAt(i);
                }
                count++;
            } else {
                break;
            }
        }

        return count;
    }
    private static int countBackward(String input, int from, int to) {
        int count = 0;
        char prevChar = input.charAt(from);
        while (from >= to) {
            if (prevChar == input.charAt(from) || input.charAt(from) == 'w' || prevChar == 'w') {
                if (prevChar == 'w') {
                    prevChar = input.charAt(from);
                }

                count++;
                from--;

            } else {
                break;
            }
        }
        return count;
    }


    private static int countConsecChars(String input) {

        if (input == null || input.length() == 0) {
            return 0;
        }

        if (input.length() == 1) {
            return 1;
        }

        int[] rTable = new int[input.length()];
        int[] bTable = new int[input.length()];

        int maxCount = 0;

        // counting 'r' letter
        int runningCount = 0;
        for (int i = 0; i < input.length(); i++) {
            if ((input.charAt(i) == 'r') || input.charAt(i) == 'w'){
                if (i == 0) {
                    rTable[i] = 1;
                } else {
                    rTable[i] = 1 + rTable[i-1];
                }
            }
        }

        // counting 'b'
        for (int i = input.length()-1; i >= 0; i--) {
            if ((input.charAt(i) == 'b') || input.charAt(i) == 'w'){
                if (i == input.length()-1) {
                    bTable[i] = 1;
                } else {
                    bTable[i] = 1 + bTable[i+1];
                }
            }
        }

        System.out.println("r table: " + Arrays.toString(rTable));
        System.out.println("b table: " + Arrays.toString(bTable));

        for (int i = 0; i < rTable.length-1; i++) {
            int tmpCount = rTable[i] + bTable[i+1];
            if (tmpCount > maxCount) {
                maxCount = tmpCount;
            }
        }
        return maxCount;
    }
}
