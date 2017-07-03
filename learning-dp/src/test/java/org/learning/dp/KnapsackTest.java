package org.learning.dp;

import org.testng.Assert;
import org.testng.annotations.Test;

public class KnapsackTest {
	@Test()
	public void sampleInput1Test() {
		
		int weights[] = {2,1,3,2};
		int values[] = {12,10,20,15};
		int knapsackCapacity = 5;
		Assert.assertEquals(37, Knapsack. dpKnapsack(weights, values, knapsackCapacity));
	}
	
	@Test()
	public void sampleInput1WithDifferentItemOrderTest() {
		
		int weights[] = {2,3,1,2};
		int values[] = {15,20,10,12};
		int knapsackCapacity = 5;
		Assert.assertEquals(37, Knapsack. dpKnapsack(weights, values, knapsackCapacity));
	}
	
	@Test()
	public void sampleInput2Test() {
		
		int weights[] = {1,2,3,};
		int values[] = {2,4,8};
		int knapsackCapacity = 3;
		Assert.assertEquals(8, Knapsack. dpKnapsack(weights, values, knapsackCapacity));
	}
}
