package my.epi.sorting;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by hluu on 12/7/15.
 */
public class MaxConcurrentEventsTest {

    @Test
    public void oneOverlappingTest() {
        List<MaxConcurrentEvents.Interval> intervalList = new ArrayList<MaxConcurrentEvents.Interval>();
        intervalList.add(new MaxConcurrentEvents.Interval(1,5));
        intervalList.add(new MaxConcurrentEvents.Interval(6,10));
        assertEquals(MaxConcurrentEvents.maxConcurrentIntervals(intervalList), 1);

        assertEquals(MaxConcurrentEvents.maxConcurrentIntervals2(intervalList), 1);
    }

    @Test
    public void twoOverlappingTest() {
        List<MaxConcurrentEvents.Interval> intervalList = new ArrayList<MaxConcurrentEvents.Interval>();
        intervalList.add(new MaxConcurrentEvents.Interval(1,5));
        intervalList.add(new MaxConcurrentEvents.Interval(2,7));
        intervalList.add(new MaxConcurrentEvents.Interval(8,10));
        assertEquals(MaxConcurrentEvents.maxConcurrentIntervals(intervalList), 2);

        assertEquals(MaxConcurrentEvents.maxConcurrentIntervals2(intervalList), 2);
    }

    @Test
    public void twoOverlappingSamePointTest() {
        List<MaxConcurrentEvents.Interval> intervalList = new ArrayList<MaxConcurrentEvents.Interval>();
        intervalList.add(new MaxConcurrentEvents.Interval(1,5));
        intervalList.add(new MaxConcurrentEvents.Interval(5,7));
        intervalList.add(new MaxConcurrentEvents.Interval(8,10));
        assertEquals(MaxConcurrentEvents.maxConcurrentIntervals(intervalList), 2);

        assertEquals(MaxConcurrentEvents.maxConcurrentIntervals2(intervalList), 2);
    }

    @Test
    public void twoOverlappingShortWithinLongIntervalTest() {
        List<MaxConcurrentEvents.Interval> intervalList = new ArrayList<MaxConcurrentEvents.Interval>();
        intervalList.add(new MaxConcurrentEvents.Interval(3,5));
        intervalList.add(new MaxConcurrentEvents.Interval(1,7));
        intervalList.add(new MaxConcurrentEvents.Interval(8,10));
        assertEquals(MaxConcurrentEvents.maxConcurrentIntervals(intervalList), 2);

        assertEquals(MaxConcurrentEvents.maxConcurrentIntervals2(intervalList), 2);
    }

    @Test
    public void threeOverlappingTest() {
        List<MaxConcurrentEvents.Interval> intervalList = new ArrayList<MaxConcurrentEvents.Interval>();
        intervalList.add(new MaxConcurrentEvents.Interval(1,5));
        intervalList.add(new MaxConcurrentEvents.Interval(2,7));
        intervalList.add(new MaxConcurrentEvents.Interval(4,10));
        intervalList.add(new MaxConcurrentEvents.Interval(15,20));
        assertEquals(MaxConcurrentEvents.maxConcurrentIntervals(intervalList), 3);

        assertEquals(MaxConcurrentEvents.maxConcurrentIntervals2(intervalList), 3);
    }

    @Test
    public void threeOverlappingOneLongOneTest() {
        List<MaxConcurrentEvents.Interval> intervalList = new ArrayList<MaxConcurrentEvents.Interval>();
        intervalList.add(new MaxConcurrentEvents.Interval(1,13));
        intervalList.add(new MaxConcurrentEvents.Interval(2,7));
        intervalList.add(new MaxConcurrentEvents.Interval(4,10));
        intervalList.add(new MaxConcurrentEvents.Interval(15,20));
        assertEquals(MaxConcurrentEvents.maxConcurrentIntervals(intervalList), 3);

        assertEquals(MaxConcurrentEvents.maxConcurrentIntervals2(intervalList), 3);
    }



}
