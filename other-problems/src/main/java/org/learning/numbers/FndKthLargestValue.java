package org.learning.numbers;

import org.common.ArrayUtils;
import org.testng.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array of unsorted integers, find the Kth largest value in the array
 *
 * For example:
 *
 *  {3,2,1,5,4}
 *     k=2 ==> expected output is 4
 *     k=3 ==> expected output is 3
 *     k=4 ==> expected output is 2
 *
 * Approach:
 * 1) Brute force is to sort {1,2,3,4,5}, the Kth largest value is at index len(array) - K
 *    This approach requires O(nlogn), and it does more than that it needs to.
 *    We don't need total order
 *
 * 2) When something that doesn't require total ordering, but only partial ordering, think of
 *    heap.  For Kth largest value, what if we maintain a min heap of size K?
 *    This approach requires O(nlogk). We still need to iterate through the list.
 *
 * 3) Can we use quickSelect, based on the quicksort approach,
 *    a) Select a pivot
 *    b) partition the elements into two subsets -
 *       one is smaller than the pivot value and the other with values greater than pivot value
 *       move the pivot into a position
 *       if that position is equal to k, return the pivot value
 *       if that position is greater than k, then recursively work on the first subset, otherwise
 *         work on the right subset
 *    c) what is the runtime here? O(logn)
 *

 */
public class FndKthLargestValue {
    public static void main(String[] args) {
        int[] arr = ArrayUtils.randomArrayWithUnique(9, 50);

        testPartition();
    }

    private static int getKthLargestValue(int[] sortedArr, int k) {
        if (k > sortedArr.length || k < 0) {
            throw new IllegalArgumentException("k larger than array length");
        }

        return sortedArr[sortedArr.length - k];
    }

    private static void test(int[] arr, int k, int expectedValue) {

        System.out.println("========== test ==========");
        System.out.println("k: " + k + " arr: "  + Arrays.toString(arr));

        System.out.println("expectedValue: " + expectedValue);

        int resultFromMinHeap = usingMinHeap(arr, k);
        System.out.println("resultFromMinHeap: " + resultFromMinHeap);
        Assert.assertEquals(resultFromMinHeap, expectedValue);

        int resultFromMaxHeap = usingMaxHeap(arr, k);
        System.out.println("resultFromMaxHeap: " + resultFromMaxHeap);

        Assert.assertEquals(resultFromMaxHeap, expectedValue);

        int resultFromQuickSelect = usingQuickSelect(arr, k);
        System.out.println("resultFromQuickSelect: " + resultFromQuickSelect);

        System.out.println();
    }


    /**
     * Brute force approach using sorting the array first
     *
     * @param arr
     * @param k
     * @return
     */
    private static int bruteForce(int[] arr, int k) {
        int[] cloneArr = arr.clone();
        Arrays.sort(cloneArr);

        return cloneArr[cloneArr.length - k];
    }
    /**
     * This approach uses the min heap to maintain k number of elements.
     *
     * As it iterates through each elements in the array, if the element value is
     * greater than the top of the heap, then it first remove the top of the heap
     * and add the element value.
     *
     * At the end, the k largest element is at the top of heap.
     *
     * Runtime is O(nlogk)
     *
     * @param arr
     * @param k
     * @return
     */
    private static int usingMinHeap(int[] arr, int k) {
        if (arr == null || k < 1 || k > arr.length) {
            throw new IllegalArgumentException();
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int elmValue : arr) {
            if (minHeap.size() < k) {
                minHeap.add(elmValue);
            } else {
                int topOfHead = minHeap.peek();
                if (elmValue > topOfHead) {
                    minHeap.poll();
                    minHeap.add(elmValue);
                }
            }
        }

        return minHeap.peek();
    }

