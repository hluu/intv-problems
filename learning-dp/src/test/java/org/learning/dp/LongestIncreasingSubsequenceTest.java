package org.learning.dp;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 2/20/16.
 */
public class LongestIncreasingSubsequenceTest {
    @Test()
    public void test1() {
        int[] arr = {3,4,-1,5,8,2,3,12,7,9,10};
        Assert.assertEquals(LongestIncreasingSubsequence.lis(arr), 6);
    }

    @Test()
    public void test2() {
        int[] arr = {3,4,-1,0,6,2,3};
        Assert.assertEquals(LongestIncreasingSubsequence.lis(arr), 4);
    }

    @Test()
    public void test3() {
        int[] arr = {3,2,6,4,5,1};
        Assert.assertEquals(LongestIncreasingSubsequence.lis(arr), 3);
    }

    @Test()
    public void test4() {
        int[] arr = {9,5,2,8,7,3,1,6,4};
        Assert.assertEquals(LongestIncreasingSubsequence.lis(arr), 3);
    }

    @Test()
    public void test5() {
        int[] arr = {7,3,8,4,2,6};
        Assert.assertEquals(LongestIncreasingSubsequence.lis(arr), 3);
    }

    @Test()
    public void test6() {
        int[] arr = {2,4,3,5,1,7,6,9,8};
        Assert.assertEquals(LongestIncreasingSubsequence.lis(arr), 5);
    }


    @Test()
    public void test7() {
        int[] arr = {5,2,8,6,3,6,9,7};
        Assert.assertEquals(LongestIncreasingSubsequence.lis(arr), 4);
    }




}
