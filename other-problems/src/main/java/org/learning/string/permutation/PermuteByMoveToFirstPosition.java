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
 * This approach is based on the following observation:
 *
 * p(a)   => {a}
 * p(ab)  => {a + p(b), b + p(a)}
 * p(abc) => {a + p(bc), b + p(ac) + c p(ab)}
 */
public class PermuteByMoveToFirstPosition {

    public static void main(String[] args) {

        System.out.printf("PermuteByMoveToFirstPosition\n");
        test("");
        test("b");
        test("ba");
        test("bar");
        test("abcd");
    }

    private static void test(String str) {
        List<String> result = permute(str);
        Collections.sort(result);
        System.out.printf("%s: %s\n", str, result);

        Assert.assertEquals(result.size(), NumberUtility.factorial(str.length()));
    }

    protected static List<String> permute(String str) {
        if (str == null || str.length() < 1) {
            return Collections.emptyList();
        }

        if (str.length() == 1) {
            return Arrays.asList(str);
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char prefix = str.charAt(i);

            String beforePrefix = str.substring(0,i);
            String afterPrefix = str.substring(i+1);
            List<String> prevList = permute(beforePrefix + afterPrefix);
            for (String s : prevList) {
                result.add(prefix + s);
            }

        }
        return result;
    }
}
