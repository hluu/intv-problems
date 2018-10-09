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
 * 	For each amount from 1 to n and for each denomination,
 * 	we need to find the minimum # of coins,
 *
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
public class CoinChangeMinCoins {

	public static void main(String[] args) {

	    TestObject[] testObjects = {
               // TestObject.createTestObj(3, new int[] {2}, -1),
				// TestObject.createTestObj(6249, new int[] {186,419,83,408}, 20),
              //  TestObject.createTestObj(7, new int[] {1,3,4}, 2),
              //  TestObject.createTestObj(22, new int[] {1,5,12,25}, 3),
              //  TestObject.createTestObj(15, new int[] {1,3,9,10}, 3)
              //TestObject.createTestObj(12, new int[] {1,6,10}, 2),
              //TestObject.createTestObj(17, new int[] {1,6,10}, 3),
              TestObject.createTestObj(3, new int[] {2,6,10}, -1)
        };

        System.out.println("********* brute force *********");
        for (TestObject to : testObjects) {
           testBruteForce(to.amount, to.coins, to.expectedNumCoins);
        }


/*
        System.out.println("\n********* Top down DP *********");
        for (TestObject to : testObjects) {
            testTopDownDP(to.amount, to.coins, to.expectedNumCoins);
        }


        System.out.println("\n********* Bottom up DP *********");
        for (TestObject to : testObjects) {
            testBottomUpDP(to.amount, to.coins, to.expectedNumCoins);
        }
*/
	}

	private static class TestObject {
	    int amount;
        int[] coins;
	    int expectedNumCoins;

	    public TestObject(int amount, int[] coins, int expectedNumCoins) {
	        this.amount =amount;
	        this.coins = coins;
	        this.expectedNumCoins = expectedNumCoins;
        }

        public static TestObject createTestObj(int amount, int[] coins, int expectedNumCoins) {
	        return new TestObject(amount, coins,  expectedNumCoins);
        }
    }

	private static int testBruteForce(int amount, int[] coins, int expectedNumCoins) {
		System.out.println("\n===> testBruteForce: <===\n");
		System.out.printf("amount: %d, coins: %s\n", amount, Arrays.toString(coins));
		int numCoins = coinChangeBruteForce(amount, coins);
		System.out.printf("min coins is %d, expected: %d\n", numCoins, expectedNumCoins);

		Assert.assertEquals(numCoins, expectedNumCoins);
		return numCoins;
	}

    private static int testTopDownDP(int amount, int[] coins, int expectedNumCoins) {
        System.out.println("\n===> testTopDownDP: <===\n");
        System.out.printf("amount: %d, coins: %s\n", amount, Arrays.toString(coins));

        // setup the cache
        int[] cache = new int[amount+1];
        Arrays.fill(cache, -1);
        coinChangeTopDownDP(amount, coins, cache);

        int numCoins = cache[amount];
        System.out.printf("min coins is %d, expected: %d\n", numCoins, expectedNumCoins);

        Assert.assertEquals(numCoins, expectedNumCoins);
        return numCoins;
    }

	private static int testBottomUpDP(int amount, int[] coins, int expectedNumCoins) {
        System.out.println("\n===> bottom up approach <===");

		System.out.println("denominations: " + Arrays.toString(coins));
		int numCoins = coinChangeBottomUP(amount, coins);
		System.out.printf("min coins is %d, expected: %d\n", numCoins, expectedNumCoins);

		Assert.assertEquals(numCoins, expectedNumCoins);
		return numCoins;
	}


	/**
	 * Determine # of min coin changes using recursion.
     *
     * There is definitely an overlapping sub-problem, where calculating the
     * same change amount multiple times
	 *
	 * Runtime Analysis of this is:
	 * 	* Each level is branched out to maximum of # of coins
	 * 	* Sounds like exponential
     * 	* O(amount ^ denominations)
	 *
	 * @param amount
	 * @param coins
     * @return
     */
	public static int coinChangeBruteForce(int amount, int[] coins) {

		if (amount <= 0) {
		    // since there is a check on to ensure the amount is greater than
            // the denomination, therefore amount will always be 0 or greater
			return 0;
		}

		int minCount = Integer.MAX_VALUE;
		for (int coin : coins) {
			if (amount >= coin) {
				int numCoin = coinChangeBruteForce(amount- coin, coins);
				minCount = Math.min(minCount, numCoin);
			}
		}

		if (minCount == Integer.MAX_VALUE || minCount == -1) {
		    return -1;
        } else {
            return minCount + 1;
        }
	}

    /**
     * This solution is built on top of the coinChangeBruteForce solution.
     * By recognizing the overlapping subproblem, so we cache the minCoin for
     * the change amount, then there is no need to recompute it.
     *
     * With that info., we add a checking to see if the minCoin for an amount
     * exists, if so return it. If not, go a figure out the minCoin, then
     * store it in the cache.
     *
     * @param amount
     * @param coins
     * @param cache
     * @return
     */
	public static int coinChangeTopDownDP(int amount, int[] coins, int[] cache) {
	    if (amount == 0) {
	        return 0;
        }

        if (cache[amount] >= 0) {
	        return cache[amount];
        }

        System.out.println("amount:" + amount);
        int minCoin = Integer.MAX_VALUE;
        for (int coin : coins) {
	        if (amount >= coin) {
	            minCoin = Math.min(minCoin,
                        coinChangeTopDownDP(amount-coin, coins, cache));
            }
        }

        /*if (minCoin == Integer.MAX_VALUE || minCoin == -1) {
            cache[amount] = -1;
        } else {
            cache[amount] = minCoin + 1;
        }*/

        cache[amount] = minCoin + 1;

        return cache[amount];
    }

	/**
	 * Bottom-up approach with DP with memoization.
     *
     * Calculate the minCoin for change amount from 1 to the specified amount.
     *
	 *
	 * @param amount
	 * @param coins
     * @return
     */
	public static int coinChangeBottomUP(int amount, int[] coins) {
        if (amount == 0) {
            return 0;
        }

		if (amount < coins[0]) {
			return -1;
		}

		int minCoin[] = new int[amount+1];
		int denominations[] = new int[minCoin.length];
		
		for (int amt = 1; amt <= amount; amt++) {
			int minSoFar = Integer.MAX_VALUE;
			for (int coinDenomination : coins) {
				// make sure the coinDenomination is less than or equal to amount
				if (amt >= coinDenomination) {
					if (minCoin[amt - coinDenomination] < minSoFar) {
						denominations[amt] = coinDenomination;
					}
					minSoFar = Math.min(minCoin[amt - coinDenomination]+1, minSoFar);
				}
			}
			if (minSoFar == Integer.MAX_VALUE || minSoFar == -1) {
				// this is for the case where amount is less than any denomination
				minCoin[amt] = -1;
			} else {
				minCoin[amt] = minSoFar;
			}
		}

		System.out.println(Arrays.toString(denominations));
		int j = denominations.length - 1;
		List<Integer> denominationList = new ArrayList<>();
		/*while (j > 0) {
			denominationList.add(denominations[j]);
			j = j - denominations[j];
		}*/

		System.out.println(denominationList.toString());
		return (minCoin[amount] > 0) ? minCoin[amount] : -1;
	}

}
