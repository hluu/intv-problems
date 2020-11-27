package org.learning.string;

import org.testng.Assert;

/**
 * This is a clever string search algorithm, where it search for string s in the text t.
 *
 * The brute force approach is to compare characters from s against t and start from the
 * begging of s
 * - if two characters match, then advance in the index of both strings
 * - if no match, then move s forward by one character and repeat the process
 * - Runtime would be O(n^2) of t consists of n 'a' letter and s contains n/2 'a' letter and follow by 'b'
 *
 * There are three linear string search algorithms
 * 1) KMP
 * 2) Boyer-Moor
 * 3) Rabin-Karp (easiest to understand and implement
 *
 * Rabin-Karp uses the "fingerprint" concept. We first create fingerprint of 's', then
 * compare this fingerprint with the ones we create in t as we slide one character as a time.
 * The important aspect is to leverage an additive (rolling) hashing function so we can subtract
 * and add code as we slide the fingerprint of 's' from lest to right with one character
 * at a time.  The runtime for this is O(n) where n is the number of characters in t.
 *
 * Since there might be collision of the hash number "fingerprint", additional measure needs
 * to be taken by literally comparing the character there is a "fingerprint" match in t.
 *
 */
public class StringSearchRabinKarp {
    public static void main(String[] args) {
        System.out.println("StringSearchRabinKarp.main");

        test("abc", "abcd", -1);
        test("abcdefhgi", "cde", 2);
        test("abcdefhgi", "cdex", -1);
        test("abcdefhgi", "hgi", 6);
        test("abcdefhgi", "gi", 7);
        test("abcdefhgi", "i", 8);
        test("abcdefhgi", "ab", 0);
        test("abc", "abc", 0);
    }

    private static void test(String t, String s, int expectedIdx) {
        System.out.printf("t: '%s', s:'%s'\n", t, s);

        int actual = rabinKarp(t, s);

        System.out.printf("expected: %d, actual: %d\n", expectedIdx, actual);
        System.out.println("");

        Assert.assertEquals(actual, expectedIdx);
    }

    private static int rabinKarp(String t, String s) {
        if (t.length() < s.length()) {
            return -1;
        }
        // first create the finger print of s using a naive hash function
        final int BASE = 26;
        int power = 1;
        int sFingerPrint = 0;
        int tFingerPrint = 0;
        for (int i = 0; i < s.length(); i++) {
            power = i > 0 ? power * BASE : 1; // start with one, after it would be power * BASE
            sFingerPrint = sFingerPrint * BASE + s.charAt(i);
            tFingerPrint = tFingerPrint * BASE + t.charAt(i); // starting fingerprint for t
        }

        // start from s.length();
        for (int i = s.length(); i < t.length(); i++) {
            if (sFingerPrint == tFingerPrint && t.substring(i -  s.length(),i).equals(s)) {
                return i - s.length();  // found a match
            }

            // update the rolling hash of t
            // - subtract the character at the front
            // - add the new character
            tFingerPrint -= t.charAt(i - s.length()) * power;
            tFingerPrint = tFingerPrint * BASE + t.charAt(i);
        }

        // after we are done w/ the while loop, we might have a match
        if (sFingerPrint == tFingerPrint && t.substring(t.length() -  s.length(),t.length()).equals(s)) {
            return t.length() - s.length();  // found a match
        }

        return -1;
    }
}
