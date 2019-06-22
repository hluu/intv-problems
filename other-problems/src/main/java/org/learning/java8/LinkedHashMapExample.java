package org.learning.java8;

import java.util.LinkedHashMap;

/**
 * LinkedHashMap extends HashMap to provide additional functionalities like:
 * - iteration based on either
 *   - insertion order (default behavior)
 *   - access order : from least-recently access to most-recently access
 * - by maintaining a double-linked list (in addition the hashmap)
 */
public class LinkedHashMapExample {
    public static void main(String[] args) {
        System.out.println(LinkedHashMapExample.class.getName());

        insertOrderTest();
    }

    private static void insertOrderTest() {
        System.out.println("=== testing insertion order ===");

        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();

        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        for (Integer key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }
    }
}
