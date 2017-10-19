package org.learning.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Created by hluu on 10/14/17.
 */
public class ConcurrencyExamples {
    public static void main(String[] args) throws Exception {
        ConcurrencyExamples example = new ConcurrencyExamples();
        example.noLock();

    }

    private int count = 0;

    private ReentrantLock lock = new ReentrantLock();

    private void incrementUsingLock() {
        lock.lock();
        try {
            count = count + 1;
        } finally {
             lock.unlock();
        }

    }

    synchronized void increment() {
        count = count + 1;
    }

    private void noLock() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        IntStream.range(0, 10000)
                .forEach(i -> executor.submit(this::incrementUsingLock));

        executor.shutdown();

        while (!executor.isTerminated()) {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        }
        System.out.println(count);  // 9965
    }
}
