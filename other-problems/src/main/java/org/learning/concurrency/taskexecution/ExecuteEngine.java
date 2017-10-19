package org.learning.concurrency.taskexecution;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by hluu on 10/14/17.
 */
public class ExecuteEngine<T> {
    private int numTasks = 1;
    private ExecutorService executor;

    private ExecuteEngine() {
        executor = Executors.newSingleThreadExecutor();
    }

    public static ExecuteEngine builder() {
        return new ExecuteEngine();
    }

    public ExecuteEngine numTasks(int n) {
        this.numTasks = n;
        return this;
    }

    public ExecuteEngine build() {
        return this;
    }

    /**
     * Synchronous task execution
     *
     * @param task
     * @return
     * @throws TaskExecutionException
     */
    public T execute(Task<T> task) throws TaskExecutionException {
        long startTS = System.currentTimeMillis();

        InternalTask<T> internalTask = new InternalTask<>(task);

        Future<T> futureTask = executor.submit(internalTask);

        try {
            T result = futureTask.get();
            return result;
        } catch (Throwable t) {
            throw new TaskExecutionException(t);
        } finally {
            long stopTS = System.currentTimeMillis();
            System.out.printf("task took: %d ms\n", stopTS - startTS);
        }
    }

    /**
     * Asynchronous task execution
     *
     * @param task
     * @return
     * @throws TaskExecutionException
     */
    public void executeAsync(Task<T> task, TaskCallback<T> callback) {
        long startTS = System.currentTimeMillis();

        InternalTask<T> internalTask = new InternalTask<>(task);

        Future<T> futureTask = executor.submit(internalTask);

        try {
            T result = futureTask.get();
            callback.onSuccess(result);
        } catch (Throwable t) {
            callback.onFailure(new TaskExecutionException(t));
        } finally {
            long stopTS = System.currentTimeMillis();
            System.out.printf("task took: %d ms\n", stopTS - startTS);
        }
    }

    private T executeInternal(Task<T> task) throws TaskExecutionException {
        long startTS = System.currentTimeMillis();
        try {
            T result = task.execute();
            return result;
        } catch (Throwable t) {
            throw new TaskExecutionException(t);
        } finally {
            long stopTS = System.currentTimeMillis();
            System.out.printf("task took: %d ms\n", stopTS - startTS);
        }
    }

    public void shutDown() {
        try {
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);

            executor.shutdownNow();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
