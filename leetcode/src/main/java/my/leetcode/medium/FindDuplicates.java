package my.leetcode.medium;

import org.testng.Assert;

import java.util.*;

/**
 * Created by hluu on 11/30/16.
 *
 * Problem:
 *  Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array),
 *  some elements appear twice and others appear
 *
 *  Find all the elements that appear twice in this array.
 *
 *  Could you do it without extra space and in O(n) runtime?
 *
 *  Example:
 *      Input: {4,3,2,7,8,2,3,1}
 *      output: {2,3}
 *
 * Approach:
 *  * Brute force - sort the array using quick-sort and then walk from
 *    beginning to end. Runtime O(nlogn).  This sorting is useful for looking up
 *    elements that are close to each other, it doesn't take advantage of the
 *    sorted nature of elements, SO NOT A GOOD USE OF SORTING
 *  * HashMap - iterate through array and build up the array, as we discover
 *              a number already exists in the array then that is a duplcate
 *              O(n) runtime, O(n) space
 *
 *  * Optimize - O(n) no extra space
 *    * Meaning either re-using the give array or constant memory
 *    * HINT - elements in the array will have only values are that are
 *             between i and len(array).  So use element value as the index of the array
 *    * So perform sorting in place by swapping, and when see a duplicate value, then
 *      track them
 *
 */
public class FindDuplicates {
    public static void main(String[] args) {
        System.out.println("FindDuplicates.main");
        int[] inputs = {4,3,2,7,8,2,3,1};
        int[] expectedOutputs = {2,3};

        testFindDups(new int[] {3,1,1}, new int[] {1});
        testFindDups(new int[] {3,3,1}, new int[] {3});
        testFindDups(new int[] {2,3,2}, new int[] {2});

        testFindDups(inputs, expectedOutputs);


    }

    private static void testFindDups(int[] inputs, int[] expectedOutput) {
        System.out.printf("******** test finding dups\n");
        System.out.printf("input: %s\n", Arrays.toString(inputs));
        System.out.printf("expected output: %s\n", Arrays.toString(expectedOutput));

        List<Integer> actual = findDuplicates(inputs);
        System.out.printf("actual output: %s\n", actual);

        //ArrayAssert.assertEquals(actual.toArray(), expectedOutput);

        Assert.assertEquals(actual.toArray(), expectedOutput);


    }

    private static List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        SortedSet<Integer> ss = new TreeSet<>();

        int fromIndex = 0;
        int toIndex = 0;
        int fromValue = 0;
        int toValue = 0;

        for (int i = 0; i < nums.length; i++) {
            fromIndex = i;


            while (true) {
                toIndex = nums[fromIndex] - 1;

                fromValue = nums[fromIndex];

                if (fromValue == 0) {
                    break;
                }
                toValue = nums[toIndex];

                // three cases [1,3,3]
                // 1) (fromIndex + 1) == fromValue, then break
                // 2) (fromValue == toValue), then add to result, break;
                // 3) (fromValue != toValue), swap, and update fromValue;

                if (fromIndex + 1 == fromValue) {
                    break;
                }

                if (fromValue == toValue) {
                    ss.add(fromValue);
                    nums[fromIndex] = 0;

                    break;
                }

                nums[toIndex] = fromValue;
                nums[fromIndex] = toValue;


            }
        }

        for (Integer i : ss) {
            result.add(i);
        }
        return result;
    }


    private static List<Integer> toIntList(int[] input) {
        List<Integer> result = new ArrayList<>(input.length);
        for (int v : input) {
            result.add(v);
        }

        return result;
    }
    private static int[] toIntArray(List<Integer> list ) {
        int result[] = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i).intValue();
        }
        return result;
    }



}
