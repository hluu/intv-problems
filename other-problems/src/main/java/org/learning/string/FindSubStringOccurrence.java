package org.learning.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hluu on 12/12/16.
 */
public class FindSubStringOccurrence {
    public static void main(String[] args) {
        System.out.println("FindSubStringOccurrence.main");

        String pattern = "aba";
        String str = "abababab";

        List<Integer> result = findSubStringIndexes(pattern, str);

        System.out.printf("str: %s, pattern: %s, result: %s\n",
                pattern, str, result);
    }

    private static List<Integer> findSubStringIndexes(String pattern,
                                                      String str) {

        List<Integer> result = new ArrayList<>();
        if (pattern == null || str == null || str.length() < pattern.length()) {
            return Collections.emptyList();
        }


        for (int i = 0; i <= str.length() - pattern.length(); i++) {
            boolean foundIt = true;
            for (int j = 0; j < pattern.length(); j++) {
                if (str.charAt(i+j) != pattern.charAt(j)) {
                    foundIt = false;
                    break;
                }
            }
            if (foundIt) {
                result.add(i);
            }
        }

        return result;
    }
}
