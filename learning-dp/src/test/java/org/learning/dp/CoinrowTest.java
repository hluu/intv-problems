package org.learning.dp;

import org.learning.common.Tuple;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CoinrowTest {

	@Test()
	public void basicTest() {
		int[] coins = {5,1,2,10,6 };
		Assert.assertEquals(15, Coinrow.coinRow(coins));
	}
	
	@Test()
	public void coinRow2Test() {
		int[] coins = {5,1,2,10,6 };
		Assert.assertEquals(15, Coinrow.coinRow2(coins));
	}
	
	@Test()
	public void coinRowWithCoinsTest() {
		int[] coins = {5,1,2,10,6 };
		
		Tuple result = Coinrow.coinRowWithCoins(coins);
		Assert.assertEquals(15, result.first);
		Assert.assertEquals("5, 10", result.second);
	}
}
