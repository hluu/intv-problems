package org.learning.dp;

import static org.common.ArrayUtils.*;

/**
 * Given n items where each item has an associated weight (w) and value (v) and
 * a knapsack, of capacity W. Find the most valuable subset of the items that
 * fit into the knapsack.
 * 
 * All weights and knapsack capacity are positive integers. Item values do not
 * have to be integers.
 * 
 * @author hluu
 *
 */
public class Knapsack {

	public static void main(String[] args) {
		int weights[] = { 2, 1, 3, 2 };
		int values[] = { 12, 10, 20, 15 };

		// int weights[] = {2,3,1,2};
		// int values[] = {15,20,10,12};

		int knapsackCapacity = 5;

		System.out.println("optimal value is: "
				+ knapsack(weights, values, knapsackCapacity));
	}

	/**
	 * Go through each of the capacity from 1 to knapsackCapacity
	 * 
	 * @param weights
	 * @param values
	 * @param knapsackCapacity
	 * @return
	 */
	public static int knapsack(int[] weights, int[] values, int knapsackCapacity) {
		int[][] table = new int[values.length + 1][knapsackCapacity + 1];

		// for each of the capacities
		for (int i = 1; i <= values.length; i++) {
			for (int j = 1; j <= knapsackCapacity; j++) {

				int optimalValue = 0;
				int itemWeight = weights[i - 1];
				// only update the table when the item weight can fit into the
				// current
				// capacity represented by j
				if (itemWeight <= j) {
					int candidateOptimalValue = table[i - 1][j - itemWeight]
							+ values[i - 1];
					optimalValue = Math.max(candidateOptimalValue,
							table[i - 1][j]);
				} else {
					// item weight is bigger the current capacity
					optimalValue = table[i - 1][j];
				}
				table[i][j] = optimalValue;
				printMatrix(table);
			}

		}

		return table[values.length][knapsackCapacity];
	}

}
