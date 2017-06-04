package org.common;

import java.util.Arrays;
import junit.framework.Assert;


/**
 * Created by hluu on 1/2/17.
 */
public class NumberUtility {
    public static void main(String[] args) {
        System.out.printf("factorial of %d is %d\n", 3, factorial(3));
    }

    public static long factorial(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            long result = n * factorial(n-1);
            return  result;
        }
    }

    public static void swap(int[] arr, int from, int to) {
        int tmp = arr[from];
        arr[from] = arr[to];
        arr[to] = tmp;
    }

    public static void assertSortedArray(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i-1] > arr[i]) {
                Assert.fail(String.format("Array %s not sorted\n", Arrays.toString(arr)));
            }
        }
    }
}
