package org.learning.numbers;

import org.testng.Assert;

import java.util.Arrays;

/**
 * Merging two sorted lists of numbers
 *
 */
public class Merging {
    public static void main(String[] args) {
        System.out.println("Merging.main");

        int[] left = {1,3,5,7};
        int[] right = {2,4};

        test(left, right);
        test(right, left);

        test(new int[]{2,5}, new int[] {1,6});

    }

    private static void test(int[] left, int[] right) {
        System.out.println("left: " + Arrays.toString(left));
        System.out.println("right: " + Arrays.toString(right));

        int[] resultLR = leftToRightMerge(left, right);
        int[] resultRL = rightToLeftMerge(left, right);

        System.out.println("resultLR: " + Arrays.toString(resultLR));
        System.out.println("resultRL: " + Arrays.toString(resultRL));

        Assert.assertTrue(Arrays.equals(resultLR, resultRL));

        System.out.println();
    }

    /**
     * This method tries to provide the simplest way of doing merging.
     *
     * Observations:
     *  Tne input arrays can be of different lengths
     *
     * Assumption:
     *  Input arrays are already sorted
     *
     * Approach
     *  One approach is to go from left to right and stop when it reaches the end of
     *  either both arrays or one of the arrays.
     *
     *  Dealing with the remaining elements of the longer array can be done outside
     *  of the first loop or can be done inside the only one loop
     *
     *  This method will do the left to right merge using a single loop
     *
     */
    public static int[] leftToRightMerge(int[] left, int[] right) {
        int result[] = new int[left.length + right.length];

        int j = 0;
        int k = 0;

        // make sure the j,k boundary are correct.  Array is 0 based index
        for (int i = 0; i < result.length; i++) {
           if ((k >= right.length) || ((j < left.length) && (left[j] < right[k]))) {
               // copy elements from left array to result
               result[i] = left[j++];
           } else {
               // copy element from right array to result
               result[i] = right[k++];
           }
        }

        return result;
    }

    /**
     * Going right to left meaning copy bigger element over to result
     *
     * @param left
     * @param right
     * @return
     */
    public static int[] rightToLeftMerge(int[] left, int[] right) {
        int result[] = new int[left.length + right.length];

        int leftIndx = left.length-1;
        int rightIndx = right.length-1;

        int idx = result.length-1;
        while (leftIndx >= 0 && rightIndx >= 0) {
            // if left is greater than right, copy left else copy right
            int leftValue = left[leftIndx];
            int rightValue = right[rightIndx];

            if (leftValue == rightValue) {
                result[idx] = leftValue;
                leftIndx--;
                rightIndx--;
            } else if (leftValue > rightValue) {
                result[idx] = leftValue;
                leftIndx--;
            } else {
                result[idx] = rightValue;
                rightIndx--;
            }
            idx--;
        }

        // if remaining values in left
        while (leftIndx >= 0) {
            result[idx--] = left[leftIndx--];
        }

        // if remaining value in right
        while (rightIndx >= 0) {
            result[idx--] = right[rightIndx--];
        }

        return result;
    }

    public static int[] rightToLeftMerge2(int[] left, int[] right) {
        int result[] = new int[left.length + right.length];

        int leftIndx = left.length-1;
        int rightIndx = right.length-1;
        for (int idx = result.length-1; idx >= 0; idx--) {
            // conditions to go into if statement
            // finished with right side
            // or (left side is not node, left side value is greater than right side value
            if ((rightIndx < 0) || ((leftIndx >= 0) && (left[leftIndx] > right[rightIndx]))) {
                // copy elements from left array to result
                result[idx] = left[leftIndx--];
            } else {
                // copy element from right array to result
                result[idx] = right[rightIndx--];
            }
        }

        return result;
    }
}
