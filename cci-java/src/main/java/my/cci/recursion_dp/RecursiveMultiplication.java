package my.cci.recursion_dp;

import org.testng.Assert;


/**
 * Created by hluu on 5/27/17.
 *
 * Write a cursive function to multiple two positive integers w/o using
 * the multiplication operator or division operator.
 *
 * Allowable operators are: +, -, and bit shifting
 *
 * Example:
 *   recursiveMultiply(8, 6) ==> 8 8 8 8 8 8
 *
 * Analysis:
 *  * Based on the observation above example of 8 8 8 8 8 8 8, we can calculate
 *    the sum of three 8 and add that sum to itself
 *  * Run time would be log(s)
 *
 *
 */
public class RecursiveMultiplication {
  public static void main(String[] args) {
    System.out.println("RecursiveMultiplication.main");

    Assert.assertEquals(recursiveMult(6,8), 6*8);

    Assert.assertEquals(recursiveMult(9,5), 9*5);
  }

  private static int recursiveMult(int x, int y) {
    int smaller = x < y ? x : y;
    int bigger = x < y ? y : x;

    return recursiveMultHelper(bigger, smaller);
  }

  /**
   * Recursive multiplication method.  Each call will reduce multiplyBy by half
   *
   * @param elm
   * @param multiplyBy
   * @return
   */
  private static int recursiveMultHelper(int elm, int multiplyBy) {
    if (multiplyBy == 0) {
      return 0;
    }

    if (multiplyBy == 1) {
      return elm;
    }

    // each recursive call, multiplyBy will be smaller by half
    int multiplyByHalf = multiplyBy >> 1;
    int leftSide = recursiveMultHelper(elm, multiplyByHalf);

    int prod = leftSide + leftSide;
    // take care of even or odd multiplyBy
    if (multiplyBy % 2 == 1) {
      return prod + elm;
    } else {
      return prod;
    }
  }


}
