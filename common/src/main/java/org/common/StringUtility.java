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
        System.out.println("**** substrings ****");
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
                // now print out the substring from i to j
                String subStr = "";
                for (int k = i; k <= j; k++) {
                    subStr += str.charAt(k);
                }
                result.add(subStr);
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
     * Generate sub-sequence using recursion with two cases:
     * 1) One to include
     * 2) One to not include
     *
     */
    public static void generateSubseqRecursion(String input, int index,
                                               String stringSoFar,
                                               Set<String> collector) {

        if (index >= input.length()) {
            return;
        }

        // not to include char at index
        collector.add(stringSoFar);
        generateSubseqRecursion(input, index+1, stringSoFar, collector);

        // to include char at index
        collector.add(stringSoFar + input.charAt(index));
        generateSubseqRecursion(input, index+1, stringSoFar + input.charAt(index),
                collector);

    }
}
