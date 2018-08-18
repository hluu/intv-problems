package org.learning.concurrency;

/**
 * Write a scheduler to schedule task
 *
 */
public class Scheduler {

    public interface DelayedScheduler {

        void delayedRun(long timeToRun, Runnable task);
    }
    public interface Runnable {
        public void run();
    }

    public static class MyDelayedScheduler implements DelayedScheduler {
        public void delayedRun(long timeToRun, Runnable task) {

        }
    }
}
