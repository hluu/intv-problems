package org.learning.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hluu on 1/26/18.
 *
 * Given a list of strings where each one contains letters and/or digits.
 * Write a function to extract out only the digits.
 *
 * For example:
 *   Input: ["AB1", "5AB", "A11B", "10AB25", "AB10CD89", "A", "9", "01A05"]
 *   Output: [1,5,11,10,25,10,89,9,5]
 *
 */
public class ExtractDigits {
    public static void main(String[] args) {
        List<String> input = Arrays.asList("AB1", "5AB", "A11B", "10AB25",
                "AB10CD89", "A", "9", "01A05");

        test(input);
    }

    private static void test(List<String> input) {
        System.out.println("====== input: " + input + " =======");

        List<Integer> output = extractIntegers(input);
        System.out.println("-------- output -------");
        System.out.println(output);
    }

    private static List<Integer> extractIntegers(List<String> input) {
        StringBuilder buf = new StringBuilder();
        List<Integer> output = new ArrayList<>();

        for (String str : input) {
            // make sure to reset the buffer each time
            buf.setLength(0);

            for (Character c : str.toCharArray()) {
                if (Character.isDigit(c)) {
                    buf.append(c);
                } else {
                    if (buf.length() > 0) {
                        output.add(Integer.parseInt(buf.toString()));
                        buf.setLength(0);
                    }
                }
            }

            if (buf.length() > 0) {
                output.add(Integer.parseInt(buf.toString()));
            }
        }
        return output;

    }
}
