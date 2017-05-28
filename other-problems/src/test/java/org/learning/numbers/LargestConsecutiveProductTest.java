package org.learning.numbers;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 1/10/16.
 */
public class LargestConsecutiveProductTest {
    @Test
    public void allPositiveNumbers() {
        int arr[] = {3,2,6,4,5,1};
        Assert.assertEquals(LargestConsecutiveProduct.maxContiguousProduct(arr), 720);
    }

    @Test
    public void oneNegativeNumber() {
        int[] arr = {1, -2, 3, 4};
        Assert.assertEquals(LargestConsecutiveProduct.maxContiguousProduct(arr), 12);
    }

    @Test
    public void twoNegativeNumbers() {
        int[] arr = {1, -2, 3, -4};
        Assert.assertEquals(LargestConsecutiveProduct.maxContiguousProduct(arr), 24);
    }

    @Test
    public void threeNegativeNumbers() {
        int[] arr = {1, -2, 3, -4, -5};
        Assert.assertEquals(LargestConsecutiveProduct.maxContiguousProduct(arr), 60);

        int[] arr2 = {-2, -3, -4};
        Assert.assertEquals(LargestConsecutiveProduct.maxContiguousProduct(arr2), 12);
    }

    @Test
    public void fourNegativeNumbers() {
        int[] arr = {-2, -3, -2, -10};
        Assert.assertEquals(LargestConsecutiveProduct.maxContiguousProduct(arr), 120);
    }

    @Test
    public void fourNegativeNumbersWithZeroInMiddle() {
        int[] arr = {-2, -3, 0, -2, -10};
        Assert.assertEquals(LargestConsecutiveProduct.maxContiguousProduct(arr), 20);
    }

    @Test
    public void twoNegativeNumbersOnOneSideOfZero() {
        int[] arr = {6, -3, -10, 0, 2};
        Assert.assertEquals(LargestConsecutiveProduct.maxContiguousProduct(arr), 180);
    }

    @Test
    public void twoNegativeNumbersOnOtherSideOfZero() {
        int[] arr = {2, 0, 6, -3, -10};
        Assert.assertEquals(LargestConsecutiveProduct.maxContiguousProduct(arr), 180);
    }

    @Test
    public void allOddNegativeNumbers() {
        int[] arr = {-1, -2, -3, -4,-5};
        Assert.assertEquals(LargestConsecutiveProduct.maxContiguousProduct(arr), 120);
    }

    @Test
    public void allEvenNegativeNumbers() {
        int[] arr = {-1, -3, -4,-2};
        Assert.assertEquals(LargestConsecutiveProduct.maxContiguousProduct(arr), 24);
    }


}
