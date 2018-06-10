package org.learning.numbers;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Each time an api it call, it takes a certain amount of time for it to come
 * back.  We want to track the average api performance over a period of time.
 *
 * Write a class to do that.
 *
 * First principle thinking:
 * 1) Only care about the durations of the calls that fall within the window
 * 2) this means we can expire the older one, when do we that?
 *    * There are two opportunities (when record() and getAverage() are called)
 * 3) We don't want to go through the list of durations each time getAverage()
 * 4) We need total duration and number of calls to calculate the average
 * 5) Maintain an up-to-date total duration and # of calls
 * 6) We need a data structure to maintain the list of durations
 *
 * THE MAIN POINT HERE IS TO KEEP A MOVING WINDOW that contains only the
 * need info. and keep the total up-to-date during record and getAverageTime
 * are called.
 *
 * * How to deal with concurrency? multiple threads calling record,
 *   getAverageTime
 *   * Maybe considering product-consumer pattern and have only a single
 *     consumer to touch the callList.
 *
 */
public class MovingAverage {

    private long windowLen = -1;

    private Queue<CallInfo> callList = new LinkedList<>();
    private long totalDuration;

    public MovingAverage(int windowLen) {
        this.windowLen = windowLen * 60 * 1000;
    }

    public long now() {
        return System.currentTimeMillis();
    }
    /**
     * This method is called to record the performance of each call
     * @param duration - valid range of duration?
     */
    public void record(int duration) {
        if (duration < 1) {
            throw new IllegalArgumentException("Invalid duration");
        }

        // this is adding to the tail
        callList.add(new CallInfo(now(), duration));
        totalDuration += duration;

        removeOldCallInfo();
    }

    /**
     * Return the average of all the calls that fall within the last
     * windowLen
     *
     * @return
     */
    public double getAverageTime() {

        // trim the list and update the running time
        removeOldCallInfo();

        return totalDuration / callList.size();
    }

    /**
     * Main goal is to maintain the calls that are in window from the
     * the time of this call.
     *
     * DON'T FORGET TO UPDATE THE RUNNING TOTAL
     */
    private  void removeOldCallInfo() {
        long now = now();
        while (!callList.isEmpty()) {
         CallInfo callInfo = callList.peek();

         if ((now - callInfo.timeStamp) > windowLen) {
            callList.remove();
            totalDuration -= callInfo.duration;
         } else {
             return;
         }
     }
    }

    private static class CallInfo {
        public long timeStamp;
        public int duration;

        public CallInfo(long ts, int duration) {
            this.timeStamp = ts;
            this.duration = duration;
        }
    }

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage(5);

        ma.record(2);
        ma.record(4);
        ma.record(6);
        ma.record(12);

        System.out.println("avg: " + ma.getAverageTime());
    }
}
