package org.learning.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by hluu on 10/14/17.
 *
 * Semaphore is usually used with a use case where there is a limited amount of shared
 * resources to be used. An example of this is JDBC connection pool, file handle, network connection
 * pool, etc.
 *
 * http://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/
 *
 */
public class SemaphoreExample {

    public static void main(String[] args) throws Exception {

        System.out.println("*********** SemaphoreExample ******* ");
        ExecutorService executorService = Executors.newFixedThreadPool(5);


        Semaphore semaphore = new Semaphore(4);

        Runnable longRunningTask = () -> {
            boolean isPermitted  = false;

            try {
                isPermitted = semaphore.tryAcquire();
                if (isPermitted) {
                    System.out.println("Semaphored acquired");
                    ConcurrentUtil.sleep(TimeUnit.SECONDS.toMillis(3));
                } else {
                    System.out.println("Could not acquired semaphored");
                }
            } finally {
                semaphore.release();
            }
        };


        IntStream.range(0, 10).forEach(i -> executorService.submit(longRunningTask));

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);


        System.out.println("*********** completed ******* ");
    }
}

