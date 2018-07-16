package org.learning.linked_list;


import org.testng.Assert;

import java.util.*;

/**
 *
 *  https://www.programcreek.com/2013/03/leetcode-lru-cache-java/
 *
 *  Design and implement a data structure for LRU cache.  It should support following operations:
 *  * get and set
 *  * get(key) - get the value of the key if key exists in the cache. otherwise return -1
 *  * set(key,value) - set or insert the value if the key is not already present. When
 *     cache is full, it should first invalidate the least recently used cache item before inserting
 *     a new now
 *
 *  Approach:
 *  * Need a way for a fast look up => hash table
 *  * Need a way to maintaining the order of cache element usage in access order (doubled linked list)
 *    i.e least recently used at the beginning, most recently used at the end
 *    * used implies either at insertion or access time
 *  * So we need to use two data structures, this means we need a bridge.
 *    The value represent an object that points to a node in the linked list, which contains
 *    the actual element
 *    * get(key) -> if element exists, move element to the end of the double-linked list
 *    * set(key) -> if element exists, update the element, move it to the end of the double linked list
 *                  if doesn't exist, check if capacity has reach
 *                    If no, then simply insert the element and add to end of the double linked list
 *                    If yes, then first remove the entry in hash table, then remove the element from linked-list
 *                     * this implies from double linked list, we need a way to refer to element in hashtable
 *                     * then add element and move it to the end of the linked list
 *    * need a function to move element to end of doubled linked list
 *    * need a function to remove an element from doubled linked list
 *
 *
 */
public class LRUCache<K,V> {

    public static void main(String[] args) {
        test0();
        test1();
    }

    private static void test0() {
        LRUCache<String, Integer> cache = new LRUCache<>(1);
        Assert.assertNull(cache.get("one"));

        cache.set("one",1);
        Assert.assertEquals(cache.get("one"), Integer.valueOf(1));

        cache.set("two",2);
        Assert.assertNull(cache.get("one"));
        Assert.assertEquals(cache.get("two"), Integer.valueOf(2));
    }

    private static void test1() {
        LRUCache<String,Integer> cache = new LRUCache<>(2);

        cache.set("one", 1);
        cache.set("two", 2);

        Assert.assertEquals(cache.get("two"), Integer.valueOf(2));
        Assert.assertEquals(cache.get("one"), Integer.valueOf(1));

        Assert.assertEquals(cache.getValuesInOrderOfAccess(), Arrays.asList(2,1));


        cache.get("two");

        Assert.assertEquals(cache.getValuesInOrderOfAccess(), Arrays.asList(1,2));

        cache.set("two", 3);
        Assert.assertEquals(cache.getValuesInOrderOfAccess(), Arrays.asList(1,3));

        // now add to evict the old one

        cache.set("four", 4);
        Assert.assertEquals(cache.getValuesInOrderOfAccess(), Arrays.asList(3,4));

        cache.set("give", 5);
        Assert.assertEquals(cache.getValuesInOrderOfAccess(), Arrays.asList(4,5));

        cache.get("give");
        Assert.assertEquals(cache.getValuesInOrderOfAccess(), Arrays.asList(4,5));

        cache.get("four");
        Assert.assertEquals(cache.getValuesInOrderOfAccess(), Arrays.asList(5,4));
    }

    private Map<K,Node<K,V>> cache;
    private Node<K,V> head;
    private Node<K,V> tail;

    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
    }

    public List<V> getValuesInOrderOfAccess() {
        List<V> result = new ArrayList<>();
        if (head == null) {
            return result;
        } else {
            Node<K,V> tmpNode = head;
            while (tmpNode != null) {
                result.add(tmpNode.value);
                tmpNode = tmpNode.next;
            }
        }
        return result;
    }

    public void printCache() {
        for (Map.Entry<K,Node<K,V>> entry : cache.entrySet()) {
            System.out.println(entry.getValue().value);
        }
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }
        Node<K,V> node = cache.get(key);
        if (node != null) {
            moveToTail(node);
            return node.value;
        } else {
            return null;
        }
    }

    public void set(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("key or value can't be null");
        }

        Node<K,V> node = cache.get(key);
        if (node != null) {
            node.setValue(value);
            moveToTail(node);
        } else {
            // check capacity
            if (cache.size() == capacity) {
                System.out.println("*** capacity reached, evicting LRU entry at the head ***");
                // evict an element
                // remove from cache, the head is the least recently used
                cache.remove(head.key);
                // update head
                Node<K,V> tmpNode = head;
                head = head.next;
                if (head != null) {
                    head.prev = null;
                }

                tmpNode.next = null;
            }
            node = new Node<>(key, value);
            cache.put(key, node);
        }

        if (head == null) {
            head = node;
            tail = node;
        } else {
            addToTail(node);
        }

    }

    private void addToTail(Node<K,V> node) {
        tail.next = node;
        node.prev = tail;
        node.next = null;

        // update the tail
        tail = node;
    }

    /**
     * NOTE: Remember to deal with following situations
     *
     * 0) Make sure to not run into NPE
     * 1) Node is tail
     * 2) Node is head
     * 3) Node is in the middle
     *
     *
     * @param node
     */
    private void moveToTail(Node<K,V> node) {
        // move node to tail
        if (node == tail) {
            // no need to update node if it is already a tail
            return;
        }

        if (node == head) {
            head = node.next;
        } else {
            // update previous node's next pointer
            node.prev.next = node.next;
        }

        // update the next node's previous pointer
        node.next.prev = node.prev;

        tail.next = node;
        node.prev = tail;
        node.next = null;

        // update the tail
        tail = node;

    }

    private static class Node<K,V> {
        private K key;
        private V value;
        private Node<K,V> prev;
        private Node<K,V> next;

        public Node(K k, V v) {
            this.key = k;
            this.value = v;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V v) {
            this.value = v;
        }
    }
}

