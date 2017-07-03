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
 * G	0	5	7	1	1
 * B	1	1	2	0	4
 * 
 * Approach:
 *  Base and build technique
 *
 *  What if there is only 1 house 
 *    {
 *      2
 *      0
 *      1
 *    }
 *  Then the solution is just the min of the cost
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
		// # of rows represent # of houses
		// # of columns represent # of colors
		int[][] costs = {
				{2, 0, 1},
				{2, 5, 1},
				{6, 7, 2},
				{4, 1, 0},
				{2, 1, 4}
		};

		int[][] costs2 = {
				{1000, 1000, 1},
				{3, 2, 1},
				{100, 1, 100}

				/*{1000,3,1000},
				{1000,2,1},
				{1,   1,1000}
				*/
		};

		test(costs);

		test(costs2);
	}

	private static void test(int[][] costs) {
		System.out.println("****** test ******");
		for (int i = 0; i < costs.length; i++) {
			System.out.println(Arrays.toString(costs[i]));
		}

		System.out.println();
		System.out.println("greedy min cost: " + greedyPaintHouse(costs));
		System.out.println("dp min cost : " + dpPaintHouse(costs));

		System.out.println();
	}


	/**
	 * DP Approach:
	 *  0) For each house, we need to evaluate two colors that are not used by adjacent neighbor.
	 *
	 *
	 *  1) Final state F(n) be the minimum cost of painting all the houses
	 *     F(n) = min { f{n,r}, f(n,g), f(n,b) }
	 *
	 *  2) Work backward from final state:
	 *     f(n,r) = min { f(n-1,g), f(n-1,b) } + cost(n,r)
	 *     f(n,b) = min { f(n-1,r), f(n-1,g) } + cost(n,b)
	 *     f(n,g) = min { f(n-1,r), f(n-1,b) } + cost(n,g)
	 *
	 *     f(0,r) = 0 + cost(0,r)
	 *     f(0,g) = 0 + cost(0,g)
	 *     f(0,b) = 0 + cost(0,b)
	 *
	 *
	 *
	 * @param costs
	 * @return
	 */
	private static int dpPaintHouse(int[][] costs) {
		int[][] housePaintingCost = new int[costs.length][costs[0].length];

		for (int color = 0; color < costs[0].length; color++) {
			housePaintingCost[0][color] = costs[0][color];
		}

		int numHouses = costs.length;
		int numColors = costs[0].length;

		// for each of the houses
		for (int house = 1; house < numHouses; house++) {

			for (int color = 0; color < numColors; color++) {
				int minCostSoFar = Integer.MAX_VALUE;
				for (int colorPrevHouse = 0; colorPrevHouse < numColors; colorPrevHouse++) {
					if (color != colorPrevHouse) {
						int cost = costs[house][color];
						int tmpCost = cost + housePaintingCost[house-1][colorPrevHouse];
						if (tmpCost < minCostSoFar) {
							minCostSoFar = tmpCost;
						}
					}
				}
				housePaintingCost[house][color] = minCostSoFar;
			}
		}

		int minCost = Integer.MAX_VALUE;
		int lastRow = housePaintingCost.length - 1;
		for (int i = 0; i < housePaintingCost[0].length; i++) {
			if (housePaintingCost[lastRow][i] < minCost) {
				minCost = housePaintingCost[lastRow][i];
			}
		}
		return minCost;
	}

	/**
	 * Greedy approach
	 *
	 *  For each house
	 *    For each color
	 *      * if color is not the same as prev. neighbor and smaller than prev. cost
	 *        * update the cost so far and color so far
	 *    * Add cost to total cost
	 *
	 *  return total cost.
	 *
	 *  The brute force approach uses a greedy approach, where it makes a determination
	 *  of lowest cost based solely on previous house.  It doesn't go back and update
	 *  the min. cost when additional information is available to indicate otherwise.
	 *
	 *  GREEDY APPROACH DOESN'T GIVE CORRECT SOLUTION
	 *
	 *
	 *
	 * @param costs
	 * @return
	 */
	private static int greedyPaintHouse(int[][] costs) {
		// costs - where # of rows are houses, # of columns are colors

		int totalCost = 0;
		int prevNeighborColor = -1;
		for (int house = 0; house < costs.length; house++) {
			int minCostSoFar = Integer.MAX_VALUE;
			int colorForMinCostSoFar = -1;
			for (int color = 0; color < costs[0].length; color ++) {
				if (prevNeighborColor != color && costs[house][color] < minCostSoFar) {
					minCostSoFar = costs[house][color];
					colorForMinCostSoFar = color;
				}
			}
			totalCost += minCostSoFar;
			prevNeighborColor = colorForMinCostSoFar;
		}
		return totalCost;
	}

	/**
	 * 
	 * @param costs
	 * @return
	 */
	private static int paintHouses(int[][] costs) {
		int numColors = costs[0].length;
		int numHouses = costs.length;
		
		int[] minSoFar = new int[numColors];
		
		// init with initial costs for first house
		for (int i = 0; i < numColors; i++) {
			minSoFar[i] = costs[i][0];
		}
		
		System.out.println("minSoFar: " + Arrays.toString(minSoFar));
		
		for (int i = 1; i < numHouses; i++) {
			
			for (int j = 0; j < numColors; j++) {
				int minValue = Integer.MAX_VALUE;
				for (int k = 0; k < numColors; k++) {
					if (j != k) {
						if (minValue > costs[j][i]) {
							minValue = costs[j][i];
						}
					}
					minSoFar[k] = minSoFar[k] + minValue;
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