    /**
     * The idea of this approach is very similar to the minHeap.  An easy way of visualizing this
     * is by looking at the array below.
     *
     *   1 2 3 4 5 6 7    let say k is 3 => the expected value is 5
     *
     *   We can break the above arrays into two parts - one includes 5 at the end and the other
     *   include 5 at the beginning
     *
     *   1 2 3 4 5  ==>  This is the idea of using max heap ( size is len(arr) - k + 1)
     *   5 6 7      ==>  This is the idea of using min heap (size is k)
     *
     *  Runtime is O(nlog(len(arr)+1 - k))
     *
     * @param arr
     * @param k
     * @return
     */
    private static int usingMaxHeap(int[] arr, int k) {
        if (arr == null || k < 1 || k > arr.length) {
            throw new IllegalArgumentException();
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MaxHeapComparator());

        int heapSize = arr.length + 1 - k;
        for (int elmValue : arr) {
            if (maxHeap.size() < heapSize){
                maxHeap.add(elmValue);
            } else {
                // add only if value is smaller than the top of the head
                int topOfHead = maxHeap.peek();
                if (elmValue < topOfHead) {
                    // remove the top
                    maxHeap.poll();
                    maxHeap.add(elmValue);
                }
            }
        }

        return maxHeap.peek();

    }

    private static class MaxHeapComparator implements Comparator<Integer> {
        public int compare(Integer i1, Integer i2) {
            return i2 - i1;
        }
    }

    /**
     * This approach uses the quick sort algorithm approach with the following modifications:
     *   1) Stop the sorting when the pivot is moved into the k position
     *   2) Recognizing the pivot final position and decide which side of the sub-array to
     *      perform quick sort on
     *
     * The general approach has 2 steps:
     *   1) Partition
     *   2) Decide which side of the sub-array to continue the sorting
     *
     * @param arr
     * @param k
     * @return
     */
    private static int usingQuickSelect(int[] arr, int k) {

        return findKth(arr, 0, arr.length-1, arr.length - k);
    }

    private static int findKth(int[] arr, int start, int end, int k) {
        int pivotIndex = ((start + end)/2);
        pivotIndex = partition(arr, pivotIndex, start, end);

        if (pivotIndex == k) {
            return arr[pivotIndex];
        } else {
            if (pivotIndex < k) {
                // move right
                return findKth(arr, pivotIndex+1, end, k);
            } else {
                return findKth(arr, start, pivotIndex-1, k);
            }
        }

    }

    /**
     * Partition the elements in the arr that are between start and end,
     * such that all the elements that are smaller than the pivotValue are on
     * the left hand side and all the elements that are bigger the pivotValue are
     * on the right hand side.
     *
     * There are multiple ways of doing this:
     *   1) Use two pointers (one for left, the other for right)
     *   2) An slighter straight forward is to user a barrier
     *      * The barrier is point at an element that is greater than the pivot value
     *      * As scanning from left to right, when finding a value smaller than pivot value,
     *        swapItem it with the element at the barrier index
     *      * example [10, 1, 4, 5, 2, 7]
     *        start = 0;
     *        end = 5
     *        pivotIndex = 2;
     *        barrierIndx = 0;
     *
     *           0   1  2  3  4  5
     *           ------------------
     *           [10, 1, 4, 5, 2, 7]
     *           [10, 1, 7, 5, 2, 4]
     *          i = 0, nothing happens
     *          i = 1,
     *           [1, 10, 7, 5, 2, 4]
     *          barrierIndex = 1
     *          i = 2, nothing happens
     *          i = 3, nothing happens
     *          i = 4, nothing happens
     *          [1, 2, 7, 5, 10, 4]
     *          barrierIndex = 2
     *
     *          done
     *
     *          now swapItem pivot value with element at barrierIndex
     *          [1, 2, 4, 5, 10, 7]
     *
     *
     * @param arr
     * @param pivotIndex
     * @param start
     * @param end
     * @return
     */
    private static int partition(int[] arr, int pivotIndex, int start, int end) {

        int barrierIndx = start;
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, end);

        for (int i = start; i < end; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, i, barrierIndx);
                barrierIndx++;
            }
        }

        swap(arr, barrierIndx, end);
        return barrierIndx;

    }

    private static void swap(int[] arr, int from, int to) {
        int tmp = arr[from];

        arr[from] = arr[to];
        arr[to] = tmp;
    }

    private static void testPartition() {
        int[] arr = {10, 1, 4, 5, 2, 7};
        System.out.println("before: " + Arrays.toString(arr));
        int pivotIndex = partition(arr, 5, 0, arr.length-1);

        System.out.println("after: " + Arrays.toString(arr));
        System.out.println("pivotIndex: " + pivotIndex);
    }

}
