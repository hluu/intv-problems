package org.learning.dp;

import org.testng.Assert;

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

		testCoinChange(7, new int[] {1,3,4}, 2);
		testCoinChange(22, new int[] {1,5,12,25}, 3);
		testCoinChange(15, new int[] {1,3,9,10}, 3);

		testCoinChangeUsingRecursion(7, new int[] {1,3,4}, 2);
		testCoinChangeUsingRecursion(22, new int[] {1,5,12,25}, 3);
		testCoinChangeUsingRecursion(15, new int[] {1,3,9,10}, 3);
	}

	private static int testCoinChangeUsingRecursion(int amount, int[] coins, int expectedNumCoins) {
		System.out.println("\n*** testCoinChangeUsingRecursion: " + Arrays.toString(coins));
		int numCoins = coinChangeRecursion(amount, coins);
		System.out.printf("min coins for amount %d is %d\n", amount,
				numCoins);

		Assert.assertEquals(numCoins, expectedNumCoins);
		return numCoins;
	}

	private static int testCoinChange(int amount, int[] coins, int expectedNumCoins) {
		System.out.println("denominations: " + Arrays.toString(coins));
		int numCoins = coinChange(amount, coins);
		System.out.printf("min coins for amount %d is %d\n", amount,
				numCoins);

		Assert.assertEquals(numCoins, expectedNumCoins);
		return numCoins;
	}


	/**
	 * Determine # of min coin changes using recursion
	 *
	 * Runtime Analysis of this is:
	 * 	* Each level is branched out to maximum of # of coins
	 * 	* Sounds like exponential
	 *
	 * @param amount
	 * @param coins
     * @return
     */
	public static int coinChangeRecursion(int amount, int[] coins) {

		if (amount == 0) {
			return 1;
		}

		if (amount < 1) {
			return 0;
		}
		int minCount = Integer.MAX_VALUE;
		for (int i = 0; i < coins.length; i++) {
			int denomination = coins[i];
			if (amount >= denomination) {
				int numCoin = coinChangeRecursion(amount- denomination, coins);
				if (numCoin < minCount) {
					minCount = numCoin;
				}
			}
		}

		return minCount;
	}

	/**
	 * Using DP with memoization.
	 *
	 * @param amount
	 * @param coins
     * @return
     */
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
