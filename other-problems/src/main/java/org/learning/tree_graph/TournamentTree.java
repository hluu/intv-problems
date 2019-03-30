package org.learning.tree_graph;

import org.common.TreeNode;
import org.common.TreeUtility;
import org.testng.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A tournament tree is a binary tree where the parent is the minimum of the two children.
 *
 * Given a tournament tree find the second minimum value in the tree.
 * A node in the tree will always have 2 or 0 children.
 *
 * Also all leaves will have distinct and unique values.
 *
 *         2
 *      /    \
 *     2      3
 *    / \    /  \
 *   4   2  5   3
 *
 * Answer is 3.
 *
 *
 *       2
 *     /  \
 *    4    2
 *   / \  / \
 *  4   5 2  3
 *
 * Answer is 3.
 *
 * Approach:
 *   * Solving for second minimum in any tree
 *      * Traverse the entire tree and keep track of second minimum.
 *          Runtime => O(n) -> n for number of nodes
 *          Space => O(log(n)) -> stack for depth of the tree
 *      * Traverse the entire tree and populate the min-heap of size of 2 (k),
 *        pop out two times.
 *          * No need to maintain all the nodes in the tree
 *            Runtime => O(n) for traversing the tree, populate the min heap
 *            O(klog(n)), pop out 2, 2logn
 *          Space => O(log(n)) -> stack while traversing, O(n) for the heap
 *          * To get the kth smallest value from the tree, we can perform operations
 *            of Delete-Min and Get-Min k-1 times.
*    * From previous approaches, it requires traversing the whole tree,
 *      IS THAT REALLY NECESSARY?
 *      * Can we come up with a condition and then go left or right at each
 *        level to cut the tree in half?
 *
 */


public class TournamentTree {
    public static void main(String[] args) {
        TreeNode<Integer> tree1 = createTree1();
        test(tree1, 3);

        TreeNode<Integer> tree2 = createTree2();
        test(tree2, 3);

        test(createTree3(), 5);
        test(createTree4(), 2);
    }

    private static void test(TreeNode<Integer> tree, int expectedValue) {
        System.out.println("========= test ========");
        TreeUtility.printLevelByLevel(tree);

        int actualValueFromBruteForce = bruteForce(tree);
        System.out.printf("expected: %d, actual from brute force: %d\n", expectedValue, actualValueFromBruteForce);
        Assert.assertEquals(actualValueFromBruteForce, expectedValue);

        // brute force using BFS
        int actualValueFromBFBFS = bruteForceBFS(tree);
        System.out.printf("expected: %d, actual from brute force BFS: %d\n",
                expectedValue, actualValueFromBFBFS);
        Assert.assertEquals(actualValueFromBFBFS, expectedValue);

        int actualValueFromOptimize1 = optimizedSolution1(tree);

        System.out.printf("expected: %d, actual from optimized solution1: %d\n",
                expectedValue, actualValueFromOptimize1);

        System.out.println();
    }

    private static int optimizedSolution1(TreeNode<Integer> node) {

        // handle these cases root is null and root has less than two children return null
        if (node == null) {
            return -1;
        }

        if (node.left == null || node.right == null) {
            return -1;
        }

        int secondMin = Integer.MAX_VALUE;
        secondMin = optimizedSolution1Helper(node, secondMin);
        return secondMin;
    }

    private static int optimizedSolution1Helper(TreeNode<Integer> node, int secondMin) {
        // this is an invariant of this problem, so when that is violated, return secondMin
        if (node.left == null || node.right == null) {
            return secondMin;
        }

        if (node.value != node.left.value) {
            // going right
            secondMin = Math.min(node.left.value, secondMin);
            return optimizedSolution1Helper(node.right, secondMin);
        } else {
            // going left
            secondMin = Math.min(node.right.value, secondMin);
            return optimizedSolution1Helper(node.left, secondMin);
        }
    }

