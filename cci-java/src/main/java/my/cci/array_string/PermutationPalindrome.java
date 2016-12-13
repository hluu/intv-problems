package my.cci.array_string;

import org.testng.Assert;

/**
 * Created by hluu on 12/13/16.
 *
 * Problem:
 *  Give a string, determine if any of the permutations is a palindrome
 *
 *  Palindrome -> reading the same forward and backward as
 *  i.e madam, civic, abba
 *
 * Approach:
 *  Brute force: generate all permutations of string, and perform palindrome
 *  check on each of the permutations.
 *
 *  Runtime analysis of this is O(n! * n) where n is the length of string
 *
 * Optimized approach:
 *  How can we take advantage of the property of palindrome?
 *  For a string with even # of characters,
 *      then the count will be even for each of the characters in that string
 *  For a string with odd # of characters,
 *      then the count will be even for each of the characters in that string,
 *      except one character will have odd count
 *
 *  At the end of the day, we don't absolute need the count, we just need to
 *  know of odd or even, therefore it is binary 0 or 1.  Similar to switching
 *  on light on or off -> even # of switches, the light will be off, odd # of
 *  switches, the light will be on.
 *
 *  There are two steps:
 *    1) Create a bit vector
 *    2) Iterate through the characters and flip the corresponding bit in
 *       the vector according to the bit index
 *    3) There are 26 characters (lower case) 'a' ==> 0, 'z' ==> '25'
 *
 */
public class PermutationPalindrome {
    public static void main(String[] args) {
        System.out.println("PermutationPalindrome.main");

        test("abc", false);
        test("abcc", false);
        test("madbam", false);
        test("aaa", true);
        test("civic", true);
        test("ccvii", true);
        test("aabb", true);
        test("madam", true);


    }

    private static boolean test(String str, boolean expected) {
        boolean result = isPermPalindrome(str);
        System.out.printf("str: %s, expected: %b, actual: %b\n",
                str, expected, result);

        Assert.assertEquals(result, expected);

        return result;
    }

    private static boolean isPermPalindrome(String str) {
        if (str == null || str.length() < 1) {
            return false;
        }

        int bitVector = createBitVector(str);

        // bitVector value can either be zero or has only one bit on
        return (bitVector == 0) ? true : ((bitVector & (bitVector - 1)) == 0);

    }

    private static final int BaseCharacterIntValue = Character.getNumericValue('a');
    private static int createBitVector(String str) {
        int bitVector = 0;


        for (char c : str.toCharArray()) {
            int bitIndex = Character.getNumericValue(c) - BaseCharacterIntValue;
            bitVector = flipBit(bitVector, bitIndex);
        }

        return  bitVector;
    }

    private static int flipBit(int bitVector, int bitIndex) {
        // if the bit in bitVector at position bitIndex is 0, then it become 1
        // other it becomes 0
        int mask = 1 << bitIndex;

        /*
        if ((bitVector & mask) == 0) {
            // that bit was 0
            bitVector = bitVector | mask;
        } else {
            // inverse the mask, and turn that bit to 0
            bitVector = bitVector & ~mask;
        }

        return  bitVector;
        */

        return bitVector ^ mask;
    }
}
