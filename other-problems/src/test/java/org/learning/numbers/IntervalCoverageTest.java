package org.learning.numbers;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 1/9/16.
 */
public class IntervalCoverageTest {
    @Test
    public void oneInterval(){
        IntervalCoverage ic = new IntervalCoverage();
        ic.add(1,5);
        Assert.assertEquals(ic.getTotalLength(), 4);
    }

    @Test
    public void twoNonoverlappingIntervals(){
        IntervalCoverage ic = new IntervalCoverage();
        ic.add(1,5);
        ic.add(8,10);
        Assert.assertEquals(ic.getTotalLength(), 6);
    }

    @Test
    public void twoIntersectingIntervals(){
        IntervalCoverage ic = new IntervalCoverage();
        ic.add(1,5);
        ic.add(3,8);
        Assert.assertEquals(ic.getTotalLength(), 7);
    }

    @Test
    public void twoOverlappingIntervals(){
        IntervalCoverage ic = new IntervalCoverage();
        ic.add(2,5);
        ic.add(1,6);
        Assert.assertEquals(ic.getTotalLength(), 5);
    }

    @Test
    public void OverlappingIntervalsWithNonoverlappingIntervals(){
        IntervalCoverage ic = new IntervalCoverage();
        ic.add(1,5);
        ic.add(3,8);

        ic.add(10,15);
        Assert.assertEquals(ic.getTotalLength(), 12);
    }
}
