package org.learning.concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by hluu on 10/14/17.
 *
 * @ConcurrentHashMap is one of the most useful data structure and it handles
 * most of the concurrency needs for you.
 *
 * A few key and important methods to know are:
 * 1) putIfAbsent - The return value will let us know if there was a previous value.
 *    This method is executed atomically
 * 2) replace - this is done atomically
 *
 * Resources
 *  * http://winterbe.com/posts/2015/05/22/java8-concurrency-tutorial-atomic-concurrent-map-examples/
 *  * Great Java concurrency resource - http://tutorials.jenkov.com/java-concurrency/read-write-locks.html
 *
 *
 */
public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();

        String prevValue = map.putIfAbsent("one", "one day I will");
        System.out.println("prevValue: " + prevValue);

        prevValue = map.putIfAbsent("one", "one day I will NOT");
        System.out.println("prevValue: " + prevValue);

        map.forEach((key,value) -> System.out.printf("k: '%s', v: '%s'\n",key, value));

        map.replace("one", "one day I will NOT");
        map.forEach((key,value) -> System.out.printf("k: '%s', v: '%s'\n",key, value));

        // try getOrDefault

        System.out.println("default: " + map.getOrDefault("two", "two is not here"));

    }
}
