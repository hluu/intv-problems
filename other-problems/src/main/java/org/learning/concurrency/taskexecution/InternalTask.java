package org.learning.concurrency.taskexecution;

import java.util.concurrent.Callable;

/**
 * Created by hluu on 10/14/17.
 */
public class InternalTask<T> implements Callable<T> {
    private Task<T> task;
    private T result;

    public InternalTask(Task<T> task) {
        this.task = task;
    }

    public T call() throws Exception {
        result = this.task.execute();
        return result;
    }

    public T getResult() {
        return result;
    }
}
