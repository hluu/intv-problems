package org.learning.concurrency;

/**
 * Write a scheduler to schedule task
 *
 */
public class Scheduler {

    public interface DelayedScheduler {

        void delayedRun(long timeToRun, Runnable task);
    }

    /**
     * Run tasks based on the timeToRun of each task.
     * * timeToRun is supposed to be in the future
     * * we can compute the diff. to figure out how long from the current time
     *
     * What data structure do we need?
     */
    public static class MyDelayedScheduler implements DelayedScheduler {
        public void delayedRun(long timeToRun, Runnable task) {

        }
    }

    public static void main(String[] args) {
        MyDelayedScheduler scheduler = new MyDelayedScheduler();

        scheduler.delayedRun(100, new Runnable() {
            @Override
            public void run() {
                System.out.println("Task #1");
            }
        });
    }
}
