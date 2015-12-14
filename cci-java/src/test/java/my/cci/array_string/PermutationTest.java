package my.cci.array_string;

import junit.framework.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 12/13/15.
 */
public class PermutationTest {
    @Test
    public void emptyStringPositive() {
        Assert.assertTrue(Permutation.isPermutation("",""));
    }

    @Test
    public void singleCharacterPositive() {
        Assert.assertTrue(Permutation.isPermutation("a","a"));
    }

    @Test
    public void sameStringPositive() {
        String input = "apple";
        Assert.assertTrue(Permutation.isPermutation(input,input));
    }

    @Test
    public void simpleUniqueCharacterPositive() {
        Assert.assertTrue(Permutation.isPermutation("abc","cba"));
    }

    @Test
    public void duplicateCharactersPositive() {
        Assert.assertTrue(Permutation.isPermutation("apple","papel"));
        Assert.assertTrue(Permutation.isPermutation("ccccc","ccccc"));
    }

    @Test
    public void singleCharacterNegative() {
        Assert.assertFalse(Permutation.isPermutation("a", "z"));
    }

    @Test
    public void uniqueCharactersNegative() {
        Assert.assertFalse(Permutation.isPermutation("abcd", "edfg"));
    }

    @Test
    public void differentLengthNegative() {
        Assert.assertFalse(Permutation.isPermutation("apple", "apple1"));
    }

    @Test
    public void duplicateCharactersNegative() {
        Assert.assertFalse(Permutation.isPermutation("ccccccc", "ccccc"));
    }
}
