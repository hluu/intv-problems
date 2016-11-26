package org.learning.dp;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 11/6/16.
 */
public class LongestIncreasingSubsequenceTest {
    @Test
    public void test1() {
        int[] arr = {3,2,6,4,5,1};
        Assert.assertEquals(LongestIncreasingSubsequence.lis(arr), 3);
        Assert.assertEquals(LongestIncreasingSubsequence.lis2(arr), 3);
    }

    @Test
    public void test2() {
        int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60, 80 };
        Assert.assertEquals(LongestIncreasingSubsequence.lis(arr), 6);
        Assert.assertEquals(LongestIncreasingSubsequence.lis2(arr), 6);
    }

    @Test
    public void test3() {
        int arr[] = {7,3,8,4,2,6};
        Assert.assertEquals(LongestIncreasingSubsequence.lis(arr), 3);
        Assert.assertEquals(LongestIncreasingSubsequence.lis2(arr), 3);
    }

    @Test
    public void test4() {
        int arr[] = {5,6,2,3,4,-1,9,9,8,9,5};
        Assert.assertEquals(LongestIncreasingSubsequence.lis(arr), 5);
        Assert.assertEquals(LongestIncreasingSubsequence.lis2(arr), 5);
    }




}
