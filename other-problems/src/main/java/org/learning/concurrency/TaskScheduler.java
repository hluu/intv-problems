package org.learning.concurrency;


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Develop a task scheduler that will be used inside an application.
 *
 * There are two high level requirements:
 * 1) Schedule a task with a starting time and an repeated interval
 * 2) Cancel a task
 *
 */
public class TaskScheduler {
    private long counter = 0;
    // to order job in the order of running
    private PriorityQueue<Job> jobQueue = new PriorityQueue<>();
    // for looking up the job during canceling
    private Map<Long,Job> jobMap = new HashMap<>();

    // background thread to examine the job queue
    private TaskRunner taskRunner;

    private ReentrantLock lock;
    private Condition condition = lock.newCondition();

    public TaskScheduler() {
        lock = new ReentrantLock();
        taskRunner = new TaskRunner();
        new Thread(taskRunner).start();
    }


    public long schedule(Runnable task, long startTime, long interval) {
        lock.lock();

        try {
            counter++;

            Job job = new Job(counter, task, startTime, interval);

            jobQueue.add(job);

            jobMap.put(counter, job);
            condition.signal();
            return job.jobId;
        } finally {
            lock.unlock();
        }
    }

    public void cancel(long jobId) {
        Job job = jobMap.get(jobId);
        if (job != null) {
            lock.lock();
            try {
                job.interval = 0;
                jobMap.remove(job.jobId);
            }finally {
                lock.unlock();
            }
        }
    }

    private class TaskRunner implements  Runnable {

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    if (jobQueue.isEmpty()) {
                        try {
                            condition.await();
                        } catch (Throwable t) {

                        }
                    }

                    if (!jobQueue.isEmpty()) {
                        Job topJob = jobQueue.peek();
                        if (topJob.startTime <= System.currentTimeMillis()) {
                            topJob = jobQueue.poll();

                            // run
                            topJob.task.run();

                            if (topJob.interval > 0) {
                                jobQueue.add(topJob);
                                condition.signal();
                            }
                        }

                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    /**
     * Need job class to hold info. for each task
     */
    private static class Job implements Comparable<Job> {
        public long jobId;
        public Runnable task;
        public long  interval;
        public long startTime;

        public Job(long jobId, Runnable task, long startTime, long interval) {
            if (task == null) {
                throw new IllegalArgumentException("task can't be null");
            }
            this.jobId = jobId;
            this.task = task;
            this.interval = interval;
            this.startTime = startTime;
        }

        @Override
        public int compareTo(Job other) {
            return (int)(startTime - other.startTime);
        }
    }
}
