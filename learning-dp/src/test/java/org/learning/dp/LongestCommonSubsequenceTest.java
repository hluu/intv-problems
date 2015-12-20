package org.learning.dp;


import org.learning.common.Tuple;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 12/20/15.
 */
public class LongestCommonSubsequenceTest {

    @Test()
    public void oneEmptyString() {
        String x = "aaaaa", y = "";

        Tuple<String, int[][]> result = LongestCommonSubsequence.lcs(x,y);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.first, "");

        y = "aaaaa";
        x = "";

        Tuple<String, int[][]> result2 = LongestCommonSubsequence.lcs(x,y);
        Assert.assertEquals(result2.first, "");
    }

    @Test()
    public void noLCS() {
        String x = "aaaaa", y = "bbbbbbbbb";

        Tuple<String, int[][]> result =LongestCommonSubsequence.lcs(x,y);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.first, "");

        //LongestCommonSubsequence.printTable(result.second, x, y);
    }


    @Test()
    public void oneLetterString() {
        String x = "a", y = "a";

        Tuple<String, int[][]> result =LongestCommonSubsequence.lcs(x,y);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.first, "a");
    }

    @Test()
    public void oneLetterCommonString() {
        String x = "abcdef", y = "c";

        Tuple<String, int[][]> result =LongestCommonSubsequence.lcs(x,y);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.first, "c");
    }

    @Test()
    public void oneLetterCommonString2() {
        String y = "abcdef", x = "c";

        Tuple<String, int[][]> result =LongestCommonSubsequence.lcs(x,y);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.first, "c");
    }

    @Test()
    public void everyOtherCharOfFirstString() {
        String y = "123456789", x = "7254861";

        Tuple<String, int[][]> result =  LongestCommonSubsequence.lcs(x,y);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.first, "246");
    }
}
