package org.learning.numbers;

import org.common.ArrayUtils;
import org.testng.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MergedNSortedArrays {

    public static void main(String[] args) {

        test(new int[][] {
                {1}, {1, 3, 5}, {1, 10, 20, 30, 40}
        }, new int[]{1,1,1,3,5,10,20,30,40 } );

        test(new int[][] {
                {1}, {1, 3, 5},{}, {1, 10, 20, 30, 40}
        }, new int[]{1,1,1,3,5,10,20,30,40 } );

        test(new int[][] {
                {1}, {1, 3, 5},{},{2}, {1, 10, 20, 30, 40}
        }, new int[]{1,1,1,2,3,5,10,20,30,40 } );

    }

    private static void test(int[][] arr, int[] expected) {
        System.out.println("\n*********** testing *******");
        ArrayUtils.printMatrix2(arr);

        int[] actual = mergeNSortedArray(arr);

        System.out.printf("expected: %s, actual: %s\n",
                Arrays.toString(expected), Arrays.toString(actual));

        Assert.assertEquals(actual, expected);

    }
    /**
     * Give an array of arrays, merge them and return a totally sorted list.
     *
     * We would extend the merging of two arrays to implement this, but it is
     * a bit complicated because it will require n comparison and n indexes
     * to keep track of.
     *
     * A simpler approach is to use a min-heap to perform the sorted list.
     * The element in the list contains both the value and the array it came from.
     *
     * Steps
     *  - create an output array
     *  - populate the min-heap with the first element of each array
     *    - each elm contains (value, arrIdx, idx)
     *  - extract the top elm from min-heap, put the value in the output array
     *    - increment the output array index
     *    - figure out which array it came from,
     *      - if haven't reached the end of that array, then add elm at idx+1 to heap
     *
     * Runtime O(n*k log(k)), space O(k)
     *
     * @param inputs
     * @return
     */
    private static int[] mergeNSortedArray(int[][] inputs) {
        if (inputs == null || inputs.length == 0) {
            return new int[0];
        }

        PriorityQueue<MinHeapElm> minHeap =
                new PriorityQueue<MinHeapElm>(new CustomComparitor());

        for (int arrIdx = 0; arrIdx < inputs.length; arrIdx++) {
            int[] arr = inputs[arrIdx];
            // add only if it has more than 0 elements
            if (arr.length > 0) {
                // adding the first element in each of the arrays
                minHeap.offer(new MinHeapElm(arr[0], arrIdx, 0));
            }
        }

        int totalSize = 0;
        for (int[] arr : inputs) {
            totalSize += arr.length;
        }

        // output array
        int[] output = new int[totalSize];
        int outputIdx = 0;

        // while there are elements in the min heap
        while (!minHeap.isEmpty()) {
            MinHeapElm topElm = minHeap.poll();

            output[outputIdx++] = topElm.value;

            int arrIdx = topElm.arrayIdx;  // which array it came from
            // see if there are elements to add
            if (topElm.idx < inputs[arrIdx].length-1) {
                int nextIdx = topElm.idx+1;
                int nextValue = inputs[arrIdx][nextIdx];
                minHeap.offer(new MinHeapElm(nextValue, arrIdx, nextIdx ));
            }
        }

        return output;
    }

    private static class CustomComparitor implements Comparator<MinHeapElm> {
        public CustomComparitor() {
        }

        // ascending
        public int compare(MinHeapElm first, MinHeapElm second) {
            return first.value - second.value;
        }
    }

    private static class MinHeapElm {
        public int value;
        public int arrayIdx;
        public int idx;

        public MinHeapElm(int value, int arrayIdx, int idx) {
            this.value =value;
            this.arrayIdx = arrayIdx;
            this.idx = idx;
        }
    }
}
