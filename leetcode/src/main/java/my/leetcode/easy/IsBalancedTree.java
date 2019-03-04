package my.leetcode.easy;

import org.common.TreeNode;

public class IsBalancedTree {

    public static void main(String[] args) {
        System.out.println(IsBalancedTree.class.getName());

    }

    /**
     * This is the post order traversal and compare the depth of left and right
     * child.
     *
     * - if the return value from left or right child is -1, then it means
     *   that subtree was not balanced, therefore do not continue
     *
     * - if the diff > 1 return -1
     * - else return the max(leftDepth, rightDepth) + 1 up to parent
     *
     *
     * @param node
     * @return
     */
    private static int isBalancedHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }


        int leftDepth = isBalancedHelper(node.left);
        if (leftDepth == -1) {
            return -1;
        }
        int rightDepth = isBalancedHelper(node.right);
        if (rightDepth == -1) {
            return -1;
        }

        if (Math.abs(leftDepth - rightDepth) > 1) {
            return -1;
        }

        // the depth of a node is the max of its two children
        return Math.max(leftDepth, rightDepth) +1;
    }
}
