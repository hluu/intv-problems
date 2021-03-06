package my.leetcode.medium;

import org.common.TreeNode;
import org.testng.Assert;

import java.util.Arrays;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index
 * of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * For example:
 *   A = [2,3,1,1,4], return true.
 *   A = [2,1,1,1,1], return true
 *   A = [3,2,1,0,4], return false.
 *
 * Approach:
 *  A value can be zero
 *
 *  Brute force, for each index, follow the value to see if we can reach the end
 *  - Runtime - O(n^2)
 *
 *  Maintaining ladder:
 *  - The index + the element value at that index represents the point we can jump to
 *  - Maintain this max
 *  - If able to get to last index of beyond, return true
 *  - Runtime: O(n)
 *
 *  https://leetcode.com/problems/jump-game/
 */
public class JumpGame {
    public static void main(String[] args) {
        test(new int[] {0},  true);

        test(new int[] {0,1},  false);
        test(new int[] {0,2,3},  false);

        test(new int[] {1,0},  true);
        test(new int[] {1,0,1,0},  false);

        test(new int[] {0,1,0},  false);
        test(new int[] {2,3,1,1,4},  true);
        test(new int[] {3,2,1,0,4},  false);
        test(new int[] {1,2},  true);
    }

    private static void test(int[] input, boolean expected) {
        System.out.println("==== input: " + Arrays.toString(input) + " ====");

        boolean actual = canJump(input);

        System.out.printf("expected: %b, actual: %b\n", expected, actual);
        System.out.println();

        Assert.assertEquals(actual, expected);
    }

    private static boolean canJump(int[] input) {


        if (input.length == 0) {
            return false;
        }

        if (input.length == 1) {
            return true;
        }

        int currLadderSize = 0;

        currLadderSize += input[0];
        int lastIndex = input.length - 1;
        for (int i = 1; i < lastIndex ; i++) {
            if (currLadderSize < i) {
                break;
            }
            int nextLadderSize = i + input[i];

            if (nextLadderSize >= lastIndex) {
                return true;
            }

            currLadderSize = Math.max(currLadderSize, nextLadderSize);

        }

        // make sure currNoJump is at least at the last index or beyond
        return currLadderSize >= lastIndex;


    }

}
