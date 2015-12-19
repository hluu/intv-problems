package my.epi.combination;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by hluu on 12/18/15.
 */
public class AllPossibleCombinationsTest {
    @Test
    public void oneSubSet() {
        int[] inputs = {1,2,3};
        int k = 1;

        List<String> result = AllPossibleCombinations.printCombinations(inputs, k);

        Assert.assertEquals(3, result.size());

        List<String> result2 = AllPossibleCombinations.printCombinationsWithBooleanArray(inputs, k);

        Assert.assertEquals(3, result2.size());

        Assert.assertEquals(result, result2);
    }
}
