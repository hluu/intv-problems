package my.epi.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hluu on 12/12/15.
 *
 * Problem:
 *   Given an array of size n, generate and print all possible combinations or subset of
 *   size r elements in array. For example, if input array is {1, 2, 3, 4} and k is 2,
 *   then output should be {1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4} and {3, 4}
 *
 *
 */
public class AllPossibleCombinations {
    public static void main(String[] args) {
        System.out.println("This is a test");

        int[] inputs = {1, 2, 3, 4, 5};

        int noChar = 3;

        List<String> result = printCombinations(inputs, noChar);
        System.out.println(result);

        System.out.println("====== printCombinationsWithBooleanArray ======");

        List<String> result2 = printCombinationsWithBooleanArray(inputs, noChar);
        System.out.println(result2);
    }

    public static List<String> printCombinations(int[] inputs, int k) {
        List<String> collector = new ArrayList<String>();
        printCombinationInternal(inputs, new int[k], 0, 0, collector);
        return collector;
    }
    /**
     * The approach is to recursion with a buffer to store the k numbers.
     *
     * At each level of recursion, the iteration starts from the start index to
     * the end of the input array.  When the soFar index reaches the length of the
     * buffer, which means the buffer is filled up, then we print it out.
     *
     * @param inputs - data
     * @param buf - to store the # of characters
     * @param start - start point
     * @param bufferIndex - position in the buf
     */
    public static void printCombinationInternal(int[] inputs, int[] buf, int start,
                                                int bufferIndex, List<String> collector)
                                                {
        if (bufferIndex == buf.length) {
            //System.out.println(Arrays.toString(buf));
            collector.add(Arrays.toString(buf));
            return;
        }


        for (int i = start; i < inputs.length; i++) {
            buf[bufferIndex] = inputs[i];
            printCombinationInternal(inputs, buf, i + 1, bufferIndex + 1,
                    collector);
        }
    }

    public static List<String> printCombinationsWithBooleanArray(int[] inputs, int k) {
        List<String> collector = new ArrayList<String>();
        printCombinationsWithBooleanArray(inputs, new boolean[inputs.length],
                0,0,k, collector);

        return collector;

    }
    public static void printCombinationsWithBooleanArray(int[] inputs, boolean[] flagArray,
                                                         int start, int currLen, int k,
                                                         List<String> collector) {

        if (currLen == k) {
            StringBuilder buf = new StringBuilder();
            buf.append("[");
            boolean hasContent = false;
            for (int i = 0; i < inputs.length; i++) {
                if (flagArray[i]) {
                    //System.out.print(inputs[i] + " ");
                    if (hasContent) {
                        buf.append(", ");
                    }
                    hasContent = true;
                    buf.append(inputs[i]);
                }
            }
            buf.append("]");
            collector.add(buf.toString());
            //System.out.println();
            return;
        }

        if (start == inputs.length) {
            return;
        }

        // select the number and increment the currLen
        flagArray[start] = true;
        printCombinationsWithBooleanArray(inputs, flagArray, start+1, currLen+1, k, collector);

        // don't select it so don't increment the currLen
        flagArray[start] = false;
        printCombinationsWithBooleanArray(inputs, flagArray, start+1, currLen, k, collector);

    }
}
