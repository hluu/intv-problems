package org.learning.string;

import org.testng.Assert;

import java.util.BitSet;

/**
 *
 * Remove alternate duplicate characters from a char array.
 * Keeping only the odd occurrences of each character.
 *
 * For example:
 *    Input: “you got beautiful eyes”
 *   Output: ”you gtbeaiful es”
 *
 *  Allowed Time Complexity was O(n) and Space Complexity was O(1)
 *
 * Analysis:
 *  1) There are 48 characters in ascii alphabet
 *  2) Use 48 bits to represent the character set
 *  3) We only need to track odd and even occurrence.
 *  4) Use the state of the bit to represent occurrence
 *
 * Approach:
 *  1) Create 48 bit set
 *  2) Iterate through each character in string
 *  3) If not space
 *       if state is 0, print out, flip bit to 1
 *       if state is 1, don't print out, flip bit to 0
 */
public class AlternateDuplicateCharacters {

    public static void main(String[] args) {
        System.out.println(AlternateDuplicateCharacters.class.getName());

        test("you got beautiful eyes", "you gtbeaiful es");

        test("youareme", "youarem");

        test("this day", "this day");
        test("is insight", "is night");

        printCharIntegerValue();
    }

    private static void printCharIntegerValue() {
        System.out.printf("======== character integer values =======\n");
        String alphabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";



        for (char c : alphabet.toCharArray()) {
            System.out.printf("%d ", (int)c);
        }
        System.out.println();

        System.out.println();
    }

    private static void test(String input, String expectedOutput) {
        String actualOutput = alternateDuplicateChars(input);
        System.out.printf("input: '%s', actualOutput: '%s', expected output: '%s' \n",
                input, actualOutput, expectedOutput);
        Assert.assertEquals(actualOutput, expectedOutput);
    }

    private static final char SPACE = ' ';

    /**
     * Only handle characters, no digits
     * @param input
     * @return
     */
    private static String alternateDuplicateChars(String input) {
        if (input == null || input.length() == 0 || input.length() == 1) {
            return input;
        }

        char[] charArray = input.toCharArray();

        StringBuilder buf = new StringBuilder();

        BitSet bitSet = new BitSet(48);
        System.out.println("initial value: " + bitSet.get(0));

        // which is space
        int lowerCharValue  = Character.getNumericValue(' ');
        // make sure to handle space as well
        for (char c : charArray) {
            int bitIndex = Character.getNumericValue(c)  - lowerCharValue;
            //System.out.println("bitIndex: " + bitIndex);
            if (!bitSet.get(bitIndex)) {
                buf.append(c);
            }
            bitSet.flip(bitIndex);

        }

        return buf.toString();
    }
}
