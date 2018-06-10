package org.learning.dp;

import org.common.StringUtility;

import java.util.*;

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

        String str = "abcd";

        List<String> collector1 = new ArrayList<>();
        helper(str, collector1);
        System.out.printf("collector1(%d): %s\n", collector1.size(), collector1);

        System.out.println("=======================");
        Set<String> collector2 = new HashSet<>();
        StringUtility.generateSubseqRecursion(str, 0, "", collector2);

        System.out.printf("collector2(%d): %s\n", collector2.size(), collector2);


        test("abqwerba");
    }

    private static void test(String input) {
        Map<String, Set<String>> cache = new HashMap<>();

        Set<String> result = findPalindromes(input, cache);

        System.out.println("input: " + input);
        System.out.println("resut(" + result.size() + "): " + result);

    }

    private static Set<String> findPalindromes(String str, Map<String, Set<String>> cache) {
        // check the cache first
        Set<String> cacheValue = cache.get(str);

        if (cacheValue != null) {
            System.out.println("**** cache hit for: " + str);
            return cacheValue;
        }

        cacheValue = new HashSet<>();

        int len = str.length();
        if (len == 0) {
            return cacheValue;
        }

        // base case
        if (len == 1) {
            cacheValue.add(str);
            return cacheValue;
        }

        // recurse left side
        cacheValue.addAll(findPalindromes(str.substring(0, len-1),cache));

        // recurse right side
        cacheValue.addAll(findPalindromes(str.substring(1, len),cache));


        // start of a palindrome
        if (str.charAt(0) == str.charAt(len-1)) {
            // get the inner palindrome
            Set<String> innerPalindromes = findPalindromes(str.substring(1, len-1), cache);

            String head = str.substring(0,1);
            for (String palStr : innerPalindromes) {
                cacheValue.add(head + palStr + head);
            }
            // 2-letter palindrome - because of str.charAt(0) == str.charAt(len-1)
            cacheValue.add(head+head);
        }

        cache.put(str, cacheValue);

        return cacheValue;
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

        //collector.add(str);

    }
}
