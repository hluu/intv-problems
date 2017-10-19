package org.learning.concurrency;

/**
 * Created by hluu on 10/14/17.
 */
public class ConcurrentUtil {
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }
}
