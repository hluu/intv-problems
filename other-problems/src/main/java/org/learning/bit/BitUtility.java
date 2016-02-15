package org.learning.bit;

/**
 * Created by hluu on 2/14/16.
 */
public class BitUtility {
    public static void main(String[] args) {
        System.out.println("BitUtility.main");

        System.out.println("35: " + sign(35)+ " : " + flip(sign(35)));
        System.out.println("-21: " + sign(-21) + " : " + flip(sign(-21)));
    }

    /**
     * Give a bit, flip it meaning 1 become 0 and 0 become 1
     *
     * @param bit
     * @return
     */
    public static int flip(int bit) {
       return 1^bit;
    }
    /**
     * Return 1 if negative and 0 if negative
     * @param n
     * @return
     */
    public static int sign(int n) {
        return (n >> 31) & 0x1;
    }
}
