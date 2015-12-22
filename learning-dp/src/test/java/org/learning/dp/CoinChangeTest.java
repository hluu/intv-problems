package org.learning.dp;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CoinChangeTest {
	
	@Test()
	public void basicTest() {
		int[] denominations = {1,3,5};
		Assert.assertEquals(CoinChange.coinChange(9, denominations), 3);
	}
	
	@Test()
	public void largeAmountTest() {
		int[] denominations = {1,3,5};
		Assert.assertEquals(CoinChange.coinChange(50, denominations), 10);
	}
	
	@Test()
	public void zeroAmountTest() {
		int[] denominations = {1,3,5};
		Assert.assertEquals(CoinChange.coinChange(0, denominations), 0);
	}
}
