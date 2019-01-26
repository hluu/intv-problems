package org.learning.others;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Given a nested list of integers, returns the sum of all integers in the list
 * weighted by their reversed depth.
 *
 * For example,
 *  given the list {{1,1},2,{1,1}} the deepest level is 2.
 *  Thus the function should return 8 (four 1's with weight 1, one 2 with weight 2)
 *
 *  Given the list {1,{4,{6}}} the function should return 17
 *  (one 1 with weight 3, one 4 with weight 2, and one 6 with weight 1)
 *
 * Approach:
 *  There are several approaches to this problem.
 *
 *  1) One brute force way is to first traverse the list and record the depth, then
 *     traverse the list to compute the final result
 *  2) The other approach is to record the sum at each each and maintain those sums in
 *     a list or map (level, sum) and go over that list of sum to compute the final sum
 *  3) Both above approaches use two passes.  Is there a way to solve this in one pass?
 *     * It is easy to do one path if the problem is just depth sum because we know the
 *       weight we go traverse the list
 *     * Let's take a concrete example:
 *       {3, {4, {5}}}  => regular depth sum is 3*1 + 4*2 + 5*3
 *     * What we need is 3*3 + 4*2 + 5*1
 *       3*3 + 4*2 + 5*1 = ?????? - 3*1 + 4*2 + 5*3
 *       3x + 2y + 1z =  4(x + y + z) - (x + 2y + 3z)
 *
 *
 */
public class ReverseDepthSum {
    public static void main(String[] args) {
        List<NestedInteger> intList1 = createNestedInteger1();
        List<NestedInteger> intList2 = createNestedInteger2();


        testDepthSum(intList1, 27);
        testDepthSum(intList2, 19);

        testReverseDepthSum(intList1, 17);
        testReverseDepthSum(intList2, 9);
    }

    private static void testReverseDepthSum(List<NestedInteger> niList, int expectedValue) {
        System.out.printf("\n========= testReverseDepthSum =========\n");
        printNestedInteger(niList);

        int actualSum = reverseDepthSum(niList);
        System.out.printf("actual: %d, expected: %d ", actualSum, expectedValue);
        Assert.assertEquals(actualSum, expectedValue);
    }

    private static int reverseDepthSum(List<NestedInteger> niList) {
       // implement 3x + 2y + z = 4(x + y + z) - (x + 2y + 3z)

        if (niList == null || niList.isEmpty()) {
            return 0;
        }

        // first element is for the depth, second element is for the sum
        int[] valueHolder = new int[2];
        valueHolder[0] = 1;

        int sum = reverseDepthSumHelper(niList, valueHolder, 1);

        int depth = valueHolder[0];
        int elmSum = valueHolder[1];

        System.out.printf("depth: %d, elmSum: %d, depthSum: %d\n", depth, elmSum, sum);
        int result = (depth+1)*elmSum - sum;

        return result;
    }

    private static int reverseDepthSumHelper(List<NestedInteger> niList, int[] valueHolder,
                                              int level) {
        int sum = 0;
        for (NestedInteger ni : niList) {
            if (ni.isInteger()) {
                valueHolder[1] += ni.getInteger();
                sum += level * ni.getInteger();
            } else {
                valueHolder[0]++; // increment the depth
                sum += reverseDepthSumHelper(ni.getList(), valueHolder, level+1);
            }
        }
        return sum;
    }


    /***
     * A simple recursion by incrementing the level as a new list is encountered
     *
     * @param niList
     * @return
     */
    private static int depthSum(List<NestedInteger> niList) {
        int sum = 0;

        if (niList == null || niList.isEmpty()) {
            return sum;
        } else {
            sum = depthSumHelper(niList, 1);
        }
        return  sum;
    }

    private static int depthSumHelper(List<NestedInteger> niList, int level) {
        int sum = 0;
        for (NestedInteger ni : niList) {
            if (ni.isInteger()) {
                sum += ni.getInteger() * level;
            } else {
                sum += depthSumHelper(ni.getList(), level+1);
            }
        }
        return  sum;
    }

    private static void testDepthSum(List<NestedInteger> niList, int expectedValue) {
        System.out.printf("\n========= testDepthSum =========\n");
        printNestedInteger(niList);

        int actualSum = depthSum(niList);
        System.out.printf("actual: %d, expected: %d ", actualSum, expectedValue);
        Assert.assertEquals(actualSum, expectedValue);
    }


    private static List<NestedInteger> createNestedInteger1() {
        NestIntegerImpl ni1 = new NestIntegerImpl(1);

        List<NestedInteger> list1 = new ArrayList<>();
        list1.add(new NestIntegerImpl(4));

        List<NestedInteger> list2 = new ArrayList<>();
        list2.add(new NestIntegerImpl(6));

        list1.add(new NestIntegerImpl(list2));

        NestIntegerImpl ni2 = new NestIntegerImpl(list1);

        List<NestedInteger> masterList = new ArrayList<>();
        masterList.add(ni1);
        masterList.add(ni2);

        return masterList;
    }

    private static List<NestedInteger> createNestedInteger2() {
        NestIntegerImpl ni1 = new NestIntegerImpl(1);

        List<NestedInteger> list1 = new ArrayList<>();

        List<NestedInteger> list2 = new ArrayList<>();
        list2.add(new NestIntegerImpl(6));

        list1.add(new NestIntegerImpl(list2));

        NestIntegerImpl ni2 = new NestIntegerImpl(list1);

        List<NestedInteger> masterList = new ArrayList<>();
        masterList.add(ni1);
        masterList.add(ni2);

        return masterList;
    }

    private static void printNestedInteger(List<NestedInteger> niList) {
        for (NestedInteger ni : niList) {
           printNestedIntegerHelper(ni);
        }
        System.out.println();
    }

    private static void printNestedIntegerHelper(NestedInteger ni) {
        if (ni.isInteger()) {
            System.out.print(ni.getInteger());
        } else {
            System.out.println();
            for (NestedInteger elm : ni.getList()) {
                printNestedIntegerHelper(elm);
            }
        }
    }



    public interface NestedInteger
    {
        boolean isInteger();
        Integer getInteger();
        List<NestedInteger> getList();
    }

    public static class NestIntegerImpl implements NestedInteger {
        private Integer value;
        private List<NestedInteger> valueList;
        public NestIntegerImpl(Integer value) {
            this.value = value;
        }

        public NestIntegerImpl(List<NestedInteger> value) {
            this.valueList = value;
        }


        public boolean isInteger() {
            return (value != null);
        }

        public Integer getInteger() {
            return value;
        }


        public List<NestedInteger> getList() {
            return  valueList;
        }
    }

}
