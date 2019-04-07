package org.learning.tree_graph;

import org.common.TreeNode;
import org.common.TreeUtility;

/**
 *
 * Problem:
 *  Given a binary tree, write a program to count the number of Single Valued Subtrees.
 *  A Single Valued Subtree is one in which all the nodes have same value.
 *
 *  Expected time complexity is O(n).
 *
 *     5
 *    / \
 *   1   5
 *  / \   \
 * 5   5   5
 *
 * Expected output => 4
 *
 *     5
 *    / \
 *   1   5
 *  / \   \
 * 5   5   6
 *
 * Expected output => 3
 *
 *
 *      5
 *     / \
 *    4   5
 *   / \   \
 *  4   4   5
 *
 *
 * Expected output => 5
 *
 * Approach:
 *  leaf node is consider 1
 *  parent node = sum (left, right) + 1 if node value is same as child node
 *   * handle the case where node has only child or right node
 *
 *
 */
public class UnivalTree {
    public static void main(String[] args) {
        System.out.println(UnivalTree.class.getName());

        TreeNode<Integer> root1 = createTree1();
        test(root1);

        TreeNode<Integer> root2 = createTree2();
        test(root2);

        TreeNode<Integer> root3 = createTree3();
        test(root3);

    }

    private static void test(TreeNode<Integer> root) {
        System.out.println("*** test ***\n");
        TreeUtility.printLevelByLevel(root);

        System.out.println("unival count is " + countUnivalTree(root));
    }

    /**
     * The algorithm is DFS
     *  - The unival of a node is sum(left child + right child)
     *  - add one of it if the following conditions are met
     *    - node, left, right are equal
     *    - null value of left and right are considered same as the other node
     *    - leaf node is consider one
     *
     *
     * @param root
     * @return
     */
    private static int countUnivalTree(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }


        int leftTreeCount = countUnivalTree(root.left);
        int rightTreeCount = countUnivalTree(root.right);

        int total = leftTreeCount + rightTreeCount;

        if (root.left == null && root.right == null) {
            // leaf node
            total = total + 1;
        } else if (root.left == null && root.right!= null && root.right.value == root.value) {
            // left node is null and right node value is same as parent
            total = total + 1;
        } else if (root.right == null && root.left != null && root.left.value == root.value) {
            // right node is null and left value is same as parent
            total = total + 1;
        } else if ((root.left.value == root.right.value) && (root.value == root.left.value)) {
            total = total + 1;
        }

        /*
        * */

        return total;
    }

    private static TreeNode<Integer> createTree1() {
        TreeNode<Integer> root = new TreeNode<Integer>(5);
        TreeNode<Integer> one = new TreeNode<Integer>(1);
        TreeNode<Integer> five1 = new TreeNode<Integer>(5);
        TreeNode<Integer> five2 = new TreeNode<Integer>(5);
        TreeNode<Integer> five3 = new TreeNode<Integer>(5);
        TreeNode<Integer> five4 = new TreeNode<Integer>(5);

        root.left = one;
        root.right = five1;

        one.left = five3; one.right = five4;
        five1.right = five2;

        return root;
    }

    private static TreeNode<Integer> createTree2() {
        TreeNode<Integer> root = new TreeNode<Integer>(5);
        TreeNode<Integer> four1 = new TreeNode<Integer>(4);
        TreeNode<Integer> five1 = new TreeNode<Integer>(5);
        TreeNode<Integer> four2 = new TreeNode<Integer>(4);
        TreeNode<Integer> four3 = new TreeNode<Integer>(4);
        TreeNode<Integer> five2 = new TreeNode<Integer>(5);

        root.left = four1;
        root.right = five1;

        four1.left = four2; four1.right = four3;
        five1.right = five2;

        return root;
    }

    private static TreeNode<Integer> createTree3() {
        TreeNode<Integer> root = new TreeNode<Integer>(5);
        TreeNode<Integer> one = new TreeNode<Integer>(1);
        TreeNode<Integer> five1 = new TreeNode<Integer>(5);
        TreeNode<Integer> six = new TreeNode<Integer>(6);
        TreeNode<Integer> five3 = new TreeNode<Integer>(5);
        TreeNode<Integer> five4 = new TreeNode<Integer>(5);

        root.left = one;
        root.right = five1;

        one.left = five3; one.right = five4;
        five1.right = six;

        return root;
    }
}
