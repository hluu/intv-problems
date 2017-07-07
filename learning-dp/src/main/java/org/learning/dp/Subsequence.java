package org.learning.dp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by hluu on 12/16/15.
 *
 * Problem statement:
 *  Find all subsequences of a string.
 *
 *  String S = s1,s2,s3,s4,sn for a string with length n
 *
 *  For example: s = "GAC" => {"GAC", "GA", "GC", "AC", "G", "A", "C", ""}
 *
 *  One subsequence of a string is the original string with n characters removed.
 *
 *  n goes from 0 -> n.  For each iteration of character removal - anchor + 1 character.
 *  anchor => (n-1) + m.  Where the value of 1, but each of the remain characters.
 *
 * Approach:
 *  Give a string s that has length of n, we will remove from 0 to n characters from s.
 *  This will give a set of subsequence string of length from n to 0.
 *
 *  For each m characters to remove - we will need to find all the combinations of those
 *  m characters in a string of n characters.
 *
 *  m = 0 ==> base case, nothing to remove
 *  m = n ==> base case, remove all the characters
 *
 *
 *
 *
 */
public class Subsequence {

    public static void main(String[] args) {
        System.out.println(Subsequence.class.getName());

        String input = "abcd";
        //String input = "ACGATGTAC";

        usingBinaryApproach(input);

        usingBinaryApproach(input + "e");
    }

    private static void usingBinaryApproach(String input) {
        int[] intArray = new int[input.length()];
        for (int i = 0; i < Math.pow(2, input.length()); i++) {
            binaryAddOne(intArray);
            System.out.println(Arrays.toString(intArray));
        }

        List<String> collector2 = new ArrayList<String>();
        printSubSeqUsingBinary(input, collector2);
        Collections.sort(collector2);

        System.out.println("collector2: " + collector2.size());
        System.out.println(collector2);
    }



    public static void printSubSeqUsingBinary(String input, List<String> collector) {
        if ((input == null) || (input.length() == 0)) {
            return;
        }
        char[] charArr = input.toCharArray();
        int inputLen = input.length();
        int[] binaryArr = new int[inputLen];


        int numIterations = (int)Math.pow(2, inputLen);
        System.out.println("# iterations: " + numIterations);
        for (int i = 0; i < numIterations; i++) {
            String subSet = "";
            for (int j = 0; j < binaryArr.length; j++) {
                if (binaryArr[j] == 1) {
                    subSet = subSet +  charArr[j];
                }
            }
            collector.add(subSet);
            //System.out.println(subSet);
            binaryAddOne(binaryArr);
        }

    }

    /**
     * Add 1 to the binary number represented in digits array
     *
     * binaryAddOne([0,0,0]) ==> [0,0,1]
     * binaryAddOne([0,0,1]) ==> [0,1,0]
     * binaryAddOne([0,1,0]) ==> [0,1,1]
     *
     * @param digits
     */
    public static void binaryAddOne(int[] digits) {
         int carry = 1;
         for (int i = digits.length-1; i >= 0; i--) {
             if (carry > 0) {
                 if (digits[i] == 1) {
                     digits[i] = 0;
                     carry = 1;
                 } else {
                     digits[i] = carry;
                     carry = 0;
                 }
             }
         }
    }
}
