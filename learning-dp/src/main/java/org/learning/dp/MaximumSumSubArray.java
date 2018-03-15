package org.learning.dp;

import java.util.Arrays;

import org.common.Tuple;
import org.testng.Assert;

/**
 * Given an array of integers, find the interval indices a and b such that the sum is maximized.
 * 
 * For example:
 *  {904, 40, 523, 12, -335, -385, -124, 481, -31 }
 *  
 * Analysis:
 *   Brute force approach - to compute each subarray sum, which requires O(n^3) time.
 *     * This can be reduce down to O(n^2) by first computing sums for subarrays A[0:i]
 *   
 *   Another approach is to use divide and conquer.
 *     * Figure out the maximum sub array on the left and right
 *     * Also need to figure the maximum sub array that ends at the last entry in L
 *       and start at 0 for R
 *      
 *   Using dynamic programming approach:
 *     * Initial thought is to use A[0:n-2], however knowing largest sum subarray for A[0:n-2]
 *       doesn't help for A[0:n-1]
 *     * Second thought is to iterate from beginning to end and do some book keep along the way      
 *      
 * @author hluu
 *
 */
public class MaximumSumSubArray {
	public static void main(String[] args) {
		//int arr[] = {904, 40, 523, 12, -335, -385, -124, 481, -31 };
		
		int arr[] = {10, 35, -50, 12, 40, 30, -31 };

		test(arr, 82);
	}

	private static void test(int[] nums, int expectedMaxSum) {
		System.out.printf("nums: %s, expected max sum: %d\n",
				Arrays.toString(nums), expectedMaxSum);

		Tuple<Integer,Integer> range = findMaxSubarray(nums);

		int actualSum = 0;
		for (int i = range.first; i <= range.second; i++) {
			actualSum += nums[i];
		}

		System.out.printf("actual sum: %d\n", actualSum);

		Assert.assertEquals(actualSum, expectedMaxSum);
	}
	
	public static Tuple<Integer,Integer> findMaxSubarray(int[] arr) {
		Tuple<Integer,Integer> result = new Tuple<Integer,Integer>(0,0);
		int min_idx = -1;
		int min_sum = 0;
		int sum = 0;
		int max_sum = 0;
		
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			
			if (sum < min_sum) {
				min_sum = sum;
				min_idx = i;
			}
			if (sum - min_sum > max_sum) {
				max_sum = sum - min_sum;
				result.first = min_idx + 1;
				result.second = i;
			}

			System.out.printf("sum: %d, min_sum: %d, max_sum: %d\n", sum, min_sum, max_sum);

		}
		
		return result;
	}
	
}
