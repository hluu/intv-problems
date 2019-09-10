package org.learning.string;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 * You may assume the dictionary does not contain duplicate words.
 * Return all such possible sentences.
 *
 * For example, given

 * string = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 *
 * A solution is ["cats and dog", "cat sand dog"].
 *
 * http://thenoisychannel.com/2011/08/08/retiring-a-great-interview-problem
 *
 * Power set - https://en.wikipedia.org/wiki/Power_set
 *
 *
 * Approach
 *  1) Iterate through the input string from left to right
 *  2) extract the prefix - string from 0 to i
 *  3) look it up the in the dict
 *  4) if exists in dictionary
 *  5) add to list of words
 *  6) recurse the suffix
 *
 * One approach is to use recursion, the other one is to use top down recursion with DP
 */
public class WordBreak2 {
    public static void main(String[] args) {
        System.out.printf("%s\n", WordBreak2.class.getName());

        List<String> wordList2 = Arrays.asList("a", "abc", "b", "cd");
        test(wordList2, "abcd");


        List<String> wordList = Arrays.asList("cat", "cats", "and", "sand", "dog");
        test(wordList, "catsanddog");


        List<String> wordList3 = Arrays.asList("a");
        test(wordList3, "a");

        List<String> wordList4 = Arrays.asList("aaa", "aa", "a");
        test(wordList4, "aaaaaa");

        List<String> wordList5 = Arrays.asList("A", "AN", "AND", "DROID", "ANDROID","I", "LOVE", "SRC");
        test(wordList5, "ILOVEANDROIDSRC");

    }

    private static void test(List<String> dict, String word) {
        System.out.printf("\n**** test dict: %s\n", dict);
        System.out.printf("word: %s\n", word);

        Set<String> dictSet = new HashSet<>(dict);
        //List<String> actualResult = wordBreakHelper(word, dictSet);
        List<String> actualResult = wordBreak(word, dictSet);


        List<String> actualResultDP = wordBreakHelperWithMemoiz(word, dictSet,
                new HashMap<String, List<String>>());

        System.out.println("=====> resumts <=======");
        System.out.printf("  actualResult: %s\n", actualResult);
        System.out.printf("actualResultDP: %s\n", actualResultDP);
    }


    public static List<String> wordBreak(String s, Set<String> dictSet) {
        if (s == null  || s.isEmpty()  ||  dictSet == null | dictSet.isEmpty()) {
            return Collections.emptyList();
        }

        //Set<String> dictSet = new HashSet<>(wordDict);
        List<String>  result = wordBreakHelper(s, dictSet);

        List<String>  newResult = new ArrayList<>();

        for (String str : result) {
            newResult.add(str);
        }

        return newResult;
    }

    /**
     * Each time through recursion, the wordRemaining is getting smaller.
     *
     * Runtime analysis
     *  * what is the worse case scenario?
     *    * every prefix exists in dictionary, that is when recursion continues
     *      to check the suffix
     *
     *    T(n) = T(n-1) + T(n-2) + ... + T(1) = 2T(n-1) = O(2^n)
     *
     *  * for a string of n characters, there will be n prefixes
     *  * O(2^n)
     *
     * @param wordRemaining
     * @param dict
     * @return
     */
    public static List<String> wordBreakHelper(String wordRemaining, Set<String> dict) {
        List<String> sentences = new ArrayList<>();

        if (wordRemaining == null  || wordRemaining.isEmpty()) {
            return sentences;
        }

        // start with 1 to make it easier with substring
        for (int i = 1; i <= wordRemaining.length(); i++) {

            String prefix = wordRemaining.substring(0, i);

            if (dict.contains(prefix)) {
                String suffix = wordRemaining.substring(i);
                List<String> tmpResult = wordBreakHelper(suffix, dict);

                // if tmpResult is empty, make sure handle this case
                // when add it sentences
                if (!tmpResult.isEmpty()) {
                    for (String str : tmpResult) {
                        sentences.add(prefix + " " + str);
                    }
                } else {
                    sentences.add(prefix);
                }
            }
        }

        return sentences;
    }

    /**
     * Using additional memory to store previously computed answer for a
     * particular prefix
     *
     * @param word
     * @param dict
     * @param cache
     * @return
     */
    public static List<String> wordBreakHelperWithMemoiz(String word, Set<String> dict,
                                                         Map<String,List<String>> cache) {
        if (cache.containsKey(word)) {
            System.out.println("**** found in cache: " + word);
            return cache.get(word);
        }

        List<String> sentences = new ArrayList<>();
        if (word == null  || word.isEmpty()  ||  dict == null | dict.isEmpty()) {
            return sentences;
        }


        for (int i = 1; i <= word.length(); i++) {
            String prefix = word.substring(0, i);
            if (dict.contains(prefix)) {
                String suffix = word.substring(i);
                List<String> tmpResult = wordBreakHelperWithMemoiz(suffix, dict, cache);

                // if tmpResult is empty, make sure handle this case
                // when writing it
                if (!tmpResult.isEmpty()) {
                    for (String str : tmpResult) {
                        sentences.add(prefix + " " + str);
                    }
                } else {
                    sentences.add(prefix);
                }
            }
        }

        cache.put(word, sentences);
        return sentences;

    }
}
