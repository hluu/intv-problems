package org.learning.string;

/**
 *  Input = "AAABBZZDDD", Output = "A3B2Z2D3"
 */
public class StringRunLengthEncoding {
    public static void main(String[] args) {
        System.out.println(StringRunLengthEncoding.class.getName());

        System.out.println(lenEncode("AAABBZZDDD"));
        System.out.println(lenEncode("AAABBZZD"));
    }

    private static String lenEncode(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder buf = new StringBuilder();
        char currChar = input.charAt(0);
        int len = 1;

        for (int idx = 1; idx < input.length(); idx++) {
            // if same keep going

            // if not same
            // * add currChar and len to buf
            // * change currChar to new char
            // * reset len to 1

            if (currChar != input.charAt(idx)) {
                buf.append(currChar).append(len);
                currChar = input.charAt(idx);
                len = 1;
            } else {
                len++;
            }
        }

        // for the last character(s)
        buf.append(currChar).append(len);

        return buf.toString();
    }
}
