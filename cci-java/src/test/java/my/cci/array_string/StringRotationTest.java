package my.cci.array_string;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 12/31/15.
 */
public class StringRotationTest {

    @Test
    public void isRotationNotSameLength() {
        String s1 = "abc";
        String s2 = "abcd";

        Assert.assertEquals(StringRotation.isRotationOf(s1, s2), false);
    }

    @Test
    public void isRotationSameStrings() {
        String s1 = "abcd";
        String s2 = "abcd";

        Assert.assertEquals(StringRotation.isRotationOf(s1,s2), true);
    }

    @Test
    public void isRotationByOneChar() {
        String s1 = "dabc";
        String s2 = "abcd";

        Assert.assertEquals(StringRotation.isRotationOf(s1,s2), true);
    }

    @Test
    public void isRotationByTwoChar() {
        String s1 = "cdab";
        String s2 = "abcd";

        Assert.assertEquals(StringRotation.isRotationOf(s1,s2), true);
    }

    @Test
    public void noRotation() {
        String s1 = "bcaa";
        String s2 = "abcd";

        Assert.assertEquals(StringRotation.isRotationOf(s1,s2), false);
    }

    @Test
    public void isRotationByThreeChar() {
        String s1 = "bcda";
        String s2 = "abcd";

        Assert.assertEquals(StringRotation.isRotationOf(s1,s2), true);
    }

    @Test
    public void noSubStringOneCharPattern() {
        String p = "x";
        String s = "dedabcfg";

        Assert.assertEquals(StringRotation.isSubString(p, s), false);
    }


    @Test
    public void noSubStringOneCharString() {
        String p = "xyz";
        String s = "a";

        Assert.assertEquals(StringRotation.isSubString(p, s), false);
    }

    @Test
    public void oneSubString() {
        String p = "abc";
        String s = "dedabcfg";

        Assert.assertEquals(StringRotation.isSubString(p, s), true);
    }

    @Test
    public void oneSubStringAtTheBeginning() {
        String p = "abc";
        String s = "abcdefcfg";

        Assert.assertEquals(StringRotation.isSubString(p, s), true);
    }

    @Test
    public void oneSubStringAtTheEnd() {
        String p = "abc";
        String s = "defcfgabc";

        Assert.assertEquals(StringRotation.isSubString(p, s), true);
    }

    @Test
    public void partialSubStringFirst() {
        String p = "abc";
        String s = "defcabfgabc";

        Assert.assertEquals(StringRotation.isSubString(p, s), true);
    }

    @Test
    public void patternStringSameLength() {
        String p = "abc";
        String s = "abc";

        Assert.assertEquals(StringRotation.isSubString(p, s), true);
    }
}
