package org.learning.numbers;

/**
 * Created by hluu on 2/10/18.
 *
 * Given an unsorted array of integer and assume the number of element is odd.
 *
 * Figure out 5 different ways of find the median, not mean (avg)
 *
 * Traditional ways of finding a median:
 * 1) Sort, and get value at index = len(array)/2 + 1                      => O(nlog(n))
 * 2) Use a min heap, dump all values in there and get out (len/2+1) times => O(nlog(n))
 * 3) Use a max heap, dump all values in there and get out (len/2+1) times => O(nlog(n))
 * 4) Use quickselect                                                      => O(n^2)
 * 5) Use two heaps - max and min heap
 *
 *  http://www.ics.uci.edu/~eppstein/161/960125.html
 *
 *  https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-046j-introduction-to-algorithms-sma-5503-fall-2005/video-lectures/lecture-6-order-statistics-median/
 */
public class WayOfFindingMedian {
}
