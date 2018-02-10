package org.learning.numbers;

/**
 * Created by hluu on 8/22/16.
 *
 * Problem statement:
 *  Give a list of integer with the minimum size of three, find the highest product
 *  from three of the integers
 *
 * Approach:
 *  Brute force: 3 for loops, that would be O(n^3)
 *
 *  Can we do O(nlogn)?  Divide and conquer doesn't work because the three numbers can
 *  be in any part of the list.
 *
 *  Highest product of three integers imply the largest 3 integer values.  What about
 *  maintaining that list of three highest values.  Similar to top N problem.
 *  How about using max heap but maintain the size to be of 3
 *
 */
public class HighestProductOfThreeInteger {
    public static void main(String[] args ) {
        System.out.println("HighestProductOfThreeInteger.main");


    }
}
