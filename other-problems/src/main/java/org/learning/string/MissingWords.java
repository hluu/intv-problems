package org.learning.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a string S that contains a list of words and a string T that contains
 * a subsequence of words in string S.  Find the missing words, such that when
 * inserting them in the return order will be able to reconstruct the string S
 *
 * For example:
 *  S = "I am using hackerrank to improve programming"
 *  T = "am hackerrank to improve"
 *
 * Expected output:
 *  "I",  "using", "programming"
 *
 *
 * Approach:
 *  * Need to iterate from beginning to end of string S
 *  * Also iterating from beginning to send of string T
 *  If S(i) != T(i), we know word S(i) is missing from T, so add to output
 *  If S(i) = T(i), then increment both
 */
public class MissingWords {
    public static void main(String[] args) {
        String s = "I am using hackerrank to improve programming";
        String t = "am hackerrank to improve";

        System.out.println(findMissingWords(s,t));
    }

    private static List<String> findMissingWords(String s, String t) {
        if (s == null || t == null) {
            return Collections.emptyList();
        }
        String[] wordsFromS = s.split(" ");
        String[] wordsFromT = t.split(" ");

        System.out.println("wordsFromS: " + Arrays.toString(wordsFromS));
        System.out.println("wordsFromT: " + Arrays.toString(wordsFromT));

        List<String> result = new ArrayList<>();

        int tIdx = 0;

        for (int sIdx = 0; sIdx < wordsFromS.length; sIdx++) {
            System.out.printf("sIdx: %d, tIdx: %d\n", sIdx, tIdx);
            // to handle the case where the words at the end of S are not in T
           /* if (tIdx < wordsFromT.length && wordsFromS[sIdx].equals(wordsFromT[tIdx])) {
                tIdx++;
            } else {
                result.add(wordsFromS[sIdx]);
            }*/

           // **********************
           // NEED TO BE REALLY CAREFUL ABOUT THE EDGE CASE AT THE END OF T STRING
           if (tIdx >= wordsFromT.length || !wordsFromS[sIdx].equals(wordsFromT[tIdx])) {
               result.add(wordsFromS[sIdx]);
           } else {
               // before of the edge case
               //if (tIdx < (wordsFromT.length -1)) {
                   tIdx++;
              // }
           }
        }

        return  result;
    }
}
