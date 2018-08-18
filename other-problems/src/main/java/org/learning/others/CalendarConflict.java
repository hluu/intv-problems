package org.learning.others;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hluu on
 *
 * Problem:
 *  Give a list of calendar events (start time, end time, event name),
 *  find out the ones that are conflict with each other.
 *
 *  Are the events sorted by start time?
 *  If end time is the same, does that consider a conflict?
 *
 *  [1,2, 1]
 *  [3,5, 2]
 *  [4,6, 3]
 *  [7,10, 4]
 *  [8,11, 5]
 *  [10,12, 6]
 *  [13,14, 7]
 *  [13,14, 8]
 *
 *  Conflict scenarios:
 *
 *    |------|       |---------|
 *       |------|       |-----|
 *
 *
 *    |------||----        |------------------|
 *       |------|             |-----| |------|
 *
 *  Approach:
 *      * Conflict detection - when start time of event event is less than
 *        end time of previous event
 *
 *      * All conflicted events are grouped together into one group
 *      * Maintain an end time for previous cal. event and update it as going
 *        through the loop
 *      * Copy temp_result to final result at the end of the loop
 *
 * Analysis
 *      * O(n) because only one loop through the events array
 *
 * Two main lessons:
 *      * Make sure to update the previous end time
 *      * Make sure to copy temp_result to final result
 *
 *
 *
 */
public class CalendarConflict {
    public static void main(String[] args) {
        System.out.printf("%s\n", CalendarConflict.class.getName());

        int[][] events = {
                {1,2, 1},
                {3,5, 2},
                {4,6, 3},
                {7,10, 4},
                {8,11, 5},
                {10,12, 6},
                {13,14, 7},
                {13,14, 8},
                {20, 25}
        };

        testConflict(events, 3);

        int[][] events2 = {
        };
        testConflict(events2, 0);

        int[][] events3 = {
                {1, 2, 1},
                {3, 5, 2},
        };
        testConflict(events3, 0);

        int[][] events4 = {
                {1, 2, 1},
                {3, 5, 2},
                {4,6, 3},
        };
        testConflict(events4, 1);

        int[][] events5 = {
                {1, 2, 1},
                {3, 20, 2},
                {4,6, 3},
        };
        testConflict(events5, 1);
    }

    private static void testConflict(int[][] events, int expectedNumConflicts) {
        List<List<String>> result = findConflicts(events);

        System.out.printf("result: %s\n", result);

        Assert.assertEquals(result.size(), expectedNumConflicts);
    }

    private static List<List<String>> findConflicts(int[][] cal) {
        if (cal == null || cal.length < 2) {
            return new ArrayList<>();
        }

        // final result
        List<List<String>> result = new ArrayList<>();
        // intermediate result
        List<String> tmp_result = new ArrayList<>();
        // priming the loop
        int prev_endTime = cal[0][1];

        for (int i = 1; i < cal.length; i++) {
            int start_time = cal[i][0];
            if (start_time < prev_endTime) {  // if conflict
                // it takes two events to have conflicts
                // that is why there is this check
                if (tmp_result.isEmpty()) {
                    tmp_result.add(String.valueOf(cal[i-1][2]));
                }
                tmp_result.add(String.valueOf(cal[i][2]));
            } else {  // no conflict
                if (!tmp_result.isEmpty()) {
                    result.add(tmp_result);
                    tmp_result = new ArrayList<>();
                }
            }

            // update prev end time
            prev_endTime = Math.max(cal[i][1], prev_endTime);

        }

        // at any time that you build up the final result with
        // temporary result, make sure to take care of them
        // when at the end of the loop.
        // the example is when the last event has a conflict

        if (!tmp_result.isEmpty()) {
            result.add(tmp_result);
        }

        return result;
    }

}
