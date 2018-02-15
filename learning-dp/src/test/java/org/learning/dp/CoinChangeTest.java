package org.learning.dp;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CoinChangeTest {

	@Test()
	public void oneElementTest() {
		int[] denominations = {2};
		Assert.assertEquals(CoinChangeMinCoins.coinChange(1, denominations), -1);
	}

	@Test()
	public void oneElementTest2() {
		int[] denominations = {2};
		Assert.assertEquals(CoinChangeMinCoins.coinChange(2, denominations), 1);
	}

	@Test()
	public void basicTest() {
		int[] denominations = {1,3,4};
		Assert.assertEquals(CoinChangeMinCoins.coinChange(6, denominations), 2);
	}

	@Test()
	public void basicTest2() {
		int[] denominations = {1,3,5};
		Assert.assertEquals(CoinChangeMinCoins.coinChange(9, denominations), 3);
	}
	
	@Test()
	public void largeAmountTest() {
		int[] denominations = {1,3,5};
		Assert.assertEquals(CoinChangeMinCoins.coinChange(50, denominations), 10);
	}
	
	@Test()
	public void zeroAmountTest() {
		int[] denominations = {1,3,5};
		Assert.assertEquals(CoinChangeMinCoins.coinChange(0, denominations), -1);
	}

	@Test(enabled = false)
	public void anotherLargeAmountTest() {
		int[] denominations = {186,419,83,408};
		Assert.assertEquals(CoinChangeMinCoins.coinChange(6249, denominations), 20);
	}
}
