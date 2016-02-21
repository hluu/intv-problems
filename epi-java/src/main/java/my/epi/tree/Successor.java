package my.epi.tree;

import apple.laf.JRSUIUtils;
import org.common.TreeNode;
import org.common.TreeUtility;

/**
 * Created by hluu on 2/21/16.
 *
 * Problem:
 *  Give an node in a binary tree, compute the successor.
 *
 *  The successor is defined as the node appears immediately after the
 *  given node in an inorder traversal.
 *
 * Example:
 *                    20
 *                  /    \
 *                 10     30
 *               /    \      \
 *             5       15     40
 *           /   \     /
 *          3     7  14
 *
 * Observation:
 *  * The successor for 5 is 7 (right substree exists)
 *  * The successor for 7 is 10 (no right substree, traverse up to parent)
 *  * The successor for 15 is 20 (no right subtree,)
 *  * The successor for 10 is 14 (right substree so go left as far as possible)
 *
 * Approach
 *  * Two general cases: with right subtree and without right subtree
 *  * With right subtree, if leaf, return leaf
 *  * With right subtree, inOrder traversal
 *  * Without right subtree, traversal up parent that haven't processed
 */
public class Successor {
    public static void main(String[] args) {
        System.out.println("Successor.main");

        TreeNode<Integer> three = TreeNode.createTreeNode(3);
        TreeNode<Integer> seven = TreeNode.createTreeNode(7);
        TreeNode<Integer> leftLeft = TreeNode.createTreeNode(5,three,seven);
        TreeNode<Integer> leftRight = TreeNode.createTreeNode(15,14);
        TreeNode<Integer> left = TreeNode.createTreeNode(10, leftLeft, leftRight);

        TreeNode<Integer> root = TreeNode.createTreeNode(20, left, TreeNode.createTreeNode(30, null, 40));

        TreeUtility.inOrderTraversal(root);

        System.out.println("successor : " + successor(leftLeft).value);
        System.out.println("successor : " + successor(seven).value);
        System.out.println("successor : " + successor(left).value);
        successTest(leftRight);
        System.out.println("successor : " + successor(root).value);

        System.out.println("====== bigger tree test =========");
        biggerTreeTest();
    }

    private static void biggerTreeTest() {
        TreeNode<Integer> three = TreeNode.createTreeNode(3, 17);
        TreeNode<Integer> leftRight = TreeNode.createTreeNode(561, null, three);
        TreeNode<Integer> leftLeft = TreeNode.createTreeNode(271, 28,0);
        TreeNode<Integer> left = TreeNode.createTreeNode(6, leftLeft, leftRight);


        TreeNode<Integer> rightLeft = TreeNode.createTreeNode(2, null, TreeNode.createTreeNode(1,
                TreeNode.createTreeNode(401, null, 641), TreeNode.createTreeNode(257)));

        TreeNode<Integer> rightRight = TreeNode.createTreeNode(217, null, 28);
        TreeNode<Integer> right = TreeNode.createTreeNode(6, rightLeft, rightRight);

        TreeNode<Integer> root = TreeNode.createTreeNode(314,left, right);

        TreeUtility.inOrderTraversal(root);

        successTest(three);
    }

    public static <T> void successTest(TreeNode<T> node) {
        System.out.printf("node = %s, successor = %s\n", node.value, successor(node).value);
    }
    public static <T> TreeNode<T> successor(TreeNode<T> node) {
        if (node == null) {
            return node;
        }

        if (node.right != null) {
            // right node exist
            TreeNode<T> tmp = node.right;
            TreeNode<T> next = null;

            while (tmp != null) {
                if (tmp.left == null && tmp.right == null) {
                    break;
                }

                if (tmp.left != null) {
                    tmp = tmp.left;
                } else {
                    break;
                }
            }
            return tmp;
        } else {
            // right node does exist, so traver up the parent
            // however if going up the right side of the tree, mean we
            // visit that root already, so we need to go up till left side of the parent
            TreeNode<T> parent = node.parent;
            TreeNode<T> child = node;
            while (parent != null) {
                if (parent.right == child) {
                    child = parent;
                    parent = parent.parent;
                } else {
                    break;
                }
            }
            return parent;
        }
    }


}
