package org.learning.string;

import org.testng.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given 2 words, determine if they are isomorphic, which is defined as there is a 1 to 1 mapping
 * between the letters in the first word to the second word.  One constraint is no 2 different letters can
 * map to the same letter, in either direction.
 *
 * Examples:
 * "foo" => "app"   = true
 * "foo" => "boa"   = false
 * "bar" => "foo"   = false (because 2 o letters of foo are mapped to different characters in bar)
 * "em" => "me"     = true
 * "ab" => "ca"     = true
 *
 * "turtle" => "tletur" = true
 *
 * How to solve this for n words, where n > 2?
 *
 * Approach:
 *  1) Need mapping from of each character from word1 to word2, as well as mapping of each character
 *     from word2 to word1
 *
 *
 */
public class IsomorphicWords {
    public static void main(String[] args) {
        System.out.println(IsomorphicWords.class.getName());

        // tru tests
        test("abc", "def", true);
        test("foo", "app", true);

        // false tests
        test("foo", "boa", false);
        test("boa", "foo", false);


        // true case
        test("abc", "def", "ghi", true);
        test("foo", "app", "brr", true);

        // false case
        test("foo", "boa", "brr", false);
        test("foo", "brr", "boa", false);


    }

    private static void test(String w1, String w2, boolean expectedResult) {
        System.out.printf("==== w2 - %s, w2: %s, er: %b\n", w1, w2, expectedResult);

        boolean actualResult = areTheyIsomorphic(w1, w2);

        System.out.printf("actualResult: %b\n", actualResult);

        Assert.assertEquals(actualResult, expectedResult);

        Assert.assertEquals(actualResult, areTheyIsomorphic2(w1, w2));

        System.out.println();

    }


    private static void test(String w1, String w2, String w3, boolean expectedResult) {
        System.out.printf("=== w3 - w1: %s, w2: %s, w3: %s er: %b\n", w1, w2, w3, expectedResult);

        boolean actualResult = areTheyIsomorphic(w1, w2, w3);

        System.out.printf("actualResult: %b\n", actualResult);

        Assert.assertEquals(actualResult, expectedResult);

        System.out.println();
    }



    private static boolean areTheyIsomorphic(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return false;
        }

        if (word1.length() != word2.length()) {
            return false;
        }

        // if there is only one character, then the mapping should be good
        if (word1.length() == 1) {
            return true;
        }

        Map<Character,Character> letter1ToLetter2Mapping = new HashMap<>();
        Map<Character,Character> letter2ToLetter1Mapping = new HashMap<>();

        for (int idx = 0; idx < word1.length(); idx++) {
            Character c1 = word1.charAt(idx);
            Character c2 = word2.charAt(idx);

            // this must include checking for both side, not just one side
            if (!letter1ToLetter2Mapping.containsKey(c1) && !letter2ToLetter1Mapping.containsKey(c2)) {
                letter1ToLetter2Mapping.put(c1, c2);
                letter2ToLetter1Mapping.put(c2, c1);
            } else {
                // from word1 to word2
                Character mappedToChar2 = letter1ToLetter2Mapping.get(c1);
                if (mappedToChar2 != c2) {
                    return false;
                }

                // from word2 to word1
                Character mappedToChar1 = letter2ToLetter1Mapping.get(c2);
                if (mappedToChar1 != c1) {
                    return false;
                }
            }
        }

        return true;

    }

    /**
     * This solution uses the count at the index of the character in the array.
     *
     *
     * @param word1
     * @param word2
     * @return
     */
    private static boolean areTheyIsomorphic2(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return false;
        }

        if (word1.length() != word2.length()) {
            return false;
        }

        // if there is only one character, then the mapping should be good
        if (word1.length() == 1) {
            return true;
        }

        int[] w1Arr = new int[256];
        int[] w2Arr = new int[256];

        // unnecessary, but good to have
        Arrays.fill(w1Arr, 0);

        for (int i = 0; i < word1.length(); i++) {
            if (w1Arr[word1.charAt(i)] == w2Arr[word2.charAt(i)]) {
                w1Arr[word1.charAt(i)]++;
                w2Arr[word2.charAt(i)]++;
            } else {
                return false;
            }
        }

        return true;


    }

    private static boolean areTheyIsomorphic(String w1, String w2, String w3) {
        return areTheyIsomorphic(w1, w2) && areTheyIsomorphic(w2, w3);
    }

    /**
     * Give a list of words, are they isomorphic.  This is an extension of the previous
     * problem, going from 2 to n words.
     *
     *
     *
     * @param word2
     * @return
     */
    private static boolean areTheyIsomorphic(List<String> word2) {
        return false;
    }
}
