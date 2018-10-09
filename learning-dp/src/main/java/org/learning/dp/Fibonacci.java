package org.learning.dp;

import org.testng.Assert;

/**
 *
 */
public class Fibonacci {

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            test(i);
        }
    }

    private static void test(int n) {
        int fibTopdown = fibTopdown(n, new Integer[n+1]);
        int fibBottomup = fibBottomup(n);

        System.out.printf("fibTopdown(%d) = %d, fibBottomup(%d) = %d\n",
                n, fibTopdown, n, fibBottomup);


        Assert.assertEquals(fibTopdown, fibBottomup);
    }

    public static int fibTopdown(int n, Integer[] cache) {
        // base case
        if (n < 2) {
            return n;
        }

        // check the cache
        if (cache[n] != null) return cache[n];

        cache[n] = fibTopdown(n-1, cache) + fibTopdown(n-2, cache);
        return cache[n];
    }

    public static int fibBottomup(int n) {
        int[] cache = new int[n+1];

        cache[1] = 1;
        for (int i = 2; i <= n; i++) {
            cache[i] = cache[i-1] + cache[i-2];
        }

        return cache[n];
    }

}
