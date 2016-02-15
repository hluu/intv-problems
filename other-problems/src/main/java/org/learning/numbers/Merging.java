package org.learning.numbers;

import java.util.Arrays;

/**
 * Created by hluu on 2/14/16.
 */
public class Merging {
    public static void main(String[] args) {
        System.out.println("Merging.main");

        int[] left = {1,3,5,7};
        int[] right = {2,4};

        System.out.println("left: " + Arrays.toString(left));
        System.out.println("right: " + Arrays.toString(right));

        System.out.println("result: " + Arrays.toString(leftToRightMerge(left, right)));
        System.out.println("result: " + Arrays.toString(leftToRightMerge(right, left)));


        System.out.println("result: " + Arrays.toString(leftToRightMerge(new int[]{2,5},
                new int[] {1,6})));



        System.out.println("=== right left merging ===");
        System.out.println("result: " + Arrays.toString(rightToLeftMerge(left, right)));
        System.out.println("result: " + Arrays.toString(rightToLeftMerge(right, left)));

        System.out.println("result: " + Arrays.toString(rightToLeftMerge(new int[]{2, 5},
                new int[]{1, 6})));
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

        int j = left.length-1;
        int k = right.length-1;
        for (int i = result.length-1; i >= 0; i--) {
            // conditions to go into if statement
            // finished with right side
            // or (left side is not node, left side value is greater than right side value
            if ((k < 0) || ((j >= 0) && (left[j] > right[k]))) {
                // copy elements from left array to result
                result[i] = left[j--];
            } else {
                // copy element from right array to result
                result[i] = right[k--];
            }
        }

        return result;
    }
}
