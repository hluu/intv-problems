package my.cci.bit;

/**
 * Created by hluu on 1/29/16.
 *
 * Problem:
 *  Given two numbers, n and m.  Determine the number of bits that are different.
 *
 *  For example:
 *     10 ==>  1010
 *     7  ==>  0111
 *   ==============
 *             1101  ==> 3 bit differences
 *
 *
 * Approach:
 *  We can use XOR to find bit differences n ^ m, and then count the # of 1 in the binary number.
 *
 */
public class BitDifference {
    public static void main(String[] args) {
        System.out.println("BitDifference.main");

        tryOut(10,7);
        tryOut(8,1);

        tryOut(7,7);
    }

    private static void tryOut(int n, int m) {
        System.out.printf("%d & %d => %d\n", m, n, numBitDiff(n, m));
        System.out.printf("%d & %d => %d\n", m, n, numBitDiffUsingBitClearning(n, m));
    }

    public static int numBitDiff(int n, int m) {
        int count = 0;

        int xor = n ^ m;

        System.out.println("xor: " + xor);

        for (int i = xor; i > 0; i = i >> 1) {
            count = count + (i & 1);
        }

        return count;
    }

    public static int numBitDiffUsingBitClearning(int n, int m) {
        int count = 0;

        int xor = n ^ m;

        for (int i = xor; i > 0; i = i & (i-1)) {
            count++;
        }
        return count;
    }
}
