package org.learning.java8;

import org.testng.Assert;

import java.util.*;

/**
 * Different ways of creating priority queue with customer comparator
 */
public class PriorityQueueExample {

    public static void main(String[] args) {
        PriorityQueue<Integer> pq1 = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder());

        int[] input = new int[] {5,2,20,11,18, 19, 12};
        List<Integer> expected = new ArrayList<>();

        for (int val : input) {
            pq1.offer(val);
            pq2.offer(val);

            expected.add(val);
        }

        Collections.sort(expected, Collections.reverseOrder());
        System.out.println("expected: " + expected);

        validatePQ(pq1, expected);
        validatePQ(pq2, expected);
    }

    private static void validatePQ(PriorityQueue<Integer> pq, List<Integer> expected) {

        System.out.println("\n*** running validation ****");

        // validate size
        Assert.assertEquals(expected.size(), pq.size());

        // validate each value in pq matches and in the right order
        for (Integer val : expected) {
            Assert.assertEquals(val, pq.poll());
        }

        // make sure pq is empty at this point, maybe redundant
        Assert.assertTrue(pq.isEmpty());

        System.out.println("*** passed ****");
    }
}
