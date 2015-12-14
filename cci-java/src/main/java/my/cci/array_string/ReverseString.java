package my.cci.array_string;

/**
 * Created by hluu on 12/13/15.
 *
 * Problem statement:
 *   Give an a string with one or more words, reverse the string
 *   For example:  This is awesome ==> awesome is this.
 *
 * Approach:
 *   Reverse the characters in a string
 *      This is awesome => emosewa si siht
 *   Then reverse the characters in each of the words.
 *
 *   Be careful of a string w/o spaces => "abc"
 *
 *   Try to do the reversing in place using the character array
 */
public class ReverseString {

    public static void main(String[] args) {

        System.out.println(ReverseString.class.getName());

        String input = "This is awesome";

        //sString input = "xy";

        System.out.println("input: " + input);
        System.out.println("output: " + reverseString(input));

    }

    public static String reverseString(String input) {
        char[] inputCharArr = input.toCharArray();

        reverseChars(inputCharArr, 0, inputCharArr.length-1);

        int start = 0, end = 0;

        boolean spaceFound = false;
        while(end < inputCharArr.length) {
            if (inputCharArr[end] == ' ') {
                reverseChars(inputCharArr, start, end-1);
                end++; // advance end to pass space
                start = end;
                spaceFound = true;
            } else {
                end++;
            }
        }

        if (spaceFound) {
            reverseChars(inputCharArr, start, end - 1);
        }

        return new String(inputCharArr);
    }
    /**
     * The given # of character could be even or odd.
     *
     *   0  1  2  3  4
     *  [a, p, p, l, e]
     *  [f, o, u, r]
     *
     *  # of swaps is length / 2 -> this works for both odd and even # of chars
     *
     *  Use two indexes - one for end and the other for beginning.
     *  Perform the swaps (length/2)
     *
     * @param input
     * @param start - start character inclusive
     * @param end - end character inclusive
     */
    public static void reverseChars(char[] input, int start, int end) {

        int noSwap = ((end - start) + 1)/2;

        System.out.println(String.format("start: %d, end: %d, noSwap: %d", start, end, noSwap));

        for (int i = 0; i < noSwap; i++) {
            char tmp = input[start];
            input[start] = input[end];
            input[end] = tmp;
            start++;
            end--;
        }
    }
}
