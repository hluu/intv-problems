package my.epi.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hluu on 12/7/15.
 *
 * Problem statement:
 *
 * Approach:
 *
 * Runtime Analysis
 *
 *
 * Memory Analysis:
 */
public class MaxConcurrentEvents {
    public static void main(String[] args) {
        System.out.println("This is a test");

        List<Interval> intervalList = new ArrayList<Interval>();
        intervalList.add(new Interval(1,5));
        intervalList.add(new Interval(5,10));
        intervalList.add(new Interval(3,7));

        System.out.println(intervalList);
        Collections.sort(intervalList);
        System.out.println("After sorting: " + intervalList);
    }


    private static class Interval implements Comparable<Interval> {
        public int start;
        public int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
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


