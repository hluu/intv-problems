package my.leetcode.easy;

import java.util.Arrays;

/**
 * Created by hluu on 10/1/16.
 *
 * Given a sorted array, remove the duplicates in place such that each
 * element appear only once and return the new length.
 *
 * Do not allocate extra space for another array, you must do this
 * in place with constant memory.
 */
public class RemoveDuplicate {
    public static void main(String[] args) {
        System.out.println("RemoveDuplicate.main");
        //int[] arr = {1,2,3};
        int[] arr = {1,1,1,2};

        System.out.printf("result of %s is %d \n",
                Arrays.toString(arr), removeDuplicates(arr));

        System.out.printf("after removing duplicates %s\n", Arrays.toString(arr));
    }

    /**
     * Using two indexes with one of them ahead of the other (first, second)
     * There are two cases:
     * 1) Values at both indexes are the same: then increment the second index value
     * 2) Values at both indexes are different: then increment both indexes
     *    * One trick to use is to copy value at second index to first index
     * @param nums
     * @return
     */
    private static int removeDuplicates(int[] nums) {

        System.out.printf("Input: %s\n", Arrays.toString(nums));
        int first = 0;
        int second = first + 1;

        if (nums.length < 2) {
            return nums.length;
        }

        int length = nums.length;

        while (second < nums.length && first < second) {
            if (nums[first] != nums[second]) {
                first++;
                nums[first] = nums[second];
                second++;
            } else {
                // same
                second++;
                length--;
            }
        }

        return length;
    }
}
