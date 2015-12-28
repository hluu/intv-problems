package my.cci.bit;

/**
 * Created by hluu on 12/23/15.
 *
 * Problem statement:
 *   Give two 32-bit numbers (M, N) and two bit position i and j.
 *   Write a method to set all bits between i and j in N equal to M
 *   such that M becomes a substring of N locations at i and start at j
 *
 *   For example:
 *     N = 10000000000, M = 10101, i = 2, j = 6
 *     Output: N = 10001010100
 *
 *
 * Approach:
 *
 *   Two step process:
 *     * Wipe out the bits in N at position i to j (make them zero)
 *     * Shift M by i bits
 *     * OR N and M together
 *
 *
 *   Create a mask with bits i to j as 0 and AND that with N:
 *     * Create (j-i) + 1 mask with 1 bits
 *     * Shift this mask by i times
 */
public class SetSubsetBitToPattern {

    public static void main(String[] args) {

        System.out.println("SetSubsetBitToPattern.main");

        int n = (int)Math.pow(2, 10);

        int m = 21;

        System.out.println("n " + Integer.toBinaryString(n));
        System.out.println("m " + Integer.toBinaryString(m));



        System.out.println("result " + Integer.toBinaryString(doIt(n,m,2,6)));
    }

    public static int doIt(int n, int m, int i, int j) {

        // to create 111 bits, which is 7 = (8-1); 8 = 2^3
        int numBits = (j-i) + 1;
        int mask = (int)Math.pow(2, numBits+1) - 1;

        // move the 1 bits into position, we should have something like 000011111000
        mask = mask << i;

        // now invert the bits to make 11111000000111
        mask = ((int)Math.pow(2, 32) - 1) ^ mask;

        // to turn those bits from i to j int zeros
        n = n & mask;

        m = m << i;

        return (n | m);
    }
}
