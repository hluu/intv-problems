package my.leetcode.medium;

import org.common.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/course-schedule/
 *
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you
 * have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs,
 * is it possible for you to finish all courses?
 *
 * Notes;
 * The input prerequisites is a graph represented by a list of edges,
 * not adjacency matrices. Read more about how a graph is represented.
 *
 * You may assume that there are no duplicate edges in the input prerequisites.
 *
 * Example:
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 *
 *
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 *
 * General approach:
 *  - This problem is equivalent to finding if a cycle exists in a directed
 *    graph. If a cycle exists, no topological ordering exists and therefore
 *    it will be impossible to take all courses.
 */
public class CourseSchedule {

    public static void main(String[] args) {
        System.out.println(CourseSchedule.class.getName());

        test(2, new int[][] {{1,0}}, true);
        test(3, new int[][] {{1,0}}, true);
        test(2, new int[][] {{1,0}, {0,1}}, false);
    }

    private static void test(int numCourses, int[][] prereqs, boolean expected) {
        System.out.println("=====> test <======");
        ArrayUtils.printMatrix(prereqs);

        boolean actual = new CourseSchedule().canFinish(numCourses, prereqs);

        System.out.printf("expected: %b, actual: %b\n", expected, actual);
    }

    private Map<Integer, List<Integer>> prereqMap = new HashMap<>();
    private Map<Integer, List<Integer>> incomingMap = new HashMap<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        convertToMap(prerequisites);

        /* if (numCourses != prereqMap.size()) {
            return false;
        }*/

        while (!prereqMap.isEmpty()) {
            Integer noPrereqCourse = findNoPrereqCourse();
            // if can't find one while prereqMap is not empty, then we can't
            // satisfy the condition??
            if (noPrereqCourse == null) {
                return false;
            }

            // before remove it from prereqMap
            List<Integer> incomingList = incomingMap.get(noPrereqCourse);
            if (incomingList != null) {
                for (Integer courseNo : incomingList) {
                    List<Integer> prereqList = prereqMap.get(courseNo);
                    if (prereqList != null) {
                        prereqList.remove(noPrereqCourse);
                    }
                }
            }

            prereqMap.remove(noPrereqCourse);

        }

        return true;
    }

    private Integer findNoPrereqCourse() {
        for (Map.Entry<Integer, List<Integer>> entry : prereqMap.entrySet()) {
            if (entry.getValue().isEmpty()) {
                return entry.getKey();
            }
        }

        return null;
    }

    private void convertToMap(int[][] prereqs) {
        for (int[] prereq : prereqs) {
            int courseNo = prereq[0];
            int preReqCourseNo = prereq[1];

            // handle the prereq map
            List<Integer> prereqList = prereqMap.get(courseNo);
            if (prereqList == null) {
                prereqList = new ArrayList<>();
            }
            prereqList.add(preReqCourseNo);
            prereqMap.put(courseNo, prereqList);

            if (!prereqMap.containsKey(preReqCourseNo)) {
                prereqMap.put(preReqCourseNo, new ArrayList<>());
            }

            // handle the incomingMap
            List<Integer> incomingList = incomingMap.get(preReqCourseNo);
            if (incomingList == null) {
                incomingList = new ArrayList<>();
            }

            incomingList.add(courseNo);
            incomingMap.put(preReqCourseNo, incomingList);

        }
    }
}
