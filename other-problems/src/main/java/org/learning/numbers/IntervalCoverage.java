package org.learning.numbers;

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
 * [1,6) and [8,9) don't intersect, so the total covered length is a sum of both intervals, that is 5+1=6.
 *
 *          |__|__|__|                  (3,6)
 *                         |__|         (8,9)
 *    |__|__|__|__|                     (1,5)
 *
 * 0  1  2  3  4  5  6  7  8  9  10
 *
 */
public class IntervalCoverage {
    public static void main(String[] args) {
        System.out.println("IntervalCoverage.main");
    }
}