    /**
     * Traverse the entire tree and maintain a second smallest value while traversing.
     *
     * @param node
     * @return
     */
    private static int bruteForce(TreeNode<Integer> node) {
        // first element is the smallest, second element is the second smallest
        int[] state = new int[2];
        state[0] = Integer.MAX_VALUE; state[1] = Integer.MAX_VALUE;

        bruteForceHelper(node, state);

        return state[1];
    }

    private static void bruteForceHelper(TreeNode<Integer> node, int[] state) {
        if (node == null) {
            return;
        }

        if (node.value < state[0]) {
            state[1] = state[0];
            state[0] = node.value;
        }

        if (node.value > state[0] && node.value < state[1]) {
            state[1] = node.value;
        }

        bruteForceHelper(node.left, state);
        bruteForceHelper(node.right, state);
    }

    /**
     * This one is using BFS to traverse the entire tree level by level
     *
     * @param node
     */
    private static int bruteForceBFS(TreeNode<Integer> node) {
        int[] result = new int[2];
        result[0] = Integer.MAX_VALUE;
        result[1] = Integer.MAX_VALUE;

        if (node == null) {
            return 0;
        }

        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(node);

        // while there are still nodes to process
        while (!queue.isEmpty()) {
            TreeNode<Integer> tmpNode = queue.poll();

            if (tmpNode.value < result[0]) {
                result[1] = result[0];
                result[0] = tmpNode.value;
            } else if (tmpNode.value > result[0] && tmpNode.value < result[1]) {
                result[1]= tmpNode.value;
            }

            if (tmpNode.left != null) {
                queue.add(tmpNode.left);
            }

            if (tmpNode.right != null) {
                queue.add(tmpNode.right);
            }
        }

        return result[1];
    }

    private static TreeNode<Integer> createTree4() {
        TreeNode<Integer> root = new TreeNode<Integer>(1);

        root.left = TreeNode.createTreeNode(1);
        root.right = TreeNode.createTreeNode(2);

        root.left.left = TreeNode.createTreeNode(1);
        root.left.right = TreeNode.createTreeNode(1);

        root.right.left = TreeNode.createTreeNode(2);
        root.right.right = TreeNode.createTreeNode(2);

        return root;
    }


    private static TreeNode<Integer> createTree3() {
        TreeNode<Integer> root = new TreeNode<Integer>(2);

        root.left = TreeNode.createTreeNode(2);
        root.right = TreeNode.createTreeNode(5);
        root.right.left = TreeNode.createTreeNode(5);
        root.right.right = TreeNode.createTreeNode(7);

        return root;
    }

    private static TreeNode<Integer> createTree2() {
        TreeNode<Integer> root = new TreeNode<Integer>(2);
        TreeNode<Integer> two1 = new TreeNode<Integer>(2);
        TreeNode<Integer> two2 = new TreeNode<Integer>(2);

        TreeNode<Integer> three = new TreeNode<Integer>(3);

        TreeNode<Integer> four1 = new TreeNode<Integer>(4);
        TreeNode<Integer> four2 = new TreeNode<Integer>(4);
        TreeNode<Integer> five = new TreeNode<Integer>(5);

        root.left = four1;
        root.right = two1;

        four1.left = four2; four1.right = five;
        two1.left = two2; two1.right = three;

        return root;
    }

    private static TreeNode<Integer> createTree1() {
        TreeNode<Integer> root = new TreeNode<Integer>(2);
        TreeNode<Integer> two1 = new TreeNode<Integer>(2);
        TreeNode<Integer> two2 = new TreeNode<Integer>(2);
        TreeNode<Integer> three1 = new TreeNode<Integer>(3);
        TreeNode<Integer> three2 = new TreeNode<Integer>(3);
        TreeNode<Integer> four = new TreeNode<Integer>(4);
        TreeNode<Integer> five = new TreeNode<Integer>(5);

        root.left = two1;
        root.right = three1;

        two1.left = four; two1.right = two2;
        three1.left = five;  three1.right = three2;

        return root;
    }
}
