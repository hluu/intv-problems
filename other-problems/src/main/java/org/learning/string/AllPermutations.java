package org.learning.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by hluu on 1/13/16.
 *
 * Problem statement:
 *  Give an string, print out all the permutations of characters in that string
 *
 *  For example:
 *      dog -> dog, dgo, odg, ogd, god, gdo
 *
 *      Run time is n!, n is the length of characters in the string.  Why?
 *
 *      Let's the length of string is 4:
 *      4 possibilities for the first position
 *      3 possibilities for the second position
 *      2 possibilities for the third position
 *      1 possibilities for the third position
 *
 *  Approach:
 *      Using recursion
 *
 *      General approach is to pick a character at position 0 and permute on the
 *      remaining characters.
 *
 *      This can be done via swapping or boolean array
 */
public class AllPermutations {
    public static void main(String[] args) {

        System.out.println("AllPermutations.main");

        String tmp = "a";
        System.out.printf("output: \"%s\"\n", tmp.substring(0,0));


        //String str = "dog";
        String str = "abc";

        List<String> output = new ArrayList<>();

        permuteUsingSwapping(str.toCharArray(), output,0);
        System.out.println(output.size());
        Collections.sort(output);
        System.out.println("output1: " + output);
        output.clear();


        permutationWithBooleanFlagsTest(str);

        permutationWithSubString(str);
    }



    private static void permutationWithSubString(String str) {
        System.out.printf("***** permutationWithSubString\n");
        List<String> output2 = new ArrayList<>();
        permUsingSubstring("", str, output2);
        System.out.println(output2.size());
        Collections.sort(output2);
        System.out.println("output2: " + output2);
    }

    private static void permutationWithBooleanFlagsTest(String str) {
        List<String> output2 = new ArrayList<>();
        permuteUsingBooleanFlags(str.toCharArray(), new boolean[str.length()], 0,
                output2,
                new StringBuilder(str.length()));
        System.out.println(output2.size());
        Collections.sort(output2);
        System.out.println("output2: " + output2);
    }

    /**
     * From index of 0 to the # of characters in the string.
     *
     * @TODO - need more understand of the algorithm
     *
     * @param letters
     * @param output
     * @param pos
     */
    public static void permuteUsingSwapping(char[] letters, List<String> output, int pos) {
        if (pos == (letters.length - 1)) {
            output.add(new String(letters));
            return;
        }

        for (int i = pos; i < letters.length; i++) {
            //swap
            swap(letters, pos, i);
            // permute
            permuteUsingSwapping(letters, output, pos+1);
            // swap back
            swap(letters, pos, i);
        }
    }

    /**
     *
     * @param prefix
     * @param s
     * @param output
     */
    private static void permUsingSubstring(String prefix, String s, List<String> output) {

        System.out.printf("prefix: \"%s\", s: \"%s\"\n", prefix, s);
        int n = s.length();
        if (n == 0) {
            output.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                String prefixTmp = prefix + s.charAt(i);
                // pluck out the characters before i and characters after i
                String tmp = s.substring(0, i) + s.substring(i + 1, n);
                //System.out.printf("prefix: \"%s\", s: \"%s\"\n", prefix, tmp);
                permUsingSubstring(prefixTmp,
                        tmp,
                        output);
            }
        }

    }

    private static void swap(char[] letter, int i, int j) {
        char tmp = letter[i];
        letter[i] = letter[j];
        letter[j] = tmp;
    }

    public static void permuteUsingBooleanFlags(char[] letters, boolean[] flags,
                                                int pos, List<String> output,
                                                StringBuilder buf) {

        // base case
        if (pos == letters.length) {
            output.add(buf.toString());
            return;
        }

        for (int i = 0; i < flags.length; i++) {
            if (flags[i]) {
                continue;
            }

            flags[i] = true;
            buf.append(letters[i]);

            permuteUsingBooleanFlags(letters, flags, pos+1, output, buf);

            flags[i] = false;
            // last characters
            buf.deleteCharAt(buf.length()-1);
        }
    }
}
