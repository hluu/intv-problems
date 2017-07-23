package my.epi.bits;

import org.common.BitUtility;
import org.testng.Assert;

/**
 * Created by hluu on 7/23/17.
 *
 * Problem:
 *  Right propagate the rightmost set bit in x, e.g., turns (01010000)2 to (01011111)2.
 *
 * Approach:
 *  How to leverage the technique of isolating the least significant 1 bit
 *
 *  x = 01010000
 *  y = 00010000
 *
 *  z = y - 1 => 00010000 - 1 => 00001111
 *
 *  result = x | z => 01011111
 */
public class RightPropagateSetBit {
    public static void main(String[] args) {
        System.out.println(RightPropagateSetBit.class.getName());

        test(0, 0);
        test(1, 1);
        test(20, 23);
        test(Integer.parseInt("01010000",2), Integer.parseInt("01011111", 2));
    }

    private static void test(int value, int expectedValue) {
        int rightPropagate = rightPropagateSetBit(value);
        System.out.printf("value: %d(%s), right propagate: %d(%s)\n",
                value,  Integer.toBinaryString(value),
                rightPropagate, Integer.toBinaryString(rightPropagate));

        Assert.assertEquals(rightPropagate, expectedValue);
    }

    private static int rightPropagateSetBit(int value) {
        int rightMostBit = BitUtility.isolateRightMostBit(value);

        if (rightMostBit == 0) {
            return rightMostBit;
        }
        int decreaseByOne = rightMostBit - 1;

        return value | decreaseByOne;

    }
}
