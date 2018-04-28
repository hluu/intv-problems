package org.learning.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Generate all the substrings in a given string.
 * Each substring contains a consecutive number of characters in a string
 *
 * For example:
 *   String = "amox" has 10 substrings.
 *
 *   {"a", "am", "amo", "amox", "m", "mo", "mox", "o", "ox", "x"}
 *
 * Each string of len n has (n * (n +1)) / 2.
 *
 * How does one derive this number?
 *
 */
public class SubStringGenerator {
    public static void main(String[] args) {
        System.out.println(SubStringGenerator.class.getName());

        String input = "amox";
        for (String str : generateSubStrings(input)) {
            System.out.println(str);
        }
    }

    private static List<String> generateSubStrings(String str) {
        int len = str.length();

        List<String> coll = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                coll.add(str.substring(i, j+1));
            }
        }

        return coll;
    }

}
