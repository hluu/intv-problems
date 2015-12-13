package my.epi.combination;

import java.util.Arrays;

/**
 * Created by hluu on 12/12/15.
 *
 * Problem:
 *   Given an array of size n, generate and print all possible combinations of r
 *   elements in array. For example, if input array is {1, 2, 3, 4} and r is 2,
 *   then output should be {1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4} and {3, 4}
 *
 *
 */
public class AllPossibleCombinations {
    public static void main(String[] args) {
        System.out.println("This is a test");

        int[] inputs = {1, 2, 3, 4, 5};

        int noChar = 3;
        int[] buf = new int[noChar];
        printCombinations(inputs, buf, 0, 0);
    }

    /**
     *
     * @param inputs - data
     * @param buf - to store the # of characters
     * @param start - start point
     * @param soFar - position in the buf
     */
    public static void printCombinations(int[] inputs, int[] buf, int start, int soFar) {
        if (soFar == buf.length) {
            System.out.println(Arrays.toString(buf));
            return;
        }


        for (int i = start; i < inputs.length; i++) {
            buf[soFar] = inputs[i];
            printCombinations(inputs, buf, i+1, soFar+1);
        }
    }

    /**
     * soFar = 0
     * i = 0
     * buf[soFar] = 1
     *
     *    soFar=1
     *    i = 1
     *    buf[1] = 2
     *    {1,2}
     *
     *    i = 2
     *    buf[1] = 3
     *    {1,3}
     *
     *    i = 3
     *    buf[1] = 4
     *    {1,4}
     *
     * soFar = 0;
     * i = 1;
     * buf[0] = 2
     *
     *
     *
     */
}
