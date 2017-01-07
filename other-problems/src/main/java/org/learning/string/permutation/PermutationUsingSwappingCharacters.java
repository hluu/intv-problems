package org.learning.string.permutation;

import org.common.NumberUtility;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hluu on 1/2/17.
 *
 * This approach uses a character array and swapping every characters into
 * every possible positions in the array.  Start from the left and going to
 * the right.  Each recursive call, increase the index by one or reduces
 * the string by one.
 *                             cat
 *                cat          act         tac
 *           cat      cta  act     atc  tac     tca
 *
 * In a way, this uses the backtrack approach
 */
public class PermutationUsingSwappingCharacters {
    public static void main(String[] args) {
        test("");
        test("b");
        test("ba");
        test("bar");
        test("abcd");
    }

    private static void test(String str) {
        swapCount = 0;
        List<String> result = new ArrayList<>();
        permute(str.toCharArray(), 0, result);
        Collections.sort(result);
        System.out.printf("%s: %s\n", str, result);

        System.out.printf("swapCount: %d\n", swapCount);
        Assert.assertEquals(result.size(), NumberUtility.factorial(str.length()));
    }

    protected static void permute(char[] arr, int pos, List<String> collector) {
        // always check for null or empty string
        if (arr == null || arr.length < 1) {
            return;
        }

        if (pos == arr.length-1) {
            collector.add(new String(arr));
            return;
        } else {
            for (int i = pos; i < arr.length; i++) {
                swap(arr, pos, i);
                permute(arr, pos + 1, collector);
                //restore
                swap(arr, pos, i);
            }
        }

    }

    private static void swap(char[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    static int swapCount = 0;
}
