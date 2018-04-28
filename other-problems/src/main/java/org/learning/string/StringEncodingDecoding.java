package org.learning.string;

import org.common.ArrayUtils;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 *
 * Give an array of strings (Unicode), write two functions:
 * 1) String encoding(String[] inputs)
 * 2) String[] decoding(String str)
 *
 * Approach:
 * * The key thing is to figure a good delimiter to use between word
 * * One option is to use string length, which by itself is not sufficient because
 *   a string can have a leading number, i.e {"one", "1and2"}
 * * After the string length, we must add a control character like comma
 *   Does this work for following  {"one", "1and2", "5,five"}
 *
 * * For input: {"one", "1and2", "5,five"}, the output is
 *    "3,one5,1and24,4,five"
 *
 *  * Algorithm:
 *   encoding
 *    0) iterate through each string
 *    1) write out length,<actual string>
 *   decoding
 *    0) have two indexes: idx1 and indx2
 *    1) read until comma - move indx2 to comman
 *    2) substring from original string
 *    3) convert substring to len
 *    4) update idx1 = idx2+1
 *    5) update idx2 = idx1 + len
 *
 * String general encoding/compression techniques:
 * 1) Dictionary encoding
 *    * Replace duplicate string with an ordinal number
 * 2) Run len encoding
 *    * to reduce the length of duplicate consecutive characters
 *
 */
public class StringEncodingDecoding {
    public static void main(String[] args) {

        test(new String[] {"one", "1and2", "5,five"});

        test(new String[] {"one", "1,2", ""});

        test(new String[] {"one", "1,2", " ,1,"});
    }

    private static void test(String[] input) {
        System.out.println("===== test =====");
        System.out.println("input: " + Arrays.toString(input));

        String encodedStr = encode(input);

        System.out.println("encodedStr: " + encodedStr);
        List<String> decodedStr = decode(encodedStr);
        System.out.println("decoded strings: " + decodedStr);

        Assert.assertEquals(input, decodedStr.toArray());
    }


    private static List<String> decode(String str) {
        if (str == null || str.length() == 0) {
            Collections.emptyList();
        }

        List<String> result = new ArrayList<>();

        int idx1 = 0, idx2 = 0;

        int len = str.length();

        /**
         * The key thing here is to move the indx1 and idx2 in tandem
         */
        while (idx2 < len) {
            // udpate idx1 to be where idx2 was
            idx1 = idx2;
            // looking for comma
            while (str.charAt(idx2) != ',') {
                idx2++;
            }

            // extract only the length
            String subStr = str.substring(idx1, idx2);
            int subLen = Integer.parseInt(subStr);

            // idx2 was at the comma, move one character pass that
            idx1 = idx2 +1;
            // update idx2 to be the end of that substring
            idx2 = idx1 + subLen;

            // extract that substring
            result.add(str.substring(idx1, idx2));
        }

        return result;
    }

    /**
     * The encoding include string lenght, follows by a comma
     *
     * @param input
     * @return
     */
    private static String encode(String[] input) {
        StringBuilder buf = new StringBuilder();

        for (String str : input) {
            buf.append(str.length());
            buf.append(",");
            buf.append(str);
        }

        return buf.toString();
    }
}
