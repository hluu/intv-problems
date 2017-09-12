package org.learning.numbers;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by hluu on 1/24/16.
 *
 * Problem:
 *  For example, given the following triangle
 *  [
 *      [2],
 *    [3,4],
 *    [6,5,7],
 *    [4,1,8,3]
 *  ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 *
 * Bonus point if you are able to do this using only O(n) extra space,
 * where n is the total number of rows in the triangle.
 *
 * Approach:
 *  Let F(n) be the minimum path from top to bottom.
 *
 *  What if we choose the minimum value at each level and add them up?
 */
public class MinimumTotal {
    public static void main(String[] args) {
        System.out.println("MinimumTotal.main");

        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(2));
        input.add(Arrays.asList(3,4));
        input.add(Arrays.asList(6,5,7));
        input.add(Arrays.asList(4,1,8,3));

        System.out.println("output: " + minimumTotal(input));

        List<List<Integer>> input2 = new ArrayList<>();
        input2.add(Arrays.asList(-1));
        input2.add(Arrays.asList(2,3));
        input2.add(Arrays.asList(1,-1,-3));
        System.out.println("output: " + minimumTotal(input2));

    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> nums;

        List<Integer> minValueList = new ArrayList<>();
        for (int i = 0; i < triangle.size(); i++) {
            nums = triangle.get(i);

            Collections.sort(nums);
            minValueList.add(nums.get(0));
        }

        System.out.println(minValueList);
        int sum = 0;
        for (int v : minValueList) {
            sum += v;
        }

        return sum;
    }


}
