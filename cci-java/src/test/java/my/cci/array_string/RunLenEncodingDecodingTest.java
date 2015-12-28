package my.cci.array_string;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 12/28/15.
 */
public class RunLenEncodingDecodingTest {
    @Test
    public void emptyString() {
        String input = "";
        Assert.assertEquals(RunLenEncodingDecoding.runLenEncoding(input), "");
    }

    @Test
    public void oneAndTwoCharacterString() {
        String input = "a";
        Assert.assertEquals(RunLenEncodingDecoding.runLenEncoding(input), input);

        input = "bb";
        Assert.assertEquals(RunLenEncodingDecoding.runLenEncoding(input), input);
    }

    @Test
    public void compressedStringLongerThanOriginalString() {
        String input = "abcde";
        Assert.assertEquals(RunLenEncodingDecoding.runLenEncoding(input), input);

    }

    @Test
    public void compressedStringLenSameAsOriginalString() {
        String input = "aabb";
        Assert.assertEquals(RunLenEncodingDecoding.runLenEncoding(input), input);

    }

    @Test
    public void compressedStringWithMorethanOneSameCharAtTheEnd() {
        String input = "aabcccccaaa";
        Assert.assertEquals(RunLenEncodingDecoding.runLenEncoding(input), "a2b1c5a3");
    }

    @Test
    public void compressedStringWithMorethanUniqueCharAtTheEnd() {
        String input = "aabbbbccccca";
        Assert.assertEquals(RunLenEncodingDecoding.runLenEncoding(input), "a2b4c5a1");
    }
}
