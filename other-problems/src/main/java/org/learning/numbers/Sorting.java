package org.learning.numbers;

import junit.framework.Assert;
import org.common.ArrayUtils;

import java.util.Arrays;

/**
 * Created by hluu on 1/1/17.
 */
public class Sorting {
    public static void main(String[] args) {
        System.out.printf("%s\n", Sorting.class.getName());

        test(1);
        test(10);
        test(15);
        test(50);
    }

    private static void test(int size) {
        System.out.printf("******* testing *********\n");
        int[] a = ArrayUtils.randomArray(size, 100);

        System.out.printf("Before: %s\n", Arrays.toString(a));

        selectionSorting(a);

        System.out.printf("After: %s\n", Arrays.toString(a));

        Assert.assertTrue(ArrayUtils.isSorted(a));

    }

    /**
     * Iterating from left to right, for each position find smallest
     * value at from that position to the end and then swapItem
     *
     * O(n^2)
     * @param a
     */
    private static void selectionSorting(int[] a) {
        if (a == null || a.length < 2) {
            return;
        }

        for (int i = 0; i < a.length; i++) {
            int lowestValueIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[lowestValueIndex]) {
                    lowestValueIndex = j;
                }
            }
            ArrayUtils.swap(a, lowestValueIndex, i);


        }

    }
}
