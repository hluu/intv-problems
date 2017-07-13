package my.cci.array_string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hluu on 7/11/17.
 *
 * Problem:
 *  Give a string with characters and numbers
 *  Determine the subarray(s) that has/have same number of characters and numbers
 *
 *  For example:
 *      s = {a,b,c,1,1,b,1,2,3}
 *
 *  Output:
 *      a = {(b,c,1,1},(1,b),(b,1)}
 *
 *  Approach:
 *      Brute force:
 *          * For each sub-array determine if the number of letters and digits are the same
 *          * This will be O(n^2) n being number of letters in the string
 */
public class SameCharacterNumberInSubarray {
    public static void main(String[] args) {
        String str = "abc11b123";

        System.out.println(str);

        test(str);
    }

    private static void test(String str) {
        int letterCnt = 0;
        int numberCnt = 0;

        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                numberCnt++;
            } else if (Character.isLetter(c)) {
                letterCnt++;
            }
        }

        System.out.printf("There are %d letters and %d numbers", letterCnt, numberCnt);

        List<String> result = bruteForce(str);
        System.out.println("result: " + result);
    }

    /**
     * For each possible subarray, check whether # of characters and digits are the same.
     *
     * For both to have the same, the combined length should be even.  If odd, there is no
     * way they are the same.  This is one simple optimization to reduce the checking by half
     * of the time
     *
     *
     * @param str
     * @return
     */
    private static List<String> bruteForce(String str) {
        List<String> result = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return result;
        }

        for (int i = 0; i < str.length() -1; i++) {
            for (int j = i+1; j < str.length(); j++) {
                if (isLetterCharSameCount(str, i,j)) {
                    result.add(str.substring(i,j+1));
                }
            }
        }


        return  result;
    }

    /**
     * Both i and j are inclusive
     *
     * @param str
     * @param i
     * @param j
     * @return
     */
    private static boolean isLetterCharSameCount(String str, int i, int j) {
        // same # characters and digits
        int count = 0;

        System.out.println("subtr: " + str.substring(i,j+1));

        // if odd length, can't be the same
        if ((j - i + 1) % 2 == 1) {
            System.out.println("**** skipping ****");
            return false;
        }

        for (int k = i; k <= j; k++) {
            char character = str.charAt(k);
            if (Character.isDigit(character)) {
                count++;
            } else if (Character.isLetter(character)) {
                count--;
            } else {
                throw new IllegalStateException("Not a digit or a character: " + character);
            }
        }

        return (count == 0);
    }
}
