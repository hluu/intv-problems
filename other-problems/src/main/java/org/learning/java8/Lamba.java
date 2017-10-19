package org.learning.java8;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hluu on 10/29/16.
 */
public class Lamba {

    public static void main(String[] args) {
        Arrays.asList(1,2,3,4,5).forEach(e -> System.out.printf("%d \n", e));

        int[] nums = {1,2,3,4,5};
        int evens[] = Arrays.stream(nums).filter(x -> (x % 2 == 0)).toArray();

        System.out.printf("%s \n", Arrays.toString(evens));
    }

    private static void runAnonymousRunnable() {
        System.out.println("======= runAnonymousRunnable =========");
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Runnable task = () -> {
            String name = Thread.currentThread().getName();
            System.out.println("thread name: " + name);
        };

        executor.submit(task);
        executor.shutdownNow();

        System.out.println("======= completed runAnonymousRunnable =========");
    }
}
