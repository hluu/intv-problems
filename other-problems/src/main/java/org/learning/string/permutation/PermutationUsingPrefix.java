package org.learning.string.permutation;

import org.common.NumberUtility;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hluu on 1/2/17.
 */
public class PermutationUsingPrefix {
    public static void main(String[] args) {

        System.out.printf("PermutationUsingPrefix\n");

        int fact = 10;
        System.out.printf("fact of %d is %d\n", fact,  NumberUtility.factorial(10));

        //test("");
        test("b");
        test("ba");
        test("bar");
        test("abcdefghij");
    }

    private static void test(String str) {
        List<String> result = new ArrayList<>();
        permute("", str, result);
        //Collections.sort(result);
        //System.out.printf("%s: %s\n", str, result);

        Assert.assertEquals(result.size(), NumberUtility.factorial(str.length()));
    }

    protected static void permute(String prefix, String remaining, List<String> collector) {
        if (remaining == null) {
            return;
        }

        if (remaining.length() == 0) {
            collector.add(prefix);
            return;
        } else {
            for (int i = 0; i < remaining.length(); i++) {
                // building up the prefix
                String tmpPrefix = prefix + remaining.charAt(i);

                // trimming the remaining string by one character at a time
                String tmpRemaining = remaining.substring(0,i) + remaining.substring(i+1);
                permute(tmpPrefix, tmpRemaining, collector);
            }
        }
    }
}
