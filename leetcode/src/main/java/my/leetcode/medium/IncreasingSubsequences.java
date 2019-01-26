package my.leetcode.medium;

import java.util.*;

/**
 * Given an integer array, your task is to find all the different possible
 * increasing subsequences of the given array,
 * and the length of an increasing subsequence should be at least 2
 *
 * Input: [4, 6, 7, 7]
 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 *
 */
public class IncreasingSubsequences {

    public static void main(String[] args) {
        System.out.println(IncreasingSubsequences.class.getName());

        int[] input = {4,6,7,7};
        //int[] input = {4,6,7};

        test(input);

        int[] input2 = {1, 1, 9, 3, 6};
        test(input2);
    }

    private static void test(int[] input) {
        System.out.println("*** input: " + Arrays.toString(input));
        List<List<Integer>> output = findSubsequences(input);

        for (List<Integer> list : output) {
            System.out.println(list);
        }
    }

    /**
     * Brute force method. Runtime O(n^3)
     * @param nums
     * @return
     */
    private static List<List<Integer>> findSubsequences(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        Set<List<Integer>> collector = new HashSet<>();

        // for each elm idx
        for (int i = 0; i < nums.length-1; i++) {
            // go from i+1 to the end and build an increasing subsequence
            // from nums[i]
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] <= nums[j]) {
                    List<Integer> subSeq = new ArrayList<>();
                    subSeq.add(nums[i]);
                    subSeq.add(nums[j]);
                    collector.add(new ArrayList<>(subSeq));
                    findSubsequencesHelper(nums, subSeq, j + 1, collector);
                }

            }
        }
        return new ArrayList<List<Integer>>(collector);
    }

    /**
     * Going from idx to the end, see if nums[idx] is greater than the last
     * element in the numlist.  If so build a new subsequence
     *
     * @param nums
     * @param numList
     * @param idx
     * @param collector
     */
    private static void findSubsequencesHelper(int[] nums, List<Integer> numList,
                                               int idx,
                                               Set<List<Integer>> collector) {

        if (idx == nums.length) {
            return;
        }

        if (nums[idx] >= numList.get(numList.size()-1)) {
            List<Integer> newList = new ArrayList<>(numList);
            newList.add(nums[idx]);
            collector.add(new ArrayList<>(newList));

            findSubsequencesHelper(nums, numList, idx+1, collector);
            findSubsequencesHelper(nums, newList, idx+1, collector);
        } else {
            findSubsequencesHelper(nums, numList, idx+1, collector);
        }
    }

}
