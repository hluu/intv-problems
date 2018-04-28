package my.leetcode.easy;

import org.common.TreeNode;
import org.common.TreeUtility;
import org.testng.Assert;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all
 * the values along the path equals the given sum.
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 *
 * Sum = 22 => true (5->4->11->2)
 *
 * Assumption:
 * 1) value can be positive, negative, and zero
 * 2) a path can be zig zag (left or right), but must end at leaf
 *
 * Approach:
 *  1) The path must include from root to leaf
 *  2) Only condition to check is when reach the leaf and remaining is 0
 *  3) In between the path, we don't care about the remaining value
 */
public class PathSumExists {
    public static void main(String[] args) {
        test(createTree1(), 22, true);

        test(createTree2(), 2, true);
        test(createTree2(), -4, true);
        test(createTree2(), -1, true);
    }

    private static void test(TreeNode<Integer> tree, int targetSum, boolean expectedResult) {

        System.out.println("***** test with target sum: " + targetSum + " *****");

        System.out.println("====== root to leaf =====");
        TreeUtility.printRootToLeafPath(tree);

        boolean actualResult = pathSumExistHelper(tree, targetSum);
        boolean actualResult2 = pathSumExistHelper2(tree, targetSum, 0);

        System.out.printf("expected: %b, actual: %b actual2: %b\n",
                expectedResult, actualResult, actualResult2);

        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(actualResult2, expectedResult);
        System.out.println();
    }

    private static boolean pathSumExistHelper(TreeNode<Integer> node, int remaining) {
        if (node == null) {
            return false;
        }

        remaining = remaining - node.value;

        if ((remaining == 0) && (node.left == null) && (node.right == null)) {
            return true;
        }

        return pathSumExistHelper(node.left, remaining) ||
                pathSumExistHelper(node.right, remaining);


    }

    private static boolean pathSumExistHelper2(TreeNode<Integer> node, int targetSum, int sumSoFar) {
        if (node == null) {
            return false;
        }

        sumSoFar = sumSoFar + node.value;

        if ((sumSoFar == targetSum) && (node.left == null) && (node.right == null)) {
            return true;
        }

        return pathSumExistHelper2(node.left, targetSum, sumSoFar) ||
                pathSumExistHelper2(node.right, targetSum, sumSoFar);


    }

    private static TreeNode<Integer> createTree1() {
        TreeNode<Integer> root =TreeNode.createTreeNode(5);
        TreeNode<Integer> four1 = TreeNode.createTreeNode(4);
        TreeNode<Integer> four2 = TreeNode.createTreeNode(4);
        TreeNode<Integer> eight = TreeNode.createTreeNode(8);
        TreeNode<Integer> eleven = TreeNode.createTreeNode(11);
        TreeNode<Integer> thirteen = TreeNode.createTreeNode(13);
        TreeNode<Integer> two = TreeNode.createTreeNode(2);
        TreeNode<Integer> seven = TreeNode.createTreeNode(7);
        TreeNode<Integer> one = TreeNode.createTreeNode(1);

        root.left = four1; root.right = eight;

        four1.left = eleven;
        eight.left = thirteen; eight.right = four2;

        eleven.left = seven; eleven.right = two;

        four2.right = one;
        return root;
    }

    private static TreeNode<Integer> createTree2() {
        TreeNode<Integer> root =TreeNode.createTreeNode(1);
        TreeNode<Integer> nTwo1 = TreeNode.createTreeNode(-2);
        TreeNode<Integer> nThree = TreeNode.createTreeNode(-3);
        TreeNode<Integer> one = TreeNode.createTreeNode(1);
        TreeNode<Integer> three = TreeNode.createTreeNode(3);
        TreeNode<Integer> nTwo2 = TreeNode.createTreeNode(-2);
        TreeNode<Integer> nOne = TreeNode.createTreeNode(-1);

        root.left = nTwo1; root.right = nThree;

        nTwo1.left = one; nTwo1.right = three;
        nThree.left = nTwo2;

        one.left = nOne;

        return root;
    }
}
