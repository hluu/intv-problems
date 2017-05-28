package my.cci.recursion_dp;

import com.google.common.base.Stopwatch;


/**
 * Created by hluu on 5/27/17.
 *
 * Definition of Fibonacci:  f(n) = f(n-1) + f(n-2)
 *
 * This is a class recursion problem where f(n) depends on subproblems namely f(n-1) and f(n-2).
 *
 * If we can calculate f(n-1) and f(n-2), then we  can calculate f(n)
 *
 * Base case is: f(0) = 0, f(1) = 2
 *
 */
public class Fibonacci {

  public static void main(String[] args) {
    System.out.println("Fibonacci.main");

    int n = 30;

    Stopwatch sw = Stopwatch.createStarted();
    System.out.printf("naiveFib of %d is %d\n", n, naiveFib(n));

    System.out.printf("took: %d (ms)\n", sw.stop().elapsed().toMillis());

    Stopwatch sw2 = Stopwatch.createStarted();
    System.out.printf("memoFib of %d is %d\n", n, memoFib(n, new int[n+1]));
    System.out.printf("took: %d (ms)\n", sw2.stop().elapsed().toMillis());

  }

  /**
   * This is a naive recursive solution.  Runtime is O(2^n) because
   * each number generates two child nodes, and each one of those generates their own 2 child nodes.
   *
   * There are a lot of redundant calculations:
   *   f(5) = f(4) + f(3)
   *   f(4) = f(3) + f(2)
   *   f(3) = f(2) + f(1)
   *
   *  From above, the f(3), f(2), f(1) are calculate at least two times.
   *
   * @param n
   * @return
   */
  private static int naiveFib(int n) {
    if (n == 0) return 0;
    if (n == 1) return 1;

    return naiveFib(n-1) + naiveFib(n-2);
  }

  private static int memoFib(int n, int[] memo) {
    if (n == 0 || n == 1) return n;

    if (memo[n] == 0) {
      memo[n] = memoFib(n-1, memo) + memoFib(n-2, memo);
    }

    return memo[n];
  }
}
