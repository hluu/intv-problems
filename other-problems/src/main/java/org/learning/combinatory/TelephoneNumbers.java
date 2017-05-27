package org.learning.combinatory;

/**
 * Created by hluu on 12/11/16.
 *
 * Problem:
 *  Each telephone number represents a set of characters
 *  1 -> "ABC", 2 -> "DEF", 3-> "GHI"
 *
 *  Given a 2-dimensional array, print out the possible combination of words
 *  and see which ones exist in the dictionary
 *
 * Approach:
 *  * Since we don't know the dimension ahead of time, we need to use
 *  * recursion.  Each recursive call will advance to the next level
 *  * When we reach the last level, that's when we will print out
 *  * the string and then return.
 *  * We build the buffer as we go, but also remember to remove the letter as well.
 */
public class TelephoneNumbers {
    public static void main(String[] args) {

        System.out.println("TelephoneNumbers.main");

        char[][] letters = {
                {'A','B','C'},
                {'D','E','F'},
                {'G','H','I'},
        };

        printWords(letters, 0, new StringBuilder());
    }

    private static void printWords(char[][] letters, int rowIndex, StringBuilder soFar) {
        if (rowIndex == letters.length-1) {
            // for each letter in the last row
            for (int i = 0; i < letters[rowIndex].length; i++) {
                soFar.append(letters[rowIndex][i]);
                System.out.printf("%s\n", soFar.toString());
                // remove to make sure for the next letter in the last row
                soFar.deleteCharAt(soFar.length()-1);
            }
            return;
        }

        for (int i = 0; i < letters[rowIndex].length; i++) {
            printWords(letters, rowIndex+1, soFar.append(letters[rowIndex][i]));
            soFar.deleteCharAt(soFar.length()-1);
        }
    }


}
