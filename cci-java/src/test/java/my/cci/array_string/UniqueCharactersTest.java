package my.cci.array_string;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 12/13/15.
 */
public class UniqueCharactersTest {
    @Test
    public void positiveTest() {

        Assert.assertTrue(UniqueCharacters.isAllUnique("ABC".toCharArray()));
    }

    @Test
    public void negativeTest1() {

        Assert.assertFalse(UniqueCharacters.isAllUnique("ABCA".toCharArray()));
    }

    @Test
    public void negativeTest2() {
        Assert.assertFalse(UniqueCharacters.isAllUnique("ABBCA".toCharArray()));
    }
}
