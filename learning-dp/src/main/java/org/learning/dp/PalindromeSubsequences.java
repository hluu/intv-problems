package org.learning.dp;

import org.common.StringUtility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Returns the set of all subsequences of which are palindromes.
 *
 * abqwerba -> [aba, bqb, e, abwba, b, awa, a, abeba, bwb, abqba, w, aqa, aea, r,
 * abrba, q, beb, ara, brb, abba, aa, bb]
 *
 */
public class PalindromeSubsequences {

    public static void main(String[] args) {
        System.out.println(PalindromeSubsequences.class.getName());

        String str = "abc";

        //List<String> collector1 = new ArrayList<>();
        //helper(str, collector1);
        //System.out.printf("collector1(%d): %s\n", collector1.size(), collector1);

        Set<String> collector2 = new HashSet<>();
        StringUtility.generateSubseqRecursion(str, 0, "", collector2);

        System.out.printf("collector2(%d): %s\n", collector2.size(), collector2);
    }

    private static void helper(String str, List<String> collector) {
        System.out.println(str);

        if (str.length() == 1) {
            collector.add(str);
            return;
        }

        collector.add(str);

        int len = str.length();
        // not include the last character
        helper(str.substring(0, len-1), collector);
        // not include the first character
        helper(str.substring(1, len), collector);

    }
}
