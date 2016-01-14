package org.learning.string;

import java.util.ArrayList;
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
 *      Run time is n!, n is the length of characters in the string
 *
 *  Approach:
 *      General approach is to pick a character at position 0 and permute on the
 *      remaining characters.
 *
 *      This can be done via swapping or boolean array
 */
public class AllPermutations {
    public static void main(String[] args) {

        System.out.println("AllPermutations.main");

        //String str = "dog";
        String str = "abcd";

        List<String> output = new ArrayList<>();

        permuteUsingSwapping(str.toCharArray(), output,0);
        System.out.println(output.size());
        System.out.println("output1: " + output);
        output.clear();

        permuteUsingBooleanFlags(str.toCharArray(), new boolean[str.length()], 0, output,
                new StringBuilder(str.length()));
        System.out.println(output.size());
        System.out.println("output2: " + output);


    }

    public static void permuteUsingSwapping(char[] letters, List<String> output, int pos) {
        if (pos == letters.length) {
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

    private static void swap(char[] letter, int i, int j) {
        char tmp = letter[i];
        letter[i] = letter[j];
        letter[j] = tmp;
    }

    public static void permuteUsingBooleanFlags(char[] letters, boolean[] flags,
                                                int pos, List<String> output, StringBuilder buf) {

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
