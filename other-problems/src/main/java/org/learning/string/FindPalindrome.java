package org.learning.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a function to compute a set of all subsequences of s which are palindromes.
 *
 * For example:
 *  string => "abcbrta",
 *  return strings => "a", "abba", "abcba", "aca", "bcb", "aba", etc
 *
 */
public class FindPalindrome {
    public static void main(String[] args) {
        System.out.println(FindPalindrome.class.getName());

        List<String> collector = new ArrayList<>();
        findPalindrome("abcbrta", collector);
        for (String s : collector) {
            System.out.println(s);
        }
    }

    private static void findPalindrome(String str, List<String> collector) {
        if (str == null || str.length() <= 1) {
            // our base case
            str = (str == null) ? "" : str;
            collector.add(str);
            return;
        }

        collector.add(str);
        //System.out.println(str);
        int len = str.length();

        findPalindrome(str.substring(0, len-1), collector);
        //System.out.println("*** " + str);
        findPalindrome(str.substring(1, len), collector);
    }
}
