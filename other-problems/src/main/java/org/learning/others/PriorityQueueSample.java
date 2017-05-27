package org.learning.others;

import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;

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

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(1);
        pq.offer(10);
        pq.offer(5);
        pq.offer(10);

        System.out.printf("%s\n", pq);

        while (!pq.isEmpty()) {
            System.out.printf("%d\n", pq.poll());
        }

       // playWithSortedSet();
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
