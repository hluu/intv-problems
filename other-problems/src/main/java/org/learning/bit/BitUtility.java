package org.learning.bit;

/**
 * Created by hluu on 2/14/16.
 */
public class BitUtility {
    public static void main(String[] args) {
        System.out.println("BitUtility.main");

        int value = 35;
        System.out.printf("value %d: sign: %d flipIt: %d\n", value,
                sign(value),  flip(sign(value)));
        value = -21;
        System.out.printf("value %d: sign: %d flipIt: %d\n", value,
                sign(value),  flip(sign(value)));

    }

    /**
     * Give a bit, flip it meaning 1 become 0 and 0 become 1
     * By performing exclusive or operation
     *  1001 ^ 1111 ==> 0110
     *
     * @param bit
     * @return
     */
    public static int flip(int bit) {
        return 1^bit;
    }


    /**
     * Return 1 if positive and 0 if negative
     *
     * Find the sign bit and flip it.
     * Find the sign bit by shift the right most bit to position 0
     *
     * @param n
     * @return
     */
    public static int sign(int n) {

        return flip((n >> 31) & 0x1);
    }
}
