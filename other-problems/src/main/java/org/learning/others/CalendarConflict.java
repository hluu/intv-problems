package org.learning.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hluu on 11/25/16.
 *
 * Problem:
 *  Give a list of calendar events (start time, end time, event name),
 *  find out the ones that are conflict with each other.
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
 *  Approach:
 *      * Conflict detection - when start time of event event is less than
 *        end time of previous event
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
        };

        List<List<String>> result = findConflicts(events);

        System.out.printf("result: %s\n", result);
    }

    private static List<List<String>> findConflicts(int[][] cal) {
        if (cal == null || cal.length < 2) {
            return new ArrayList<>();
        }

        List<List<String>> result = new ArrayList<>();
        List<String> tmp_result = new ArrayList<>();
        int prev_endTime = cal[0][1];

        for (int i = 1; i < cal.length; i++) {
            int start_time = cal[i][0];
            if (start_time < prev_endTime) {  // if conflict
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
            if (cal[i][1] > prev_endTime) {
                prev_endTime = cal[i][1];
            }

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
