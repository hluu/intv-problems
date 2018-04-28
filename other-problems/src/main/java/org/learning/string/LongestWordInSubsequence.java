package org.learning.string;

import org.common.Tuple;
import org.testng.Assert;

import java.util.*;

/**
 * https://techdevguide.withgoogle.com/resources/find-longest-word-in-dictionary-that-subsequence-of-given-string/
 *
 * Find longest word in dictionary that is a subsequence of a given string
 *
 * For example:
 *   S = "abppplee"
 *   Dict = {"able", "ale", "apple", "bale", "kangaroo"}
 *
 * Brute force:
 *   * Generate all the subsequences of S
 *   * For each word in dict, check to see if they are in the list of subsequences
 *   * Return the longest word
 *   * Runtime
 *      * Let s be length of S
 *      * Let n be # of words
 *      * O(2^s + n)

 * Greedy approach
 *  * Don't generate all the subsequences, if the dict has only small # of words
 *  * For each word in dict
 *      * Check if it is a subsequence of S
 *  * Runtime
 *      * Let s be the length of S
 *      * Let n be # of words
 *      * O(s * n)
 *
 *  * Observation
 *      * Since we check letters of each word in dict against each letter in S
 *      * That seems to scream more for some pre-processing on letter S to
 *      * speed up the look process
 *
 *
 */
public class LongestWordInSubsequence {
    public static void main(String[] args) {
        System.out.println(LongestWordInSubsequence.class.getName());


        testIsASubsequence("abppplee", "able", true);
        testIsASubsequence("abppplee", "p", true);
        testIsASubsequence("abppplee", "ae", true);
        testIsASubsequence("abppplee", "abppplee", true);
        testIsASubsequence("abppplee", "xable", false);
        testIsASubsequence("abppplee", "ablex", false);
        testIsASubsequence("abppplee", "ablxe", false);
        testIsASubsequence("apple", "abppplee", false);
        testIsASubsequence("aapple", "abppplee", false);
        testIsASubsequence("apaple", "abppplee", false);

        System.out.println(" ============================ ");
        List<String> wordList = Arrays.asList("able", "ale", "apple",
                "bale", "kangaroo");


        String str = "abppplee";

        test(new HashSet(wordList), str, "apple");
    }


    private static void test(Set<String> dict, String pattern, String expected) {
        System.out.printf("**** dict: %s pattern: %s ****\n",
                dict, pattern);

        String actual = greedyApproach(dict, expected);

        String actual2 = optimalApproach(dict, pattern);

        System.out.printf("expected: %s, actual: %s, actual2: %s",
                expected, actual, actual2);

        System.out.println();

    }


    /**
     * Approach, for each word in dict, check to see if it is a subsequence
     * of the pattern.
     *
     * A small optimization is to sort the dictionary by word length
     *
     * Runtime:
     *  * Let s be length of pattern
     *  * Let n be the number of words in the dictionary
     *  * O(s * n)
     *  * If s is long and dictionary words are short and we are still
     *  * required to loop through the len of S.
     *
     * Observation for performance improvement:
     *  * For each word in dict, we need to scan from beginning
     *  * Can pre-process S, so we can look each letter up, that way, it would
     *    O(m) for each word, where m is lengh of a word.
     *
     * @param dict
     * @param pattern
     * @return
     */
    private static String greedyApproach(Set<String> dict, String pattern) {
        if (dict == null || pattern == null) {
            return null;
        }

        Set<String> sortedDict = new TreeSet<String>(new Comparator<String>(){
            public int compare(String s1, String s2) {
                return s2.length() - s1.length();
            }
        });

        sortedDict.addAll(dict);

        // since the words already sorted by length
        for (String word : sortedDict) {
            System.out.println("word: " + word);
            if (isASubsequence(word, pattern)) {
                return word;
            }
        }

        return null;
    }

