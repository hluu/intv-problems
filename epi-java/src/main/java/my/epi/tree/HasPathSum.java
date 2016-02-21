package my.epi.tree;

import apple.laf.JRSUIUtils;
import org.common.TreeNode;
import org.common.TreeUtility;

/**
 * Created by hluu on 2/20/16.
 *
 * Problem:
 *  Give a binary tree with integers, find a root to leaf path with specified sum
 *
 *  Desired complexity is O(n) time complexity and O(h) space complexity
 *
 * Approach:
 *  * Each path from root to leaf is a unique path
 *  * However all leafs from same parent share certain commonality in terms of parent nodes
 *  * As we traverse in DFS manner, keep track of the sum so far.  When we get to the leaf,
 *  * we compute the final sum for that path and compare with given sum.
 *  * if found, stop traversing, else continue
 */
public class HasPathSum {
    public static void main(String[] args) {
        System.out.println("HasPathSum.main");


        TreeNode<Integer> leftRight = TreeNode.createTreeNode(561, null, TreeNode.createTreeNode(3, 17));
        TreeNode<Integer> leftLeft = TreeNode.createTreeNode(271, 28,0);
        TreeNode<Integer> left = TreeNode.createTreeNode(6, leftLeft, leftRight);


        TreeNode<Integer> rightLeft = TreeNode.createTreeNode(2, null, TreeNode.createTreeNode(1,
                TreeNode.createTreeNode(401, null, 641), TreeNode.createTreeNode(257)));

        TreeNode<Integer> rightRight = TreeNode.createTreeNode(217, null, 28);
        TreeNode<Integer> right = TreeNode.createTreeNode(6, rightLeft, rightRight);

        TreeNode<Integer> root = TreeNode.createTreeNode(314,left, right);

        TreeUtility.inOrderTraversal(root);

        TreeUtility.printRootToLeafPath(root);

        //int targetSum = 591;
        int targetSum = 580;
        System.out.println("targetSum: " + targetSum + " hasPathSum: " +
                hasPathSum(root, 0, targetSum));
    }

    public static boolean hasPathSum(TreeNode<Integer> root, int sumSoFar, int sum) {
        if (root == null) {
            return false;
        }

        sumSoFar += root.value;
        if (root.left == null && root.right == null) {
            // leaf
            return (sumSoFar == sum);
        }

        return hasPathSum(root.left, sumSoFar, sum) ||
                hasPathSum(root.right, sumSoFar, sum);

    }
}
