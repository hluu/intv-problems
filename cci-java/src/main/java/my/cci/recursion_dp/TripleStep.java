package my.cci.recursion_dp;

import java.util.Arrays;


/**
 * Created by hluu on 5/27/17.
 *
 * Given a staircase has n steps and one can take either 1, 2, or 3 steps
 * at a time, determine the number of possible ways one can run up the stairs
 *
 * For example:
 *  n = 5
 *  first step can be: 1 (4), 2 (3) , 3 (2)
 *  for each of those possible outcome, repeat the each option of taking steps
 *
 * Analysis:
 *   The base case when there are no remaining steps to take.
 *
 *   naiveCountWays(n-1) + naiveCountWays(n-2) + naiveCountWays(n-3)
 *
 *   Runtime analysis: O(3^n) because of 3 different ways
 *
 *   This problem is similar to fibonacci, where it has two possible way f(n-1) + f(n-2)
 */
public class TripleStep {
  public static void main(String[] args) {
    System.out.println("TripleStep.main");


    for (int i = 3; i <= 15; i++) {
      int[] memo = new int[i+1];
      Arrays.fill(memo, -1);
      System.out.printf("stairs: %d, naiveCountWays: %d, fastCountWays: %d\n", i,
          naiveCountWays(i), fastCountWays(i, memo));

    }

  }

  /**
   * This is the naive way because of repeated computation.  We can use
   * similar technique like in Fibonacci to speed things up and remembering
   *
   *
   * @param n
   * @return
   */
  private static int naiveCountWays(int n) {
    // when n is less than 2 or 3, then the result of subtraction can be negative
    if (n < 0 ) {
      return 0;
    }

    // when there is no more steps to take, then that is valid step to take, so we count it
    if (n == 0) {
      return 1;
    }

    return naiveCountWays(n-1) + naiveCountWays(n-2) + naiveCountWays(n-3);
  }

  private static int fastCountWays(int n, int[] memo) {
    if (n < 0 ) {
      return 0;
    }

    // when there is no more steps to take, then that is valid step to take, so we count it
    if (n == 0) {
      return 1;
    }

    if (memo[n] > -1) {
      return memo[n];
    }

    memo[n] = fastCountWays(n-1, memo) + fastCountWays(n-2, memo) + fastCountWays(n-3, memo);

    return memo[n];
  }
}
