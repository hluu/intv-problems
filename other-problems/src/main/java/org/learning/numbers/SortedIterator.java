package org.learning.numbers;

import org.testng.Assert;

import java.util.*;

/**
 * Give a list of sorted lists of size maximum size M, implement
 * an iterator and maintain the order of items in the original lists
 *
 * For example:
 *   {1 ,2, 8}, {6,9,10}, {1, 4, 5 , 12 }
 *
 * Iterator output: {1,1,2,4,5,6,8,9,10,12}
 *
 * Observation:
 * - lists are sorted, as the iterator iterates we just need to pick the smallest
 *   value from among the lists
 * - one approach is to used a min heap (priority queue) to maintain at most one
 *   element from each list, let the min heap performs the sorting
 * - as we pick one element from the min heap, we will add another element from
 *   the same list if we haven't reach the end of that list
 */
public class SortedIterator<E extends  Comparable> implements Iterator<E> {

    public static void main(String[] args) {
        System.out.println(SortedIterator.class.getName());

        Integer[][] arr = {{1 ,2, 8}, {6,9,10}, {1, 4, 5 , 12 }};

        test(new Integer[][] {{},{}, {}}, Arrays.asList());
        test(new Integer[][] {{1},{}, {2}}, Arrays.asList(1,2));
        test(new Integer[][] {{1,3,15},{4}, {6,12}}, Arrays.asList(1,3,4,6,12,15));
        test(arr, Arrays.asList(1,1,2,4,5,6,8,9,10,12));

    }

    private static void test(Integer[][] arr, List<Integer> expected) {

        System.out.println("\n ====== testing =====");
        SortedIterator<Integer> iterator = new SortedIterator(arr);

        List<Integer> actual = new LinkedList<>();
        while (iterator.hasNext()) {
            actual.add(iterator.next());
        }

        System.out.println("expected: " + expected);
        System.out.println("  actual: " + actual);

        Assert.assertEquals(actual, expected);
    }


    private int totalSize = 0;
    private int currSize = 0;
    private Integer[][] arrayData;

    private PriorityQueue<InternalNode> minHeap = new PriorityQueue<>();
    public SortedIterator(Integer[][] input) {
        arrayData = input;

        init();
    }

    private void init() {
        if (arrayData != null) {
            // bootstraping the min heap with
            // the first element of each array
            for (int i = 0; i <arrayData.length; i++) {
                totalSize += arrayData[i].length;
                // what if the array is empty
                if (arrayData[i].length > 0) {
                    minHeap.offer(new InternalNode(i, 0));
                }
            }
        }
    }

    public boolean hasNext() {
        return (currSize < totalSize);
    }

    /**
     * As we pull one element out of the top of the heap, we will
     * add the next element to the min heap from the same array
     * @return
     */
    public E next() {
        InternalNode node = minHeap.poll();
        if (node != null) {
            int arrayNum = node.arrayNum;
            int idx = node.index;
            Integer valueToReturn = arrayData[arrayNum][idx];

            // if this is not the last element in arrayData[arrayNum]
            if (idx < (arrayData[arrayNum].length - 1)) {
                minHeap.offer(new InternalNode(arrayNum, idx+1));
            }
            currSize++;
            return (E)valueToReturn;
        } else {
            return null;
        }
    }

    private class InternalNode implements Comparable<InternalNode> {
        int arrayNum;
        int index;
        public InternalNode(int arrayNum, int index) {
            this.arrayNum = arrayNum;
            this.index = index;
        }

        public int compareTo(InternalNode other) {
            return arrayData[arrayNum][index] - arrayData[other.arrayNum][other.index];
        }

    }


}
