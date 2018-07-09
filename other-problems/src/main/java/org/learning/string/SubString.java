package org.learning.string;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * Problem:
 *  Give a string and a pattern, find all the occurrences of that patterns within
 *  the given string.
 *
 *  For example:
 *      string: "ababababc"
 *      pattern: "ab"
 *
 *  Return the indexes of the occurrences of the patterns
 *
 *  Notice there maybe overlapping:
 */
public class SubString {
    public static void main(String[] args) {

        System.out.println("SubString.main");

        test("I am here", "am", Arrays.asList(2));
        test("I am here am", "am", Arrays.asList(2, 10));
        test("ababab", "ab", Arrays.asList(0,2,4));
        test("abababc", "ab", Arrays.asList(0,2,4));
    }

    private static void test(String str, String p, List<Integer> expectedList) {
        System.out.printf("str: %s, pattern: %s, expected: %s\n", str, p, expectedList);

        List<Integer> actual = findPattern(str, p);

        Assert.assertEquals(actual.toString(), expectedList.toString());

        List<Integer> actual2 = findPattern2(str, p);

        Assert.assertEquals(actual2.toString(), expectedList.toString());
    }

    private static List<Integer> findPattern(String str, String pattern) {
        if (str == null || pattern == null){
            return Collections.EMPTY_LIST;
        }

        List<Integer> indexList = new ArrayList<>();

        int strLen = str.length();
        int patternLen = pattern.length();

        // no need to search if length of pattern is greater than string length
        if (patternLen > strLen) {
            return indexList;
        }

        /* be careful of the one off situation here */
        /* one important point is i will only stop if i > strLen - patternLen */

        for (int i = 0; i <= strLen - patternLen; i++) {
            int matchCnt  = 0;
            while (matchCnt < patternLen &&
                    str.charAt(i+matchCnt) == pattern.charAt(matchCnt)) {
                matchCnt++;
            }
            if (matchCnt == patternLen) {
                indexList.add(i);
            }

        }

        return indexList;

    }

    /**
     * This one uses the different way of detecting pattern matching. Using
     * two for loops
     *
     * @param str
     * @param pattern
     * @return
     */
    private static List<Integer> findPattern2(String str, String pattern) {

        System.out.printf("*** findPattern2 ***");

        if (str == null || pattern == null){
            return Collections.EMPTY_LIST;
        }

        List<Integer> indexList = new ArrayList<>();

        int strLen = str.length();
        int patternLen = pattern.length();

        if (patternLen > strLen) {
            return indexList;
        }

        for (int i = 0; i <= strLen - patternLen; i++) {
            boolean foundIt  = true;

            for (int j = 0; j < patternLen; j++) {
                if (str.charAt(i+j) != pattern.charAt(j)) {
                    foundIt = false;
                    break;
                }
            }

            if (foundIt) {
                indexList.add(i);
            }

        }

        return indexList;

    }
}
