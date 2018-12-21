package org.learning.numbers;

import java.util.Comparator;
import java.util.PriorityQueue;


/**
 *
 * The median of a dataset of integers is the midpoint value of the dataset
 * for which an equal number of integers are less than and greater than the value
 *
 * Given an input stream of  integers, you must perform the following task for each integer:
 * 1) Add the  integer to a running list of integers.
 * 2) Find the median of the updated list (i.e., for the first element through the  element).
 * 3) Print the list's updated median on a new line. The printed value must be a double-precision
 *    number scaled to decimal place (i.e.,  format).
 *
 * Sample input:
 *   6, 12, 4, 5, 3, 8, 7
 *
 * Sample output:
 *   12.0, 8.0, 5.0, 4.5, 5.0, 6.0
 *
 *
 * Analysis:
 *   * Each time a number is added to the list, we need to maintain the numbers in sorted order
 *     * Effectively need to find appropriate place to insert it into a list
 *     * Some tree structure is useful for this to get log(n) runtime
 *   * If we use a tree, then the challenge is how to figure out the middle element
 *   * Notice we don't really need to know the sorted order of elements that are to the left and right
 *     of the median value
 *   * If some how we can have access to just those elements around the median values
 *
 * Approach:
 *   * Use two heaps - one is max heap and other is min heap
 *     * Max heap is for the right side
 *     * Min heap is for the left side
 *   * Maintain the number of element difference between two heaps is not more than 1
 *   * The invariant we must maintain is the top of element value of min heap is
 *        ALWAYS greater than the top of the max heap.
 *   * Steps
 *     1) Add an element to max heap
 *     2) If size difference is greater than 1 between two heaps, then move top top of the larger
 *        size heap to smaller size heap
 *     3)
 *     4) Calculate the median:
 *        1) If max heap size is odd, return the top
 *        2) If max heap is even, extract top of both heap and return the average
 *
 *
 */
public class RunningMedian {

  public static void main(String[] args) {
    System.out.printf("%s\n", RunningMedian.class.getName());

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2 - o1;
      }
    });

    int array[] = {12, 4, 5, 3, 8, 7};

    runningMedian(minHeap, maxHeap, array);
  }

  private static void runningMedian(PriorityQueue<Integer> minHeap,
      PriorityQueue<Integer> maxHeap, int[] array) {

    int size = 0;
    for (int elm : array) {
      maxHeap.offer(elm);
      size++;

      if ((maxHeap.size() - minHeap.size()) > 1) {
        // balance the # of elements in each heap
        minHeap.offer(maxHeap.poll());
      }

      // maintains the invariant - top of max heap must be smaller than top of min heap
      if ((size > 1) && maxHeap.peek() > minHeap.peek()) {
        // swapItem
        int tmp = maxHeap.poll();
        maxHeap.offer(minHeap.poll());
        minHeap.offer(tmp);
      }

      boolean isEven = (size % 2 == 0);
      double median = maxHeap.peek() / 1.0;
      if (isEven) {
        median = (median + minHeap.peek())/ 2.0;
      }

      System.out.printf("elm: %d, median: %f\n", elm, median);
    }
  }
}
