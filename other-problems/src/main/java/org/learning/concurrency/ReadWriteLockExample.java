package org.learning.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWrite lock example
 */
public class ReadWriteLockExample {
    public static void main(String[] args) throws Exception {

        System.out.println("======= ReadWriteLockExample ========= ");
        ReadWriteLockExample example = new ReadWriteLockExample();

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            example.put("one", "1");
        });

        Runnable readTask = () -> {
            System.out.println(example.get("one"));
        };

        executor.submit(readTask);
        executor.submit(readTask);

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("******** finished ***********");
    }

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private Lock readLock = rwLock.readLock();
    private Lock writeLock = rwLock.writeLock();

    private Map<String,String> cache = new HashMap();

    private String get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key argument can't be null");
        }

        readLock.lock();
        try {
            return cache.get(key);
        } finally {
            readLock.unlock();
        }
    }

    private void put(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("key and value argument can't be null");
        }

        writeLock.lock();
        try {
            cache.put(key, value);
            ConcurrentUtil.sleep(TimeUnit.SECONDS.toMillis(1));
        } finally {
            writeLock.unlock();
        }
    }


}
