package my.epi.tree;

import org.common.TreeNode;
import org.common.TreeUtility;

/**
 *
 *
 * Problem statement:
 *  Given a binary tree where each node contains a binary digit.  The MSB is at the root.
 *  Design an algorithm to compute the sum of the binary numbers represented by the root-to-leaf
 *  paths.
 *
 * Example:
 *                                       1
 *                          0                                 1
 *                     0           1               0                     0
 *                 0      1            1               0                      0
 *                                   0               1    0
 *                                                     1
 * (1,0,0,0), (1,0,0,1), (1,0,1,1,0), (1,1,0,0,1,1), (1,1,0,0,0), (1,1,0,0)
 *
 * Basically, there is a binary value for each leaf in the tree.  In this case there are 5
 * leafs.
 *
 * Approach:
 *  1) DFS to reach the leaf, store the digits in a path and convert binary representation to decimal
 *     * Run time analysis would be O(n*h) where n is the number of leafs and h is the height of tree
 *     * Space would be height of the tree
 *  2) Notice the re-computation for paths that share common nodes
 *     * For example (1,0,0,0) and (1,0,0,1)  => they share (1,0,0).  If some how (1,0,0) is
 *       computed only once
 *     * The algorithm is each time we visit a node:
 *       * if it is not a leaf, we compute the value of path so far and keep traversing.
 *       * if it is a leaf, we return the path value and stop traversing deeper
 *     * Run time complexity O(n) because we visit each node once
 *     * Space  complexity is O(h) because at any point in time, the space is needed for recursion stack
 *
 *
 */
public class SumBinaryDigits {
    public static void main(String[] args) {

        System.out.println("SumBinaryDigits.main");

        TreeNode<Integer> leftLeft = TreeNode.createTreeNode(0,0,1);
        TreeNode<Integer> leftRight = TreeNode.createTreeNode(1, null,
                TreeNode.createTreeNode(1, null, 0));

        TreeNode<Integer> left = TreeNode.createTreeNode(0, leftLeft, leftRight);

        TreeNode<Integer> rightLeft = TreeNode.createTreeNode(0, null, TreeNode.createTreeNode(0,
                TreeNode.createTreeNode(1,null, 1), TreeNode.createTreeNode(0)) );

        TreeNode<Integer> rightRight = TreeNode.createTreeNode(0, null, TreeNode.createTreeNode(0) );

        TreeNode<Integer> right = TreeNode.createTreeNode(1, rightLeft, rightRight);

        TreeNode<Integer> root = TreeNode.createTreeNode(1,left, right);

        TreeUtility.printRootToLeafPath(root);

        System.out.println("total sum: " + sumRootToLeaf(0, root));


    }

    /**
     * Another example of in-order traversal
     * @param pathSum
     * @param root
     * @return
     */
    public static int sumRootToLeaf(int pathSum, TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        pathSum = pathSum * 2 + root.value;

        // if leaf then stop and bubble up to parent
        if (root.left == null && root.right == null) {
            System.out.println(pathSum);
            return pathSum;
        }

        // sum of left side and right side
        //     1
        //   /  \
        //  0     1
        return sumRootToLeaf(pathSum, root.left) +  sumRootToLeaf(pathSum, root.right);

    }

}
