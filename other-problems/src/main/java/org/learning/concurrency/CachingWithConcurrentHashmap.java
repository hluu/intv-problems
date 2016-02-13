package org.learning.concurrency;

import java.util.concurrent.*;

/**
 * Created by hluu on 1/14/16.
 *
 * Problem:
 *  Create a cache with good concurrency and make sure the
 *  expensive computation is not recomputed twice in the case
 *  of race condition.
 *
 * Approach:
 *  Using a combination of ConcurrentHashMap, FutureTask, Future
 *
 */
public class CachingWithConcurrentHashmap<K,V> {

    private ConcurrentHashMap<K,FutureTask<V>> cache = new ConcurrentHashMap<>();

    public CachingWithConcurrentHashmap() {
    }

    public static void main(String[] args) {
        System.out.println("CachingWithConcurrentHashmap.main");

        CachingWithConcurrentHashmap<String,Integer> cache =
                new CachingWithConcurrentHashmap<String,Integer>();

        Integer prevValue = cache.put("One", 1);
        prevValue = cache.put("Two", 2);

        System.out.println("One: " + cache.get("One"));
        System.out.println("Three: " + cache.get("Three"));
    }

    public V put(K key, V value) {
        Future<V> resultHolder =  cache.put(key, new LocalFutureTask(value));
        try {
            return resultHolder.get();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * The main point here is storing a Future inside cache and leverage
     * atomic operation ConcurrentHashmap.putIfAbsent()
     *
     * @param key
     * @return
     */
    public V get(K key) {
        Future<V> resultHolder = cache.get(key);

        if (resultHolder == null) {
            FutureTask<V> ft = new FutureTask<V>(new LocalCallable(key));
            cache.putIfAbsent(key, ft);
            ft.run();
            resultHolder = ft;
        }
        try {
            V result = resultHolder.get();
            return  result;
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong");
        }
    }

    private static class LocalCallable<T> implements Callable<T> {
        private T value;
       public LocalCallable(T value) {
           this.value = value;
       }
        public T call() throws Exception {
            return value;
        }
    }

    private static class DummyCallable<V> implements Callable<V> {
        private V v;
        public DummyCallable(V v) {
            this.v = v;
        }

        public V call() throws Exception {
            return v;
        }
    }

    private static class LocalFutureTask<V> extends FutureTask<V> {
        private V value;

        public LocalFutureTask(V v) {
            super(new DummyCallable<V>(v));
            this.value = v;
        }

        @Override
        public V get() throws InterruptedException, ExecutionException {
            return value;
        }
    }
}
