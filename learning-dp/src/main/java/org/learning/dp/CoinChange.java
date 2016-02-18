package org.learning.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Problem statement:
 * 	Give change for the amount n using the MINIMUM number of coins
 * 	of given denominations.
 * 
 * 	The assumption is each denomination has unlimited quantities
 *
 * 	We also would like find out what are the denominations that make up the
 * 	optimal solution.
 *
 * Example:
 * 	d = {1,5,12,25}
 * 	n = 16
 * 	Using greedy will yield {12,1,1,1,1}
 *
 * 	Optimal solution {1,5,5,5}
 *
 * Approach:
 * 	Let F(n) be the minimum of coins for the given amount where F(0) = 0
 * 
 * 	For each amount from 1 to n and for each denomination, we need to find the minimum # of coins,
 * 	therefore F(n) = min { F(n-d(j), minSoFar } + 1.
 *
 *  The subproblem of F(n) is F(n-d(j))
 *
 * 	Time complexity is O(nm) => n is amount, m is the # of denominations
 * 	Space complexity is O(n) => n is the amount
 * 
 * 
 * @author hluu
 *
 */
public class CoinChange {

	public static void main(String[] args) {
		//int[] denominations = {1,3,4};
		int[] denominations = {1,5,12,25};
		int amount = 22;
		
		System.out.println("denominations: " + Arrays.toString(denominations));
		System.out.printf("min coins for amount %d is %d\n", amount, 
				coinChange(amount, denominations));
		// i = 5
		// j = 0 
		// minSoFar = 2
		//   0 1 2 3 4 5
		// d[0,1,2,1,2,m]
		
	}
	
	public static int coinChange(int amount, int[] coins) {
		if (amount < coins[0]) {
			return -1;
		}

		int minCoin[] = new int[amount+1];
		int denominations[] = new int[minCoin.length];
		
		for (int amt = 1; amt <= amount; amt++) {
			int minSoFar = Integer.MAX_VALUE;
			for (int j = 0; j < coins.length; j++) {
				// make sure the coinDenomination is less than or equal to amount
				int coinDenomination = coins[j];
				if (amt >= coinDenomination) {
					if (minCoin[amt - coinDenomination] < minSoFar) {
						denominations[amt] = coinDenomination;
					}
					minSoFar = Math.min(minCoin[amt - coinDenomination], minSoFar);
				}
			}
			if (minSoFar == Integer.MAX_VALUE) {
				// this is for the case where amount is less than any denomination
				minCoin[amt] = 0;
			} else {
				minCoin[amt] = minSoFar + 1;
			}
			//System.out.println("amount: " + amt + " minCoin: " + Arrays.toString(minCoin));
		}

		System.out.println(Arrays.toString(denominations));
		int j = denominations.length - 1;
		List<Integer> denominationList = new ArrayList<>();
		while (j > 0) {
			denominationList.add(denominations[j]);
			j = j - denominations[j];
		}

		System.out.println(denominationList.toString());
		return (minCoin[amount] > 0) ? minCoin[amount] : -1;
	}

}
