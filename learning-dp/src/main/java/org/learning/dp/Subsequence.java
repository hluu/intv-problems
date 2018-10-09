package org.learning.dp;


import java.util.*;

/**
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

        //usingBinaryApproach(input + "e");

        System.out.println("*** topDownApproach");
        List<String> topDownResult = new ArrayList<>();
        topDownApproach(input.toCharArray(), 0, "", topDownResult);
        System.out.println(topDownResult);

        System.out.println("*** bottomUpApproach ***");
        System.out.println(bottomUpApproach(input));


    }



    /**
     * For each level, branch into two sub levels
     *
     * @param charArr
     * @param index
     * @param subSequence
     * @param collector
     */
    private static void topDownApproach(char[] charArr, int index, String subSequence,
                                        List<String> collector) {
        if (index >= charArr.length) {
            /// base case when index is at the end
            //System.out.println(subSequence);
            collector.add(subSequence);
            return;
        }

        // without character at index position
        topDownApproach(charArr, index+1, subSequence, collector);
        // with character at index position
        topDownApproach(charArr, index+1, subSequence + charArr[index],
                collector);
    }

    /**
     * Give a string, generate all the sub sequences:
     *
     * String = "abc"  => "a" * bottomUpApproach("bc")
     *                          b * bottomUpApproach("c")
     *                              c * bottomUpApproach("")
     *
     *  Once it is bubble up from the bottom, the prefix add itself
     *  to the list - one with prefix, one without
     *      "c", ""
     *      "c", ,"", "bc", "b"
     *      "c", "", "bc", "b", "ac", "a", "abc", "ab"
     *
     * @param str
     * @return
     */
    private static List<String> bottomUpApproach(String str) {
        if (str.length() == 0) {
            return Collections.emptyList();
        }

        String prefix = str.substring(0,1);
        String suffix = str.substring(1);
        List<String> bubbleUpResult = bottomUpApproach(suffix);

        List<String> result = new ArrayList<>();
        result.add(prefix);

        if (!bubbleUpResult.isEmpty()) {
            for (String s : bubbleUpResult) {
                result.add(s);  // w/o prefix
                result.add(prefix + s); // with prefix
            }
        }

        return result;

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
