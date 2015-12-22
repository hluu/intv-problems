package org.learning.dp;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 12/21/15.
 */
public class EditDistanceTest {
    @Test
    public void emptyX() {
        String x = "";
        String y = "abc";

        Assert.assertEquals(EditDistance.minDistance(x, y).first, Integer.valueOf(3));
    }

    @Test
    public void emptyY() {
        String x = "abc";
        String y = "";

        Assert.assertEquals(EditDistance.minDistance(x, y).first, Integer.valueOf(3));
    }

    @Test
    public void oneDistanceFromInsertion() {
        String x = "ab";
        String y = "abc";

        Assert.assertEquals(EditDistance.minDistance(x, y).first, Integer.valueOf(1));
    }

    @Test
    public void oneDistanceFromDeletion() {
        String x = "abc";
        String y = "abcd";

        Assert.assertEquals(EditDistance.minDistance(x, y).first, Integer.valueOf(1));
    }

    @Test
    public void twoDistance() {
        String x = "axcy";
        String y = "abcd";

        Assert.assertEquals(EditDistance.minDistance(x, y).first, Integer.valueOf(2));
    }

    @Test
    public void randomString() {
        String x = "hien";
        String y = "luu";

        Assert.assertEquals(EditDistance.minDistance(x, y).first, Integer.valueOf(4));
    }

    @Test
    public void commonMisspelledWordWithReplacement() {
        String x = "beleive";
        String y = "believe";

        Assert.assertEquals(EditDistance.minDistance(x, y).first, Integer.valueOf(2));
    }

    @Test
    public void commonMisspelledWordWithInsertion() {
        String x = "belive";
        String y = "believe";

        Assert.assertEquals(EditDistance.minDistance(x, y).first, Integer.valueOf(1));
    }
}
