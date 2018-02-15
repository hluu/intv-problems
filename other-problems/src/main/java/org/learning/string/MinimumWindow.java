package org.learning.string;

import org.common.Tuple;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S and a shorter string T, find the minimum window in S that contains all the
 * characters in string T.
 *
 * For example:
 *  S = "acbbaca"
 *  T = "aba"
 *
 * The minimum window is "baca".
 *
 * cabeca cae  => (3,5)
 * cfabeca cae => (4,6)
 * cabefgecdaecf cae => (9,11)
 * acbdbaab aabd  => 3 6   dbaa
 *
 * Write a function to return the index of start and end of the window.
 *
 * Approach:
 *  There are multiple approaches:
 *  1) Find the indexes of each letter in String T and mind the minimum range from that
 *  2) Maintain two tables for string T - one for what needs to be found, what has found so far
 *     * Each table has the key as the character and value as the count
 *       (each character can appear multiple times in string T)
 *     * Iterate through each character in S
 *     * If that characters is in needs to be found, then update the has found if
 *       its count is less than what's in to be found
 *     * We also maintain a running count of found characters.  If its value is
 *       equivalent to the number of actual number of characters in T, then
 *
 *
 */
public class MinimumWindow {
    public static void main(String[] args) {
        System.out.println(MinimumWindow.class.getName());

        String longStr = "acbbaca";
        String shortStr = "aba";

        test("acbbaca", "aba", "baca");
        test("cfabeca", "cae", "eca");
        test("fffffcfabecaffffffff", "cae", "eca");

        test("acbba", "aab", "acbba");

        test("acbbaab", "aab", "baa");

        test("a", "b", "");
        test("aa", "a", "a");

        test("aaa", "aaa", "aaa");
    }

    private static void test(String longStr, String shortStr, String expectedSubString) {
        System.out.printf("longStr: %s, shortStr: %s\n", longStr, shortStr);
        Tuple<Integer,Integer> result = myFindMinWindow(longStr, shortStr);

        if (result.first != -1) {
            String subString1 = longStr.substring(result.first, result.second + 1);
            System.out.println("tuple: " + result + " substring1: " + subString1);

            Assert.assertEquals(subString1, expectedSubString);

            String subString2 = minWindow(longStr, shortStr);
            System.out.println("substring2: " + minWindow(longStr, shortStr));

            Assert.assertEquals(subString2, expectedSubString);
        } else {
            System.out.println("*** no window");
        }
        System.out.println();

    }

    private static Tuple<Integer, Integer> myFindMinWindow(String longStr, String shortStr) {
        if (longStr == null || shortStr == null) {
            return new Tuple<>(-1,-1);
        }

        if (shortStr.length() > longStr.length()) {
            return new Tuple<>(-1,-1);
        }


        Map<Character,Integer> needToFind = new HashMap<>();
        Map<Character,Integer> hasFound = new HashMap<>();

        // build the need to find map
        for (char c : shortStr.toCharArray()) {
            if (needToFind.containsKey(c)) {
                needToFind.put(c, 1 + needToFind.get(c));
            } else {
                needToFind.put(c, 1);
            }

            // initialized to 0
            hasFound.put(c, 0);
        }


        int numCharFound = 0;
        int beginIdx = 0;

        int startRange = 0;
        int endRange = Integer.MAX_VALUE;

        // iterate from beginning to end of long string
        for (int endIdx = 0; endIdx < longStr.length(); endIdx++) {
            char charInLongStr = longStr.charAt(endIdx);

            // no need to do anything when a character is not short string
            if (!needToFind.containsKey(charInLongStr)) {
                continue;
            }

            // keep increasing the count
            hasFound.put(charInLongStr, hasFound.get(charInLongStr)+1);

            // only when we actually found a new character then bump up the counter
            if (hasFound.get(charInLongStr) <= needToFind.get(charInLongStr)) {
                numCharFound++;
            }

            if (numCharFound == shortStr.length()) {
                // we found all the characters we need
                // the endIdx represents the end index, now we need
                // to figure out the begin index
                // when do we stop?, when it doesn't break the constraints
                // which are: when the current char is is a part short string


                char currentChar = longStr.charAt(beginIdx);

                // advance the beginIdx only if currentChar is not in the need to find list
                // or the count of currentChar is more than what we need
                while (!needToFind.containsKey(currentChar) ||
                        hasFound.get(currentChar) > needToFind.get(currentChar)) {

                    if (needToFind.containsKey(currentChar)) {
                        // decrement the count the beginIdx has moved beyond the previous window
                        hasFound.put(currentChar, hasFound.get(currentChar) - 1);
                    }

                    beginIdx++;
                    currentChar = longStr.charAt(beginIdx);
                }

                // determine to update the range or not
                if ((endIdx - beginIdx) < (endRange - startRange)) {
                    startRange = beginIdx;
                    endRange = endIdx;
                }
            }
        }

        if (endRange == Integer.MAX_VALUE) {
            return new Tuple<>(-1, -1);
        } else {
            return new Tuple<>(startRange, endRange);
        }

    }

    public static String minWindow(String longStr, String shortStr) {
        if(shortStr.length()>longStr.length())
            return "";
        String result = "";

        //character counter for t
        HashMap<Character, Integer> needToFind = new HashMap<Character, Integer>();
        for(int i=0; i<shortStr.length(); i++){
            char c = shortStr.charAt(i);
            if(needToFind.containsKey(c)){
                needToFind.put(c,needToFind.get(c)+1);
            }else{
                needToFind.put(c,1);
            }
        }

        // character counter for s
        HashMap<Character, Integer> foundSoFar = new HashMap<Character, Integer>();
        int left = 0;
        int minLen = longStr.length()+1;

        int count = 0; // the total of mapped characters

        for(int endIdx=0; endIdx<longStr.length(); endIdx++){
            char c = longStr.charAt(endIdx);

            if(needToFind.containsKey(c)){
                if(foundSoFar.containsKey(c)){
                    if (foundSoFar.get(c) < needToFind.get(c)) {
                        count++;
                    }
                    foundSoFar.put(c,foundSoFar.get(c)+1);
                } else {
                    foundSoFar.put(c,1);
                    count++;
                }
            }

            if (count == shortStr.length()) {
                char sc = longStr.charAt(left);
                while (!foundSoFar.containsKey(sc) || foundSoFar.get(sc) > needToFind.get(sc)) {
                    if (foundSoFar.containsKey(sc) && foundSoFar.get(sc) > needToFind.get(sc))
                        foundSoFar.put(sc, foundSoFar.get(sc) - 1);
                    left++;
                    sc = longStr.charAt(left);
                }

                if (endIdx - left + 1 < minLen) {
                    result = longStr.substring(left, endIdx + 1);
                    minLen = endIdx - left + 1;
                }
            }
        }

        return result;
    }
}
