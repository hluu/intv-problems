package org.common;

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
}
