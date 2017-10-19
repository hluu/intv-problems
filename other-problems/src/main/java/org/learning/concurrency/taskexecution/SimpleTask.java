package org.learning.concurrency.taskexecution;

import java.util.concurrent.TimeUnit;

/**
 * Created by hluu on 10/14/17.
 */
public class SimpleTask implements Task<String>, TaskCallback<String> {
    private String msg ;
    public SimpleTask(String msg) {
        this.msg = msg;
    }
    public String execute() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (Exception e) {

        }
        return msg;
    }

    public static void main(String[] args) throws Exception {
        ExecuteEngine<String> engine = ExecuteEngine.builder().numTasks(3).build();

        Task task1 = new SimpleTask("This is an awesome task");
        String result = engine.execute(task1);
        System.out.println("sync task result: " + result);


        SimpleTask task2 = new SimpleTask("This is an awesome ASYNC task");
        engine.executeAsync(task2, task2);


        engine.shutDown();
    }

    public void onSuccess(String value) {
        System.out.println("result from async: " + value);
    }

    public void onFailure(TaskExecutionException ex) {
        ex.printStackTrace();
    }

}
