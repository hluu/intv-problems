package org.common;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hluu on 7/7/17.
 */
public class StringUtility {

    public  static  void main(String[] args) {
        String str1 = "abc";
        String str2 = "abcd";

        test(str1);

        test(str2);

    }

    private static void test(String str) {
        System.out.println("***** substrings and subsequences of str: " + str);

        int strLen = str.length();
        System.out.println("**** substrings  ****");
        List<String> subStringList = generateSubStrings(str);
        System.out.println(subStringList);

        Assert.assertEquals(subStringList.size(), (strLen * (strLen + 1))/ 2 );


        System.out.println("**** subsequences using recursion ****");
        Set<String> subsequences = new HashSet<>();
        generateSubseqRecursion(str,
                0, "", subsequences);
        System.out.println(subsequences);

        Assert.assertEquals(subsequences.size(), (int)Math.pow(2, strLen));

        System.out.println("**** subsequence using binary");
        List<String> result = generateSubseqBinary(str);
        System.out.println(result);

        Assert.assertEquals(result.size(), (int)Math.pow(2, strLen));
    }

    public static List<String> generateSubStrings(String str) {
        List<String> result = new ArrayList<>();

        if (str == null) {
            return result;
        }

        // two for loops to build all the possible substrings
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                result.add(str.substring(i, j+1));
            }
        }
        return result;
    }

    public static List<String> generateSubseqBinary(String str) {
        List<String> result = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return result;
        }

        double numSequences = Math.pow(2, str.length());

        for (int i = 0; i < numSequences; i++) {
            String subSeqStr = "";
            int index = 0;
            int temp = i;

            while (temp > 0) {
                if ((temp & 1) == 1) {
                    subSeqStr += str.charAt(index);
                }
                index++;
                temp = temp >> 1;
            }
            result.add(subSeqStr);
        }
        return  result;
    }

    /**
     * Use index to know when to reach the end.
     * Use string to collect string at each index
     *
     * Generate sub-sequence using recursion with two cases
     *
     * 1) One to not include character at index
     * 2) One to include character at index
     *
     * Do not modify index a each level, because it is used for both
     * calls (branches), instead, pass the modified value into the recursion call
     *
     * Runtime: O(2^n)
     *
     */
    public static void generateSubseqRecursion(String input, int index,
                                               String stringSoFar,
                                               Set<String> collector) {

        StringUtility.printSpace(index);
        System.out.printf("level: %d, stringSoFar: '%s'\n", index, stringSoFar);
        if (index >= input.length()) {
            collector.add(stringSoFar);
            return;
        }

        // not to include char at index

        generateSubseqRecursion(input, index+1, stringSoFar, collector);

        // to include char at index
        generateSubseqRecursion(input, index+1,
                stringSoFar + input.charAt(index),
                collector);

    }

    public static void printSpace(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }
    }
}
