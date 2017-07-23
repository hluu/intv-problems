package my.epi.bits;

import org.testng.Assert;

/**
 * Created by hluu on 7/22/17.
 *
 * The parity of a binary word is 1 of the # of 1s is odd, otherwise it is 0.
 *
 * Parity is use to detect single bit errors in data storage and communication
 *
 * For example:
 *  1011 ==> parity is 1
 *  1001 ==> parity is 0
 *
 * Approach:
 *  * Brute force is to iterate through each bit and perform bit count
 *  * O(n) => n is the number of bits
 *
 *  * For the given example 10001000, which has only two bits, is there a way
 *    to count only the two 1 bits?
 *
 *  * The key is using x & ~(x-1) to isolate the smallest 1 bit in x
 *
 *
 */
public class ParityDetection {
    public static void main(String[] args) {
        System.out.println(ParityDetection.class.getName());

        test(6, 0);
        test(7, 1);
        test(14, 1);
        test(15, 0);

        test(Integer.parseInt("10001010", 2), 1);
    }

    private static void test(int value, int expectedParity) {
        System.out.println("======= parity test of " + value + " expected: " + expectedParity);
        Assert.assertEquals(bruteForceParity(value), expectedParity);

        Assert.assertEquals(optimizedParity(value), expectedParity);
    }

    public static int bruteForceParity(long value) {
        short result = 0;  // doesn't have to be int

        while (value != 0) {
            // 1 ^ 0 => 1 (same value)
            // 0 ^ 1 => 1 (first time)
            // 1 ^ 1 => 0 (even times)
            // 0 ^ 0 => 0 (same value)
            result ^= (value & 1);

            value = value >>> 1;
        }
        return result;
    }

    /**
     * This approach is more optimized because it only counts the bits that are 1s.
     * This is done by converting the 1 bits to 0.  So the algorithm keeps going until
     * there are not more 1 bits, which means the value of x become 0.
     *
     * The way to convert the lowest 1 bit is by doing x & ~(x - 1)
     *
     * For example:
     *   x = 10  => 01010
     *   x-1 = 9 => 01001
     *   ~(x-1)  => 10110
     *
     *      x &     01010
     *   ~(x-1) =>  10110
     *  ==================
     *  y =         00010 (which is the lowest bit in x)
     *
     *  Algorithm:
     *    1) Isolate the lowest bit from x and set it to y
     *    2) Remove lowest bit from x  by perform  x= x XOR y
     *    3) Repeat step 1 and 2 until x become 0
     *
     *
     *
     * @param value
     * @return
     */
    public static int optimizedParity(long value) {
        short result = 0;

        while (value != 0) {
            // toggling result each time going through while loop
            result ^= 1;

            // drop the
            long y = value & ~(value - 1);
            value = value ^ y;

        }
        return result;
    }
}
