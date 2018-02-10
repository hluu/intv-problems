package my.epi.sorting;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hluu on 12/7/15.
 *
 * Problem statement:
 * Given a set of potentially overlapped intervals where each has
 * a start and end point, determine the maximum number of concurrent
 * intervals.  Concurrent intervals are defined as overlapping, where
 * the start and end of an interval is between the start or the end of
 * another interval.
 *
 * Approach:
 *
 * Naive:
 *   Since the set of intervals are not sorted, we need to go through the
 *   list for each of the interval therefore run time will be O(n^2)
 *
 * Better:
 *   Sort the list of interval by starting point.  For intervals that have
 *   same starting point, sort them by end point.
 *
 *   Imagine lining up the interval based on the starting point and move
 *   a vertical line through this sorted list.  The intersection of the
 *   number of intervals represents # of concurrent intervals.  So now
 *   the question is how to keep track of this count in the code.
 *
 *   Imagine below
 *
 *   |-------------| (1)
 *         |------------| (2)
 *                        |----------| (3)
 *   ===================================
 *
 *   Interval #1 and 2 are overlapped and interval #3 is not overlapped with any other
 *   intervals.  In this simple example, the max concurrent # of intervals is 2.
 *
 *   The general technique is to keep a counter where it is incremented when starting an
 *   interval and decremented when ending an interval.  Additional max concurrent interval
 *   counter is needed and updated when the counter is incremented.
 *
 *   This is similar to the braces matching problem, but with a twist of add a counter to
 *   track the highest number of open braces at any point in time.
 *
 *   This approach requires two steps:
 *     1) Sort the intervals by start O(nlogn)
 *     2) Iterate through the sorted interval O(n)
 *
 *
 *
 * Runtime Analysis:
 *   The runtime for the better approach is O(nlogn)
 *
 * Memory Analysis:
 *   We only need two variables  and they are independent of n, so it would be O(1)
 */
public class MaxConcurrentEvents {
    public static void main(String[] args) {
        System.out.println("This is a test");

        List<Interval> intervalList1 = new ArrayList<Interval>();
        intervalList1.add(new Interval(1,5));
        intervalList1.add(new Interval(5,10));
        intervalList1.add(new Interval(3,7));
        intervalList1.add(new Interval(5,8));

        test(intervalList1, 4);

        List<Interval> intervalList2 = new ArrayList<Interval>();
        intervalList2.add(new Interval(1,2));
        intervalList2.add(new Interval(3,5));
        intervalList2.add(new Interval(5,10));
        intervalList2.add(new Interval(4,7));


        test(intervalList2, 3);
    }

    private static void test(List<Interval> intervals, int expectedCount) {
        int actual = maxConcurrentIntervals(intervals);
        System.out.println("result from maxConcurrentIntervals: " + actual);
        Assert.assertEquals(actual, expectedCount);

        actual = maxConcurrentIntervals2(intervals);
        System.out.println("result from maxConcurrentIntervals2: " + maxConcurrentIntervals2(intervals));
        Assert.assertEquals(actual, expectedCount);
    }

    public static int maxConcurrentIntervals(List<Interval> input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        System.out.println(input);
        Collections.sort(input);
        System.out.println("After sorting: " + input);

        int maxConcurrIntervalCounter = 0;
        int counter = 0;

        Interval prevInterval = null;
        for (Interval currInterval : input) {
            if (prevInterval == null) {
                // special case
                prevInterval = currInterval;
                counter++;
                maxConcurrIntervalCounter = counter;
            } else if (currInterval.start <= prevInterval.end) {
                counter++;
                if (counter > maxConcurrIntervalCounter) {
                    maxConcurrIntervalCounter = counter;
                }
                // maintain the interval the largest end point
                prevInterval = (currInterval.end > prevInterval.end) ? currInterval : prevInterval;
            } else {
                // new interval starts after the end of prevInternval
                prevInterval = currInterval;
                counter = 1;
            }
        }
        return maxConcurrIntervalCounter;
    }

    /**
     * This approach breaks each interval into two points - starting and ending.
     * 0) Notice how the interval is created in the constructor - where the start value
     *    for ending interval is the end value.
     * 1) Sort the list of points by starting value. If they are the same, then order by
     *    end value
     * 2) Iterator through the list, increment counter when seeing a starting point as well
     *    as updating the max counter
     * 3) When the ending point is encountered, decrement the counter
     *
     * Observation: The counter value will be doing up and down, the max counter
     *  will only be updated once there is a value greater than it.
     *
     * At the end we just return the max counter
     *
     * @param input
     * @return
     */
    public static int maxConcurrentIntervals2(List<Interval> input) {
        List<Interval> startEndList = new ArrayList<Interval>(input.size()*2);
        for (Interval i : input) {
            startEndList.add(new Interval(i.start, i.end, true));
            startEndList.add(new Interval(i.end, i.end, false));
        }
        Collections.sort(startEndList, new Comparator<Interval>() {
            public int compare(Interval o1, Interval o2) {
                int result = o1.start - o2.start;
                if(result == 0) {
                    result = o2.end - o1.end;
                }
                return result;

            }}
        );

        System.out.println("maxConcurrentIntervals2: " + startEndList);

        int maxConcurrentIntvCounter = 0;
        int counter = 0;
        for (Interval ie : startEndList) {
            if (ie.isStart) {
                counter++;
                maxConcurrentIntvCounter = (counter > maxConcurrentIntvCounter) ? counter : maxConcurrentIntvCounter;
            } else {
                counter--;
            }
        }

        return maxConcurrentIntvCounter;
    }

    public static class Interval implements Comparable<Interval> {
        public int start;
        public int end;
        public boolean isStart;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public Interval(int start, int end, boolean isStart) {
            this.start = start;
            this.end = end;
            this.isStart = isStart;
        }

        public int compareTo(Interval o) {
            if (start != o.start) {
                return start - o.start;
            } else {
                return end - o.end;
            }
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)", start, end);
        }
    }

}


