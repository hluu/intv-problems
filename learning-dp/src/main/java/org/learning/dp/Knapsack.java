package org.learning.dp;


import java.util.HashMap;
import java.util.Map;

/**
 * Problem Statement:
 *
 *  Given n items where each item has an associated weight (w) and value (v) and
 *  a knapsack, of capacity W. Find the most valuable subset of the items that
 *  fit into the knapsack.
 * 
 *  All weights and knapsack capacity are positive integers. Item values do not
 *  have to be integers.
 * 
 * Approach:
 *  The brute force approach is solving this problem as a combination problem.
 *  Given a list of items, figure out a combination of items that would give the maximum
 *  value given that their combined weight is less than or equal to sack capacity.
 *
 *  This is a short, simple recursion where for each item, we can include and not include it.
 *  The runtime is exponential -> O(2^n)
 *
 *
 *  This is another DP problem.
 *
 *  What are the inefficiencies in the above solution?  O(2^n)? and any duplicate computations
 *  we can save and reuse? can we figure out the max as we go by each item at a time?
 *
 *  Can we have a table to keep track of the best maximum value for each size of the
 *  sack up the given capacity
 *
 *  The recursive formula is:
 *
 *  F(v,w) = F(v-1,w) if w[i] > w
 *  F(v,w) = max { F(v-1,w), F(v-1, w[i] - v) + v[i] }
 *
 *  1) Final state:  F(W) = most valuable subset of the items
 *
 *  2) Work backward from the final state
 *      F(W) = max { among all the different combination of items that can fit with W }
 *
 * Example:
 * 		w = {2,3,4,5,9}
 * 	    v = {3,4,5,8,10}
 *
 * 	    W = 20
 */
public class Knapsack {

	public static void main(String[] args) {
		int weights1[] = { 2, 2, 3 };
		int values1[] = { 6, 10, 12};
		int capacity1 = 5;

		System.out.println("***** first problem *****");
        test(weights1, values1, capacity1);


		System.out.println("\n***** second problem *****");
		int weights2[] = {2,3,4,5,9};
		int values2[] = {3,4,5,8,10};
		int capacity2 = 20;

		//test(weights2, values2, capacity2);
	}

	private static void test(int[] weights, int[] values, int capacity) {
		System.out.println("\n ====== start testing ========");
		System.out.println("bruteForce solution: "
				+ bruteForceKnapsack(weights, values, 0, capacity, 0));

        System.out.println("Top-down DP solution: "
                + dpTopdown(weights, values, 0, capacity, new HashMap<>()));

		System.out.println("Bottom-up DP solution: "
				+ dpBottomup(weights, values, capacity));
	}

	/**
	 * Brute force approach by going computing the maximum for each combination.
	 * The combination is generated two choices for each item:
	 *   1) To include it
	 *   2) To not include it
	 *
	 * Runtime: O(2^len(items))
	 *
	 * @return Maximum value
	 */
	private static int bruteForceKnapsack(int[]weights, int[] values,
										  int itemIdx, int remainingCapacity,
										  int valueSoFar) {

	    // reach the end of the list, no more items to try
        // this our base case
		if (itemIdx >= weights.length) {
			return valueSoFar;
		}

		System.out.printf("itemIdx: %d, (remainingCapacity,valueSoFar): (%d, %d)\n",
                itemIdx, remainingCapacity, valueSoFar);


		// only if weight of current item can fit in remaining capacity
		// trying out all possible combinations
		if (weights[itemIdx] <= remainingCapacity) {

			// include the item
			int valueForIncluding = bruteForceKnapsack(weights, values,
					itemIdx + 1, remainingCapacity - weights[itemIdx],
					valueSoFar + values[itemIdx]);

			// not including the item
			int valueForNoIncluding = bruteForceKnapsack(weights, values,
					itemIdx + 1, remainingCapacity,  valueSoFar);

			return Math.max(valueForIncluding, valueForNoIncluding);
		} else {
			// make sure to continue down with other items that may have smaller weight
			return bruteForceKnapsack(weights, values,
					itemIdx + 1, remainingCapacity,  valueSoFar);
		}

	}


    /**
     * Top down DP approach
     *
     * @param weights
     * @param values
     * @param idx
     * @param remainWeight
     * @param cache - map of item index, to weight to max value
     * @return
     */
	private static int dpTopdown(int[] weights, int[] values, int idx,
                                 int remainWeight, Map<Integer, Map<Integer,Integer>> cache) {

	    if (idx >= weights.length) {
	        // got the end of the line
	        return 0;
        }

        // check if value is in the cache
        if (!cache.containsKey(idx)) {
	        cache.put(idx, new HashMap<>());
        }

        Integer cachedValue = cache.get(idx).get(remainWeight);

	    if (cachedValue != null) {
	        return cachedValue;
        }

        int result = 0;

	    if (remainWeight - weights[idx] < 0) {
	        // no more room, move to next item
            result = dpTopdown(weights, values, idx+1, remainWeight, cache);
        } else {
	        int maxValueWithItem = dpTopdown(weights, values, idx+1,
                    remainWeight- weights[idx], cache) + values[idx];

	        int maxValueWithoutItem =
                    dpTopdown(weights, values, idx+1, remainWeight, cache);;
	        // max of
	        result = Math.max(maxValueWithItem, maxValueWithoutItem);
        }


        cache.get(idx).put(remainWeight, result);

        return result;
    }

	/**
	 * This is the bottom-up DP approach
     *
     * Go through each of the capacity from 1 to knapsackCapacity.
	 *
     * Runtime O(items * capacity)
     *
	 * @param weights
	 * @param values
	 * @param bagCapacity
	 * @return
	 */
	public static int dpBottomup(int[] weights, int[] values, int bagCapacity) {
		// # of rows is the # of items + 1
		// # of columns is the range of capacity from 0 to bag capacity + 1
		int[][] table = new int[values.length + 1][bagCapacity + 1];

		// for each of the items
		for (int item = 1; item <= values.length; item++) {
			int itemWeight = weights[item-1];
			int itemValue = values[item-1];

			// for each of the capacities up the bag capacity
			for (int currCapacity = 1; currCapacity <= bagCapacity; currCapacity++) {
				// only if the weight of the current item can fit
				if (itemWeight <= currCapacity) {
					// the max value so far at currCapacity-itemWeight + itemValue
					int potentialNewValue = itemValue + table[item-1][currCapacity-itemWeight];

					if (potentialNewValue > table[item-1][currCapacity]) {
						table[item][currCapacity] = potentialNewValue;
					} else {
						// previous max from item above
						table[item][currCapacity] = table[item-1][currCapacity];
					}

				} else {
					table[item][currCapacity] = table[item-1][currCapacity];
				}
				//printMatrix(table);
			}
		}

		return table[values.length][bagCapacity];
	}

}
