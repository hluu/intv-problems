package org.learning.string;

import org.testng.Assert;

import java.util.*;

/**
 * Created by hluu on 2/1/18.
 *
 * You have a dictionary (set) of words. You need to implement a spellchecker that
 * returns the correct word for an input word.
 *
 * The spellchecker only handles the following two class of spelling mistakes -
 * Capitalization:
 *   Example 1: <set: {yellow, radish}, input: yelloW, output: yellow>
 *   Example 2: <set: {Yellow, radish}, input: yelloW, output: Yellow>
 *
 * Vowels (letters in the set {a,e,i,o,u}) mixed up - consonants are in the correct order,
 * but one or more vowels in the input word is/are not the same as the vowels in the
 * corresponding dictionary word
 *   Example 1: <set: {yellow, radish}, input: yollow, output: yellow>
 *   Example 2: <set: {yellow, radish}, input: redosh, output: radish>
 *
 * In addition, the following cases should be handled -
 * When there is no valid suggestion, your function should return the string "NONE".
 * When there is more than one valid suggestion, your function can return any one of them.
 * When there is no spelling mistake in the input (exact match found), your function should return the same word back.
 */
public class SimpleSpellChecker {
    private Map<String, List<String>> dictionary = new HashMap<>();
    private static Set<Character> VOWELS = new HashSet<>();

    public static void main(String[] args) {
        SimpleSpellChecker spellChecker = new SimpleSpellChecker(
                Arrays.asList("yellow", "Yellow", "redish"));

        //test(spellChecker, "yelloW", "yellow");
        test(spellChecker,"Yellow", "Yellow");
        test(spellChecker,"yellow", "yellow");

        test(spellChecker,"non","NONE");
        test(spellChecker,"","NONE");
        test(spellChecker,null,"NONE");

        System.out.println(spellChecker.normalizeString("hien"));

    }

    private static void test(SimpleSpellChecker spellChecker, String input, String expectedOutput) {
        String output = spellChecker.spellCheck(input);

        System.out.printf("Input: %s, expected: %s output: %s\n", input, expectedOutput, output);
        Assert.assertEquals(output, expectedOutput);
    }




    /**
     * Use map to store the words in the dictionary:
     *  * Key as the normalize word with lower case
     *  * Value as a list of words with same normalized word
     *
     *
     * @param dict
     */
    public SimpleSpellChecker(List<String> dict) {
        if (dict == null) {
            return;
        }

        for (String word : dict) {

            List<String> originWords = dictionary.get(word.toLowerCase());
            if (originWords == null) {
                originWords = new ArrayList<String>();
            }
            originWords.add(word);

            dictionary.put(word.toLowerCase(), originWords);
        }


        VOWELS.add('a');
        VOWELS.add('e');
        VOWELS.add('i');
        VOWELS.add('o');
        VOWELS.add('u');

        VOWELS.add('A');
        VOWELS.add('E');
        VOWELS.add('I');
        VOWELS.add('O');
        VOWELS.add('U');

        System.out.println("Vowels:" + VOWELS);
    }


    /**
     * Algorithm:
     * 1) Normalize the given word
     * 2) Look it up in the dictionary
     * 3) If there is a match, iterate through the list
     * 4) Else return none
     *
     * @param word
     * @return NONE or the match word
     */
    public String spellCheck(String word) {
        if (word == null) {
            return "NONE";
        }
        String lowerCaseWord = word.toLowerCase();
        List<String> originalWords = dictionary.get(lowerCaseWord);
        if (originalWords != null) {
            String result = null;
            for (String originalWord : originalWords) {
                result = originalWord;
                if (originalWord.equals(word)) {
                    result = word;
                    break;
                }
            }
            return result;
        } else {
            return "NONE";
        }

    }

    private String normalizeString(String input) {
        if (input == null) {
            return input;
        }

        input = input.toLowerCase();
        char[] charArr = input.toCharArray();

        for (int i = 0; i < charArr.length; i++) {
            if (VOWELS.contains(charArr[i])) {
                charArr[i] = 'a';
            }
        }

        return new String(charArr);
    }


}
