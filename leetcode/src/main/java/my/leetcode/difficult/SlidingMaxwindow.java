package my.leetcode.difficult;

import org.testng.Assert;

import java.util.*;


/**
 * Give an array of unsorted numbers and k size window. As the window
 * slides from left to right, determine the max element in each
 * window.
 *
 *  https://leetcode.com/problems/sliding-window-maximum/
 *
 */
class SlidingMaxwindow {

    public static void main(String[] args) {
        int input1[] = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        int output1[] = {3,3,5,5,6,7};

        test(input1, k, output1);

        int output12[] = {3,5,5,6,7};
        test(input1, 4, output12);

        int input2[] = {1,3,-1,-3,5,3,6,7};
        int output2[] = {3,3,5,5,6,7};
        test(input2, 3, output2);

        int output21[] = {5,5,6,7};
        test(input2, 5, output21);

    }

    private static void test(int[] input, int k, int[] expectedOutput) {
        System.out.printf("\n** input: %s, k: %d, expected: %s **\n",
                Arrays.toString(input), k, Arrays.toString(expectedOutput));

        int[] actualOutput = maxSWBruteForce(input, k);

        System.out.printf("actual: %s\n", Arrays.toString(actualOutput));

        int[] actualOutput2 = usePriorityQueue(input, k);

        System.out.printf("actual2: %s\n", Arrays.toString(actualOutput2));

        int[] actualOutput3 = useDQueue(input, k);

        System.out.printf("actual3: %s\n", Arrays.toString(actualOutput3));

        Assert.assertEquals(actualOutput, expectedOutput);
        Assert.assertEquals(actualOutput, actualOutput2);
        Assert.assertEquals(actualOutput2, actualOutput3);
    }


    /**
     * This approaches uses a double-ended queue to maintain only the
     * highest value element in a window.  It does this before adding
     * a new element, therefore the runtime will O(n).
     *
     * The key thing is the double-ended queue contains only the index
     * not the value.  The index is used to determine if it is out of
     * the window range or not. We can access the value by indexing into
     * the input array.
     *
     * Approach:
     * 1) Go through each element in the nums input
     * 2) Before add a new idx of next element to the double-ended queue
     *    *) Clean up elements that are out of the window boundary
     *    *) Remove elements that are smaller than the one that will be added
     *    *) Add the new element
     *    *) Add the element with largest value to the output array
     *
     * @param nums
     * @param k
     * @return
     */
    private static int[] useDQueue(int[] nums, int k) {
        Deque<Integer> dqueue = new LinkedList<>();

        int output[] = new int[nums.length - k + 1];
        int oIdx = 0;

        for (int idx = 0; idx < nums.length; idx++) {
            // clean up elements that are out of window boundary
            // they are at the front of the d-queue
            int windowLeftBoundary = idx - k + 1;
            while (!dqueue.isEmpty() && dqueue.peek() < windowLeftBoundary) {
                dqueue.poll();
            }

            // remove elements at the head that are more than the element
            // at idx (or about to be added)
            int elmValueToBeAdded = nums[idx];
            while (!dqueue.isEmpty()) {
                int valueInQueue = nums[dqueue.peekLast()];
                if (valueInQueue < elmValueToBeAdded) {
                    dqueue.pollLast();
                } else {
                    break;
                }
            }

            // add to the end of the queue
            dqueue.offer(idx);

            // now adding to output, only if idx is beyond the first window
            if (idx >= k-1) {
                // get the elm at head of the queue
                output[oIdx++] = nums[dqueue.peek()];
            }
        }

        return output;
    }

    /**
     * This is a slightly better version of the brute force approach
     * by using a priority queue to extract the max element among the
     * elements in a window.
     *
     * 1) Each time of sliding the window by one, need to remove the element
     *    that is outside the window
     * 2) Runtime - O(nlogk)
     *
     * Note: the inefficiency comes from sort the elements in the PQ when a
     *       new element is added.
     *
     * @param nums
     * @param k
     * @return
     */
    private static int[] usePriorityQueue(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });

        int numTimes = nums.length - k + 1;

        int[] result = new int[numTimes];

        // first window
        for (int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }

        int j = 0;
        result[j++] = pq.peek();

        // slide the window by 1 until the end
        for (int i = k; i < nums.length; i++) {
            // remove the first one in k window
            pq.remove(nums[i-k]);
            // now add the new one
            pq.offer(nums[i]);
            result[j++] = pq.peek();
        }

        return result;
    }

    /**
     * Brute force and simple approach
     * 1) For each sliding window, find the max window each window
     * 2) Runtime O(n*k)
     *
     * The inefficiency comes from re-iterating through all the overlapping
     * elements in the window.
     *
     * @param nums
     * @param k
     * @return
     */
    private static int[] maxSWBruteForce(int[] nums, int k) {
        int numTimes = nums.length - k + 1;

        int[] result = new int[numTimes];

        for (int i = 0; i < numTimes; i++) {
            result[i] = maxInArray(nums, i, i+k-1);
        }

        return result;
    }

    private static int maxInArray(int[] input, int from, int to) {
        int max = Integer.MIN_VALUE;

        for (int i = from; i <= to; i++) {
            max = Math.max(input[i], max);
        }

        return max;
    }
}
