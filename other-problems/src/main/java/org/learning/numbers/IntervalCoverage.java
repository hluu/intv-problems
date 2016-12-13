package org.learning.numbers;

import java.lang.Comparable;
import java.util.TreeSet;

/**
 * Created by hluu on 1/8/16.
 *
 *
 * Returns a total length covered by the added intervals.
 * If several intervals intersect, the intersection should be counted only once.
 * Example:
 *
 * addInterval(3, 6)
 * addInterval(8, 9)
 * addInterval(1, 5)
 *
 * getTotalCoveredLength() -> 6
 * I.e. [1,5) and [3,6) intersect and give a total covered interval [1,6).
 * [1,6) and [8,9) don't intersect, so the total covered length is a sum of
 * both intervals, that is 5+1=6.
 *
 *          |__|__|__|                  (3,6)
 *                         |__|         (8,9)
 *    |__|__|__|__|                     (1,5)
 *
 * 0  1  2  3  4  5  6  7  8  9  10
 *
 * Approach:
 *  Each interval is represented by a pair of point, one for start and the other for end.
 *  If all these points are sorted.  The above example looks something like below:
 *      1s,3s,5e,6e,8s,9e
 *      (1,s),(3,s),(5,e),(6,e),(8,s),(9,e)
 *
 *  So we can just match up the start and end to calculate the interval of overlapping
 *  intervals.
 *
 *  How to deal with start and end on same value
 *    (1,s),(5,e),(5,s),7(e)
 *
 */
public class IntervalCoverage {
    public static void main(String[] args) {

        System.out.println("IntervalCoverage.main");

        IntervalCoverage ic = new IntervalCoverage();
        ic.add(3,6);
        ic.add(8,9);
        ic.add(1,5);
        ic.add(2,7);

        ic.add(10,15);
        ic.add(11,14);

        System.out.println(ic.getTotalLength());
    }

    private TreeSet<Point> points = new TreeSet<Point>();

    public void add(int from, int to) {
        points.add(new Point(from, true));
        points.add(new Point(to, false));
    }

    public int getTotalLength() {
        int totalLen = 0;

        int from = -1;
        int startCount = 0;
        for (Point point : points) {
            System.out.println(point);

            if (from < 0 && point.start) {
                from = point.coordinate;
            }
            startCount += (point.start) ? 1 : -1;

            if (startCount == 0) {
                // end of a segment
                totalLen += point.coordinate - from;
                from = -1;
            }
        }

        return totalLen;
    }

    private static class Point implements Comparable<Point> {
        public final int coordinate;
        public final boolean start;

        public Point(int from, boolean start) {
            this.coordinate = from;
            this.start = start;
        }

        public int compareTo(Point other) {
            if (other == null) {
                return -1;
            }

            return coordinate - other.coordinate;
        }

        public String toString() {
            return coordinate + ":" + start;
        }
    }

}
