package my.cci.array_string;

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
     * Assuming the string contains only ASCII characters.
     *
     * Approach #1:
     *   Need to way to way if a character has already seen.  Observe
     *   there are only 256 possible characters in ASCII.
     *   One option is to use a set or boolean array to check whether
     *   a character has already seen.
     *
     *   This example familiar one with how to convert characters into
     *   integers
     *
     * @param array
     * @return
     */
    public static boolean isAllUnique(char[] array) {
         boolean[] charFlagArr = new boolean[256];

         for (char c : array) {
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
}
