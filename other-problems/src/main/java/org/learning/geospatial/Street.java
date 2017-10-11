package org.learning.geospatial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hluu on 7/27/16.
 *
 * A street is represented by a list of segments where each segment consists of a start and end point.
 * Each point has x and y coordinate on a two dimensional plane.
 *
 * A valid street is represented by a list of segments where:
 *  * There are no gaps between segments
 *  * No loop
 *  * No fork
 *
 * In other words, a valid street consists of segments where the segments are contiguous where
 * the end point of previous segment is the same as the start point of the next segment.
 *
 * For example, below is a valid street represented by 3 segments:
 *
 * Segment 1: (1,2):(5,3)
 * Segment 2: (5,3):(6,6)
 * Segment 3: (6,6):(9,4)
 *
 *
 */
public class Street {
    public static void main(String[] args) {

        System.out.println("Street.main");

        List<Segment> validStreet = new ArrayList<>();

        System.out.printf("valid: %b", isValid(validStreet)  );
    }

    private static List<Segment> createValidStreet() {
        List<Segment> validStreet = new ArrayList<>();
        validStreet.add(Segment.create(1,2,5,3));
        validStreet.add(Segment.create(5,3,6,6));
        validStreet.add(Segment.create(6,6,9,4));
        return validStreet;
    }

    /**
     * The invariant we want to check for is only the first and end point of a street will have
     * a count of 1, all the remaining points in between should have a count of 2.
     *
     * @param segments
     * @return
     */
    public static boolean isValid(List<Segment> segments) {
        Map<Point, Integer> pointcount = new HashMap<>();

        try {
            for (Segment seg : segments) {
                incrementCount(seg.start, pointcount);
                incrementCount(seg.end, pointcount);
            }
        } catch (IllegalStateException ise) {
            System.out.println(ise.toString());
            return false;
        }


        // start point
        // end point
        return true;
    }

    private static void incrementCount(Point p, Map<Point, Integer> pointcount) {
        Integer count = pointcount.get(p);
        if (count == null) {
            count = 1;
        } else {
            count = count + 1;
        }

        if (count > 2) {
            throw new IllegalStateException("Count is greater than 2 for point: " + p);
        }
        pointcount.put(p, count);
    }

    private static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return x * 3 + y * 5;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)", x,y);
        }
    }

    private static class Segment {
        public Point start;
        public Point end;

        public Segment(Point s, Point e) {
            this.start = s;
            this.end = e;
        }

        public static Segment create(int x1, int y1, int x2, int y2) {
            return new Segment(new Point(x1,y1), new Point(x2,y2));
        }
    }
}
