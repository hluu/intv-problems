package org.learning.dp;

import java.util.Arrays;

/**
 * In this problem, we have several houses placed on a street. We'd like to
 * paint each house either red, green, or blue. The amount of money it costs to
 * paint a specific house a specific color varies (maybe the owner already has
 * some old paint that he can use, or parts of his house are already painted
 * that color). A house cannot be painted the same color as one of its neighbors
 * The goal is to paint all of the houses for the minimum total cost.
 * 
 * Example:
 *      1   2   3   4   5
 *      =================
 * R 	2	2	6	4	2
 * G	  	0	5	7	1	1
 * B		1	1	2	0	4
 * 
 * Approach:
 *  Base and build
 *  What if there is only 1 house 
 *    {  2
 *       0
 *       1
 *        }
 *    Then the solution is just the min of the cost
 *    
 *  What if there are only 2 houses - 
 *     2  2  => 2+1  (R+B) = 3
 *     0  5  => 0+1  (G+B) = 1
 *     1  1  => 1+1  (B+R) = 2
 *     
 *     
 *  High level approach:
 *  1) To figure out the minimum cost 
 *  2) Sum them up and provide final sum
 * 
 * @author hluu
 *
 */
public class HouseRGBColoring {

	public static void main(String[] args) {
		int[][] costs = {
				{2,2,6,4,2},
				{0,5,7,1,1},
				{1,1,2,0,4}
		};
		
		for (int i = 0; i < costs.length; i++) {
			System.out.println(Arrays.toString(costs[i]));
		}
		
		System.out.println("min cost: " + paintHouses(costs));
	}
	
	/**
	 * 
	 * @param costs
	 * @return
	 */
	private static int paintHouses(int[][] costs) {
		int numRow = costs.length;
		int numCol = costs[0].length;
		
		int[] minSoFar = new int[numRow];
		
		// init with initial costs for first house
		for (int i = 0; i < numRow; i++) {
			minSoFar[i] = costs[i][0];
		}
		
		System.out.println("minSoFar: " + Arrays.toString(minSoFar));
		
		for (int i = 1; i < numCol; i++) {
			
			for (int j = 0; j < numRow; j++) {
				int minValue = Integer.MAX_VALUE;
				for (int k = 0; k < numRow; k++) {
					if (j != k) {
						if (minValue > costs[j][i]) {
							minValue = costs[j][i];
						}
					}
					minSoFar[k] = minSoFar[k-1] + minValue;
				}
			}
			 
		}
		
		int min = Integer.MAX_VALUE;
		for (int i = 0; i <minSoFar.length; i++) {
			min = (min > minSoFar[i] ? minSoFar[i] : min);
		}
		return min;
	}

}
