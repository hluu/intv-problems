package my.cci.array_string;

/**
 *
 *
 * All permutations of a set, means all possible ways of ordering those objects
 *
 */
public class Permutation {

    public static void main(String[] args) {
        System.out.println(Permutation.class.getName());
        String s1 = "apple";
        String s2 = "eppla";
        String s3 = "abcde";
        System.out.println(s1 + ", " + s2 + " => " +  isPermutation(s1,s2));
        System.out.println(s1 + ", " + s3 + " => " +  isPermutation(s1,s3));
    }

    /**
     * Whether s1 is a permutation of s2.  Permutation is defined
     * as two string having the same set of characters and their occurrence.
     *
     *   "abc" => "acb"
     *   "apple" => "papel"
     *
     * There are multiple ways of maintaining the book keeping:
     *
     * 1) Using a hashmap
     * 2) Using an int array : a little simpler to work with, since
     *    there are only 256 possible characters in ASCII
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isPermutation(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }

        if (s1.length() != s2.length()) {
            return  false;
        }

        int[] charCounterArr = new int[256];

        char[] s1CharArr = s1.toCharArray();
        char[] s2CharArr = s2.toCharArray();

        // counting the characters in s1;
        for (int i = 0; i < s1CharArr.length; i++) {
            int index = s1CharArr[i];
            charCounterArr[index] = charCounterArr[index] + 1;
        }

        // now check whether characters in s2 are in s1
        // pay attention to characters that appear more than once
        for (int i = 0; i < s2CharArr.length; i++) {
            int index = s2CharArr[i];
            // expecting the value to be 1 or more.
            // if see 0, means that there was a mismatch
            int value = charCounterArr[index];
            if (value == 0) {
                return false;
            }
            value--;
            // now update the value
            charCounterArr[index] = value;
        }
        return true;

    }
}
