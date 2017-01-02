package org.learning.string.permutation;

import org.common.NumberUtility;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by hluu on 1/2/17.
 *
 * Permute a string.
 * Example: bar  ==> bar, bra, abr, rba, rab, arb
 *
 * This solution uses the BASE CASE approach (start with one character):
 * * permutation of string "b" => {"b"}
 * * permutation of string "ba" => {"ba", "ab"}
 * * permutation of string "bar" => {"bar", "bra", "rba", "abr", "arb", "rab"}\
 *
 * Basically the permutation of "ba" is built from permutation of "a" by insert
 * "b" to every possible positions in "a", namely before "ba", and after "ab".
 *
 * Same thing when "bar", which is adding "r" to every possible positions of each of the
 * permutation of "ba"
 *
 * This leads to a recursion algorithm:
 *  * Base case is when str has length of 1
 *  * Otherwise pluck out the prefix and permute on remaining letters
 *
 */
public class PermuteByInsertIntoEachPostion {
    public static void main(String[] args) {
        test("");
        test("b");
        test("ba");
        test("bar");
    }

    private static void test(String str) {
        List<String> result = permute(str);
        Collections.sort(result);
        System.out.printf("%s: %s\n", str, result);

        Assert.assertEquals(result.size(), NumberUtility.factorial(str.length()));
    }

    protected static List<String> permute(String str) {
        // check for null or empty list
        if (str == null || str.length() < 1) {
            return Collections.emptyList();
        }

        if (str.length() == 1) {
            return Arrays.asList(str);
        } else {
            String prefix = str.substring(0,1);
            // string is one character shorter
            List<String> prevList = permute(str.substring(1));

            List<String> result = new ArrayList<>();

            // for each string in the list
            for (String s : prevList) {
                // beginning
                result.add(prefix + s);
                // end
                result.add(s+ prefix);

                // for the middle ones
                for (int i = 1; i < s.length(); i++) {
                    result.add(s.substring(0,i) + prefix + s.substring(i));
                }
            }
            return result;
        }
    }

}
