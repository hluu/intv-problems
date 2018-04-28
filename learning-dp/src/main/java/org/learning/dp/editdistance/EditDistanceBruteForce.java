package org.learning.dp.editdistance;

import org.common.NumberUtility;

/**
 * Given two strings, find minimum edits to transform one string into another using
 * one of the three possible operations.
 *
 * 1) Insert a new character into one of the strings
 * 2) Delete an existing character
 * 3) Replace one character by another
 *
 *  A = “cat” and B = “cars”, the min distance is 2
 *
 *
 *
 *
 * https://jlordiales.me/2014/03/01/dynamic-programming-edit-distance/
 */
public class EditDistanceBruteForce {

    public static void main(String[] args) {

        System.out.println(EditDistanceBruteForce.class.getName());

        String str1 = "cat", str2 = "cars";

        System.out.println(minEditDistance(str1, str2));
    }

    private static int minEditDistance(String str1, String str2) {
        if (str1.isEmpty()) return str2.length();
        if (str2.isEmpty()) return str1.length();

        int replaceCost = minEditDistance(str1.substring(1), str2.substring(1)) +
                replaceCost(str1, str2, 0,0);

        int delCost = minEditDistance(str1.substring(1), str2) + 1;
        int insertCost = minEditDistance(str1, str2.substring(1)) + 1;

        return NumberUtility.min(replaceCost, delCost, insertCost);

    }

    private static int replaceCost(String w1, String w2, int w1Index, int w2Index) {
        return (w1.charAt(w1Index) == w2.charAt(w2Index)) ? 0 : 1;
    }


}
