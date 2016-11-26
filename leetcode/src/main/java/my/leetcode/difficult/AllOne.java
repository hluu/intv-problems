package my.leetcode.difficult;

import org.common.DLNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hluu on 10/25/16.
 *
 * Problem:
 *  Implement a data structure supporting the following operations:
 *  Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
 *  Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
 *  GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
 *  GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
 *
 * Challenge: Perform all these in O(1) time complexity.
 *
 * Approach:
 *  1) Need a hashmap to look up by key
 *  2) Need to maintain a list of value in increasing order
 *  3) Max will come from the end of the list
 *  4) Min will come from the beginning of the list
 *  5) Need ability to add/remove a node to/from linked list
 */
public class AllOne {

    private DLNode<Integer> head, tail;
    private Map<String,DLNode<Integer>> map = new HashMap<>();


    /** Initialize your data structure here. */
    public AllOne() {

    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     *
     * Notes:
     *  The invariant of the list is the elements are ordered.
     *  If it is a brand new key, then add to the beginning of the list
     *  If it is an existing key, the move it down.  Take care of the situation
     *  where there are bunch elements with same values
     *
     * */
    public void inc(String key) {
        DLNode<Integer> n = map.get(key);
        if (n == null) {
            n = new DLNode<>(1);

            if (head == null) {
                head = n;
                tail = n;
            }
        } else {
            n.value = n.value + 1;
            n.moveUp();
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {

    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return  null;
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return  null;
    }

    public static void main(String[] args) {
        System.out.printf("AllOne\n");
    }
}
