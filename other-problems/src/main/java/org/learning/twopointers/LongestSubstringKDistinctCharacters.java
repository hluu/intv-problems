package org.learning.twopointers;

import java.util.HashMap;
import java.util.Map;

/**
 * Longest substring length w/ k distinct characters
 *
 * Given a string you need to print longest possible substring that has exactly M unique characters.
 * If there are more than one substring of longest possible length, then print any one of them.
 *
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 *
 * -- good article
 * https://www.geeksforgeeks.org/find-the-longest-substring-with-k-unique-characters-in-a-given-string/
 *
 * Examples:
 * "aabbcc", k = 1
 *  Max substring can be any one from {"aa" , "bb" , "cc"}.
 *
 * "aabbcc", k = 2
 * Max substring can be any one from {"aabb" , "bbcc"}.
 *
 * "aabbcc", k = 3
 * There are substrings with exactly 3 unique characters
 * {"aabbcc" , "abbcc" , "aabbc" , "abbc" }
 * Max is "aabbcc" with length 6.
 *
 * "aaabbb", k = 3
 * There are only two unique characters, thus show error message.
 *
 * Input: S = "eceba" and k = 3
 * Output: 4
 * Explanation: T = "eceb"
 *
 * Input: S = "WORLD" and k = 4
 * Output: 4
 * Explanation: T = "WORL" or "ORLD"
 */
public class LongestSubstringKDistinctCharacters {
    public static void main(String[] args) {
        System.out.println("LongestSubstringKDistinctCharacters.main");
        test("aabbcc", 1, 2);
        test("aabbcc", 2, 4);
        test("aabbcc", 3, 6);
        test("aaabbb", 3, 0);
        test("eceba", 3, 4);
    }

    private static void test(String input, int k, int expected) {
        System.out.printf("input: %s, k: %d\n", input, k);

        int actual = longestSubstringDistChar(input, k);

        System.out.printf("expected: %d, actual: %d\n", expected, actual);
        System.out.println();
    }

    /**
     * Using the dynamic window sliding approach with two pointer (windowStart, windowEnd)
     * @param input
     * @param k
     * @return length of substring that meets the k distinct characters
     */
    private static int longestSubstringDistChar(String input, int k) {
        int maxLength = 0;
        String maxSubString ="";
        Map<Character, Integer> characterCountMap = new HashMap<>();

        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < input.length(); windowEnd++) {
            char currChar = input.charAt(windowEnd);
            characterCountMap.put(currChar, characterCountMap.getOrDefault(currChar,0) + 1);

            // the number of unique characters is equal to length of the characterCountMap
            // maintain the invariant where its size must be >= k by
            // - reducing the window by moving the windowStart to the right
            while (characterCountMap.size() > k) {
                char startChar = input.charAt(windowStart);
                characterCountMap.put(startChar, characterCountMap.get(startChar) - 1);
                if (characterCountMap.get(startChar) == 0) {
                    characterCountMap.remove(startChar);
                }
                windowStart++;
            }
            if (characterCountMap.size() == k) {
                if (windowEnd - windowStart + 1 > maxLength) {
                    maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
                    maxSubString = input.substring(windowStart, windowEnd + 1);
                }
            }

        }

        System.out.println("maxSubString: " + maxSubString);
        return maxLength;
    }
}
