package org.learning.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a method to generate all valid of n pairs of parenthesis.
 *
 * For example:
 *  n = 1 ==> ()
 *  n = 2 ==> (()) ()()
 *  n = 3 ==> ((())) (()()) (())(), ()(()), ()()()()
 *
 * Approaches:
 *  * There are two general approaches and they both use recursion
 *    * First one is to insert an open and close parentheses before and after each open paran.
 *      However, this generate duplicates
 *    * Second one is by recognizing the number of open and close parans is same as n
 *      * generate open parans as long as it doesn't run out
 *      * generate close parans as long as it doesn't run out and have less than or equal to left
 *        parans
 *
 */
public class GenerateParans {
    public static void main(String[] args) {
        test(1);
        test(2);
        test(3);
        test(4);
    }

    private static void test(int n) {
        System.out.println("*** test(" + n + ") *** ");
        List<String> collector = new ArrayList<>();
        char[] buf = new char[n*2];

        generate(buf, 0, n, n, collector);

        System.out.println(collector);
    }

    /**
     * This is doing a DFS on the left paran, then right paran
     *
     * @param buf
     * @param index
     * @param leftParan
     * @param rightParan
     * @param collector
     */
    private static void generate(char[] buf, int index,
                                 int leftParan, int rightParan, List<String> collector) {

        // the key thing here is checking for leftParan < 0 as a part of the base case
        // number of close paran can't be more than left paran
        if (leftParan < 0 || rightParan < leftParan) {
            return;
        }

        // when run out of both open and close parans then add to our collector
        if (leftParan == 0 && rightParan == 0) {
            collector.add(String.copyValueOf(buf));
            return;
        }


        // buff will be over written
        buf[index] = '(';
        generate(buf, index+1, leftParan-1, rightParan, collector);

        buf[index] = ')';
        generate(buf, index+1, leftParan, rightParan-1, collector);
    }
}
