package org.learning.concurrency.taskexecution;

/**
 * Created by hluu on 10/14/17.
 */
public interface Task<T> {
    public T execute();
}
