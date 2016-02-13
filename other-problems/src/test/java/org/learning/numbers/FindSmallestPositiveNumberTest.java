package org.learning.numbers;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 2/13/16.
 */
public class FindSmallestPositiveNumberTest {
    @Test
    public void allNegativeNumbers() {
        int[] arr = {-1,-5};
        Assert.assertEquals(FindSmallestPositiveNumber.smallestPositiveNumber(arr), 1);
    }

    @Test
    public void allLargeNumbers() {
        int[] arr = {20, 10};
        Assert.assertEquals(FindSmallestPositiveNumber.smallestPositiveNumber(arr), 1);
    }

    @Test
    public void negativeAndLargeNumber() {
        int[] arr = {-1, 20, -20, 10, -10};
        Assert.assertEquals(FindSmallestPositiveNumber.smallestPositiveNumber(arr), 1);
    }

    @Test
    public void inRangeWithoutSmallestPositiveInteger() {
        int[] arr = {2,5,4,3};
        Assert.assertEquals(FindSmallestPositiveNumber.smallestPositiveNumber(arr), 1);
    }

    @Test
    public void inRangeWithoutSecondSmallestPositiveInteger() {
        int[] arr = {5,4,3,1};
        Assert.assertEquals(FindSmallestPositiveNumber.smallestPositiveNumber(arr), 2);
    }

    @Test
    public void inRangeWithoutSecondThirdPositiveInteger() {
        int[] arr = {2,5,4,7,1};
        Assert.assertEquals(FindSmallestPositiveNumber.smallestPositiveNumber(arr), 3);
    }

    @Test
    public void negativeLargeInrangeNumbers() {
        int[] arr = {-1,2,5,4,7,1,99,-7};
        Assert.assertEquals(FindSmallestPositiveNumber.smallestPositiveNumber(arr), 3);
    }
}
