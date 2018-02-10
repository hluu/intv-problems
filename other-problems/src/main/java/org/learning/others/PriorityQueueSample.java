package org.learning.others;

import org.common.ArrayUtils;

import java.util.*;

/**
 * Created by hluu on 11/30/16.
 *
 * Usages of priority queue:
 *  1) Merging 3 or more lists of sorted numbers
 *  2) Maintaining Top N
 *  3) Median of an incoming stream of numbers
 *
 *
 * Common APIs in PriorityQueue:
 *  1) offer - to add an element to queue
 *  2) isEmpty -
 *  3) poll - removing an element from a queue
 *
 *
 */
public class PriorityQueueSample {
    public static void main(String[] args) {
        System.out.printf("%s\n", PriorityQueueSample.class.getName());


        playWithPriorityQueue();
       // playWithSortedSet();


        testTopBottomK(5, ArrayUtils.randomArray(20, 500));
    }

    private static void testTopBottomK(int k, int[] elements) {
        System.out.println(Arrays.toString(elements));

        int[] topKResult =  topKValue(5, elements);
        int[] bottomKResult =  bottomKValue(5, elements);

        Arrays.sort(elements);
        System.out.println("Elements in sorted order: " + Arrays.toString(elements));

        System.out.println("topK: " + Arrays.toString(topKResult));
        System.out.println("bottomK: " + Arrays.toString(bottomKResult));
    }

    /**
     * Use a max heap and keep replacing the itself with smaller value.
     * We only maintain k values in the heap at any point in time.
     *
     * @param k
     * @param elms
     * @return
     */
    private static int[] bottomKValue(int k, int[] elms) {
        System.out.println("=============== bottomKValue =============");
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new DescComparator());

        for (int value : elms) {
            if (maxHeap.size() < k) {
                maxHeap.add(value);
            } else {
                Integer maxValue = maxHeap.peek();
                if (value < maxValue) {
                    maxHeap.poll();
                    maxHeap.add(value);
                }
            }
        }

        int[] result = new int[k];
        int index = 0;
        Stack<Integer> stack = new Stack<Integer>();
        while(!maxHeap.isEmpty()) {
            stack.push(maxHeap.poll());
        }

        while (!stack.isEmpty()) {
            result[index++] = stack.pop();
        }

        return result;
    }

    private static class DescComparator implements Comparator<Integer> {
        public int compare(Integer i1, Integer i2) {
            return i2 - i1;
        }

    }

    /**
     * Top K uses min heap and keep replacing the top of the heap with a value
     * greater than itself.
     *
     * We only maintain k values in the heap at any point in time.
     *
     * @param k
     * @param elms
     * @return
     */
    private static int[] topKValue(int k, int[] elms) {
        System.out.println("=============== topKValue =============");

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for (int value : elms) {
            // if minHeap size is less than K, then just add
            if (minHeap.size() < k) {
                minHeap.add(value);
            } else  {
                Integer minValue = minHeap.peek();
                // only add if value is greater than the smallest value at the top of the heap
                if (minValue < value) {
                    minHeap.poll();
                    minHeap.add(value);
                }
            }
        }

        int result[] = new int[k];


        int index = 0;
        Stack<Integer> stack = new Stack<Integer>();
        while(!minHeap.isEmpty()) {
            stack.push(minHeap.poll());
        }

        while (!stack.isEmpty()) {
            result[index++] = stack.pop();
        }

        return result;
    }

    private static void playWithPriorityQueue() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(1);
        pq.offer(10);
        pq.offer(5);
        pq.offer(10);


        System.out.printf("%s\n", pq);

        while (!pq.isEmpty()) {
            System.out.printf("%d\n", pq.poll());
        }
    }

    private static void playWithSortedSet() {
        SortedSet<Integer> ss = new TreeSet<>();

        ss.add(1);
        ss.add(10);
        ss.add(5);
        ss.add(10);

        System.out.printf("****** sorted set *********\n");
        for (Integer i : ss) {
            System.out.printf("%d\n", i);
        }
    }
}
