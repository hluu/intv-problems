package org.learning.java8;

import java.math.BigInteger;

/**
 * Created by hluu on 7/14/17.
 */
public class BigIntegerTestBit {
    public static void main(String[] args) {
        System.out.println(BigIntegerTestBit.class.getName());

        for (int i = 0; i <= 32; i++) {
            System.out.printf("Binary string of %2d is %s\n", i, convertIntToBinary(i));
        }
    }

    private static String convertIntToBinary(int value) {
        BigInteger bi = BigInteger.valueOf(value);

        String binaryStr = "";
        for (int i = 10; i >= 0; i--) {
            binaryStr = binaryStr + (bi.testBit(i) ? "1" : "0");
        }

        return binaryStr;
    }
}
