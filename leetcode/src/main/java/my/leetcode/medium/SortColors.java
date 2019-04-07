package my.leetcode.medium;

import org.common.ArrayUtils;
import org.testng.Assert;

import java.util.Arrays;

/**
 * Given an array with n objects colored red, white or blue, sort them in-place
 * so that objects of the same color are adjacent, with the colors in the order
 * red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white,
 * and blue respectively.
 *
 * Note: You are not suppose to use the library's sort function for this problem.
 *
 * Example:
 *
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 *
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * - First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array
 * - with total number of 0's, then 1's and followed by 2's.
 *
 * Could you come up with a one-pass algorithm using only constant space?
 *
 * Observations:
 * - there are only 3 possible values
 * - possible values are only 0, 1, 2
 * - number can have duplicates
 *
 * Possible solutions:
 * - counting sort - count # of 0, count # 1, count # 2
 * - using 2 pointers p0 and p2
 * - algorithm
 *   - given there are 3 possible values, we use 2 pointers
 *   - one for 2, when seeing a two just move them to the right
 *     - this pointer will start from far right and move left
 *   - one for value 0 and value 1
 *     - this pointer will start from far left and move to right
 *     - just need to make sure 0 and 1 and in the right order
 *
 *   - move p0 as far right as possible until elm is not 0
 *   - move p2 as far left as possible until elm is not 2
 *   - initialize idx = p0 + 1
 *   - while (idx < p2) {
 *
 *   }
 */
public class SortColors {
    public static void main(String[] args) {
        System.out.println(SortColors.class.getName());

        test(new int[] {0,1,2}, new int[] {0,1,2});
        test(new int[] {2,1,0}, new int[] {0,1,2});
        test(new int[] {1,1,1}, new int[] {1,1,1});
        test(new int[] {0,1,0,1,0,1}, new int[] {0,0,0,1,1,1});
        test(new int[] {0,0,0}, new int[] {0,0,0});
        test(new int[] {2,2,2}, new int[] {2,2,2});
        test(new int[] {2,0,2,0}, new int[] {0,0,2,2});
        test(new int[] {2,0,2,2,0}, new int[] {0,0,2,2,2});
        test(new int[] {2,0,2,0,0}, new int[] {0,0,0,2,2});
        test(new int[] {2,0,2,1,1,0}, new int[] {0,0,1,1,2,2});
        test(new int[] {2,2,2,0,1,1}, new int[] {0,1,1,2,2,2});
        test(new int[] {2,2,2,1,0,1}, new int[] {0,1,1,2,2,2});
        test(new int[] {2,2,2,0,1,0,1}, new int[] {0,0,1,1,2,2,2});
        test(new int[] {0,2,2,2,0,2,1,1}, new int[] {0,0,1,1,2,2,2,2});
        test(new int[] {0,1,1,2,0,1,2,0,2,2,2}, new int[] {0,0,0,1,1,1,2,2,2,2,2});
        //test(new int[] {0,2,2,1,1,2,2,1,2,2,1,1,2,1,2,2,0,2,0,1,0,2,0,0,1,2,1,1,1,0,1,0,0,1,2,2,2,2,1,1,1,2,0,1,1,2,0,1,2,1,0},
          //      new int[] {0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2});

    }

    private static void test(int[] colors, int[] expected) {
        System.out.println("\n*** test: " + Arrays.toString(colors));

        sortColors(colors);

        System.out.printf("expected: %s, actual: %s\n",
                Arrays.toString(expected), Arrays.toString(colors));

        Assert.assertEquals(Arrays.toString(colors), Arrays.toString(expected));
    }


    /**
     * Using two pointer2: p0 and p2
     *  - p2 is for value of 2
     *  - p0 is for value of 0
     *  - variants
     *    - p0 points for the first value 1 after 0 - move from left to right
     *    - p2 points to an elements that not 2 - moving from right to left
     * @param colors
     */
    private static void sortColors(int[] colors) {
        if (colors == null || colors.length == 1) {
            return;
        }


        int p0 = 0;
        int p2 = colors.length-1;

        // move p0 to right as long as elm is 0
        // better to compare w/ length before accessing
        // this is for when array is starting with 0s
        while (p0 < colors.length && colors[p0] == 0) {
            p0++;
        }

        // better to compare w/ 0 before accessing
        // this is for when array is ending with 2s
        while (p2 >= 0 &&colors[p2] == 2) {
            p2--;
        }

        int idx = p0;
        // idx is moving from left to right
        // if it gets pass p2, then nothing to check
        //
        while (idx <= p2) {
            if (colors[idx] == 1) {
                idx++;
                continue;
            } else if (colors[idx] == 2) {
                swap(colors, p2, idx);
                p2--;
            } else if (colors[idx] == 0) {
                swap(colors, p0, idx);
                p0++;
            }

            if (colors[p0] > colors[idx]) {
                swap(colors, p0, idx);
                p0++;
            }

            // move p2 left while it is 2
            while (p2 >= 0 && colors[p2] == 2) {
                p2--;
            }

            idx++;
        }
    }

    /**
     * Good to abstract out methods like this while writing code
     * @param arr
     * @param x
     * @param y
     */
    private static void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}

