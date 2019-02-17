package org.learning.others;

import org.testng.Assert;

import java.util.Arrays;
import java.util.Stack;

/**
 *
 * Problem:
 *  Given a histogram, compute the largest rectangle in it
 *
 * Example:
 *
 *    histogram = {2,1,2}  => largest rectangle is 3
 *     __    __
 *    |  |__|  |
 *    |  |  |  |
 *    ------------
 *
 *  histogram = {1,3,2,1,2}  => largest rectangle is 5
 *
 *      __
 *     |  |__    __
 *   __|  |  |__|  |
 *  |  |  |  |  |  |
 *  ---------------------
 *   0  1  2  3   4
 *
 *
 *               __
 *            __|  |
 *         __|  |  |__ __
 *      __|  |  |  |  |  |__
 *   __|  |  |  |  |  |  |  |
 *  |  |  |  |  |  |  |  |  |
 *  ---------------------------
 *   1  2   3  4  5  3  3  2
 *
 * 1,2,3,4,5,3,3,2
 *
 * Approach:
 *  * For each bar, the width continues until another bar with lower height is encountered.
 *
 *  * A new rectangle starts when we reach a new bar with a larger height
 *    and this rectangle ends when we see another bar with lower height
 *  * Maintain two stacks, one for the height and the other for the index of the bar
 *    this could be done using a single back with a tuple or object for (height, index)
 *  * Also maintain a running largest rectangle size
 *  * Iterate from left to right, when we see a taller bar, add to stack (height, index)
 *  * When we see a smaller height, compute the rectangle at that point using formula
 *    are = height * (end index - start index).  Swap with running largest rectangle size if
 *    necessary
 *  * Also add the new bar to the stack after computing the rectangle
 *  * If we see a new bar with same height as the one at the top of the stack, then skip it
 *    and keep going
 *  * now repeat until we hit the end
 */
public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        System.out.println(LargestRectangleInHistogram.class.getName());


        //test(new int[] {2,1,2}, 3);
        //test(new int[] {2,1,2}, 3);
        test(new int[] {2,3,1,3,2}, 5);
        test(new int[] {6, 2, 5, 4, 5, 1, 6}, 12);

       // test(new int[] {2,1,5,6,2,3}, 10);
      // test(new int[] {1,3,2,1,2}, 5);
        //test(new int[] {1,2,1,3,2,0,1}, 5);


       // test(new int[] {2,1,5,6,2,3}, 10);
        /*
        test(new int[] {1,10,1}, 10);
        test(new int[] {1,3,2,1,2}, 5);

        test(new int[] {1,2,3,4,5,3,3,2}, 15);*/
    }

    private static void test(int[] hist, int expectedRectSize) {
        System.out.printf("\nhist: %s, \n", Arrays.toString(hist));

        int actualRectSizeBF = maxRectangleBF(hist);

        System.out.printf("expected: %d, actualRectSizeBF: %d\n",
                expectedRectSize, actualRectSizeBF);


       /* int actualRectSizeBF2 = s1(hist);
        System.out.printf("expected: %d, actualRectSizeBF2: %d\n",
                expectedRectSize, actualRectSizeBF2); */

        int actualRectSizeOptimized = maxRectangleSizeOptimized(hist);

        System.out.printf("expected: %d, actualRectSizeOptimized: %d\n",
                expectedRectSize, actualRectSizeOptimized);


        Assert.assertEquals(expectedRectSize, actualRectSizeBF);
        Assert.assertEquals(expectedRectSize, actualRectSizeOptimized);
    }

    /**
     * This is the brute force approach.
     *  - iterate from left to right
     *  - for each index or bar, calculate the width of the left side and right
     *  - the width is extended when the height of new bar is >= the height of current bar
     *
     *  - runtime: O(n^2)
     *
     * @param hist
     * @return
     */
    private static int maxRectangleBF(int[] hist) {
        int maxRectSoFar = -1;

        for (int i = 0; i < hist.length; i++) {
            int height = hist[i];

            int left = i-1;
            while (left >= 0 && hist[left] >= hist[i]) {
                left--;
            }

            int right = i+1;
            while (right < hist.length && hist[right] >= hist[i]) {
                right++;
            }

            // make sure to count columns, rather than the gaps between two indexes
            // that is why we add 1
            int width = (right-1) - (left+1) + 1;
            int rectSize = width * hist[i];

            maxRectSoFar = Math.max(maxRectSoFar, rectSize);
        }

        return maxRectSoFar;
    }


    /**
     * Using additional data structure to do some bookkeeping so we can
     * achieve O(n) runtime.
     *
     * General algorithm:
     *   - maintain a stack to keep track of index as iterating from left to right
     *   - keep pushing the index into stack until height of new bar is greater than
     *     the bar at the top of the stack
     *   - when reach a new bar that is lower than previous bar
     *     - calculate the rectangles of previous bars until reach the one that
     *       is equal to or smaller than current bar
     *     - maintain an anchor to calculate the width of rectangles as going backward
     *   - as the last step after done iterating, ensure to process the remaining
     *     bars in the stack.  The anchor point is the last index of the array
     *
     * @param heights
     * @return
     */
    private static int maxRectangleSizeOptimized(int[] heights) {
        int maxRectSize = 0;
        // an element in the stack is an array of isze 2 - int[2] <index, height>
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            int lastPopIndex = -1;

            // keep popping until a bar that is smaller than the current one at i index
            while (!stack.isEmpty() && heights[i] < stack.peek()[1]) {
                int[] last = stack.pop();
                maxRectSize = Math.max((i - last[0]) * last[1], maxRectSize);
                lastPopIndex = last[0]; // index of last popped elm.
            }

            // the lastPopIndex is -1 when we didn't go into the while loop
            // the lastPopIndex is not -1, which means the index of the element
            // that was taller than the current bar, so we use that index instead
            // to cover the ground.

            lastPopIndex = (lastPopIndex == -1) ? i : lastPopIndex;

            stack.push(new int[] {lastPopIndex, heights[i]});
        }

        while (!stack.isEmpty()) {
            int[] last = stack.pop();
            maxRectSize = Math.max((heights.length - last[0]) * last[1], maxRectSize);
        }
        return maxRectSize;
    }
}
