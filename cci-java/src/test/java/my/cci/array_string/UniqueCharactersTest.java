package my.cci.array_string;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 12/13/15.
 */
public class UniqueCharactersTest {

    @Test
    public void positiveTestSingleChar() {
        char[] input = "a".toCharArray();
        runPositiveTests(input);
    }

    @Test
    public void positiveTest() {
        char[] input = "ABCZ".toCharArray();
        runPositiveTests(input);
    }

    @Test
    public void positiveTest2() {
        char[] input = "ABCZabcz".toCharArray();
        runPositiveTests(input);
    }

    @Test
    public void negativeTwoCharsTest() {
        char[] input = "bb".toCharArray();
        runNegativeTests(input);

    }

    @Test
    public void negativeTest1() {
        char[] input = "ABCAZ".toCharArray();
        runNegativeTests(input);

    }

    @Test
    public void negativeTest2() {
        char[] input = "ABBCAZ".toCharArray();
        runNegativeTests(input);

    }

    private void runPositiveTests(char[] input) {
        Assert.assertTrue(UniqueCharacters.isAllUnique(input));
        Assert.assertTrue(UniqueCharacters.isAllUniqueUsingBitVector(input));

        Assert.assertTrue(UniqueCharacters.bruteForce(input));
    }

    private void runNegativeTests(char[] input) {
        Assert.assertFalse(UniqueCharacters.isAllUnique(input));
        Assert.assertFalse(UniqueCharacters.isAllUniqueUsingBitVector(input));

        Assert.assertFalse(UniqueCharacters.bruteForce(input));
    }
}