    /**
     * An optimal O(N + L) approach for any alphabet
     *
     * This approach requires preprocessing of the words in the dict and
     * will incur additional space.  The preprocessing to build a map
     * of letter to a list of tuples where each tuple contains a word, and a count
     * of characters have seen so far.
     *
     * For example:
     * a -> [("able", 0), ("ale", 0), ("apple", 0)]
     * b -> [("bale", 0)]
     * k -> [("kangaroo", 0)]
     *
     * The algorithm:
     *  * Iterate through each characters into the pattern
     *  * Look up a list of tuples and increment their count by 1
     *      * if any tuple with length equals its word length, then we
     *        found all the characters in that word
     *  * To make sure the character order is correct, we need to move the
     *    tuple around by using the character at position represented by the count
     *    * For example, tuple ("able", 1) should be moved to entry 'b' in the map
     *
     *
     * @param dict
     * @param pattern
     */
    private static String optimalApproach(Set<String> dict, String pattern) {
        // preprocess the words in the dict
        Map<Character, List<Tuple<String,Integer>>> dictMap = buildDictMap(dict);


        for (char pChar :  pattern.toCharArray()) {
            System.out.println(dictMap);

            List<Tuple<String,Integer>> wordTupleList =
                    dictMap.get(pChar);

            // bumping up their counter
            if (wordTupleList != null) {
                List<Tuple<String, Integer>> toBeRemoved = new ArrayList<>();
                for (Tuple<String, Integer> tuple : wordTupleList) {

                    if (tuple.second < tuple.first.length()-1) {
                        tuple.second += 1;
                    }

                    // add them to the bucket if needed based on the char at
                    // new count
                    char charKey = tuple.first.charAt(tuple.second);
                    if (pChar != charKey) {
                        List<Tuple<String, Integer>> tupleList = dictMap.get(charKey);
                        if (tupleList == null) {
                            tupleList = new ArrayList<>();
                        }

                        toBeRemoved.add(tuple);

                        tupleList.add(tuple);

                        dictMap.put(charKey, tupleList);
                    }
                }

                // now more from existing list
                for (Tuple<String, Integer> tuple : toBeRemoved) {
                    wordTupleList.remove(tuple);
                }
            }

        }

        // now loop through the dictMap and return the word with match count
        // and largest among the candidates
        String maxSubSequence = "";
        System.out.println(dictMap);
        for (List<Tuple<String,Integer>> values : dictMap.values()) {
            for (Tuple<String,Integer> tuple : values) {
                if (tuple.first.length()-1 == tuple.second) {
                    if (tuple.second > maxSubSequence.length()) {
                        maxSubSequence = tuple.first;
                    }
                }
            }
        }

        return maxSubSequence;
    }

    private static Map<Character, List<Tuple<String,Integer>>> buildDictMap(Set<String> dict) {
        Map<Character, List<Tuple<String,Integer>>> dictMap = new HashMap<>();

        for (String word : dict) {
            char firstChar = word.charAt(0);
            Tuple<String,Integer> tuple = new Tuple<>(word, 0);

            List<Tuple<String,Integer>> values = dictMap.get(firstChar);

            if (values == null) {
                values = new ArrayList<>();
            }

            values.add(tuple);

            dictMap.put(firstChar, values);

        }

        return dictMap;
    }

    private static void testIsASubsequence(String input, String pattern,
                                           boolean expected) {
        System.out.printf("**** input: %s pattern: %s ****\n",
                input, pattern);

        boolean actual = isASubsequence(input, pattern);

        System.out.printf("expected: %b, actual: %b\n", expected, actual);

        Assert.assertEquals(actual, expected);
        System.out.println();
    }

    private static boolean isASubsequence(String str, String pattern) {
        if (pattern == null || str == null) {
            return false;
        }

        int sLen = str.length();
        int pLen = pattern.length();
        // pattern len can't be longer than str len
        if (pLen > sLen) {
            return false;
        }

        int sIdx = 0;
        int pIdx = 0;
        while (sIdx < sLen) {
            if (str.charAt(sIdx) == pattern.charAt(pIdx)) {
                pIdx++;
            }

            if (pIdx == pLen) {
                return true;
            }

            sIdx++;
        }
        return false;
    }
}
