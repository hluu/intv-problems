package org.common;

import org.testng.Assert;

/**
 * Created by hluu on 7/23/17.
 */
public class BitUtility {
    public static void main(String[] args) {
        System.out.println(BitUtility.class.getName());

        test(1,1);
        test(2,2);
        test(3,1);
        test(6,2);
        test(7,1);
        test(14,2);
        test(28,4);

    }

    private static void test(int value, int expectedResult) {
        int rightMostBit = isolateRightMostBit(value);
        System.out.printf("last bit of %d(%s) is %d(%s)\n",
                value, Integer.toBinaryString(value),
                rightMostBit, Integer.toBinaryString(rightMostBit));

        Assert.assertEquals(rightMostBit, expectedResult);
    }

    public static int isolateRightMostBit(int value) {
        return (value  & ~(value - 1));
    }
}
