package my.epi.bits;

/**
 * Created by hluu on 7/23/17.
 *
 * Given two integers, m and n, determine the number of bits that need to be
 * changed to transform m to n.
 *
 * For example:
 *   m = 1010, n = 1101 => # of bits would be 3
 *
 * Approach:
 *   Basically what is needed is to detect what are the different bits
 *   and then we can count the number of bits.
 *
 *   1) To detect different bits, we can leverage XOR
 *   2) Then use x & (x - 1) to count # of bits
 */
public class BitsToModify {
    public static void main(String[] args) {
        System.out.println(BitsToModify.class.getName());

        test(0,0, 0);
        test(0,1, 1);
        test(10,13, 3);
        test(7,1, 2);
        test(7,2, 2);
        test(7,4, 2);
    }

    private static void test(int m, int n, int expectedBitsDiff) {
        int bitCount = bitsToModify(m, n);
        System.out.printf("bits to modify for %d(%s) to %d(%s) is %d(%s)\n",
                m, Integer.toBinaryString(m),
                n, Integer.toBinaryString(n),
                bitCount, Integer.toBinaryString(bitCount));
    }


    private static int bitsToModify(int m, int n) {
        int diff = m ^ n;

        // now count # of bits in diff
        int count  = 0;
        while (diff != 0) {
            diff = diff & (diff - 1);
            count++;
        }

        return count;
    }
}
