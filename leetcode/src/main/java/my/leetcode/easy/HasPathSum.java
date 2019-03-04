package my.leetcode.easy;

import org.common.TreeNode;
import org.testng.Assert;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals the given sum.
 *
 * Note: A leaf is a node with no children.
 */
public class HasPathSum {

    public static void main(String[] args) {
        test(createTree1(), 1, false);
        test(createTree2(), 22, true);
        test(createTree2(), 10000, false);
        test(createTree2(), 18, true);
    }

    private static void test(TreeNode<Integer> root, int targetSum,
                             boolean expected) {
        boolean actual = hasPathSum(root, targetSum, 0);

        System.out.printf("expected: %b, actual: %b\n", expected, actual);

        Assert.assertEquals(expected, actual);

    }
    /**
     * - Do DFS and update sumSoFar as recursing
     * - when reaching a new node, compute sumSoFar + node.value and
     *   compare with sum
     *   - if (sumSoFar + node.value == sum) return true
     *   - else return hasPathSum(node.left) || hasPathSum(node.right)
     */
    private static boolean hasPathSum(TreeNode<Integer> root, int sum, int sumSoFar) {
        if (root == null) {
            return false;
        }

        int newSumSoFar = sumSoFar + root.value;
        // only check matching sum when it is a leaf node
        if (root.left == null && root.right == null && (newSumSoFar == sum)) {
            return true;
        }

        return hasPathSum(root.left, sum, newSumSoFar) ||
                hasPathSum(root.right, sum, newSumSoFar);

    }

    /**
     *       5
     *      / \
     *     4   8
     *    /   / \
     *   11  13  4
     *  /  \      \
     * 7    2      1
     * @return
     */
    private static TreeNode<Integer> createTree2() {
        TreeNode<Integer> root = TreeNode.createTreeNode(5);
        root.left = TreeNode.createTreeNode(4);
        root.right = TreeNode.createTreeNode(8);

        root.left.left = TreeNode.createTreeNode(11);
        root.left.left.left = TreeNode.createTreeNode(7);
        root.left.left.right = TreeNode.createTreeNode(2);

        root.right.left = TreeNode.createTreeNode(13);
        root.right.right = TreeNode.createTreeNode(4);
        root.right.right.right = TreeNode.createTreeNode(1);

        return root;
    }

    private static TreeNode<Integer> createTree1() {
        TreeNode<Integer> root =TreeNode.createTreeNode(1);
        root.right = TreeNode.createTreeNode(2);

        return root;
    }
}
