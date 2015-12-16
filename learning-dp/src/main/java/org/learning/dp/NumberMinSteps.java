package org.learning.dp;

/**
 * Give a positive integer, one of the following operations can be performed: 1)
 * subtract 1 from it : (n = n - 1) 2) divide by 2 if divisible by 2 3) divide
 * by 3 if divisible by 3
 * 
 * Find the minimum number of steps that takes n to 1.
 * 
 * Examples: n = 4 output=> 4/2 = 2, 2/2=1 result: 2 steps n = 7 output=> 7-1 =
 * 6, 6/3=2 2/2=1 result: 1 step n = 10 output=> 10-1 = 9, 9/3=3 3/3=1 result: 3
 * steps
 * 
 * Notice subproblems = 2/2, 3/3, 4=> 4/2
 * 
 * Recurrence = F(n) = 1 + min{ F(n-1), F(n/2), F(n/3) } for n > 1. F(1) = 0
 * 
 * Runtime: ?
 * 
 * @author hluu
 *
 */
public class NumberMinSteps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 10;

		System.out.println("=== bottom up ===");
		for (int i = 1; i < 21; i++) {
			System.out.printf("value %d has minimum of %d steps\n", i,
					minStepBottomUp(i));
		}

		System.out.println("=== top down ===");
		for (int i = 1; i < 21; i++) {
			System.out.printf("value %d has minimum of %d steps\n", i,
					minStepTopDown(i, new int[i + 1]));
		}
	}

	/**
	 * This implementation uses a bottom up approach. We know 2=1 step, 3=1
	 * step, 4=2 steps.
	 * 
	 * Iterate from 2 to n => leverage the solutions that was built earlier
	 * 
	 * @param n
	 * @return
	 */
	public static int minStepBottomUp(int n) {
		int[] steps = new int[n + 1];
		steps[1] = 0;

		// since F(1) = 0
		for (int i = 2; i <= n; i++) {
			steps[i] = 1 + steps[i - 1];

			if ((i % 2) == 0) {
				steps[i] = Math.min(steps[i], 1 + steps[i / 2]);
			}

			if ((i % 3) == 0) {
				steps[i] = Math.min(steps[i], 1 + steps[i / 3]);
			}
		}
		return steps[n];
	}

	/**
	 * This approach uses the top down, which uses recursion
	 */
	public static int minStepTopDown(int n, int[] steps) {

		if (n == 1) {
			return 0;
		}

		if (steps[n] != 0) {
			return steps[n];
		}

		int guess1 = Integer.MAX_VALUE, guess2 = Integer.MAX_VALUE, guess3 = Integer.MAX_VALUE;
		if ((n % 2) == 0) {
			guess1 = 1 + minStepTopDown(n / 2, steps);
		}

		if ((n % 3) == 0) {
			guess2 = 1 + minStepTopDown(n / 3, steps);
		}

		guess3 = 1 + minStepTopDown(n - 1, steps);

		int minGuess = Math.min(Math.min(guess1, guess2), guess3);
		steps[n] = minGuess;

		return steps[n];

	}
}
