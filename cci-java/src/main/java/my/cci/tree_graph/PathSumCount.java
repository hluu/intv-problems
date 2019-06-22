package my.cci.tree_graph;

import org.common.TreeNode;
import org.common.TreeUtility;
import org.testng.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary tree of integer values, count the number of paths that
 * sum up the given target value.
 *
 * Note that a path can start or end at any node.
 *
 * For example:
 *                    10
 *                  /   \
 *                 5    -3
 *               /   \
 *             3      2
 *           /   \      \
 *          3     -2      1
 *
 *  If the target value is 8, the result should be 2 - [5,3], [5,2,1].
 *
 *  Notice the nodes to be considered for the sum are contiguous.
 *  If we have an array of integers, a similar problem would be count the
 *  number of times a consecutive substring of integer sum up to a target value.
 *
 *  For example: 10,5,1,2,-1,7,1   target value: 8
 *    The output would be: 3 - [5,2,1], [2,-1,7], [7,1]
 */
public class PathSumCount {

    public static void main(String[] args) {
        System.out.println(PathSumCount.class);


        contiguousSumDriver();

        pathSumDriver();
    }

    private static void pathSumDriver() {
        System.out.println("============== pathSumDriver ==============");
        TreeNode<Integer> root = createTree1();

        TreeUtility.printLevelByLevel(root);

        int result = pathSumCountBST(root, 8);
        System.out.println("resunt: " + result);
    }

    /**
     * Note that a path can start or end at any node.
     *
     * 1) Therefore the first part is to traverse node in pre-order fashion
     *    *) And for each node determine if there is a path with sum that matches the target
     *
     * Observation:
     * - this pattern treats each node as a starting point
     *   - for each node, call the helper
     *   - for each node's children, recursively call it self
     *   - pretty cool
     *
     *
     * Runtime: O(nlogn) -
     *   - treat each node as a starting point
     *   - then traverse down to the height of the binary tree
     *
     * @return
     */
    private static int pathSumCountBST(TreeNode<Integer> root, int target) {

        if (root == null) {
            return 0;
        }

        int totalCount = pathSumCountBSTHelper(root, target, 0);

        totalCount += pathSumCountBST(root.left, target);
        totalCount += pathSumCountBST(root.right, target);

        return totalCount;

    }

    /**
     * Start from the given node, traverse left and right in pre-order fashion.
     * * Maintain the running sum
     *
     * @param node
     * @param target
     * @param runningSum
     * @return
     */
    private static int pathSumCountBSTHelper(TreeNode<Integer> node, int target,
                                             int runningSum) {

        if (node == null ) {
            return 0;
        }

        int count = 0;

        runningSum += node.value;

        if (runningSum == target) {
            count++;
        }

        count += pathSumCountBSTHelper(node.left, target, runningSum);
        count += pathSumCountBSTHelper(node.right, target, runningSum);

        //runningSum = runningSum - node.value;
        return count;

    }

    private static TreeNode<Integer> createTree1() {
        TreeNode<Integer> root =TreeNode.createTreeNode(10);
        TreeNode<Integer> five = TreeNode.createTreeNode(5);
        TreeNode<Integer> three1 = TreeNode.createTreeNode(3);
        TreeNode<Integer> two = TreeNode.createTreeNode(2);
        TreeNode<Integer> three2 = TreeNode.createTreeNode(3);
        TreeNode<Integer> negTwo = TreeNode.createTreeNode(-2);
        TreeNode<Integer> one = TreeNode.createTreeNode(1);

        TreeNode<Integer> negThree = TreeNode.createTreeNode(-3);

        TreeNode<Integer> seven = TreeNode.createTreeNode(7);
        negTwo.right = seven;

        root.left = five; root.right = negThree;
        five.left = three1; five.right = two;
        three1.left = three2; three1.right = negTwo;
        two.right = one;
        //negThree.right = eleven;

        return root;
    }

    private static void contiguousSumDriver() {
        int input[] = new int[] {8, 10,5,1,2,-1,7,1};
        testContiguousSum(input, 8, 4);
    }

    private static void testContiguousSum(int[] input, int target, int expected) {
        System.out.printf("==== %s, target: %d\n", Arrays.toString(input), target);

        System.out.println("expected: " + expected);

        int result1 = contiguousSumBF(input, target);
        int result2 = contiguousSumOptimized(input, target);
        System.out.println("result1: " + result1);
        System.out.println("result2: " + result2);

        Assert.assertEquals(result1, result2);
        Assert.assertEquals(result1, expected);
    }

    /**
     * Given an array of integers, determine how many contiguous
     * set of integers that added up the given sum
     *
     * @param input
     * @param target
     * @return
     */
    private static int contiguousSumBF(int[] input, int target) {
        // naive solution would be O(n^2)
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            int runningSum = 0;

            for (int j = i; j < input.length; j++) {
                runningSum += input[j];
                if (runningSum == target) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * This version uses additional space to improve runtime to be O(n).
     *
     * First we need a map to maintain the running count and number of times
     * we've seen this running count
     *
     *  map<sum, count>
     *
     * The idea is to iterate through each element,
     *   * keep a running sum
     *   * perform a lookup of map.get(running sum - target)
     *      * if exists, add one to it, else set the value as 0
     *   * add the running sum to map with value as 0
     *
     *
     * @param input
     * @param target
     * @return
     */
    private static int contiguousSumOptimized(int[] input, int target) {
        Map<Integer, Integer> sumToCount = new HashMap<>();
        // seed the 0 with 0 to handle case when first element matches the target
        sumToCount.put(0, 0);

        int runningSum = 0;
        int count = 0;
        for (int value : input) {
            runningSum += value;

            int lookupSum = runningSum-target;
            Integer tmpCnt = sumToCount.get(lookupSum);
            if (tmpCnt == null) {
                // first time
                tmpCnt = 0;
            } else {
                tmpCnt = tmpCnt + 1;
            }

            sumToCount.put(lookupSum, tmpCnt);
            sumToCount.put(runningSum, 0);

        }


        for (Integer v : sumToCount.values()) {
            count += v;
        }

        return count;

    }
}
