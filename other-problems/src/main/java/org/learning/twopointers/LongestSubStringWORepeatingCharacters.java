package org.learning.twopointers;

import org.testng.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Given a string s, find the length of the longest substring without repeating characters.
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * Example 4:
 *
 * Input: s = ""
 * Output: 0
 */
public class LongestSubStringWORepeatingCharacters {
    public static void main(String[] args) {
        System.out.println("LongestSubStringWORepeatingCharacters.main");

        test("", 0);
        test("bbbbb", 1);
        test("bba", 2);
        test("abcabcbb", 3);
        test("pwwkew", 3);
        test("abcdef", 6);
        test("abcdefabcdef", 6);
        test(" ", 1);
    }

    private static void test(String input, int expected) {
        System.out.printf("input: %s\n", input);

        int actual = subStringWORepeatingChar(input);

        int actual2 = subStringWORepeatingCharUsingIntArray(input);
        System.out.printf("expected: %d, actual: %d, actual2: %d\n",
                expected, actual, actual2);

        Assert.assertEquals(actual, expected);
        Assert.assertEquals(actual2, expected);
    }

    private static int subStringWORepeatingChar(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        int maxLength = 0;
        int runLen = 0;
        int windowStart = 0;
        Map<Character, Integer> seenSoFar = new HashMap<>();

        for (int windowEnd = 0; windowEnd < input.length(); windowEnd++) {
            char currChar = input.charAt(windowEnd);
            Integer count = seenSoFar.put(currChar, seenSoFar.getOrDefault(currChar,0) + 1);
            if  (count == null) {
                runLen++;
                maxLength = Math.max(maxLength, runLen);
            } else {
                // moving the left side of the window to the right
                while (input.charAt(windowStart) != currChar)  {
                    // reduce the count and remove if needed
                    if (seenSoFar.get(input.charAt(windowStart)) == 1) {
                        seenSoFar.remove(input.charAt(windowStart));
                    } else {
                        seenSoFar.put(currChar, seenSoFar.get(input.charAt(windowStart)) - 1);
                    }
                    windowStart++;
                    runLen--;
                }
                seenSoFar.put(currChar, seenSoFar.get(currChar) - 1);
                windowStart++;
            }
        }
        return maxLength;
    }

    private static int subStringWORepeatingCharUsingIntArray(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        int maxLength = 0;
        int runLen = 0;
        int windowStart = 0;
        int[] charArray = new int[127-32];

        for (int windowEnd = 0; windowEnd < input.length(); windowEnd++) {
            char currChar = input.charAt(windowEnd);
            int charIdx = currChar - ' ';

            charArray[charIdx]++;
            if  (charArray[charIdx] == 1) {
                runLen++;
                maxLength = Math.max(maxLength, runLen);
            } else {
                // moving the left side of the window to the right
                while (input.charAt(windowStart) != currChar)  {
                    // reduce the count and remove if needed
                    charArray[input.charAt(windowStart) - ' ']--;
                    windowStart++;
                    runLen--;
                }
                charArray[charIdx]--;
                windowStart++;
            }
        }
        return maxLength;
    }
}
