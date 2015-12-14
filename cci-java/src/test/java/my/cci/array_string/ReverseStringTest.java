package my.cci.array_string;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * Created by hluu on 12/13/15.
 */
public class ReverseStringTest {
    @Test
    public void singleCharacterString() {
        String input = "a";
        Assert.assertEquals(ReverseString.reverseString(input), input);
    }

    @Test
    public void twoCharacterString() {
        String input = "ab";
        String expected = new StringBuilder(input).reverse().toString();
        Assert.assertEquals(ReverseString.reverseString(input), expected);
    }

    @Test
    public void threeCharacterString() {
        String input = "abc";
        String expected = new StringBuilder(input).reverse().toString();
        Assert.assertEquals(ReverseString.reverseString(input), expected);
    }

    @Test
    public void fourCharacterString() {
        String input = "abcd";
        String expected = new StringBuilder(input).reverse().toString();
        Assert.assertEquals(ReverseString.reverseString(input), expected);
    }

    @Test
    public void twoWordsString() {
        String input = "hello there";
        Assert.assertEquals(ReverseString.reverseString(input), "there hello");
    }

    @Test
    public void threeWordsString() {
        String input = "hello there Java";
        Assert.assertEquals(ReverseString.reverseString(input), "Java there hello");
    }

    @Test
    public void fourWordsString() {
        String input = "hello there Java 8";
        Assert.assertEquals(ReverseString.reverseString(input), "8 Java there hello");
    }
}
