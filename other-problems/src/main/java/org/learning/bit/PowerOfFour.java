package org.learning.bit;

import org.testng.Assert;

/**
 * Created by hluu on 1/26/18.
 *
 * Write a function to detect whether a number is a power of 4
 *
 * Examples of power of 4 numbers:
 *   1     ==> 000000001
 *   4     ==> 000000100
 *   16    ==> 000010000
 *   64    ==> 001000000
 *   256   ==> 100000000
 *
 * Observation:
 *  A number is a power of 2 if its binary string has only 1 bit
 *  A number is a power of 2 if its binary string has only 1 bit AT index at odd value
 *    (1,3,5,7,9)
 *
 *  The solution consists of a two-step process
 *  1) Check to make sure there is only 1 bit in a binary string
 *  2) The 1-bit is at the odd index value
 *
 */
public class PowerOfFour {
    public static void main(String[] args) {
        int[] arr = {1,4,16,64,256};

        for (int v : arr) {
            System.out.println("v: " + v + " " + Integer.toBinaryString(v));
        }

        for (int v : arr) {
            test(v, true);
        }

        for (int i = 3; i < 25; i = i + 2) {
            test(i, false);
        }
    }

    public static void test(int value, boolean expected) {
        boolean actual = isPowerOfFour(value);

        System.out.println(String.format("Value: %d, expected: %b, actual: %b",
                value ,expected, actual));

        Assert.assertEquals(actual, expected);

        actual = isPowerOfFour2(value);
        Assert.assertEquals(actual, expected);
    }

    /**
     * General logic is to loop through each bit, detetct if it is only one bit
     * and at odd index value.
     *
     * Runtime time analysis:
     *  * Basically the runtime is based on the number of bits
     *  x^2 = y
     *  x = log(y)
     *
     * @param value
     * @return
     */
    public static boolean isPowerOfFour(int value) {

      if (value < 0) {
        return false;
      }

      int numBit = 0;
      int bitIndex = 0;
      int counter = 0;

      while (value > 0) {
          // make sure to increment the counter first
          counter++;

          // when a bit is 1, then increment the numBit and track the bitIndex
          if ((value & 1) == 1) {
              numBit++;
              bitIndex = counter;
              // doesn't need to continue when numBit is > 1
              if (numBit > 1) {
                  break;
              }
          }
          value = value >>> 1;
      }

      // logic to check about # of bits and bitIndex is at odd value
      if (numBit == 1 && (bitIndex % 2 == 1)) {
          return true;
      } else {
          return false;
      }
    }

    /**
     * General logic - first detect if a value has only one bit, then check for location
     * of the one bit.
     *
     * @param value
     * @return
     */
    public static boolean isPowerOfFour2(int value) {
        if (value < 0) {
            return false;
        }

        // 1000
        // 0111
        boolean foundIt = false;
        if ((value & (value - 1)) == 0) {
            int bitIndex = 0;
            while (value > 0) {
                bitIndex++;
                if (((value & 1) == 1) && ((bitIndex % 2) == 1)) {
                    foundIt = true;
                    break;
                }

                value = value >>> 1;
            }
        }
        return foundIt;
    }
}
