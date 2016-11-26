package org.learning.java8;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by hluu on 10/29/16.
 */
public class ParallelSorting {
    public static void main(String[] args) {
        System.out.printf("%s\n", ParallelSorting.class.toString());

        long[] nums = new long[20000];

        Arrays.parallelSetAll(nums, index -> ThreadLocalRandom.current().nextInt(100000));

        Arrays.stream(nums).limit(50).forEach(i -> System.out.printf("%d ", i));

        System.out.println("\n------- after sorting -------");

        Arrays.parallelSort(nums);

        Arrays.stream(nums).limit(10).forEach(i -> System.out.printf("%d ", i));

    }
}
