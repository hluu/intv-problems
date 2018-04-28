package org.learning.backtracking;

import org.common.StringUtility;

/**
 * String permutation
 *
 * ABC
 * ACB
 * BAC
 * BCA
 * CAB
 * CBA
 *
 *
 * Using backtracking approach
 *  1) If no more characters to choose from, print out the current permutation
 *  2) For each of the characters in the remain string
 *     * Pick a character and add to current permutation string
 *     * Remove that same character from the original string
 *     * recursively re-arrange the remaining characters
 *
 * Runtime: O(n!)
 *
 *
 */
public class StringPermutation {

    public static void main(String[] args) {
        System.out.println(StringPermutation.class.getName());

        permute("abcd");
        //permute("MARTY");
    }

    private static void permute(String str) {
        counter = 0;
        permuteHelper(str, "");

        counter = 0;
        System.out.println("===============");
        permuteHelper2("", str);

        System.out.println();
    }

    private static int counter = 0;
    private static void permuteHelper(String remainStr, String choosenStr) {
        //StringUtility.printSpace(choosenStr.length());
        //System.out.printf("permute(%s, %s)\n", remainStr, choosenStr);


        if (remainStr.isEmpty()) {
            counter++;
            // base  case
            // nothing more to permute
            System.out.printf("%4d:%s\n", counter, choosenStr);
            return;
        }

        // for each of the remaining characters
        for (int i = 0; i < remainStr.length(); i++) {
            // choose character at i
            char c = remainStr.charAt(i);

            // take that character out of the remainStr
            String tmpStr = remainStr.substring(0,i) + remainStr.substring(i+1);

            // explore the remain string (tmpStr), with the new choosen
            permuteHelper(tmpStr, choosenStr + c);

        }
    }

    private static void permuteHelper2(String soFar, String remaining) {
        if (remaining.isEmpty()) {
            counter++;
            //System.out.println("helper2: " + soFar);
            System.out.printf("%4d:%s\n", counter, soFar);
            return;
        }

        for (int i = 0; i < remaining.length(); i++) {
            // remove the character at position i from remaining string
            String tmpRemain = remaining.substring(0,i) +
                    remaining.substring(i+1);

            permuteHelper2(soFar + remaining.charAt(i),  tmpRemain);
        }
    }
}
