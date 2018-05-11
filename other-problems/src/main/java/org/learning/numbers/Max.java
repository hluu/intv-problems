package org.learning.numbers;

import org.learning.bit.BitUtility;

/**
 *
 *
 *  Find the maximum of two numbers, should not use if-else or any other conditional operator
 */
public class Max {

    public static void main(String[] args ) {
        System.out.println("Max.main");

        System.out.printf("max(3,2): %d\n", max(2,3));
    }

    private static int max(int a, int b) {
        int sign = BitUtility.sign(a-b);
        System.out.printf("sign: %d\n", sign);
        int inverse = BitUtility.flip(sign);
        System.out.printf("inverse: %d\n", inverse);

        return a * sign + b * inverse;
    }
}
