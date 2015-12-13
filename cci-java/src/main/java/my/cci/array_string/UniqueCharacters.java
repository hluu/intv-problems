package my.cci.array_string;

import java.util.BitSet;

/**
 * Created by hluu on 12/13/15.
 *
 * Determine if a given string has all unique characters.
 * What if you can't use addition data structure
 */
public class UniqueCharacters {
    public static void main(String[] args) {

        System.out.print(UniqueCharacters.class.getName());
    }

    /**
     * Assuming the string contains only ASCII characters
     *
     * Approach #1:
     *   Need to way to way if a character has already seen.  Observe
     *   there are only 256 possible characters in ASCII.
     *   One option is to use a set or boolean input to check whether
     *   a character has already seen.
     *
     *   This example familiar one with how to convert characters into
     *   integers
     *
     * Runtime complexity: O(n)
     *
     * Space complexity: O(1) - why?  Because the additional space usage
     *                   is constant in relative to the size of the input
     *
     *
     *
     * @param input
     * @return
     */
    public static boolean isAllUnique(char[] input) {
         boolean[] charFlagArr = new boolean[256];

         for (char c : input) {
             int index = c;

             if (!charFlagArr[index]) {
                 // if haven't seen a character before, set it to true
                 charFlagArr[index] = true;
             } else {
                 return false;
             }
         }
         return true;
    }

    /**
     * The previous implementation use boolean flag, which each takes 1 byte.
     * We can do better by using a single bit per character.  Remember all we
     * need is to determine if a character has seen or not.
     *
     *
     * @param input
     * @return
     */
    public static boolean isAllUniqueUsingBitVector(char[] input) {
        BitSet bitSet = new BitSet(256);

        for (char c : input) {
            int pos = c;
            if (!bitSet.get(pos)) {
                bitSet.set(pos);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Brute force approach:
     *   We can compare each characters to every other characters.
     *   This will requires two loops, which mean it will run in O(n^2)
     *
     * @param input
     * @return
     */
    public static boolean bruteForce(char[] input) {
        for (int i = 0; i < input.length-1; i++) {
            for (int j = i+1; j < input.length; j++) {
                if (input[i] == input[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
