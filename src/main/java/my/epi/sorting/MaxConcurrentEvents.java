package my.epi.sorting;

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

        List<Interval> intervalList = new ArrayList<Interval>();
        intervalList.add(new Interval(1,5));
        intervalList.add(new Interval(5,10));
        intervalList.add(new Interval(3,7));
        intervalList.add(new Interval(5,8));



    }

    public static int maxConcurrentIntervals(List<Interval> input) {
        System.out.println(input);
        Collections.sort(input);
        System.out.println("After sorting: " + input);

        int maxConcurrIntervalCounter = 0;
        int counter = 0;

        Interval prevInterval = null;
        for (Interval intv : input) {
            if (prevInterval == null) {
                prevInterval = intv;
                counter++;
                maxConcurrIntervalCounter = counter;
            } else if (intv.start <= prevInterval.end) {
                counter++;
                if (counter > maxConcurrIntervalCounter) {
                    maxConcurrIntervalCounter = counter;
                }
                // maintain the internval the largest end point
                prevInterval = (intv.end > prevInterval.end) ? intv : prevInterval;
            } else {
                // new interval starts after the end of prevInternval
                prevInterval = intv;
                counter = 1;
            }
        }
        return maxConcurrIntervalCounter;
    }

    /**
     * This approach breaks each interval into two points - starting and ending
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


