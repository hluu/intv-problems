package org.learning.string;



import java.util.*;

/**
 * Created by hluu on 11/20/16.
 *
 * Problem:
 *  Give a dictionary of words and an input string which may or may not contain
 *  words in the dictionary.
 *
 *  For example:
 *      dict[I, am, free, freedom, a]
 *      string: Iam             => true
 *      string: amIfree         => true
 *      string: freedomIam      => true
 *      string: amIdofree       => false
 *
 *  Brute force:
 *      Iterate the string from left to right and build a word
 *          * If such word exist in dictionary then recursive on the
 *              substring following that word
 *          * To handle the case with free and freedom, then we must keep iterating
 *          * There is a subproblem in this problem, because the suffix is
 *          * just a subproblem, so recursion is natural choice.
 *          * The base case is when suffix is the exact word
 *
 *  Optimize:
 *      What if the dictionary contains "a", "aa", "aaa" and string is
 *      "aaaaaaaa"
 *
 *
 *  Runtime analysis:
 *      Consider the pathological dictionary -
 *          dict:["a", "aa", "aaa"]
 *          word: "aaaaaa"
 *      Let n be the # of letters in the word.
 *      For each letter, it would be included in the evaluation or not,
 *      therefore it is O(2^n) - which is exponential
 *
 *
 *  Resources:
 *      https://en.wikipedia.org/wiki/Backtracking
 *      http://thenoisychannel.com/2011/08/08/retiring-a-great-interview-problem
 *      http://www.geeksforgeeks.org/dynamic-programming-set-32-word-break-problem/
 */
public class WorkBreak {
    public static void main(String[] args) {
        System.out.printf("%s\n", WorkBreak.class.getName());

        String dict1[] = {"I","am","a","free", "freedom"};
        testBreakWord(dict1, "Iam");
        testBreakWord(dict1, "freedomIam");

        testBreakWord(dict1, "amIdofree");
        testBreakWord(dict1, "aaaaaaaaa");
        testBreakWord(dict1, "IaIaIaIaIam");
        testBreakWord(dict1, "IaIaIamIaIa");



        String dict2[] = {"a","aa","aaa"};


        // event # of as
        testBreakWord(dict2, "aa");
        testBreakWord(dict2, "aaa");
        testBreakWord(dict2, "aaaa");
        testBreakWord(dict2, "aaa");
        testBreakWord(dict2, "aaaaaa");
        testBreakWord(dict2, "aaaaaaa");

        // test with memoization
        /*System.out.printf("Testing with memoization\n");
        String input = "aaaaaaa";
        System.out.printf("input: %s, output: %s\n", input,
                breakWordWithMemoized(dict2, input));*/

        String dictionary[] = {"mobile","samsung","sam","sung","man","mango",
                "icecream","and","go","i","love","ice","cream"};


        testBreakWord(dictionary, "iloveicecreamandmango");
        testBreakWord(dictionary, "ilovesamsungmobile");

;
        String dictionary4[] = {"the","big","cat"};
        testBreakWord(dictionary4, "thebiggercat");

    }

    private static void testBreakWord(String[] dict, String str) {
        Set<String> dictSet = new HashSet<>();
        dictSet.addAll(Arrays.asList(dict));


        testBreakWord(dictSet, str);

    }
    private static void testBreakWord(Set<String> dict, String str) {
        System.out.printf("=================\n");
        System.out.printf("Dict: %s\n", dict);
        String result = breakWord(dict, str);

        System.out.printf("input: %s, output: %s\n", str, result);
    }

    /**
     * One time analysis of this is O(2^n).
     *
     * @param dict
     * @param str
     * @return
     */
    public static String breakWord(Set<String> dict, String str) {
        if (dict.contains(str)) {
            return str;
        }

        for (int i = 0; i < str.length(); i++) {
            // for substring, the second index is exclusive
            String prefix = str.substring(0, i);

            // only if prefix is a word, the recurse on the suffix
            // else keep adding characters to prefix
            if (dict.contains(prefix)) {
                String suffixAsWords = breakWord(dict, str.substring(i));
                if (suffixAsWords != null) {
                    return prefix + " " + suffixAsWords;
                }
            }
        }

        // if we got here, the can't find one of the words in dict
        return null;

    }




}
