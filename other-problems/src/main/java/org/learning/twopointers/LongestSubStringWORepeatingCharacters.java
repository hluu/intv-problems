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

        test("abcadef", 6);
        test("bcdaadef", 4);

        test("abcabkabc", 4);
    }

    private static void test(String input, int expected) {
        System.out.printf("input: %s\n", input);

        int actual = subStringWORepeatingChar(input);

        int actual2 = subStringWORepeatingCharUsingIntArray(input);

        int actual3 = subStringWORepeatingCharWithBoolArray(input);

        int actual4 = subStringWORepeatingCharTwoPointers(input);

        System.out.printf("expected: %d, actual: %d, actual2: %d,  actual3: %d, actual4: %d\n",
                expected, actual, actual2, actual3, actual4);

        Assert.assertEquals(actual, expected);
        Assert.assertEquals(actual2, expected);
        Assert.assertEquals(actual3, expected);
        Assert.assertEquals(actual4, expected);
        System.out.println();
    }

    private static int subStringWORepeatingChar(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        int maxLength = 0;
        int runLen = 0;
        int windowStart = 0;
        Map<Character, Integer> seenSoFar = new HashMap<>();
        String longestStr = null;

        for (int windowEnd = 0; windowEnd < input.length(); windowEnd++) {
            char currChar = input.charAt(windowEnd);
            Integer count = seenSoFar.put(currChar, seenSoFar.getOrDefault(currChar,0) + 1);
            if  (count == null) {
                runLen++;
                if (runLen >maxLength) {
                    maxLength = runLen;
                    longestStr = input.substring(windowStart, windowEnd+1);
                }
            } else {
                // moving the left side of the window to the right until
                // it  reaches the duplicate character
                char charAtWindowStart = input.charAt(windowStart);
                while (charAtWindowStart != currChar)  {
                    // reduce the count and remove if needed
                    if (seenSoFar.get(charAtWindowStart) == 1) {
                        seenSoFar.remove(charAtWindowStart);
                    } else {
                        seenSoFar.put(currChar, seenSoFar.get(charAtWindowStart) - 1);
                    }
                    windowStart++;
                    charAtWindowStart = input.charAt(windowStart);
                    runLen--;
                }
                // now we are at the duplicate character, decrement its count
                seenSoFar.put(currChar, seenSoFar.get(currChar) - 1);
                // move the  windowStart pass it
                windowStart++;
            }
        }
        System.out.println("longestStr: " +  longestStr);
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

    private static int subStringWORepeatingCharWithBoolArray(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        int maxLength = 0;
        int runLen = 0;
        int windowStart = 0;
        boolean[] charArray = new boolean[127-32];

        for (int windowEnd = 0; windowEnd < input.length(); windowEnd++) {
            char currChar = input.charAt(windowEnd);
            int charIdx = currChar - ' ';

            if  (!charArray[charIdx]) {
                charArray[charIdx] = true;
                runLen++;
                maxLength = Math.max(maxLength, runLen);
            } else {
                // moving the left side of the window to the right
                while (input.charAt(windowStart) != currChar)  {
                    // reduce the count and remove if needed
                    charArray[input.charAt(windowStart) - ' '] =  false;
                    windowStart++;
                    runLen--;
                }
                windowStart++;
            }
        }
        return maxLength;
    }

    private static int subStringWORepeatingCharTwoPointers(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        int maxLength = 0;
        int frontPointer = 0;
        int backPointer = 0;

        // contains the unique characters we have seen so far
        Set<Character> seenMap = new HashSet<>();

        while (frontPointer < input.length()) {
            char currChar = input.charAt(frontPointer);
            if (!seenMap.contains(currChar)) {  // not seen it before
                seenMap.add(currChar);
                frontPointer++;
                maxLength = Math.max(frontPointer - backPointer, maxLength);
                //maxLength = Math.max(seenMap.size(), maxLength);
            } else {
                // remove the character at backPointer until the
                // there is no duplicate of character at frontPointer
                seenMap.remove(input.charAt(backPointer));
                backPointer++;
            }
        }
        return maxLength;

    }
}
