package org.learning.string.permutation;

public class PermutationUsingBooleanArray {

    public static void main( String args[] ) {
        char[] array = new char[] { 'a', 'b', 'c', 'd' };
        char[] perm = new char[array.length];
        boolean[] used = new boolean[256];
        permute(array, perm, 0, used);
    }

    /**
     * Note that we are using a boolean array to remember what
     * character we have already used and another array perm
     * which contains the actual permutation. We can actually
     * generate permutations without these additional arrays but
     * for now the desire is to make the code easier to understand.
     *
     * This is a version of backtracking approach
     */
    static void permute(char[] array, char[] perm, int i, boolean[] used) {

        // base case - when i reaches the end
        if (i == perm.length) {
            System.out.println(perm);
            return;
        }

        // for each character
        for (int j = 0; j < array.length; j++) {

            // if not use yet
            if (!used[array[j] - 'a']) {
                // place it at perm[i]
                perm[i] = array[j];
                // mark it as used
                used[array[j] - 'a'] = true;
                // permute to next i
                permute(array, perm, i + 1, used);
                // done using it, unmark it
                used[array[j] - 'a'] = false;
            }
        }
    }
}
