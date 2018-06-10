package org.learning.others;

import org.testng.Assert;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by hluu on 8/11/17.
 *
 * Problem:
 *  Given a histogram, compute the largest rectangle in it
 *
 * Example:
 *  histogram = {1,3,2,1,2}  => largest rectangle is 5
 *
 *      __
 *     |  |__    __
 *   __|  |  |__|  |
 *  |  |  |  |  |  |
 *  ---------------------
 *   0  1  2  3   4
 *
 * Approach:
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

        test(new int[] {1,3,2,1,2}, 5);
        test(new int[] {2,1,5,6,2,3}, 10);
    }

    private static void test(int[] hist, int expectedRectSize) {
        int actualRectSize = maxRectangleSize(hist);

        System.out.printf("hist: %s, expected: %d, actual: %d\n",
                Arrays.toString(hist), expectedRectSize, actualRectSize);

        int actualRectSize2 = maxRectangleSize2(hist);
        System.out.printf("hist: %s, expected: %d, actual2: %d\n",
                Arrays.toString(hist), expectedRectSize, actualRectSize2);

        //Assert.assertEquals(actualRectSize, expectedRectSize);
    }


    private static int maxRectangleSize(int[] hist) {
        Stack<Integer> heightStack = new Stack();
        Stack<Integer> indexStack = new Stack();

        int maxRectSoFar = -1;

        for (int i = 0; i < hist.length; i++) {
            int height = hist[i];
            if (heightStack.isEmpty()) {
                heightStack.push(height);
                indexStack.push(i);
            } else if (height > heightStack.peek()) {
                // greater than
                heightStack.push(height);
                indexStack.push(i);
            } else if (height < heightStack.peek()) {
                // new bar is less than top of stack
                // compute the rectangle
                int size = height * (i - indexStack.peek());
                maxRectSoFar = Math.max(maxRectSoFar, size);

                // pop current bar
                heightStack.pop();
                indexStack.pop();

                // push the new bar
                heightStack.push(height);
                indexStack.push(i);
            }  // else equal, then keep going
        }

        // make sure to handle the remaining elements in the stack
        while(!heightStack.isEmpty()) {
            int size = heightStack.pop() * (hist.length - indexStack.pop());
            maxRectSoFar = Math.max(maxRectSoFar, size);
        }

        return maxRectSoFar;
    }

    private static int maxRectangleSize2(int[] histogram) {
        int maxArea = 0;

        int currHeight = 0;
        for (int i = 0; i < histogram.length; i++) {

            currHeight = histogram[i];
            if (i == 0) {
                int idx = 1;
                // check for right side
                while (idx < histogram.length && currHeight >= histogram[idx]) {
                    idx++;
                }

                int area = (idx + 1) * currHeight;
                maxArea = Math.max(maxArea, area);
            } else {
                // left side
                int left = 0;
                // right side
                int idx = i-1;
                while (idx >= 0 && currHeight <= histogram[idx]) {
                    idx--;
                    left++;
                }


                int right = 0;
                idx = i+1;
                while (idx < histogram.length && currHeight <= histogram[idx]) {
                    idx++;
                    right++;
                }

                int area = (left + right +1) * currHeight;
                maxArea = Math.max(maxArea, area);
            }


        }
        return maxArea;
    }
}
