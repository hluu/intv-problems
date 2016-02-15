package org.learning.numbers;

import junit.framework.Assert;
import org.common.ArrayUtils;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * Created by hluu on 2/15/16.
 */
public class MergingTest {
    @Test
    public void leftToRightSameSizeArraysTest() {
        int[] left = ArrayUtils.randomlySortedArray(5, 15);
        int[] right = ArrayUtils.randomlySortedArray(5, 15);

        System.out.println("left: " + Arrays.toString(left));
        System.out.println("right: " + Arrays.toString(right));

        int[] result = Merging.leftToRightMerge(left, right);

        Assert.assertTrue(ArrayUtils.isSorted(result));
    }

    @Test
    public void leftToRightLeftSmallerArraysTest() {
        int[] left = ArrayUtils.randomlySortedArray(5, 15);
        int[] right = ArrayUtils.randomlySortedArray(10, 15);


        int[] result = Merging.leftToRightMerge(left, right);
        System.out.println(Arrays.toString(result));
        System.out.println(ArrayUtils.isSorted(result));

        Assert.assertTrue(ArrayUtils.isSorted(result));
    }

    @Test
    public void leftToRightLeftLargerArraysTest() {
        int[] left = ArrayUtils.randomlySortedArray(15, 15);
        int[] right = ArrayUtils.randomlySortedArray(6, 15);


        int[] result = Merging.leftToRightMerge(left, right);
        System.out.println(Arrays.toString(result));
        System.out.println(ArrayUtils.isSorted(result));

        Assert.assertTrue(ArrayUtils.isSorted(result));
    }

    @Test
    public void rightToLeftSameSizeArraysTest() {
        int[] left = ArrayUtils.randomlySortedArray(5, 15);
        int[] right = ArrayUtils.randomlySortedArray(5, 15);

        System.out.println("left: " + Arrays.toString(left));
        System.out.println("right: " + Arrays.toString(right));

        int[] result = Merging.rightToLeftMerge(left, right);

        Assert.assertTrue(ArrayUtils.isSorted(result));
    }

    @Test
    public void rightToLeftLeftSmallerArraysTest() {
        int[] left = ArrayUtils.randomlySortedArray(5, 15);
        int[] right = ArrayUtils.randomlySortedArray(10, 15);


        int[] result = Merging.rightToLeftMerge(left, right);
        System.out.println(Arrays.toString(result));
        System.out.println(ArrayUtils.isSorted(result));

        Assert.assertTrue(ArrayUtils.isSorted(result));
    }

    @Test
    public void rightToLeftLeftLargerArraysTest() {
        int[] left = ArrayUtils.randomlySortedArray(15, 15);
        int[] right = ArrayUtils.randomlySortedArray(6, 15);


        int[] result = Merging.rightToLeftMerge(left, right);
        System.out.println(Arrays.toString(result));
        System.out.println(ArrayUtils.isSorted(result));

        Assert.assertTrue(ArrayUtils.isSorted(result));
    }
}
