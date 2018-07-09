package org.learning.string;

import java.util.HashSet;
import java.util.Set;

/**
 * 1. Base rule: take the first letter of a word, move it to the end, and add "ay".
 * Example: "hello" becomes "ellohay".
 *
 * 2. A word which begins with a vowel ('a', 'e', 'i', 'o', 'u') keeps its first letter
 * and adds "way" to the end of the word.
 * Example: "apples" becomes "applesway"
 *
 * 3. A phrase with multiple words should translate each word.
 * Example: "hello earth" becomes "ellohay earthway"
 *
 * 4. A word which is capitalized should remain capitalized after translation.
 * Example: "Hello Earth" becomes "Ellohay Earthway"
 *
 * 5. A phrase with punctuation should maintain the position of the punctuation.
 * Example: "Hello, world!" becomes "Ellohay, orldway!"
 *
 * 6. A word beginning with multiple consonants should move all of them together to the end.
 * Example: "drunk strangers" becomes "unkdray angersstray"
 *
 * 7. The letters "qu" should stay together when moved to the end of a word.
 * Example: "quickly and quietly" becomes "icklyquay andway ietlyquay"
 */
public class LanguageTranslation {
    private static Set<Character> vowels = new HashSet<Character>();
    private static final String SUFFIX1 = "ay";
    private static final String SUFFIX2 = "way";

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */

        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');


        //String input1 = "apples";
        //String input1 = "hello earth";

        // "Hello Earth" becomes "Ellohay Earthway"
        //String input1 = "Hello Earth";

        //String input1 = "drunk strangers";
        String input1 = "quickly and quietly";




        // String prefix = extractConsonants(input1);
        //    System.out.printf("input: %s, output: %s\n", input1, output);

        String output = toPigLatin(input1);

        System.out.printf("input: %s, output: %s\n", input1, output);
    }

    private static String toPigLatin(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        String[] words = input.split(" ");

        StringBuilder buf = new StringBuilder();

        for (String word : words) {
            if (buf.length() > 0) {
                buf.append(" ");
            }
            String prefix = extractConsonants(word);
            String suffix = word.substring(prefix.length());
            suffix = handleSuffixCapitalization(prefix, suffix);
            buf.append(suffix);
            prefix = handlePrefixCapitalization(prefix);
            buf.append(prefix);
            addSuffix(prefix, buf);
        }

        return buf.toString();
    }

    private static String handlePrefixCapitalization(String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return prefix;
        }

        if (Character.isUpperCase(prefix.charAt(0))) {
            return prefix.toLowerCase();
        } else {
            return prefix;
        }
    }
    private static String handleSuffixCapitalization(String prefix, String suffix) {
        if (prefix == null || prefix.isEmpty()) {
            return suffix;
        }
        if (Character.isUpperCase(prefix.charAt(0))) {
            return Character.toUpperCase(suffix.charAt(0)) + suffix.substring(1);
        } else {
            return suffix;
        }
    }
    private static void addSuffix(String prefix, StringBuilder buf) {
        if (prefix.isEmpty()) {
            buf.append(SUFFIX2);
        } else {
            buf.append(SUFFIX1);
        }
    }

    private static final String SPECIAL_QU_PREFIX = "qu";
    private static String extractConsonants(String str) {
        StringBuilder buf = new StringBuilder();

        if (str.startsWith(SPECIAL_QU_PREFIX)) {
           return SPECIAL_QU_PREFIX;
        } else {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (!vowels.contains(c)) {
                    buf.append(Character.toString(c));
                } else {
                    break;
                }
            }
        }

        return buf.toString();
    }

}
