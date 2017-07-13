package my.cci.array_string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hluu on 11/28/16.
 *
 * Problem:
 *  Give a long string b and a short string s.  Find all the permutations of
 *  string s in b.
 *
 *  s: aabc
 *  b: cbabadcbbabbcbabaabcbabc
 *
 *  Approach:
 *      * Brute force - generate all permutations of s and for each one of those
 *        search inside b
 *      * Run time analysis O(S! * B)
 *      * Bottleneck - S! - how can we avoid this? (by not doing it)
 *
 *  Optimize:
 *      * All permutations of a string have same # of characters
 *      * aabc, abca, acba -> same # of a, same # of b, same # of c
 *      * What can we do to leverage this?
 *      * Build a map to store characters and their occurrence as source of truth
 *        * This is the key here, we don't care about order, just as long as
 *        * any four characters that make up the right # of characters and their occurrence
 *      * Build a working map to store working characters
 *        * This working map contains only the characters and their occurrence in short string
 *      * First build up this second map with first len(s) of characters
 *      * Check is to see if it contains same # of characters and occurrence in
 *        source of truth
 *      * Iterate through remaining characters each letter in b
 *          * decrement the count of the previous char from the working map if exists
 *          * increment the count of the current character in the working map
 *          * check if working map same as source of truth map
 *          * basically moving a window length of short string characters
 *            one character at a time forward.  Remove the tail, add the head, and compare
 *
 *
 */
public class AllPermutationsInLongString {
    public static void main(String[] args) {
        System.out.printf("%s\b", AllPermutationsInLongString.class.getName());

        String s = "aabc";
        //String b = "cbabadcbbabbcbabaabcbabc";
        //String b = "abacaaaabcbaa";
        String b = "abacacbaa";

        List<String> result = findAllPermutations(s,b);
        System.out.printf("%s\n", result);
    }

    private static List<String> findAllPermutations(String shortStr, String longStr) {
        Map<Character, Integer> sourceOfTruth = new HashMap<>();
        Map<Character, Integer> workingMap = new HashMap<>();

        List<String> result = new ArrayList<>();

        if (longStr.length() < shortStr.length()) {
            return result;
        }

        // build the source of truth map and working map
        for (char c : shortStr.toCharArray()) {
            Integer cnt = sourceOfTruth.get(c);
            if (cnt == null) {
                cnt = Integer.valueOf(1);
            } else {
                cnt = cnt + 1;
            }
            sourceOfTruth.put(Character.valueOf(c), cnt);
            workingMap.put(Character.valueOf(c), new Integer(0));
        }


        // populate the first n chars from long string
        for (int i = 0; i < shortStr.length(); i++) {
            incrementCharCount(workingMap, longStr.charAt(i));
        }

        boolean foundPermutation = isSameMap(sourceOfTruth,workingMap);
        if (foundPermutation) {
            result.add(longStr.substring(0, 4));
        }

        int shortStrLen = shortStr.length();
        int prefixCharIndex = 0;
        for (int i = shortStrLen; i < longStr.length(); i++) {
            removeCount(workingMap, longStr.charAt(prefixCharIndex++));
            incrementCharCount(workingMap, longStr.charAt(i));
            if (isSameMap(sourceOfTruth, workingMap)) {
                result.add(longStr.substring(prefixCharIndex,prefixCharIndex+shortStrLen));
            }

        }
        System.out.printf("%s\n", sourceOfTruth);
        return result;

    }

    private static void removeCount(Map<Character, Integer> workingMap, char c) {
        Integer value = workingMap.get(Character.valueOf(c));
        if (value != null) {
            value -= 1;
            workingMap.put(Character.valueOf(c), value);
        }
    }
    private static boolean isSameMap(Map<Character, Integer> souceOfTruth,
                                     Map<Character, Integer> workingMap) {

        for (Map.Entry<Character,Integer> entry : souceOfTruth.entrySet()) {
            Integer value = workingMap.get(entry.getKey());
            if (value != null) {
                if (!value.equals(entry.getValue())) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;

    }
    private static void incrementCharCount(Map<Character, Integer> workingMap, char c) {
        Integer value = workingMap.get(Character.valueOf(c));
        if (value != null) {
            value += 1;
            workingMap.put(Character.valueOf(c), value);
        }
    }
}
