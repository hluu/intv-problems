package my.epi.combination;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hluu on 12/16/15.
 */
public class SubSetSumTest {

    @Test
    public void noMatchingSubSet() {
        int[] input = {3,2,7,1};
        int sum = 15;
        String expectedResult = "3,2,1";

        List<String> collector = new ArrayList<String>();

        SubSetSum.allSubsetSum(input, sum, sum, 0, "", collector);

        Assert.assertEquals(collector.size(), 0);


        List<String> collector2 = new ArrayList<String>();

        SubSetSum.allSubsetSumUsingBitArray(input, new int[input.length], sum, 0,0,
                collector2);

        Assert.assertEquals(collector2.size(), 0);
    }

    @Test
    public void oneSubSet() {
        int[] input = {3,2,7,1};
        int sum = 6 ;
        String expectedResult = "3,2,1";

        List<String> collector = new ArrayList<String>();

        SubSetSum.allSubsetSum(input, sum, sum, 0, "", collector);

        Assert.assertEquals(collector.size(), 1);
        System.out.println(collector);
        Assert.assertEquals( collector.get(0), expectedResult);

        List<String> collector2 = new ArrayList<String>();

        SubSetSum.allSubsetSumUsingBitArray(input, new int[input.length], sum, 0,0,
                collector2);

        Assert.assertEquals(collector2.size(), 1);
        System.out.println(collector2);
        Assert.assertEquals( collector2.get(0), expectedResult);

    }

    @Test
    public void oneSubSet2() {
        int[] input = {3,2,7,1};
        int sum = 11 ;
        String expectedResult = "3,7,1";

        List<String> collector = new ArrayList<String>();

        SubSetSum.allSubsetSum(input, sum, sum, 0, "", collector);

        Assert.assertEquals(collector.size(), 1);
        System.out.println(collector);
        Assert.assertEquals( collector.get(0), expectedResult);

        List<String> collector2 = new ArrayList<String>();

        SubSetSum.allSubsetSumUsingBitArray(input, new int[input.length], sum, 0,0,
                collector2);

        Assert.assertEquals(collector2.size(), 1);
        System.out.println(collector2);
        Assert.assertEquals( collector2.get(0), expectedResult);

    }

    @Test
    public void twoSubSets() {
        int[] input = {3,2,7,1};
        int sum = 3;

        List<String> expectedResults = Arrays.asList("3", "2,1");


        List<String> collector = new ArrayList<String>();

        SubSetSum.allSubsetSum(input, sum, sum, 0, "", collector);

        Assert.assertEquals(collector.size(), 2);
        System.out.println(collector);
        for (String result : collector) {
            Assert.assertTrue(expectedResults.contains(result));
        }


        List<String> collector2 = new ArrayList<String>();

        SubSetSum.allSubsetSumUsingBitArray(input, new int[input.length], sum, 0,0,
                collector2);

        Assert.assertEquals(collector2.size(), 2);
        for (String result : collector2) {
            Assert.assertTrue(expectedResults.contains(result));
        }

    }

    @Test
    public void emptyInput() {
        int[] input = {};
        int sum = 6 ;
        List<String> collector = new ArrayList<String>();

        SubSetSum.allSubsetSum(input, sum, sum, 0, "", collector);
        System.out.println(collector);

        Assert.assertTrue(collector.isEmpty());
    }
}
