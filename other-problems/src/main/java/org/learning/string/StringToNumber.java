package org.learning.string;

/**
 * Created by hluu on 1/1/17.
 *
 * Structure:
 *  1) Ask clarifying questions to narrow down to specific problem to solve
 *  2) Work through an example to solidify the understanding of the problem
 *  3) Talk through your thought process while coding i.e ask to see if it is OK
 *     to use certain APIs.
 *  4) After finishing coding, trace through an example to find verify the code
 *     and find bugs
 *  5) Find a bug, talk about how to fix it before changing any code
 *
 * Problem:
 *  Convert a string to a number?
 *  * What are assumptions?
 *  * What are examples?
 *  * What are numbers?  1052, -1, 0.52, -0.52, 1E6 or pie
 *  * What are corner cases?
 *  * What are the testing?
 *  * Trace the code with an small example
 *
 *
 *  Coding:
 *  * Error checking
 *  * Boundary conditions i.e loop index, adding a value to an index
 *  * Array out of bound exception
 */
public class StringToNumber {
    public static void main(String[] args) {
        System.out.printf("%s\n", StringToNumber.class.getName());

        String str = "-1052A";
        for (int i = 0; i < str.length(); i++) {
            System.out.printf("(%d:%c)", i, str.charAt(i));
        }

        System.out.printf("\n");
        System.out.printf("result: %d\n", convertToNumber(str));

        //int result = String.CASE_INSENSITIVE_ORDER.compare("me", "");
        //System.out.println("result: " + result);
    }

    private static int convertToNumber(String str) {
        // iterating from 1, then 5, the n4
        // "154" => 100 + 50 + 4

        // corner case
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("str is not valid");
        }

        int value = 0;
        boolean negative = false;
        for (int i = 0; i  < str.length(); i++) {
            if (str.charAt(i) == '-') {
                negative = true;
                continue;
            }

            int digit = Character.getNumericValue (str.charAt(i));
            if (digit > 9 || digit < 0) {
                throw new IllegalArgumentException(
                        String.format("character '%c' is not a digit",
                        str.charAt(i)));
            }
            value = value * 10 + digit;
        }

        return (!negative ? value : -value);
    }
}
