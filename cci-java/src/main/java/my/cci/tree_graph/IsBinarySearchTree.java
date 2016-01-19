package my.cci.tree_graph;

import org.common.TreeNode;

/**
 * Created by hluu on 1/18/16.
 *
 * Problem statement:
 *  Give a tree, determine if it is a binary search tree (BST).
 *  BST is defined as for a given node, all its children nodes on the
 *  left side is less than or equal to it and all its children nodes on
 *  right side is greater than it.
 *
 *  Another way of stating the above statement is left <= current < right.
 *
 *  BST example:
 *                    20
 *                  /    \
 *                 10     30
 *               /    \
 *             5      15
 *           /   \       \
 *          3     7       17
 *
 *  Not BST example:
 *                20
 *              /    \
 *            10      30
 *              \
 *               25
 *
 * Approach:
 *  1) We know that for a true BST, the in-order traversal will organize the nodes
 *     in ascending order.  So we could perform the in-order traversal and then validate
 *     the array, however this requires addition array data structure.
 *
 *  2) As we traverse, the invariant must be validated. If it is violated than it is
 *     not a BST.  The invariant is all nodes on left side must less than or equal to
 *     current node and all nodes on the right side must greater than current node.  As
 *     long as this invariant is maintained through every single node in the tree, then
 *     it is a BST.
 *
 *     * Left side must be between negative infinity and parent node
 *     * Right side must be greater than parent node and less than positive infinity
 */
public class IsBinarySearchTree {
    public static void main(String[] args) {
        System.out.println("IsBinarySearchTree.main");

        TreeNode<Integer> root1 = TreeNode.createTreeNode(20,
                TreeNode.createTreeNode(10, null, TreeNode.createTreeNode(25)),
                TreeNode.createTreeNode(30));

        System.out.println("isBST: " + isBST(root1, Integer.MIN_VALUE, Integer.MAX_VALUE));


        //TreeNode test = TreeNode.createTreeNode(15, null, 17);

        TreeNode<Integer> root2 = TreeNode.createTreeNode(20,
                TreeNode.createTreeNode(10, TreeNode.createTreeNode(5, 3,7),
                        TreeNode.createTreeNode(15, null, 17)),
                TreeNode.createTreeNode(30));

        System.out.println("isBST root2: " + isBST(root2, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    public static boolean isBST(TreeNode<Integer> root, int min, int max) {
        if (root == null) {
            return true;
        }

        // root.value is supposed to always be between min and max.
        if (!(root.value > min && root.value <= max)) {
            return false;
        }

        /*
        if (root.value <= min || root.value > max) {
            return false;
        } */

        if (!isBST(root.left, min, root.value) ||
            !isBST(root.right, root.value, max)) {
            return false;
        }

        return true;
    }

    public static boolean isBST2(TreeNode<Integer> root, int min, int max) {
        if (root == null) {
            return true;
        }

        // handle left side and look for invariant violation
        if (root.left != null) {
            if (root.left.value > min && root.left.value <= root.value) {
                return isBST(root.left, min, root.value);
            } else {
                return false;
            }
        }

        if (root.right != null) {
            if (root.right.value > root.value && root.right.value < max) {
                return isBST(root.right, root.value, max);
            } else {
                return false;
            }
        }

        return true;
    }
}
