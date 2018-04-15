package org.learning.backtracking;

import org.common.StringUtility;

/**
 * String permutation
 *
 * ABC
 * ACB
 * BAC
 * BCA
 * CAB
 * CBA
 *
 *
 * Using backtracking approach
 *
 *
 */
public class StringPermutation {

    public static void main(String[] args) {
        System.out.println(StringPermutation.class.getName());

        permute("abcd");
        permute("MARTY");
    }

    private static void permute(String str) {
        counter = 0;
        permuteHelper(str, "");

        System.out.println();
    }

    private static int counter = 0;
    private static void permuteHelper(String remainStr, String choosenStr) {
        //StringUtility.printSpace(choosenStr.length());
        //System.out.printf("permute(%s, %s)\n", remainStr, choosenStr);


        if (remainStr.isEmpty()) {
            counter++;
            // base  case
            // nothing more to permute
            System.out.printf("%4d:%s\n", counter, choosenStr);
            return;
        }


        for (int i = 0; i < remainStr.length(); i++) {
            // choose character at i
            char c = remainStr.charAt(i);

            choosenStr += c;

            // take that character out of the remainStr
            String tmpStr = remainStr.substring(0,i) + remainStr.substring(i+1);

            // explore the remain string (tmpStr), with the new choosen
            permuteHelper(tmpStr, choosenStr);

            // unchoose
           choosenStr = choosenStr.substring(0, choosenStr.length()-1);


        }
    }
}
