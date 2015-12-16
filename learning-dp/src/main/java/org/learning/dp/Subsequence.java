package org.learning.dp;



/**
 * Created by hluu on 12/16/15.
 *
 * Problem statement:
 *  Find all subsequences of a string.
 *
 *  For example: s = "GAC" => {"GAC", "GA", "GC", "AC", "G", "A", "C", ""}
 *
 *  One subsequence of a string is the original string with n characters removed.
 *
 *  n goes from 0 -> n.  For each iteration of character removal - anchor + 1 character.
 *  anchor => (n-1) + m.  Where the value of 1, but each of the remain characters.
 *
 *
 */
public class Subsequence {
    public static void main(String[] args) {
        System.out.println(Subsequence.class.getName());

        //String input = "GAC";
        String input = "ABCDE";

        printSubSeq(input, 0, 1);
    }

    public static void printSubSeq(String input, int startIndex, int numChar) {

        if (numChar == input.length()) {
            System.out.println(input);
            return;
        }
        printSubSeq(input, 0, numChar+1);
        if (numChar == 1) {
            for (int i = 0; i < input.length(); i++) {
                System.out.println(input.substring(i, i+1));
            }
        } else {
            // outer loop goes up to length - (numChar - 1)
            int upTo = input.length() - (numChar - 1);
            for (int i = startIndex; i < upTo; i++) {
                // substring with last index as exclusive
                // prefix => number of characters up to numChar - 1
                String prefix = input.substring(i, i + numChar-1);
                // prepending the last character
                for (int j = i + numChar-1; j < input.length(); j++) {
                    System.out.println(prefix + input.substring(j, j + 1));
                }
            }
        }
    }
}
