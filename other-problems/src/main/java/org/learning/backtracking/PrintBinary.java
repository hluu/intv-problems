package org.learning.backtracking;

import org.common.StringUtility;

/**
 * Given a number n, print all the binary numbers with n digits
 *
 * For example:
 *  printBinary(2) => 00, 01, 10, 11
 *  printBinary(3) => 000, 001, 010, 011, 100,101,110,111
 *
 * Approach:
 *  * Backtracking algorithm
 *      1) if no more choices, return (base case)
 *      2) for all the available choices
 *          * try one choice c
 *              * solve from here and if it works out, we are done
 *          * unmake the choice
 *      3) return false;
 *
 *
 *
 *
 */
public class PrintBinary {
    public static void main(String[] args) {
        System.out.println(PrintBinary.class.getName());

        printBinary(3);

        printDecimal(3);
    }

    private static void printBinary(int n) {
        printBinaryHelper(n, "");
    }


    /**
     * Helper that does recursion to call itself to generate the digits
     *
     * Approach
     *  1) The base case is when there are no more digits to print
     *  2) prefix is what already done before
     *
     *
     * @param remainDigit
     * @param prefix
     */
    private static void printBinaryHelper(int remainDigit, String prefix) {
       // StringUtility.printSpace(prefix.length());
       // System.out.printf("pf(%d, %s)\n", remainDigit, prefix);
        if (remainDigit == 0) {
            System.out.println(prefix);
            return;
        }

        // one for 0
        printBinaryHelper(remainDigit-1, prefix + 0);

        // one for 1
        printBinaryHelper(remainDigit-1, prefix + 1);
    }

    private static void printDecimal(int digits) {
        System.out.println("*** printDecimal ***");
        printDecimalHelper(digits, "");
    }

    private static void printDecimalHelper(int remaining, String prefix) {
        if (remaining == 0) {
            System.out.println(prefix);
            return;
        }

        // for each digit
        for (int i = 0; i <= 9; i++) {
            printDecimalHelper(remaining-1, prefix + i);
        }
    }
}
