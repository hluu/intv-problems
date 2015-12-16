package org.learning.dp;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CoinChangeTest {
	
	@Test()
	public void basicTest() {
		int[] denominations = {1,3,5};
		Assert.assertEquals(3, CoinChange.coinChange(9, denominations));
	}
	
	@Test()
	public void largeAmountTest() {
		int[] denominations = {1,3,5};
		Assert.assertEquals(10, CoinChange.coinChange(50, denominations));
	}
	
	@Test()
	public void zeroAmounTest() {
		int[] denominations = {1,3,5};
		Assert.assertEquals(0, CoinChange.coinChange(0, denominations));
	}
}
