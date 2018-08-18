package org.learning.string;

import org.testng.Assert;

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
 * What is the significant when w is at the beginning or at the end of the string? if any
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

        // these are two examples where they matter where w is replaced with right char
        test("wbwrw",5);
        test("wrwbw",5);
       test("rrwbwrrr", 6);
        test("rbbbrwbw", 5);


        test("rbrrwwbbbb", 8);

        test("brrrbwrw", 5);

        test("rrb",3);
        test("rbwwr",4);

        test("wwrrbr", 5);


    }

    private static void test(String input, int expectedCount) {

        System.out.printf("\n****** test: input: %s *\n", input);

        int acctualCountFromBF = bruteForce(input);
        System.out.printf("bruteforce: expected %d, actual: %d\n", expectedCount, acctualCountFromBF);

        int actualOnePass = onePass(input);
        System.out.printf("actualOnePass: expected %d, actual: %d\n", expectedCount, actualOnePass);

        Assert.assertEquals(acctualCountFromBF, expectedCount);
        Assert.assertEquals(actualOnePass, expectedCount);
    }

    /**
     * Brute force - for each split point count number of consecutive characters before and after
     *
     * Runtime: O(n^2)
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
        //"rrb"
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
     * Using one pass by look for left and right in each time in the while loop
     *
     *
     * @param input
     * @return
     */
    private static int onePass(String input) {
        if (input == null || input.length() ==0) {
            return 0;
        }

        if (input.length() < 3) {
            return input.length();
        }

        // "rrb"

        char[] inputChar1 = input.toCharArray();
        preprocessWcharacter(inputChar1);

        for (int i = 0; i < inputChar1.length; i++) {
            if (inputChar1[i] == 'w') {
                inputChar1[i] = 'b';
            }
        }

        char[] inputChar2 = input.toCharArray();
        preprocessWcharacter(inputChar2);
        for (int i = 0; i < inputChar2.length; i++) {
            if (inputChar2[i] == 'w') {
                inputChar2[i] = 'r';
            }
        }

        return Math.max(countMaxCharOnEitherSide(inputChar1), countMaxCharOnEitherSide(inputChar2));

    }

    /**
     * Dealing with 'w' at the beginning and at the end
     *
     * For beginning, we want 'w' to be replaced with 'non-w' character next to it, not randomly
     * For end, we want 'w' to be replaced with 'non-w' character next to it, not randomly
     *
     * @param inputChar
     */
    private static void preprocessWcharacter(char[] inputChar) {
        int idx = 0;
        // dealing with w at the beginning
        while (inputChar[idx] == 'w' && idx < inputChar.length) {
            idx++;
        }

        if (idx < inputChar.length) {
            char charToUse = inputChar[idx];
            idx--;
            while (idx >= 0) {
                inputChar[idx] = charToUse;
                idx--;
            }
        }

        // dealing with w at the end
        idx = inputChar.length-1;
        while (inputChar[idx] == 'w' && idx >= 0) {
            idx--;
        }

        if (idx >= 0) {
            char charToUse = inputChar[idx];
            idx++;
            while (idx < inputChar.length) {
                inputChar[idx] = charToUse;
                idx++;
            }
        }
    }

    /**
     * Helper method for onepass.
     *
     * Expecting inputChar to contain only either 'r' or 'b', not 'w'
     *
     * @param inputChar
     * @return max count consecutive characters on either side of the a split point
     */
    private static int countMaxCharOnEitherSide(char[] inputChar) {

        //"rrb"

        int prevCount = 0;
        int runningSum = 0;

        char currChar = inputChar[0];
        int currentCnt = 1;

        for (int idx = 1; idx < inputChar.length; idx++) {
            char nextChar = inputChar[idx];
            if (currChar == nextChar) {
                currentCnt++;
            } else {
                runningSum = Math.max(runningSum, currentCnt + prevCount);
                prevCount = currentCnt;
                currentCnt = 1;
            }
            currChar = nextChar;
        }

        if (prevCount + currentCnt > runningSum) {
            runningSum = prevCount + currentCnt;
        }

        return runningSum;
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
