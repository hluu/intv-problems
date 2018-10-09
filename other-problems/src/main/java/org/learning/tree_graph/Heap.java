package org.learning.tree_graph;

import java.util.Arrays;

/**
 * An crude implementation of a heap
 *
 * The data structure is an array:
 *   Child index:
 *     left = parentIdx * 2 + 1;
 *     right = parentIdx * 2 + 2;
 */
public class Heap {
    private int capacity = 10; // initial value
    private int size = 0;

    private int[] items;

    public Heap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("size can't be less than 1");
        }
        this.capacity = capacity;

        items = new int[capacity];
    }

    private int getLeftChildIndex(int parentIdx) {
        return 2 * parentIdx + 1;
    }

    private int getRightChildIndex(int parentIdx) {
        return 2 * parentIdx + 2;
    }

    private int getParentIndex(int childIdx) {
        return (childIdx - 1) / 2;
    }

    private boolean hasLeftChild(int idx) {
        return getLeftChildIndex(idx) < size;
    }

    private boolean hasRightChild(int idx) {
        return getRightChildIndex(idx) < size;
    }

    private boolean hasParent(int idx) {
        return getParentIndex(idx) >= 0;
    }

    private int getLeftChild(int parentIdx) {
        return items[getLeftChildIndex(parentIdx)];
    }

    private int getRightChild(int parentIdx) {
        return items[getRightChildIndex(parentIdx)];
    }

    private int getParent(int idx) {
        return items[getParentIndex(idx)];
    }

    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return (size == 0);
    }

    public void swapItem(int idx1, int idx2) {
        int tmp = items[idx1];
        items[idx1] = items[idx2];
        items[idx2] = tmp;
    }

    public void ensureExtraCapacity() {

        if (size == capacity) {
            int newCapacity = this.capacity * 2;

            int[] newItemsArray = Arrays.copyOf(items, newCapacity);

            items = newItemsArray;
            this.capacity = newCapacity;
        }
    }


    public int peek() {
        if (size == 0) {
            throw new IllegalStateException("size is 0");
        }

        // top of the heap
        return items[0];
    }

    /**
     * Extract the top of the heap, meaning return the top of the heap
     * and remove it from the heap
     *
     * @return
     */
    public int poll() {
        if (size == 0) {
            throw new IllegalStateException("size is 0");
        }

        int result = items[0];
        items[0] = items[size-1];

        heapifyDown();
        size--;
        return result;
    }

    public void add(int item) {
        ensureExtraCapacity();
        size++;

        // add item to the end of array
        items[size-1] = item;


        heapifyUp();
    }


    public void heapifyUp() {
        int idx = size - 1;  // from last element

        // move element at idx up the heap
        // while parent is greater than it
        // stop when reach the root node
        while (hasParent(idx) && getParent(idx) > items[idx]) {
            int parentIdx = getParentIndex(idx);
            swapItem(parentIdx, idx);
            idx = parentIdx;
        }
    }

    public void heapifyDown() {
        // move element at idx 0 down the path
        // to either left or right depending on which
        // one is smaller

        int idx = 0; // root node

        while (hasLeftChild(idx)) {
            // assume the left child is smaller than right child
            int smallerItemIdx = getLeftChildIndex(idx);

            // double check and update if necessary
            if (hasRightChild(idx) && getRightChild(idx) < getLeftChild(idx)) {
                smallerItemIdx = getRightChildIndex(idx);
            }

            // stop when current elm is smaller than the smaller then
            // the smallest of the two children
            if (items[idx] < items[smallerItemIdx]) {
                break;
            } else {
                swapItem(smallerItemIdx, idx);
            }

            idx = smallerItemIdx;
        }

    }

    public static void main(String[] args) {
        Heap minHeap = new Heap(5);

        minHeap.add(10);
        minHeap.add(3);
        minHeap.add(7);
        minHeap.add(1);
        minHeap.add(100);
        //minHeap.add(50);

        while (!minHeap.isEmpty()) {
            System.out.println("output: " + minHeap.poll());
            //System.out.println("size: " + minHeap.size());
        }
    }

}
