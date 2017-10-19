package org.learning.concurrency.taskexecution;

/**
 * Created by hluu on 10/18/17.
 */
public interface TaskCallback<T> {
    void onSuccess(T value);
    void onFailure(TaskExecutionException t);
}
