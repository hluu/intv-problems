package org.learning.others;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by hluu on 11/26/16.
 *
 * Problem:
 *  Give a histogram, calculate the max rectangle inside the histogram
 *
 *  For example:
 *
 *  |      --
 *  |     |  |
 *  |   --|  |--
 *  |  |  |  |  |
 *  |--|  |  |  |--
 *  |  |  |  |  |  |
 *  ---------------------
 *
 *  The histogram is represented as an array of heights. For the above histogram,
 *  the array is [1,2,3,2,1], and the maximum rectangle is 6 from 2x3
 *
 * Approach:
 *  * Observation:
 *      * Bigger histograms cover the previous smaller one
 *      * Smaller histograms ends the streak of previous bigger histograms
 *      * Need to maintain the pos of previous history until smaller one is encounter
 *      * As bigger history gram is seen, keep previous smaller histograms around as well
 *        as the new bigger one
 *      * Need some kind of a list, but need to access element in the order of seen
 *      * LIFO -> Stack
 *  * Maintain a stack of elements in the order of seen of increasing histogram size
 *  * When encounter a smaller histogram, pop the bigger ones out until no more or
 *    smaller one
 *
 * Algorithm:
 *  * If next histogram with top histogram in stack
 *      1) If equal, do nothing
 *      2) If >, then add to stack
 *      3) If < calculate the height of previous bigger histogram and update max
 *
 *
 */
public class MaxHistogram {
    public static void main(String[] args) {
        System.out.printf("%s\n", MaxHistogram.class.getName());

        int hist1[] = {1,2,3,2,1};  // expect max of 6

        int hist2[] = {1,1,1,1};  // expect max of 4
        int hist3[] = {3,4,2};    // expect max of 6
        int hist4[] = {1,2,3};    // expect max of 4
        int hist5[] = {4,3,2,1};  // expect max of 4
        int hist6[] = {1,2,3,4, 2,1};  // expect max of 8
        int hist7[] = {1,2,3,1, 0,1};  // expect max of 4

        int hist8[] = {3}; // expect max of 3

        int hist9[] = {1,3,4,2}; // expect max of 6
        int hist10[] = {1,3,5,1}; // expect max of 6
        int hist11[] = {1,2,3,1}; // expect max of 4

        testMaxRectangle(hist1, 6);
        testMaxRectangle(hist2, 4);
        testMaxRectangle(hist3, 6);
        testMaxRectangle(hist4, 4);
        testMaxRectangle(hist5, 4);
        testMaxRectangle(hist6, 8);
        testMaxRectangle(hist7, 4);
        testMaxRectangle(hist8, 3);
        testMaxRectangle(hist9, 6);
        testMaxRectangle(hist10, 6);
    }

    private static void testMaxRectangle(int[] hist, int expectedMax) {
        int tmp = maxRectangle(hist);

        System.out.printf("hist: %s expected max: %d actual max: %d\n",
                Arrays.toString(hist), expectedMax, tmp);

        if (tmp == expectedMax) {
            System.out.printf("======= SUCCESSFUL ======\n");
        } else {
            System.out.printf("******* FAILED ******\n");
        }


    }

    private static int maxRectangle(int[] hist) {
        if (hist == null || hist.length < 1) {
            return -1;
        }

        int maxRect = 0;
        Stack<Integer> histStack = new Stack<Integer>();
        Stack<Integer> posStack = new Stack<Integer>();

        histStack.push(hist[0]);
        posStack.push(0);


        for (int pos = 1; pos < hist.length; pos++) {
            int topElmHeight = histStack.peek();

            if (hist[pos] > topElmHeight) {
                histStack.push(hist[pos]);
                posStack.push(pos);
            } else if (hist[pos] < topElmHeight) {
                // there could be multiple histograms in stack < than current one
                while (!histStack.isEmpty()) {
                    // break when equal or smaller histogram
                    if (histStack.peek() <= hist[pos]) {
                        break;
                    }

                    // must be only bigger historgrams
                    int tmp = histStack.peek() * (pos - posStack.peek());
                    maxRect = Math.max(tmp, maxRect);

                    // pop
                    histStack.pop();
                    posStack.pop();
                }

                // figure out whether to push or not
                // push when stack is empty or see smaller one
                // don't need to push when see same height
                if (histStack.isEmpty() || histStack.peek() < hist[pos]) {
                    histStack.push(hist[pos]);
                    posStack.push(pos);
                }
            }
        }

        // perform the max computation of remaining histograms in stack
        while (!histStack.isEmpty()) {
            int tmp = histStack.pop() * (hist.length - posStack.pop());
            maxRect = Math.max(tmp, maxRect);
        }

        return maxRect;
    }


}
