package my.leetcode.difficult;

/**
 * Given several boxes with different colors represented by different positive numbers.
 * You may experience several rounds to remove boxes until there is no box left.
 * Each time you can choose some continuous boxes with the same color
 * (composed of k boxes, k >= 1), remove them and get k*k points.
 *
 * Find the maximum points you can get.
 *
 * For example:
 *  input: [1, 3, 2, 2, 2, 3, 4, 3, 1]
 *  output: 23
 *
 *  Explanation for output of 23:
 *  [1, 3, 2, 2, 2, 3, 4, 3, 1]
 *   ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
 *   ----> [1, 3, 3, 3, 1] (1*1=1 points)
 *   ----> [1, 1] (3*3=9 points)
 *   ----> [] (2*2=4 points)
 *
 *  1,2,1,2,1,2
 *
 *  Approaches:
 *  1) Count the # of boxes with same value
 *  2) Removing boxes to improve the # of contiguous
 *
 *  https://leetcode.com/problems/remove-boxes/discuss/101310/Java-top-down-and-bottom-up-DP-solutions
 *
 */
public class RemoveBoxes {
    public int removeBoxes(int[] boxes) {
        return -1;
    }
}
