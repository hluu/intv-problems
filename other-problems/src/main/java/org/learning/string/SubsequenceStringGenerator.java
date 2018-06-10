package org.learning.string;

import org.common.StringUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string generate all the subsequences or combinations, where
 * characters are not contiguous
 *
 * There are 2^n subsequences (binary number)
 *
 * For example:
 *  String = "gat" has 8 substrings
 *
 *                                   0 ""
 *                             /              \
 *                         1 ""                  1 g
 *                      /      \            /        \
 *                    2 ""     2 a        2 g        2 ga
 *                  /    \    /   \     /   \      /    \
 *                3 ""  3 t 3 a  3 at  3 g  3 gt  3 ga  3 gat
 *
 *  Approach:
 *      *) At each level
 *          *) don't include the char at that index, recurse
 *          *) include the char at that index, recurse
 *
 *  Run time:
 *     *) Branch factor 2
 *     *) Depth of the tree is number of characters in the string
 *     *) therefore 2^n
 */
public class SubsequenceStringGenerator {
    public static void main(String[] args) {
        System.out.println(SubsequenceStringGenerator.class.getName());

        String input = "gat";

        System.out.println("*** recursionApproach ***");
        List<String> collector1 = new ArrayList<>();
        recursionWithIndexApproach(input, 0, "", collector1);
        System.out.println(collector1);

        System.out.println("*** recursionApproach ***");
        List<String> collector2 = new ArrayList<>();
        recursionApproach(input, "", collector2);
        System.out.println(collector2);
    }


    private static void recursionWithIndexApproach(String input, int index,
                                                   String subSequence,
                                                   List<String> collector) {

       // StringUtility.printSpace(subSequence.length());
       // System.out.printf("idx: %d, subsequence: %s\n", index, subSequence);

        if (index >= input.length()) {
            /// base case when index is at the end
            //System.out.println(subSequence);
            collector.add(subSequence);
            return;
        }

        // without character at index position
        recursionWithIndexApproach(input, index+1, subSequence, collector);

        // with character at index position
        recursionWithIndexApproach(input, index+1,
                subSequence + input.charAt(index),
                collector);

    }

    /**
     * Generate all subsequences of a given string
     *
     * Using recursion with 2-way branch - one to include the first character,
     * and the second w/o include the first characters.
     *
     * @param remaining
     * @param soFar
     * @param collector
     */
    private static void recursionApproach(String remaining, String soFar,
                                          List<String> collector) {
        if (remaining.isEmpty()) {
            collector.add(soFar);
            return;
        }

        // include first character
        recursionApproach(remaining.substring(1), soFar + remaining.charAt(0),
                collector);

        // don't include first character
        recursionApproach(remaining.substring(1), soFar, collector);
    }


}
