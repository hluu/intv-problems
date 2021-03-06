package org.learning.dp;

import org.common.Tuple;
import org.testng.Assert;

import java.util.Arrays;


/**
 * Given a row of coins whose values are positive integers and not necessary distinct.
 * The goal is to pick up the maximum amount of money subject to the constraint that 
 * no two coins adjacent in the  row can be picked up
 * 
 * The main idea is to partition all the allowed coin selections into two group, those
 * that include in the last coin and those what w/o it.
 * 
 * The first group is the value of coin n and the maximum value of coins in F(n-2). The reason 
 * for the F(n-2) is due the restriction 0 - no two coins adjacent in the initial row can be 
 * picked up
 * 
 * The second group is F(n-1)
 * 
 * The recurrence relation is F(n) = max { C(n) + F(n-2), F(n-1) } for n > 1
 * F(0) = 0, F(1) = C(1)
 *
 *
 */
public class Coinrow {

	public static void main(String[] args) {


		test(new int[] {5,15,1,1,9,10 }, 26);
		test(new int[] {2,2,2,2,2,2}, 6);
		test(new int[] {1,2,3,4,5,6 }, 12);
	}

	private static void test(int[] coins, int expected) {
		System.out.printf("\n *** input: %s\n", Arrays.toString(coins));

		int actual1 = coinRow(coins);

		int actual2 = coinRow2(coins);
		Tuple actual3Tuple = coinRowWithCoins(coins);

		System.out.printf("expected: %d, actual1: %d, actual2: %d, actual3: %d\n",
				expected, actual1, actual2, actual3Tuple.first);

		Assert.assertEquals(actual1, expected);
		Assert.assertEquals(actual2, expected);
		Assert.assertEquals(actual3Tuple.first, expected);
	}
	
	public static int coinRow(int[] coins) {
		// the maximum value that can be picked up from the row
		int max[] = new int[coins.length+1];
		max[1] = coins[0];
		

		// instead of the entire array, we can possibly just
		// store 2 numbers max[i-1] and mac[i-2]
		for (int i = 2; i <= coins.length; i++) {
			max[i] = Math.max(coins[i-1] + max[i-2], max[i-1]);
		}
		
		return max[max.length-1];
	}
	
	public static Tuple coinRowWithCoins(int[] coins) {
		// the maximum value that can be picked up from the row
		int max[] = new int[coins.length+1];
		max[1] = coins[0];
		String list1 = ""  + coins[0];
		String list2 = "";
		
		for (int i = 2; i <= coins.length; i++) {
			if (coins[i-1] + max[i-2] > max[i-1]) {
				list2 = list2 + (list2.length() > 0 ? ", " : "") + coins[i-1];
				max[i] = coins[i-1] + max[i-2];
				String temp = list1;
				list1 = list2;
				list2 = temp;
			} else {
				max[i] = max[i-1]; 
				list2 = list1;
			}
		}
		
		System.out.println("coins: " + list1);
		
		return Tuple.createTuple(max[max.length-1], list1);
	}
	
	/**
	 * This implementation uses two temporary variables, one for F(n-2)
	 * and the other for F(n-1)
	 * 
	 * @param coins
	 * @return maximum amount of money that can be picked up
	 */
	public static int coinRow2(int[] coins) {
		// the maximum value that can be picked up from the row
		int backByTwo = 0;
		int backByOne = coins[0];

		int temp = 0;
		for (int i = 2; i <= coins.length; i++) {
			temp = Math.max(coins[i-1] + backByTwo, backByOne);
			backByTwo = backByOne;
			backByOne = temp;
		}
		
		return temp;
	}

}
